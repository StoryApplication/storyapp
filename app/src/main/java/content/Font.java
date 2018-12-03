package content;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.widget.TextView;

public class Font extends AppCompatTextView {



        public Font(Context context, AttributeSet attrs, int style) {
            super(context, attrs, style);
            init();
        }

        public  Font(Context context, AttributeSet attrs) {
            super(context, attrs);
            init();
        }

        public Font(Context context) {
            super(context);
            init();
        }

        public void init() {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Kindergarden.ttf");
            setTypeface(tf ,1);

        }

}
