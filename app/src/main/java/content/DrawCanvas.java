package content;

import android.content.Context;
import android.gesture.GestureOverlayView;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class DrawCanvas extends View {

    Paint paint = new Paint();
    Path path = new Path();

    public DrawCanvas(Context context) {
        super(context);

        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
    }



    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path, paint);
    }

    public boolean onTouchEvent(MotionEvent event) {
        float xPos = event.getX();
        float yPos = event.getY();

        if(event.getAction() == MotionEvent.ACTION_DOWN){
            path.moveTo(xPos, yPos);
            return true;
        }
        else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            path.lineTo(xPos,yPos);

        }
       invalidate();
        return true;
    }

    public void color(int color) {
        paint.setColor(color);
    }

}
