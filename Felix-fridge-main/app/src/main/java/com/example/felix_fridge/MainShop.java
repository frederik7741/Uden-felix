package com.example.felix_fridge;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class MainShop extends AppCompatActivity {
    protected List<Product> products;
    private GridView gridView;
    private ProductAdapter productAdapter;
    private ArrayList<ShoppingListItem> selectedProducts = new ArrayList<>();

    protected static final HashMap<String, Integer> productImageMap = new HashMap<>();

    public List<Product> getProducts() {
        return products;
    }
    private ArrayList<ShoppingListItem> shoppingList;

    private ShoppingListManager shoppingListManager;

    private GridView gridViewMain;
    private ProductAdapter productAdapterMain;





    static {
        productImageMap.put("mælk", R.drawable.maelk);
        productImageMap.put("smør", R.drawable.butter);
        productImageMap.put("fløde", R.drawable.floede);
        productImageMap.put("ost", R.drawable.ost);
        productImageMap.put("chokolade", R.drawable.chokolate);
        productImageMap.put("ketchup", R.drawable.ketchup);
        productImageMap.put("rugbrød", R.drawable.rugbrud);
        productImageMap.put("suppe", R.drawable.soup);
        productImageMap.put("tofu", R.drawable.tofu);
        productImageMap.put("banan", R.drawable.banana);
        productImageMap.put("agurk", R.drawable.cucumber);
        productImageMap.put("kartoffel", R.drawable.kartoffel);
        productImageMap.put("tomat", R.drawable.tomat);
        productImageMap.put("æble", R.drawable.apple);
        productImageMap.put("bøf", R.drawable.beefsteak);
        productImageMap.put("kylling", R.drawable.chicken);
        productImageMap.put("fisk", R.drawable.fish);
        productImageMap.put("pålæg", R.drawable.madder);
        productImageMap.put("pølse", R.drawable.sausage);
        productImageMap.put("æg", R.drawable.egg);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_shop);
        setTitle("Main Shop");

        shoppingListManager = ShoppingListManager.getInstance(getApplicationContext());
        shoppingListManager.loadShoppingList();
        shoppingList = shoppingListManager.getShoppingList(); // Add this line to initialize the shoppingList

        // Initialize the products list if it is null
        if (products == null) {
            products = createProductList();
        }

        selectedProducts = getIntent().getParcelableArrayListExtra("shoppingList");
        if (selectedProducts == null) {
            selectedProducts = new ArrayList<>(); // Initialize selectedProducts if it is null
        }

        GridView gridView = findViewById(R.id.gridViewProducts);
        productAdapter = new ProductAdapter(products, selectedProducts, shoppingListManager);
        gridView.setAdapter(productAdapter);

        // Notify the adapter that the data may have changed
        productAdapter.notifyDataSetChanged();
    }





    public void OpenDiverseShop(View v) {
        Intent diverse = new Intent(this, DiverseShop.class);
        diverse.putExtra("category", "Diverse");
        diverse.putParcelableArrayListExtra("products", new ArrayList<>(products));
        diverse.putParcelableArrayListExtra("shoppingList", selectedProducts); // Pass the selected products list
        startActivity(diverse);
    }

    public void OpenGreensShop(View v) {
        Intent greens = new Intent(this, GreensShop.class);
        greens.putExtra("category", "Fruit and Greens");
        greens.putParcelableArrayListExtra("products", new ArrayList<>(products));
        greens.putParcelableArrayListExtra("shoppingList", selectedProducts); // Pass the selected products list
        startActivity(greens);
    }

    public void OpenMeatShop(View v) {
        Intent meat = new Intent(this, MeatShop.class);
        meat.putExtra("category", "Meats");
        meat.putParcelableArrayListExtra("products", new ArrayList<>(products));
        meat.putParcelableArrayListExtra("shoppingList", selectedProducts); // Pass the selected products list
        startActivity(meat);
    }

    public void OpenDairyShop(View v) {
        Intent dairy = new Intent(this, DairyShop.class);
        dairy.putExtra("category", "Dairy");
        dairy.putParcelableArrayListExtra("products", new ArrayList<>(products));
        dairy.putParcelableArrayListExtra("shoppingList", selectedProducts); // Pass the selected products list
        startActivity(dairy);
    }


    public void goToChecklist(View view) {
        Intent checklistIntent = new Intent(this, Checklist.class);
        checklistIntent.putExtra("shoppingList", selectedProducts);
        startActivity(checklistIntent);
    }

    public void OpenFridge(View v) {
        Intent fridge = new Intent(this, FridgeActivity.class);
        startActivity(fridge);
    }

    private List<Product> createProductList() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "chokolade", "Diverse", isProductFavorite(1)));
        products.add(new Product(2, "ketchup", "Diverse", isProductFavorite(2)));
        products.add(new Product(3, "rugbrød", "Diverse", isProductFavorite(3)));
        products.add(new Product(4, "suppe", "Diverse", isProductFavorite(4)));
        products.add(new Product(5, "tofu", "Diverse", isProductFavorite(5)));
        products.add(new Product(6, "banan", "Fruit and Greens", isProductFavorite(6)));
        products.add(new Product(7, "agurk", "Fruit and Greens", isProductFavorite(7)));
        products.add(new Product(8, "kartoffel", "Fruit and Greens", isProductFavorite(8)));
        products.add(new Product(9, "tomat", "Fruit and Greens", isProductFavorite(9)));
        products.add(new Product(10, "æble", "Fruit and Greens", isProductFavorite(10)));
        products.add(new Product(11, "bøf", "Meats", isProductFavorite(11)));
        products.add(new Product(12, "kylling", "Meats", isProductFavorite(12)));
        products.add(new Product(13, "fisk", "Meats", isProductFavorite(13)));
        products.add(new Product(14, "pålæg", "Meats", isProductFavorite(14)));
        products.add(new Product(15, "pølse", "Meats", isProductFavorite(15)));
        products.add(new Product(16, "mælk", "Dairy", isProductFavorite(16)));
        products.add(new Product(17, "smør", "Dairy", isProductFavorite(17)));
        products.add(new Product(18, "fløde", "Dairy", isProductFavorite(18)));
        products.add(new Product(19, "ost", "Dairy", isProductFavorite(19)));
        products.add(new Product(20, "æg", "Dairy", isProductFavorite(20)));

        // Sort the products based on favorites first
        Collections.sort(products, new ProductComparator());

        return products;
    }

    private boolean isProductFavorite(int productId) {
        // Retrieve the favorite status for the product with the given ID from SharedPreferences
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        return sharedPreferences.getBoolean("favorite_" + productId, false);
    }


    static class Product implements Parcelable {
        private int id;
        private String name;
        private String category;
        private boolean isFavorite;

        public Product(int id, String name, String category, boolean isFavorite) {
            this.id = id;
            this.name = name;
            this.category = category;
            this.isFavorite = isFavorite;
        }

        protected Product(Parcel in) {
            id = in.readInt();
            name = in.readString();
            category = in.readString();
            isFavorite = in.readByte() != 0;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public boolean isFavorite() {
            return isFavorite;
        }

        public void setFavorite(boolean favorite) {
            isFavorite = favorite;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(id);
            dest.writeString(name);
            dest.writeString(category);
            dest.writeByte((byte) (isFavorite ? 1 : 0));
        }

        public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
            @Override
            public Product createFromParcel(Parcel in) {
                return new Product(in);
            }

            @Override
            public Product[] newArray(int size) {
                return new Product[size];
            }
        };

        // Implement the equals() method to compare products based on their attributes
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Product product = (Product) o;
            return Objects.equals(name, product.name) &&
                    Objects.equals(category, product.category);
        }
    }



    static class ProductComparator implements Comparator<Product> {
        @Override
        public int compare(Product p1, Product p2) {
            return Boolean.compare(p2.isFavorite(), p1.isFavorite());
        }
    }


}
