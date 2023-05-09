package com.example.felix_fridge;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class DairyFridge extends FridgeActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dairy_fridge);
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