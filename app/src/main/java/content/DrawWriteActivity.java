package content;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class DrawWriteActivity extends Activity implements TextToSpeech.OnInitListener {
    private static final String TAG = "DrawWriteActivity";
    static int counter = 0;
    private TextFragment mTextFragment;
    private CanvasFragment mDrawFragment;
    Button blue;
    Button green;
    Button black;
    Button purple;
    Button red;
    Button orange;
    Button brown;
    ImageButton erase;
    int times = 0;
    int tempSize = 0;

    Button prevButton, nextButton, clear, finishButton;
    int currentPageNumber = 0;
    @SuppressLint("WrongViewCast")
    DrawCanvas draw;
    Story newStory = new Story(); // JASON- assumed all new pages would be added to this variable
    static ArrayList<Story> storyList = new ArrayList<Story>();
    int w = 10, h = 10;

    Bitmap.Config conf = Bitmap.Config.ARGB_8888;
    Bitmap bmp = Bitmap.createBitmap(w, h, conf);
    Pages<String, Bitmap> page = new Pages<>("",bmp );
    String title;


    private TextToSpeech mTts; // JASON- needed to synthesize .wav file for text to speech

    private void createPopup() {
        final EditText input = new EditText(DrawWriteActivity.this);
        AlertDialog.Builder builder = new AlertDialog.Builder(DrawWriteActivity.this);
        builder.setMessage("")
                .setTitle("Name your story")
                .setView(input)

                .setPositiveButton("Save Story!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        newStory.setTitle(input.getText().toString());
                    }

                });

        // Create the AlertDialog object
        builder.create().show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_write);
        createPopup();
        if(newStory.getTitle().compareTo("") == 0) {
            Toast.makeText(getApplicationContext(), "Please enter title", Toast.LENGTH_SHORT).show();
            createPopup();
        }

        mTextFragment = new TextFragment();
        mDrawFragment = new CanvasFragment();

        FragmentManager manager = getFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        draw = (DrawCanvas) findViewById(R.id.draw_frame);
        blue = findViewById(R.id.blue);
        green = findViewById(R.id.green);
        black = findViewById(R.id.black);
        purple = findViewById(R.id.purple);
        red = findViewById(R.id.red);
        orange = findViewById(R.id.orange);
        brown = findViewById(R.id.brown);
        erase = (ImageButton) findViewById(R.id.erase_button);
        clear = findViewById(R.id.clear);

        prevButton = (Button) findViewById(R.id.previous);
        nextButton = (Button) findViewById(R.id.next_page);
        finishButton = (Button) findViewById(R.id.finish);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                draw.clear();
            }
        });
        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                draw.color(Color.BLUE);

            }
        });
        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                draw.color(Color.GREEN);

            }
        });
        black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                draw.color(Color.BLACK);

            }
        });
        purple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                draw.color(Color.MAGENTA);

            }
        });
        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                draw.color(Color.RED);

            }
        });
        orange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int orange = Color.rgb(255, 165, 0);
                draw.color(orange);

            }
        });
        brown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                draw.color(getResources().getColor(R.color.Brown));

            }
        });

        erase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                draw.setErase(true);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText text = (EditText)findViewById(R.id.page_text);
                String storyText =  text.getText().toString();
                Bitmap drawing = draw.getBitmap();

                page = new Pages<>(storyText, drawing);
                currentPageNumber++;

                if (currentPageNumber < newStory.getPages().size() - 1) {
                    text.setText(newStory.getPages().get(currentPageNumber).getLeft());
                    draw.setBitmap(newStory.getPages().get(currentPageNumber).getRight());
                }
                else {
                    newStory.addPage(page);
                    text.setText("");
                    draw.clear();
                }

                writeToStorage();

            }
        });

        prevButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                EditText text1 = (EditText)findViewById(R.id.page_text);
                String storyText1 =  text1.getText().toString();
                Bitmap drawing = draw.getBitmap();
                page = new Pages<>(storyText1, drawing);

                if (currentPageNumber > 0) {
                    if(currentPageNumber == newStory.getPages().size() && times == 0) {
                        newStory.addPage(page);
                        times++;
                        tempSize = newStory.getPages().size();
                    }
                    currentPageNumber--;
                    text1.setText(newStory.getPages().get(currentPageNumber).getLeft());
                    draw.setBitmap(newStory.getPages().get(currentPageNumber).getRight());
                }
            }
        });

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText text = (EditText)findViewById(R.id.page_text);
                String storyText =  text.getText().toString();
                Bitmap drawing = draw.getBitmap();

                page = new Pages<>(storyText, drawing);
                currentPageNumber++;

                if (currentPageNumber < newStory.getPages().size() - 1) {
                    text.setText(newStory.getPages().get(currentPageNumber).getLeft());
                    draw.setBitmap(newStory.getPages().get(currentPageNumber).getRight());
                }
                else {
                    newStory.addPage(page);
                    text.setText("");
                    draw.clear();
                }

                writeToStorage();

                storyList.add(newStory);

                finish(); // goes back to previous activity
                                }




        });

        //JASON- initializes TTS engine
        Log.i(TAG, "checking TTS");
        Intent checkIntent = new Intent();
        checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkIntent, 12121);

    }

    //JASON- will write newStory (contains all pages) to external storage once finished
    //Leo - write single page
    public void writeToStorage() {
        String text = page.getLeft();
        Bitmap bm = (Bitmap) page.getRight();

        File dir = new File(getExternalFilesDir("Stories") + File.separator + newStory.getTitle() + File.separator + currentPageNumber);
        dir.mkdirs();

        File tf = new File(dir, "text.txt");
        File imgf = new File(dir, "image.png");
        File sf = new File(dir, "sound.wav");

        try {
            // create text file
            FileOutputStream stream = new FileOutputStream(tf);
            stream.write(text.getBytes());
            stream.close();

            // create sound file
            mTts.synthesizeToFile(text, null, sf, "page" + currentPageNumber);

            // create image file
            stream = new FileOutputStream(imgf);
            bm.compress(Bitmap.CompressFormat.PNG, 80, stream);
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*
        for (int i = 0; i < newStory.getPages().size(); i++) {
            Pages currPage = newStory.getPages().get(i);
            String text = (String) currPage.getLeft(); // can probably change Pages datatypes to String/Bitmap to avoid casting
            Bitmap bm = (Bitmap) currPage.getRight();

            File dir = new File(getExternalFilesDir("Stories") + File.separator + newStory.getTitle() + File.separator + i);
            dir.mkdirs(); // creates directory for page

            File tf = new File(dir, "text.txt");
            File imgf = new File(dir, "image.png");
            File sf = new File(dir, "sound.wav");

            try {
                // create text file
                FileOutputStream stream = new FileOutputStream(tf);
                stream.write(text.getBytes());
                stream.close();

                // create sound file
                mTts.synthesizeToFile(text, null, sf, "page" + i);

                // create image file
                stream = new FileOutputStream(imgf);
                bm.compress(Bitmap.CompressFormat.PNG, 80, stream);
                stream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/
    }


    //JASON- sets settings for TTS engine, can modify this to change voice
    @Override
    public void onInit(int status) {
        Log.i(TAG, "onInit");
        if (status == TextToSpeech.SUCCESS) {
            mTts.setLanguage(Locale.US);
            mTts.setSpeechRate(.8f);
        }

    }

    //JASON- installs TTS engine if does not exist
    public void onActivityResult(
            int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == 12121) {
            Log.i(TAG, "TTS received");
            if (resultCode != TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                Log.i(TAG, "TTS fail");
                // missing data, install it
                Intent installIntent = new Intent();
                installIntent.setAction(
                        TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installIntent);
            }
            mTts = new TextToSpeech(getApplicationContext(), this);
        }
    }

    //JASON- shutsdown TTS engine
    public void onDestroy() {
        if (mTts != null)
        {
            mTts.stop();
            mTts.shutdown();
        }
        super.onDestroy();
    }
}