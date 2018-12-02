package content;

import android.app.Activity;
import android.app.FragmentManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;

public class ReadStoryActivity extends Activity {

    private static final String TAG = "436Project";

    private BearFragment mBearFragment;
    private TextFragment mTextFragment;
    private PictureFragment mPictureFragment;
    private MediaPlayer mMediaPlayer;
    private boolean audioFinished;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i(TAG, "created ReadStoryActivity");

        setContentView(R.layout.read_story_activity);

        FragmentManager fragmentManager = getFragmentManager();

        mBearFragment = (BearFragment) fragmentManager.findFragmentById(R.id.bear_frag);
        mTextFragment = (TextFragment) fragmentManager.findFragmentById(R.id.text_frag);
        mPictureFragment = (PictureFragment) fragmentManager.findFragmentById(R.id.pic_frag);
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                audioFinished = true;
            }
        });
        audioFinished = false;

        Log.i(TAG, "Directory: " + Environment.getExternalStorageDirectory().toString());
        Log.i(TAG, "Title: " + getIntent().getStringExtra("title"));
        //readStory(this.getIntent().getStringExtra("title")); // this activity should be called with intent that has extra "title" with name of story
    }

    public void readStory(String title) {

        Log.i(TAG, "Entered readStory(" + title + ")");

        if (mPictureFragment == null)
            mPictureFragment = new PictureFragment();
        if (mTextFragment == null)
            mTextFragment = new TextFragment();
        if (mBearFragment == null)
            mBearFragment = new BearFragment();

        File dir = new File(Environment.getExternalStorageDirectory() + File.separator + title);

        for (int i = 0; (i < dir.listFiles().length) && dir.listFiles()[i].isDirectory(); i++) {
            // mBearFragment.animateBear()
            mPictureFragment.updateImage(dir, i);
            mTextFragment.updateStory(dir, i);
            try {
                mMediaPlayer.setDataSource(dir + File.separator + i + File.separator + "audio.mp3");
                mMediaPlayer.prepare();
                mMediaPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
            while (!audioFinished) {
                //animate bear method
                // wait for audio to finish before proceeding
            }
            audioFinished = false;
        }
    }

}
