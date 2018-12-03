package content;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mMediaPlayer = new MediaPlayer();

        return inflater.inflate(R.layout.bear_fragment, container, false);

    }

    public void readStory(File dir, int i) {

        Log.i(TAG, "Entered readStory");
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
        }
    }

}