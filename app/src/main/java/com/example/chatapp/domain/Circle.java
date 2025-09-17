package com.example.chatapp.domain;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

public class Circle extends Drawable {

    private final Paint fillPaint;
    private final Paint strokePaint;
    private boolean drawFill = false;
    private boolean drawStroke = false;
    private float strokeWidth = 0f;

    private boolean both = false;

    public Circle(String style, int strokeColor, int fillColor, float strokeWidth) {

        this.strokeWidth = strokeWidth;

        if("stroke".equals(style)){
            this.drawStroke = true;
        }else if("fill".equals(style)){
            this.drawFill = true;
        }else if("both".equals(style)){
            this.both = true;
        }

        // fill paint
        fillPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        fillPaint.setStyle(Paint.Style.FILL);
        fillPaint.setColor(fillColor);

        // stroke paint
        strokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setStrokeWidth(this.strokeWidth);
        strokePaint.setStrokeJoin(Paint.Join.ROUND);
        strokePaint.setStrokeCap(Paint.Cap.ROUND);
        strokePaint.setColor(strokeColor);

    }

    @Override
    public void draw(Canvas canvas) {
        Rect b = getBounds();
        float cx = b.centerX();
        float cy = b.centerY();

        float radius = Math.min(b.width(), b.height()) / 2f;

        // If we will draw the stroke, shrink the radius so stroke does not get clipped.
        if (drawStroke) {
            radius -= (strokeWidth / 2f);
        }

        if (radius <= 0f) {
            return;
        }

        if (drawFill) {
            canvas.drawCircle(cx, cy, radius, fillPaint);
        }else if(drawStroke){
            canvas.drawCircle(cx, cy, radius, strokePaint);
        }else if(both){
            canvas.drawCircle(cx, cy, radius, fillPaint);
            canvas.drawCircle(cx, cy, radius, strokePaint);
        }
    }

    @Override
    public void setAlpha(int alpha) {}

    @Override
    public void setColorFilter(ColorFilter colorFilter) {}

    @Override
    public int getOpacity() {
        // semi-conservative choice; TRANSLUCENT is safe
        return PixelFormat.TRANSLUCENT;
    }


}
