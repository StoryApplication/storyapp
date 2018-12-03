package content;

import android.app.Activity;
import android.app.FragmentManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.io.IOException;

public class ReadStoryActivity extends Activity {

    private static final String TAG = "436Project";

    private BearFragment mBearFragment;
    private TextFragment mTextFragment;
    private PictureFragment mPictureFragment;
    private MediaPlayer mMediaPlayer;

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

        Log.i(TAG, "finished oncreate");
        File dir = new File(getExternalFilesDir("Stories") + File.separator + this.getIntent().getStringExtra("title"));
        for (int i = 0; i < 3; i++) {
            mTextFragment.updateStory(dir, i);
            mPictureFragment.updateImage(dir, i);
            mBearFragment.readStory(dir, i);
        }
    }

    public void readStory(File dir) {

        /*Log.i(TAG, "Entered readStory");
            try {
                mMediaPlayer.reset();
                mMediaPlayer.setDataSource(dir + File.separator + i + File.separator + "sound.wav");
                Log.i(TAG, "preparing audio for page " + i);
                mMediaPlayer.prepareAsync();
                mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        Log.i(TAG, "playing page ");
                        mp.start();
                        while (mMediaPlayer.isPlaying()) { // wait for audio to finish playing
                            //animate bear method
                        }
                        Log.i(TAG, "finished playing page " );
                        mp.release();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }*/


        for (int i = 0; (i < dir.listFiles().length) && dir.listFiles()[i].isDirectory(); i++) {
            // mBearFragment.animateBear()

            mTextFragment.updateStory(dir, i);
            mPictureFragment.updateImage(dir, i);
            try {

                Log.i(TAG, "started playing page " + i);
                mMediaPlayer.setDataSource(dir + File.separator + i + File.separator + "sound.wav");
                mMediaPlayer.prepare();
                mMediaPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.i(TAG, "playing page " + i);
            while (mMediaPlayer.isPlaying()) {
                //animate bear method
                // wait for audio to finish before proceeding
            }
            Log.i(TAG, "finished playing page " + i);
        }
    }

}
