package content;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Picture;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;

public class BearFragment extends Fragment{
    // class for the bear fragment

    private static final String TAG = "436Project";
    private MediaPlayer mMediaPlayer;
    private TextFragment mTextFragment;
    private PictureFragment mPictureFragment;
    File dir;
    int i, max;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mMediaPlayer = new MediaPlayer();

        return inflater.inflate(R.layout.bear_fragment, container, false);

    }

    public void readStory() {
        mTextFragment.updateStory(dir, i);
        mPictureFragment.updateImage(dir, i);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    mMediaPlayer.reset();
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
    }

    public void loadVariables(TextFragment t, PictureFragment p, File d) {
        mTextFragment = t;
        mPictureFragment = p;
        dir = d;
        i = 0;
        max = dir.listFiles().length - 1;
    }
}