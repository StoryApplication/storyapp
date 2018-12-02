package content;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.Environment;



public class TextFragment extends Fragment {

    private static final String TAG = "436Project";

    private TextView mTextView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_text, container, false);
    }
   // @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//
//        mTextView = getView().findViewById(R.id.text_frag);
//        mTextView.setText(Environment.getExternalStorageDirectory().toString());
//    }



}
