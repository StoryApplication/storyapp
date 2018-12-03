package content;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class FragmentText extends Fragment{

    private static final String TAG = "436Project";

    private TextView mTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.text_frag, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mTextView = getView().findViewById(R.id.text_view);
    }


    // Display selected story

    void updateStory(File dir, int i) {

        Log.i(TAG, "Entered updateStory(" + dir + ", " + i + ")");

        mTextView = getView().findViewById(R.id.text_view);

        File file = new File(dir + File.separator + i + File.separator + "text.txt");
        StringBuilder text = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            br.close();
        } catch (IOException e) {
            Log.i(TAG, "IOException");
        }
        mTextView.setText(text);
        Log.i(TAG, "Updated text " + i );
    }

}
