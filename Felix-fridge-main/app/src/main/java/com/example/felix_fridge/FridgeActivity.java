package com.example.felix_fridge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class FridgeActivity extends AppCompatActivity {

    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;

    private int selectedColor = Color.GREEN; // Green
    private int selectedColor2 = Color.RED;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fridge);

        //Banana Green
        imageView = findViewById(R.id.banana2);
        imageView.setImageResource(R.drawable.banana);

        // Create a new GlowDrawable object with the ImageView and selected color
        GlowDrawable glowDrawable = new GlowDrawable(imageView, selectedColor);

        // Set the Drawable object as the background of the ImageView
        imageView.setBackground(glowDrawable.createGlowDrawable());

        // Change the color to green
        imageView.setBackground(glowDrawable.createGlowDrawable());

        //steak Rød
        imageView3 = findViewById(R.id.steak);
        imageView3.setImageResource(R.drawable.beefsteak);

        // Create a new GlowDrawable object with the ImageView and selected color
        GlowDrawable glowDrawable3 = new GlowDrawable(imageView3, selectedColor);

        // Set the Drawable object as the background of the ImageView
        imageView3.setBackground(glowDrawable3.createGlowDrawable());

        // Change the color to green
        imageView3.setBackground(glowDrawable3.createGlowDrawable());


        //CHicken the color is Green

        imageView2 = findViewById(R.id.cheese2);
        imageView2.setImageResource(R.drawable.chicken);

        // Create a new GlowDrawable object with the ImageView and selected color
        GlowDrawable glowDrawable2 = new GlowDrawable(imageView2, selectedColor2);

        // Set the Drawable object as the background of the ImageView
        imageView2.setBackground(glowDrawable2.createGlowDrawable());

        // Change the color to green
        imageView2.setBackground(glowDrawable2.createGlowDrawable());

        //brød the color is Green

        imageView4 = findViewById(R.id.brud);
        imageView4.setImageResource(R.drawable.rugbrud);

        // Create a new GlowDrawable object with the ImageView and selected color
        GlowDrawable glowDrawable4 = new GlowDrawable(imageView4, selectedColor2);

        // Set the Drawable object as the background of the ImageView
        imageView4.setBackground(glowDrawable4.createGlowDrawable());

        // Change the color to green
        imageView2.setBackground(glowDrawable4.createGlowDrawable());


    }

    public void OpenDiverseFridge(View v) {
        //Start a new activity
        Intent Diverse = new Intent(this, DiverseFridge.class);
        startActivity(Diverse);
    }

    public void OpenDiearyFridge(View v) {
        //Start a new activity
        Intent Dieary = new Intent(this, DairyFridge.class);
        startActivity(Dieary);
    }

    public void OpenMeatFridge(View v) {
        //Start a new activity
        Intent Meat = new Intent(this, MeatFridge.class);
        startActivity(Meat);
    }

    public void OpenGreensFridge(View v) {
        //Start a new activity
        Intent Greens = new Intent(this, GreensFridge.class);
        startActivity(Greens);
    }

    public void OpenShop(View v) {
        //Start a new activity
        Intent Mainshop = new Intent(this, MainShop.class);
        startActivity(Mainshop);
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