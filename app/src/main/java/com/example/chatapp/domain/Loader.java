package com.example.chatapp.domain;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.animation.LinearInterpolator;

public class Loader extends Drawable {

    private final Paint paint;
    private final RectF rectF;
    private float sweepAngle = 0f;

    public Loader(int color, float strokeWidth) {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(color);

        rectF = new RectF();

        startAnimation();
    }

    private void startAnimation() {
        ValueAnimator animator = ValueAnimator.ofFloat(0, 360);
        animator.setDuration(1000); // 1 second
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(animation -> {
            sweepAngle = (float) animation.getAnimatedValue();
            invalidateSelf(); // redraw
        });
        animator.start();
    }

    @Override
    public void draw(Canvas canvas) {
        float inset = paint.getStrokeWidth() / 2;
        rectF.set(getBounds());
        rectF.inset(inset, inset);
        canvas.drawArc(rectF, sweepAngle, 120, false, paint); // arc loader
    }

    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        paint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
