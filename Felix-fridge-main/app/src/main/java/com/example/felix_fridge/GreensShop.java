package com.example.felix_fridge;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class GreensShop extends AppCompatActivity {

    private GridView gridViewGreens;
    private ProductAdapter productAdapterGreens;
    private String selectedCategory;
    private List<MainShop.Product> products;
    private ArrayList<ShoppingListItem> selectedProducts = new ArrayList<>();
    private ShoppingListManager shoppingListManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greens_shop);
        selectedProducts = getIntent().getParcelableArrayListExtra("shoppingList");

        shoppingListManager = ShoppingListManager.getInstance(getApplicationContext());
        shoppingListManager.loadShoppingList();

        gridViewGreens = findViewById(R.id.gridViewGreens);

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

        productAdapterGreens = new ProductAdapter(diverseProducts, selectedProducts, shoppingListManager);

        gridViewGreens.setAdapter(productAdapterGreens);
    }

    public void goToChecklist(View view) {
        Intent checklistIntent = new Intent(this, Checklist.class);
        checklistIntent.putExtra("shoppingList", selectedProducts);
        startActivity(checklistIntent);
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
