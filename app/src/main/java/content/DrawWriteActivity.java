package content;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


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
    Button gray;
    @SuppressLint("WrongViewCast")
//    DrawCanvas draw = findViewById(R.id.draw_frame);




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_write);

        mTextFragment = new TextFragment();
        mDrawFragment = new CanvasFragment();

        FragmentManager manager = getFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();

        blue = findViewById(R.id.blue);
        green = findViewById(R.id.green);
        black = findViewById(R.id.black);
        purple = findViewById(R.id.purple);
        red = findViewById(R.id.red);
        orange = findViewById(R.id.orange);
        gray = findViewById(R.id.gray);

        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // draw.color(Color.BLUE);

            }
        });
        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  draw.color(Color.GREEN);

            }
        });
         black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  draw.color(Color.BLACK);

            }
        });
        purple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  draw.color(Color.MAGENTA);

            }
        });
        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // draw.color(Color.RED);

            }
        });
        orange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int orange = Color.rgb(255, 165, 0);
               // draw.color(orange);

            }
        });
        gray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //draw.color(getResources().getColor(R.color.)));

            }
        });


        //fragmentTransaction.add(R.id.frags, mTextFragment).commit();
        //fragmentTransaction.add(R.id.frags, mDrawFragment).commit();




    }


}