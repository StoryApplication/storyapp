package content;

import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


public class TextFragment extends Fragment {
    private EditText text;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
////        return super.onCreateView(inflater, container, savedInstanceState);
//        View view = inflater.inflate(R.layout.activity_draw_write, container, false);
//        text = (EditText) view.findViewById(R.id.editText1);
//        SharedPreferences settings = this.getActivity().getSharedPreferences("PREFS", 0);
//        text.setText(settings.getString("value", ""));
//        return view;
//
//    }
//
//    public onCreateView(LayoutInflater inflater, ViewGroup parent,
//                        Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.details2, parent, false);
//        notatki = (EditText) view.findViewById(R.id.editText1);
//        SharedPreferences settings = this.getActivity().getSharedPreferences("PREFS", 0);
//        notatki.setText(settings.getString("value", ""));
//
//        return view; // return view here instead of notaki
//    }

}
