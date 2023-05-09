package com.example.felix_fridge;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;



import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MeatShop extends AppCompatActivity {

    private GridView gridViewMeat;
    private ProductAdapter productAdapterMeat;
    private String selectedCategory;
    private List<MainShop.Product> products;
    private ArrayList<ShoppingListItem> selectedProducts = new ArrayList<>();
    private ShoppingListManager shoppingListManager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meat_shop);

        shoppingListManager = ShoppingListManager.getInstance(getApplicationContext());
        shoppingListManager.loadShoppingList();

        gridViewMeat = findViewById(R.id.gridViewMeat);

        selectedCategory = getIntent().getStringExtra("category");
        products = getIntent().getParcelableArrayListExtra("products");
        selectedProducts = getIntent().getParcelableArrayListExtra("shoppingList");

        List<MainShop.Product> diverseProducts = new ArrayList<>();
        for (MainShop.Product product : products) {
            if (product.getCategory().equals(selectedCategory)) {
                diverseProducts.add(product);
            }
        }

        Collections.sort(diverseProducts, new MainShop.ProductComparator());

        productAdapterMeat = new ProductAdapter(diverseProducts, selectedProducts, shoppingListManager);

        gridViewMeat.setAdapter(productAdapterMeat);
    }

    public void goToChecklist(View view) {
        Intent checklistIntent = new Intent(this, Checklist.class);
        checklistIntent.putExtra("shoppingList", selectedProducts);
        startActivity(checklistIntent);
    }

    public void OpenDairyShop(View v) {
        Intent dairy = new Intent(this, DairyShop.class);
        dairy.putExtra("category", "Dairy");
        dairy.putParcelableArrayListExtra("products", new ArrayList<>(products));
        dairy.putParcelableArrayListExtra("shoppingList", selectedProducts);
        startActivity(dairy);
    }

    public void OpenGreensShop(View v) {
        Intent greens = new Intent(this, GreensShop.class);
        greens.putExtra("category", "Fruit and Greens");
        greens.putParcelableArrayListExtra("products", new ArrayList<>(products));
        greens.putParcelableArrayListExtra("shoppingList", selectedProducts);
        startActivity(greens);
    }

    public void OpenDiverseShop(View v) {
        Intent diverse = new Intent(this, DiverseShop.class);
        diverse.putExtra("category", "Diverse");
        diverse.putParcelableArrayListExtra("products", new ArrayList<>(products));
        diverse.putParcelableArrayListExtra("shoppingList", selectedProducts);
        startActivity(diverse);
    }

    public void goToMainShop(View view) {
        Intent mainShopIntent = new Intent(this, MainShop.class);
        mainShopIntent.putExtra("category", "Main Shop");
        mainShopIntent.putParcelableArrayListExtra("products", new ArrayList<>(products));
        mainShopIntent.putParcelableArrayListExtra("shoppingList", selectedProducts);
        startActivity(mainShopIntent);
    }



}
