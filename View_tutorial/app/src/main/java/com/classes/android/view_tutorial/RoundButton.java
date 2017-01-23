package com.classes.android.view_tutorial;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class RoundButton extends View {


    private Paint markerPaint;
    private Paint textPaint;
    private Paint rectanglePaint;
    private int textHeight;

    public RoundButton(Context context, Paint markerPaint) {
       super(context);

       initRoundButton();

    }

    public RoundButton(Context context, AttributeSet attrs, Paint markerPaint) {
        super(context, attrs);
        initRoundButton();
    }

    public RoundButton(Context context, AttributeSet attrs, int defStyleAttr, Paint markerPaint) {
        super(context, attrs, defStyleAttr);
        initRoundButton();
    }



    protected void initRoundButton(){
        setFocusable(true);

        Resources r=this.getResources();

        rectanglePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        rectanglePaint.setColor(r.getColor(R.color.background_color));
        rectanglePaint.setStrokeWidth(1);
        rectanglePaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        RectF r=new RectF();
        r.set(0, 0, 100, 100);
        canvas.drawRoundRect(r, 50,50,rectanglePaint);
        canvas.save();
        canvas.restore();
    }
}
