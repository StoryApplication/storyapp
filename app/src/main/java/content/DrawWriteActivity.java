package content;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import android.app.FragmentManager;
import android.app.FragmentTransaction;




public class DrawWriteActivity extends Activity {
    private static final String TAG = "DrawWriteActivity";
    private TextFragment mTextFragment;
    private CanvasFragment mDrawFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_write);

        mTextFragment = new TextFragment();
        mDrawFragment = new CanvasFragment();

        FragmentManager manager = getFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();

        //fragmentTransaction.add(R.id.frags, mTextFragment).commit();
        //fragmentTransaction.add(R.id.frags, mDrawFragment).commit();




    }


}