package com.example.felix_fridge;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class GreensFridge extends FridgeActivity {

    ImageView imageView;
    private int selectedColor = Color.GREEN; // Green or RED

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greens_fridge);

        imageView = findViewById(R.id.banana);
        imageView.setImageResource(R.drawable.banana);

        // Create a new GlowDrawable object with the ImageView and selected color
        GlowDrawable glowDrawable = new GlowDrawable(imageView, selectedColor);

        // Set the Drawable object as the background of the ImageView
        imageView.setBackground(glowDrawable.createGlowDrawable());

        // Change the color to green
        selectedColor = Color.GREEN;
        imageView.setBackground(glowDrawable.createGlowDrawable());
    }

    public void OpenFridge(View v) {
        //Start a new activity

        Intent f = new Intent(this, FridgeActivity.class);
        startActivity(f);
    }

    public void openFragment(View v) {
        // Create new fragment instance
        ThrowOrEat newFragment = new ThrowOrEat();

        // Get the FragmentManager and start a new transaction
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Add the new fragment to the container view
        fragmentTransaction.add(R.id.fragment_container, newFragment);
        fragmentTransaction.addToBackStack(null);

        // Commit the transaction
        fragmentTransaction.commit();
    }
}