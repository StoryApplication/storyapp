package content;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import java.io.File;

public class ReadStoryActivity extends Activity {

    private static final String TAG = "436Project";

    private BearFragment mBearFragment;
    private TextFragment mTextFragment;
    private PictureFragment mPictureFragment;
    private int currPage; // stores current # of page

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i(TAG, "created ReadStoryActivity");

        setContentView(R.layout.read_story_activity);
        System.out.println(Environment.getExternalStorageDirectory().toString());

        FragmentManager fragmentManager = getFragmentManager();

        mBearFragment = (BearFragment) fragmentManager.findFragmentById(R.id.bear_frag);
        mTextFragment = (TextFragment) fragmentManager.findFragmentById(R.id.text_frag);
        mPictureFragment = (PictureFragment) fragmentManager.findFragmentById(R.id.pic_frag);

        readStory(this.getIntent().getStringExtra("title")); // this activity should be called with intent that has extra "title" with name of story
    }

    // Display selected Twitter feed

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
            mTextFragment.readStory();
        }

    }

}
