package com.example.felix_fridge;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.VideoView;
import android.os.Handler;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductAdapter extends BaseAdapter {
    private List<MainShop.Product> productList;
    private ArrayList<ShoppingListItem> selectedProducts;
    private ShoppingListManager shoppingListManager;

    private AnimationDrawable mascotAnimation;

    public ProductAdapter(List<MainShop.Product> productList, ArrayList<ShoppingListItem> selectedProducts, ShoppingListManager shoppingListManager) {
        this.productList = productList;
        this.selectedProducts = selectedProducts;
        this.shoppingListManager = shoppingListManager;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.grid_item_product, parent, false);
        }

        ImageView imageViewProduct = convertView.findViewById(R.id.imageViewProduct);
        MainShop.Product product = productList.get(position);
        Integer imageResourceId = MainShop.productImageMap.get(product.getName());
        // Set the product image
        if (imageResourceId != null) {
            imageViewProduct.setImageResource(imageResourceId);
        }

        // Declare animationView here
        ImageView animationView = convertView.findViewById(R.id.mascot_animation);

        // Handle click to add product to the shopping list

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if the product is already in the shopping list
                boolean productExists = false;
                int productIndex = -1;

                for (int i = 0; i < selectedProducts.size(); i++) {
                    if (selectedProducts.get(i).getProductName().equals(product.getName())) {
                        productExists = true;
                        productIndex = i;
                        break;
                    }
                }

                if (productExists) {
                    // If the product already exists, increment its quantity
                    selectedProducts.get(productIndex).incrementQuantity();
                } else {
                    // If the product doesn't exist, add it to the shopping list
                    ShoppingListItem newItem = new ShoppingListItem(product.getName(), 1);
                    selectedProducts.add(newItem);
                }

                // Save the shopping list
                shoppingListManager.saveShoppingList();

                // Notify the adapter that the data has changed
                notifyDataSetChanged();
                // Get the FrameLayout from your activity
                FrameLayout animationContainer = ((Activity) v.getContext()).findViewById(R.id.animationContainer);

                if (animationContainer != null) {
                    // Create a new ImageView and set the product image to it
                    ImageView animatedImage = new ImageView(v.getContext());
                    animatedImage.setImageResource(imageResourceId);

                    // Set the size of the ImageView to match the clicked view
                    int width = v.getWidth();
                    int height = v.getHeight();
                    FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(width, height);
                    animatedImage.setLayoutParams(params);

                    // Set the initial position of the ImageView to match the clicked view
                    animatedImage.setX(v.getX());
                    animatedImage.setY(v.getY());

                    // Add the ImageView to the FrameLayout
                    animationContainer.addView(animatedImage);

                    // Animate the ImageView to move to the bottom of the screen
                    animatedImage.animate().translationY(animationContainer.getHeight()).setDuration(1000).withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            // Remove the ImageView from the FrameLayout after the animation is done
                            animationContainer.removeView(animatedImage);
                        }
                    }).start();
                }






                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        animationView.setVisibility(View.GONE);
                    }
                }, 1000);
            }
        });

        return convertView;
    }

}

