package content;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Picture;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import pl.droidsonroids.gif.GifDrawable;

import java.io.File;
import java.io.IOException;

public class BearFragment extends Fragment{
    // class for the bear fragment
    private ImageButton playpauseBtn;
    private ImageButton restartBtn;
    private ImageButton stopBtn;
    private GifDrawable gifDrawable;

    private boolean bearTalking;

    private static final String TAG = "436Project";
    private MediaPlayer mMediaPlayer;
    private TextFragment mTextFragment;
    private PictureFragment mPictureFragment;
    File dir;
    int i, max;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.bear_fragment, container, false);


        playpauseBtn = (ImageButton) view.findViewById(R.id.playpause);
        restartBtn = (ImageButton) view.findViewById(R.id.restart);
        stopBtn = (ImageButton) view.findViewById(R.id.stop);
        bearTalking = false;
        mMediaPlayer = new MediaPlayer();

        return inflater.inflate(R.layout.bear_fragment, container, false);

        playpauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bearTalking) {
                    pauseBear();
                }
                else {
                    playBear();
                }
            }
        });

        restartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }

    void playBear() {

    }

    void pauseBear() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
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