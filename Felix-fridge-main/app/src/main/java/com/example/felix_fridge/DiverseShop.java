package com.example.felix_fridge;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class DiverseShop extends AppCompatActivity {
    private GridView gridViewDiverse;
    private ProductAdapter productAdapterDiverse;
    private String selectedCategory;
    private List<MainShop.Product> products;
    private ArrayList<ShoppingListItem> selectedProducts = new ArrayList<>();
    private ShoppingListManager shoppingListManager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diverse_shop);

        gridViewDiverse = findViewById(R.id.gridViewDiverse);
        shoppingListManager = ShoppingListManager.getInstance(getApplicationContext());
        shoppingListManager.loadShoppingList();

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

        productAdapterDiverse = new ProductAdapter(diverseProducts, selectedProducts, shoppingListManager);

        gridViewDiverse.setAdapter(productAdapterDiverse);
    }

    public void goToChecklist(View view) {
        Intent checklistIntent = new Intent(this, Checklist.class);
        checklistIntent.putExtra("shoppingList", selectedProducts);
        startActivity(checklistIntent);
    }

    public void OpenGreensShop(View v) {
        Intent greens = new Intent(this, GreensShop.class);
        greens.putExtra("category", "Fruit and Greens");
        greens.putParcelableArrayListExtra("products", new ArrayList<>(products));
        greens.putParcelableArrayListExtra("shoppingList", selectedProducts);
        startActivity(greens);
    }

    public void OpenMeatShop(View v) {
        Intent meat = new Intent(this, MeatShop.class);
        meat.putExtra("category", "Meats");
        meat.putParcelableArrayListExtra("products", new ArrayList<>(products));
        meat.putParcelableArrayListExtra("shoppingList", selectedProducts);
        startActivity(meat);
    }

    public void OpenDairyShop(View v) {
        Intent dairy = new Intent(this, DairyShop.class);
        dairy.putExtra("category", "Dairy");
        dairy.putParcelableArrayListExtra("products", new ArrayList<>(products));
        dairy.putParcelableArrayListExtra("shoppingList", selectedProducts);
        startActivity(dairy);
    }

    public void goToMainShop(View view) {
        Intent mainShopIntent = new Intent(this, MainShop.class);
        mainShopIntent.putExtra("category", "Main Shop");
        mainShopIntent.putParcelableArrayListExtra("products", new ArrayList<>(products));
        mainShopIntent.putParcelableArrayListExtra("shoppingList", selectedProducts);
        startActivity(mainShopIntent);
    }



}
