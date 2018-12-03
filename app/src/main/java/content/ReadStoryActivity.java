package content;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.io.File;
import java.io.IOException;

public class ReadStoryActivity extends Activity {

    private static final String TAG = "436Project";

    private BearFragment mBearFragment;
    private FragmentText mTextFragment;
    private PictureFragment mPictureFragment;
    private MediaPlayer mMediaPlayer;
    int i, max;
    File dir;
    boolean isPrepared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i(TAG, "created ReadStoryActivity");
        setContentView(R.layout.read_story_activity);

        FragmentManager fragmentManager = getFragmentManager();

        mBearFragment = (BearFragment) fragmentManager.findFragmentById(R.id.bear_frag);
        mTextFragment = (FragmentText) fragmentManager.findFragmentById(R.id.frag_text);
        mPictureFragment = (PictureFragment) fragmentManager.findFragmentById(R.id.pic_frag);
        dir = new File(getExternalFilesDir("Stories") + File.separator + this.getIntent().getStringExtra("title"));

        mBearFragment.loadVariables(mTextFragment, mPictureFragment, dir);
        mMediaPlayer = new MediaPlayer();

        Log.i(TAG, "finished oncreate");
        mBearFragment.readStory();
    }

    /*public void readStory() {
        mTextFragment.updateStory(dir, i);
        mPictureFragment.updateImage(dir, i);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    mMediaPlayer.reset();
                    isPrepared = false;
                    Log.i(TAG, "started playing page " + i);
                    mMediaPlayer.setDataSource(dir + File.separator + i + File.separator + "sound.wav");
                    mMediaPlayer.prepare();
                    Log.i(TAG, "prepared page " + i);
                    mMediaPlayer.start();
                    MediaMetadataRetriever mmr = new MediaMetadataRetriever();
                    mmr.setDataSource(dir + File.separator + i + File.separator + "sound.wav");
                    String durationStr = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
                    int millSecond = Integer.parseInt(durationStr);
                    Handler handl = new Handler();
                    handl.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Log.i(TAG, "finished playing page " + i);
                            if (i++ < max)
                                readStory();
                        }
                    }, millSecond + 100);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 1000);
    }*/
}
