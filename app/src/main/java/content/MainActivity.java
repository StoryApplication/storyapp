package content;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent launcher = new Intent(getApplicationContext(), ReadStoryActivity.class);
        launcher.putExtra("title", "Goodnight Moon");
        startActivity(launcher);
    }
}
