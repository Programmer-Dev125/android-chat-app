package com.example.chatapp.model;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;

public final class CircleImageView {
    public static Bitmap circleImage(Bitmap image, int size){
        float radius = size / 2f;

        Bitmap output = Bitmap.createBitmap(size, size, Bitmap.Config.RGBA_F16);
        Canvas canvas = new Canvas(output);

        Bitmap scaled = Bitmap.createScaledBitmap(image, size, size, true);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setShader(new BitmapShader(scaled, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));

        canvas.drawCircle(radius, radius, radius, paint);

        return output;
    }
}
