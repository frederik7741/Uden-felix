package com.example.felix_fridge;

import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

public class FelixAnimation {
    public static void animateProductClick(View productView) {
        // Create the animation
        Animation animation = new AlphaAnimation(0.3f, 1.0f);
        animation.setDuration(1000);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());

        // Apply the animation to the product view
        productView.startAnimation(animation);
    }
}
