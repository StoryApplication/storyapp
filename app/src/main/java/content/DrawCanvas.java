package content;

import android.content.Context;
import android.gesture.GestureOverlayView;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class DrawCanvas extends View {

    Paint paint = new Paint();
    Path path = new Path();
    private Bitmap canvasBitmap;
    private Canvas mCanvas;
    private boolean erase = false;

    public DrawCanvas(Context context) {
        super(context);
        init();
    }

    public DrawCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init() {
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStyle(Paint.Style.STROKE);
        paint.setDither(true);
        paint.setStrokeWidth(20);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        // View given size
        super.onSizeChanged(w, h, oldw, oldh);
        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(canvasBitmap);
    }

    public void onDraw(Canvas canvas) {

        if(canvasBitmap != null){
            canvas.drawBitmap(this.canvasBitmap, 0, 0, this.paint);
        }
        canvas.drawPath(path, paint);
    }

    public boolean onTouchEvent(MotionEvent event) {

        float xPos = event.getX();
        float yPos = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(xPos, yPos);
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(xPos,yPos);
                break;
            case MotionEvent.ACTION_UP:
                mCanvas.drawPath(path, paint);
                path.reset();
                break;
            default:
                return false;
        }
        invalidate();
        return true;
    }

    public void color(int color) {

        paint.setStrokeWidth(20);
        paint.setColor(color);
    }

    public void setErase(boolean isErase){
        // Set erase true or false
        erase = isErase;
        if (erase) {
            paint.setStrokeWidth(60);
            paint.setColor(Color.WHITE);
        }
        else {
            paint.setXfermode(null);
        }
    }

    public void clear() {
        if (mCanvas != null) {
            this.mCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
            invalidate();
        }
    }

    public void setBitmap(Bitmap bitmap) {
        if (bitmap == null) {
            return;
        }
        clear();
        this.mCanvas.drawBitmap(bitmap,0,0, this.paint);
        invalidate();
    }
    public Bitmap getBitmap() {
        if(mCanvas == null) {
            return null;
        }
        this.setDrawingCacheEnabled(true);
        this.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(this.getDrawingCache());
        this.setDrawingCacheEnabled(false);
        return bitmap;
    }
}
