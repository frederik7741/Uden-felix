package com.example.felix_fridge;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class GlowDrawable {

    private ImageView imageView;
    private final int[] colorArray = new int[]{Color.RED,Color.GREEN};

    public GlowDrawable(ImageView imageView, int selectedColor) {
        this.imageView = imageView;
        colorArray[0] = selectedColor;
    }

    public Drawable createGlowDrawable() {
        return new Drawable() {
            @Override
            public void draw(@NonNull Canvas canvas) {
                // Get the center point of the image
                int centerX = imageView.getWidth() / 2;
                int centerY = imageView.getHeight() / 2;

                // Create a new RadialGradient object with the center point and the selected color
                RadialGradient gradient = new RadialGradient(centerX, centerY, imageView.getWidth() / 2,
                        Color.TRANSPARENT, colorArray[0], Shader.TileMode.CLAMP);

                // Create a new Paint object with the gradient
                Paint paint = new Paint();
                paint.setShader(gradient);

                // Draw the gradient around the image
                canvas.drawCircle(centerX, centerY, imageView.getWidth() / 2, paint);
            }

            @Override
            public void setAlpha(int i) {

            }

            @Override
            public void setColorFilter(@Nullable ColorFilter colorFilter) {

            }

            @Override
            public int getOpacity() {
                return PixelFormat.TRANSPARENT;
            }
        };
    }
}