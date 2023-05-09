package com.example.felix_fridge;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import java.util.ArrayList;

public class ShoppingListManager {
    private static final String SHOPPING_LIST_KEY = "shoppingList";
    private static ShoppingListManager instance;
    private ArrayList<ShoppingListItem> shoppingList;
    private Context applicationContext;

    private ShoppingListManager(Context context) {
        shoppingList = new ArrayList<>();
        applicationContext = context.getApplicationContext();
    }

    public static synchronized ShoppingListManager getInstance(Context context) {
        if (instance == null) {
            instance = new ShoppingListManager(context);
        }
        return instance;
    }

    public ArrayList<ShoppingListItem> getShoppingList() {
        return shoppingList;
    }

    public void addToShoppingList(String productName) {
        // Check if the product already exists in the shopping list
        for (ShoppingListItem item : shoppingList) {
            if (item.getProductName().equals(productName)) {
                // If it does, increment the quantity
                item.incrementQuantity();
                saveShoppingList();
                return;
            }
        }

        // If the product doesn't exist in the shopping list, add it with a quantity of 1
        shoppingList.add(new ShoppingListItem(productName, 1));
        saveShoppingList();
    }

    public void clearShoppingList() {
        shoppingList.clear();
        saveShoppingList();
    }

    public void saveShoppingList() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        StringBuilder shoppingListString = new StringBuilder();
        for (ShoppingListItem item : shoppingList) {
            shoppingListString.append(item.getProductName()).append(",").append(item.getQuantity()).append(";");
        }

        editor.putString(SHOPPING_LIST_KEY, shoppingListString.toString());
        editor.apply();
    }

    public void loadShoppingList() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext);
        String shoppingListString = sharedPreferences.getString(SHOPPING_LIST_KEY, "");

        shoppingList.clear();

        if (!TextUtils.isEmpty(shoppingListString)) {
            String[] shoppingListArray = shoppingListString.split(";");
            for (String itemString : shoppingListArray) {
                String[] itemData = itemString.split(",");
                if (itemData.length == 2) {
                    String productName = itemData[0];
                    int quantity = Integer.parseInt(itemData[1]);
                    shoppingList.add(new ShoppingListItem(productName, quantity));
                }
            }
        }
    }
}
