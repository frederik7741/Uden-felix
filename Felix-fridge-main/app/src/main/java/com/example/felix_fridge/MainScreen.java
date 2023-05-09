package com.example.felix_fridge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;



public class MainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscreen);
    }

    public void OpenFridge(View v) {
        //Start a new activity

        Intent f = new Intent(this, FridgeActivity.class);
        startActivity(f);
    }

    public void OpenShop(View v) {
        //Start a new activity
        Intent Mainshop = new Intent(this, MainShop.class);
        startActivity(Mainshop);
    }

    public void OpenTutorial(View v) {
        //Start a new activity
        Intent Tutorial = new Intent(this, Tutorial.class);
        startActivity(Tutorial);
    }
}

