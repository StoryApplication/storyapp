package content;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.io.File;

public class ChooseStoryActivity extends Activity implements
        ChooseStoryFragment.SelectionListener {

    private static final String TAG = "436Project";

    private ChooseStoryFragment mChooseStoryFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_story_activity);

        FragmentManager fragmentManager = getFragmentManager();

        if (mChooseStoryFragment == null)
            mChooseStoryFragment = new ChooseStoryFragment();

        mChooseStoryFragment = (ChooseStoryFragment) fragmentManager.findFragmentById(R.id.stories_frag);
        mChooseStoryFragment.loadVariables(getExternalFilesDir("Stories").listFiles(), getExternalFilesDir("Stories") + File.separator);
    }

    // Display selected Twitter feed

    public void onItemSelected(String title) {

        Log.i(TAG, "Entered onItemSelected(" + title + ")");
        Intent intent = new Intent(this, ReadStoryActivity.class);
        intent.putExtra("title", title);
        startActivity(intent);
    }

}