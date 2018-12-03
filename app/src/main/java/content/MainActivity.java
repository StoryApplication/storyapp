package content;

import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textViewTitle = (TextView) findViewById(R.id.title);
        Button textViewButton1 = (Button) findViewById(R.id.button1);
        Button textViewButton2 = (Button) findViewById(R.id.button2);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Kindergarden.ttf");
        textViewTitle.setTypeface(typeface);
        textViewButton1.setTypeface(typeface);
        textViewButton2.setTypeface(typeface);

        textViewButton1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,
                        DrawWriteActivity.class);


                startActivity(intent);
            }
        });
        textViewButton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
               // Intent intent = new Intent(MainActivity.this,
              //          ChooseStoryActivity.class);


             //   startActivity(intent);
            }
        });
    }
}
