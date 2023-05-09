package com.example.felix_fridge;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MeatFridge extends AppCompatActivity {

    ImageView imageView;
    ImageView imageView2;
    private int selectedColor = Color.RED;
    private int selectedColor2 = Color.GREEN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meat_fridge);

        //steak Rød
        imageView = findViewById(R.id.Steak);
        imageView.setImageResource(R.drawable.beefsteak);

        // Create a new GlowDrawable object with the ImageView and selected color
        GlowDrawable glowDrawable = new GlowDrawable(imageView, selectedColor);

        // Set the Drawable object as the background of the ImageView
        imageView.setBackground(glowDrawable.createGlowDrawable());

        // Change the color to green
        selectedColor = Color.GREEN;
        imageView.setBackground(glowDrawable.createGlowDrawable());


        //CHicken the color is Green

        imageView2 = findViewById(R.id.Chicken);
        imageView2.setImageResource(R.drawable.chicken);

        // Create a new GlowDrawable object with the ImageView and selected color
        GlowDrawable glowDrawable2 = new GlowDrawable(imageView2, selectedColor2);

        // Set the Drawable object as the background of the ImageView
        imageView2.setBackground(glowDrawable2.createGlowDrawable());

        // Change the color to green
        selectedColor2 = Color.GREEN;
        imageView2.setBackground(glowDrawable2.createGlowDrawable());

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