package content;

import android.app.Activity;
import android.app.ListFragment;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;

public class ChooseStoryFragment  extends ListFragment {

    private static String[] TITLES;
    private static final String TAG = "436Project";




    public interface SelectionListener {
        public void onItemSelected(String title);
    }

    private SelectionListener mCallback;

    public void loadVariables(File[] stories, String dir) {
        TITLES = new String[stories.length];
        for(int i = 0; i < stories.length; i++) {
            TITLES[i] = stories[i].toString().substring(dir.length());
        }

        int layout = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ? android.R.layout.simple_list_item_activated_1
                : android.R.layout.simple_list_item_1;
        setListAdapter(new ArrayAdapter<String>(getActivity(), layout, TITLES));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        // use different layout definition, depending on whether device is pre-
        // or post-honeycomb

        // Set the list adapter for this ListFragment
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // Make sure that the hosting Activity has implemented
        // the SelectionListener callback interface. We need this
        // because when an item in this ListFragment is selected,
        // the hosting Activity's onItemSelected() method will be called.

        try {

            mCallback = (SelectionListener) activity;

        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement SelectionListener");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.i(TAG, "Entered onActivityCreated()");

        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    @Override
    public void onListItemClick(ListView l, View view, int position, long id) {

        // Notify the hosting Activity that a selection has been made.

        mCallback.onItemSelected(TITLES[position]);
        l.clearChoices();
        l.requestLayout();
    }
}