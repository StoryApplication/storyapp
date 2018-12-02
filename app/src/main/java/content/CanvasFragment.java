package content;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class CanvasFragment extends Fragment {
    Button blue;
    Button green;
    Button black;
    Button purple;
    Button red;
    Button orange;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_draw, container, false);
    }



}
