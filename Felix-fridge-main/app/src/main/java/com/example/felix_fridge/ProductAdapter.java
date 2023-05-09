package com.example.felix_fridge;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductAdapter extends BaseAdapter {
    private List<MainShop.Product> productList;
    private ArrayList<ShoppingListItem> selectedProducts;
    private ShoppingListManager shoppingListManager;

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
                FelixAnimation.animateProductClick(v);

            }
        });

        // Handle long press to toggle favorite status
        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // Toggle the favorite status of the product
                product.setFavorite(!product.isFavorite());

                // Update the favorite status in SharedPreferences
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(v.getContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("favorite_" + product.getId(), product.isFavorite());
                editor.apply();

                // Reorder the product list based on favorites
                Collections.sort(productList, new MainShop.ProductComparator());

                // Notify the adapter that the data has changed
                notifyDataSetChanged();

                return true;
            }
        });

        return convertView;
    }
}

