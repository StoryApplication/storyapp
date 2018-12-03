package content;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.File;

public class PictureFragment extends Fragment {

    private static final String TAG = "436Project";

    private ImageView mImageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.picture_fragment, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mImageView = getView().findViewById(R.id.picture_view);
    }

    void updateImage(File dir, int i) {

        Log.i(TAG, "Entered updateImage(" + dir + ", " + i + ")");
        mImageView = getView().findViewById(R.id.picture_view);
        Bitmap bitmap = BitmapFactory.decodeFile(dir + File.separator + i + File.separator + "image.png");

        mImageView.setImageBitmap(bitmap);
        Log.i(TAG, "Updated image " + i );
    }


}
