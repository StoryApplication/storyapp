package content;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.view.View;
import android.view.View.OnClickListener;
import android.graphics.Color;
import android.util.Log;

public class CreateStory extends Activity {
    public final String TAG = "__"; // Change this
    private DrawingView drawingView;
    Button prevButton, nextButton, previewButton, finishButton;


    int currPageNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.____);
        // prevButton = (Button) findViewById(R.id.____);
        // nextButton = (Button) findViewById(R.id.____);
        // previewButton = (Button) findViewById(R.id.____);
        // finishButton = (Button) findViewById(R.id.____);
        // drawingView = (DrawingView) findViewById(R.id.__);

        prevButton.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.i(TAG,"Entered prevButton OnClickListener");
                if (currPageNumber > 0) {
                    // ___
                }
                else {
                    // ___
                }
            }
        });

        nextButton.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.i(TAG,"Entered nextButton OnClickListener");
            }
        });

        previewButton.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.i(TAG,"Entered previewButton OnClickListener");
            }
        });

        finishButton.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.i(TAG,"Entered finishButton OnClickListener");
            }
        });
    }

}
