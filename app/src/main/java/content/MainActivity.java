package content;

import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textViewTitle = (TextView) findViewById(R.id.title);
        TextView textViewButton1 = (TextView) findViewById(R.id.button1);
        TextView textViewButton2 = (TextView) findViewById(R.id.button2);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Kindergarden.ttf");
        textViewTitle.setTypeface(typeface);
        textViewButton1.setTypeface(typeface);
        textViewButton2.setTypeface(typeface);
    }
}
