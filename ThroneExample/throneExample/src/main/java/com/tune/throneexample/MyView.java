package com.tune.throneexample;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MyView extends View {
    private Paint paint = new Paint();
    private Path path = new Path();
    
    private boolean clearCanvas;

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        
        paint.setColor(Color.BLACK);
        paint.setStyle(Style.STROKE);
        paint.setStrokeWidth(10f);
    }
    
    /*public void clearCanvas() {
        clearCanvas = true;
        // Trigger onDraw
        invalidate();
    }*/
    
    @Override
    protected void onDraw(Canvas canvas) {
    /*    if (clearCanvas) {
            // Reset path and canvas
            path = new Path();
            canvas.drawColor(Color.TRANSPARENT);
            clearCanvas = false;
        } else {*/
            canvas.drawPath(path, paint);
//        }
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float eventX = event.getX();
        float eventY = event.getY();
        
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(eventX, eventY);
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                path.lineTo(eventX, eventY);
                break;
            default: 
                return false;
        }
        
        invalidate();
        return true;
    }
}
