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

public class DairyShop extends AppCompatActivity {

    private GridView gridViewDairy;
    private ProductAdapter productAdapterDairy;
    private String selectedCategory;
    private List<MainShop.Product> products;
    private ArrayList<ShoppingListItem> selectedProducts = new ArrayList<>();
    private ShoppingListManager shoppingListManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dairy_shop);

        shoppingListManager = ShoppingListManager.getInstance(getApplicationContext());
        shoppingListManager.loadShoppingList();

        gridViewDairy = findViewById(R.id.gridViewDairy);

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

        productAdapterDairy = new ProductAdapter(diverseProducts, selectedProducts, shoppingListManager);

        gridViewDairy.setAdapter(productAdapterDairy);
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
