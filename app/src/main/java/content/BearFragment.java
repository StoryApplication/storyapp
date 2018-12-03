package content;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import pl.droidsonroids.gif.GifDrawable;

public class BearFragment extends Fragment{
    // class for the bear fragment
    private ImageButton playpauseBtn;
    private ImageButton restartBtn;
    private ImageButton stopBtn;
    private GifDrawable gifDrawable;

    private boolean bearTalking;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.bear_fragment, container, false);


        playpauseBtn = (ImageButton) view.findViewById(R.id.playpause);
        restartBtn = (ImageButton) view.findViewById(R.id.restart);
        stopBtn = (ImageButton) view.findViewById(R.id.stop);
        bearTalking = false;

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
}