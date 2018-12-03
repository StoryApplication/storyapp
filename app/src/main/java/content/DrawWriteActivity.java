package content;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
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


public class DrawWriteActivity extends Activity {
    private static final String TAG = "DrawWriteActivity";
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
    Button prevButton, nextButton, clear, finishButton;
    int currentPageNumber = 0;
    @SuppressLint("WrongViewCast")
    DrawCanvas draw;
    Story newStory = new Story();
    static ArrayList<Story> storyList = new ArrayList<Story>();
    Pages<String, Bitmap> page ;
    String title;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_write);

        mTextFragment = new TextFragment();
        mDrawFragment = new CanvasFragment();

        FragmentManager manager = getFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        draw = (DrawCanvas)findViewById(R.id.draw_frame);
        blue = findViewById(R.id.blue);
        green = findViewById(R.id.green);
        black = findViewById(R.id.black);
        purple = findViewById(R.id.purple);
        red = findViewById(R.id.red);
        orange = findViewById(R.id.orange);
        brown = findViewById(R.id.brown);
        erase = (ImageButton)findViewById(R.id.erase_button);
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

        erase.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                 draw.setErase(true);
            }
        });
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText input = new EditText(DrawWriteActivity.this);
                AlertDialog.Builder builder = new AlertDialog.Builder(DrawWriteActivity.this);
                builder.setMessage("")
                        .setTitle("Name your story")
                        .setView(input)

                        .setPositiveButton("Save Story!", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                newStory.setTitle(input.getText().toString());
                                storyList.add(newStory);

                                // now we need to add to firebase

                                //AND NOW WE PASS INTENT TO GO TO LIST OF STORIES
                                //Intent intent = new Intent(DrawWriteActivity.this, WHATEVERCLASS.class);
                                //startActivity(intent);

                            }
                        });

                // Create the AlertDialog object and return it
                 builder.create().show();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // page = new Pages<>(findViewById(R.id.page_text).)
                EditText text = (EditText)findViewById(R.id.page_text);
                String storyText =  text.getText().toString();
                //Bitmap drawing = Bitmap.createBitmap(findViewById(R.id.draw_frame))//(Bitmap) findViewById(R.id.draw_frame);
                FrameLayout canvas = findViewById(R.id.frame_param);
                canvas.setDrawingCacheEnabled(true);
                canvas.buildDrawingCache();
                Bitmap drawing = canvas.getDrawingCache();

                page = new Pages<>(storyText, drawing);
                newStory.addPage(page);
                currentPageNumber++;


                if (currentPageNumber <= newStory.getPages().size() - 1) {
                    text.setText(newStory.getPages().get(currentPageNumber).getLeft());
                    draw.setBitmap(newStory.getPages().get(currentPageNumber).getRight());
                }
                else {
                    text.setText("");
                    draw.clear();
                }
            }
        });

        prevButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // page = new Pages<>(findViewById(R.id.page_text).)
                EditText text = (EditText)findViewById(R.id.page_text);
                String storyText =  text.getText().toString();
                //Bitmap drawing = Bitmap.createBitmap(findViewById(R.id.draw_frame))//(Bitmap) findViewById(R.id.draw_frame);
                FrameLayout canvas = findViewById(R.id.frame_param);
                canvas.setDrawingCacheEnabled(true);
                canvas.buildDrawingCache();
                Bitmap drawing = canvas.getDrawingCache();

                page = new Pages<>(storyText, drawing);
                newStory.addPage(page);

                if (currentPageNumber > 0) {
                    currentPageNumber--;
                    text.setText(newStory.getPages().get(currentPageNumber).getLeft());
                    draw.setBitmap(newStory.getPages().get(currentPageNumber).getRight());
                }
                else {
                    // _____
                }
            }
        });
//
//        nextButton.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                Log.i(TAG,"Entered nextButton OnClickListener");
//                /*
//                if (currentPageNumber < ___.size - 1) {
//
//                    updatePage(1);
//                }
//                else {
//
//
//                    updatePage(1);
//                }
//                */
//            }
//        });

//        previewButton.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                Log.i(TAG,"Entered previewButton OnClickListener");
//            }
//        });
//
//        finishButton.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                Log.i(TAG,"Entered finishButton OnClickListener");
//            }
//        });



    }

}