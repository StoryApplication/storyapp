package content;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.app.Activity;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Locale;

public class MainActivity extends Activity implements TextToSpeech.OnInitListener{

    private static final String TAG = "436Project";
    private TextToSpeech mTts;
    private File root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        root = getExternalFilesDir("Stories");

Log.i(TAG, "checking TTS");
        Intent checkIntent = new Intent();
        checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkIntent, 12121);
/**/
    }

    public void onActivityResult(
            int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == 12121) {
            Log.i(TAG, "TTS received");
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                Log.i(TAG, "TTS success");
                // success, create the TTS instance
                mTts = new TextToSpeech(getApplicationContext(), this);
            } else {
                Log.i(TAG, "TTS fail");
                // missing data, install it
                Intent installIntent = new Intent();
                installIntent.setAction(
                        TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installIntent);
            }
        }
    }


    public void installOllie(String title) {
        Log.i(TAG, "Creating Ollie's Jar Story");
        int[] pages = {R.string.page0, R.string.page1, R.string.page2, R.string.page3, R.string.page4, R.string.page5, R.string.page6};
        int[] pictures = {R.drawable.page0, R.drawable.page1, R.drawable.page2, R.drawable.page3, R.drawable.page4, R.drawable.page5, R.drawable.page6};
        for (int i = 0; i < 7; i++) {
            File dir = new File(root + File.separator + title + File.separator + i);
            dir.mkdirs();
            String text = getText(pages[i]).toString();
            File tf = new File(dir, "text.txt");;
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
                Bitmap bm = BitmapFactory.decodeResource(getResources(), pictures[i]);
                stream = new FileOutputStream(imgf);
                bm.compress(Bitmap.CompressFormat.PNG, 80, stream);
                stream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onInit(int status) {
        Log.i(TAG, "onInit");
        if (status == TextToSpeech.SUCCESS)
        {
            mTts.setLanguage(Locale.US);
            mTts.setSpeechRate(.8f);

            if (root.listFiles().length == 0) { // if the directory is empty
                installOllie("Ollie's Jar");
                installOllie("Ollie's Jar 2");
            }

            // starts the choosestory activity
            startActivity(new Intent(getApplicationContext(), ChooseStoryActivity.class));
        }
    }

    public void onDestroy() {
        if (mTts != null)
        {
            mTts.stop();
            mTts.shutdown();
        }
        super.onDestroy();
    }
}
