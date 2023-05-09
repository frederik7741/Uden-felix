package com.example.felix_fridge;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Checklist extends AppCompatActivity {
    private ListView listViewChecklist;
    private ChecklistAdapter checklistAdapter;
    private ArrayList<ShoppingListItem> shoppingListItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist);
        setTitle("Checklist");

        listViewChecklist = findViewById(R.id.listViewChecklist);

        // Get the list of clicked products and their click counts from the intent
        shoppingListItems = getIntent().getParcelableArrayListExtra("shoppingList");

        // Create the adapter and set it to the ListView
        checklistAdapter = new ChecklistAdapter(shoppingListItems);
        listViewChecklist.setAdapter(checklistAdapter);
    }


    public void resetShoppingList(View view) {
        // Clear the shopping list
        ShoppingListManager.getInstance(getApplicationContext()).clearShoppingList();

        // Open the MainScreen activity
        Intent mainScreenIntent = new Intent(this, MainScreen.class);
        startActivity(mainScreenIntent);
    }


    static class ChecklistAdapter extends BaseAdapter {
        private ArrayList<ShoppingListItem> shoppingListItems;

        public ChecklistAdapter(ArrayList<ShoppingListItem> shoppingListItems) {
            this.shoppingListItems = shoppingListItems;
        }

        @Override
        public int getCount() {
            return shoppingListItems.size();
        }

        @Override
        public ShoppingListItem getItem(int position) {
            return shoppingListItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;

            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_checklist, parent, false);

                holder = new ViewHolder();
                holder.productNameTextView = convertView.findViewById(R.id.productName);
                holder.quantityTextView = convertView.findViewById(R.id.quantity);
                holder.decreaseButton = convertView.findViewById(R.id.decreaseButton);
                holder.increaseButton = convertView.findViewById(R.id.increaseButton);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            ShoppingListItem item = shoppingListItems.get(position);

            holder.productNameTextView.setText(item.getProductName());
            holder.quantityTextView.setText(String.valueOf(item.getQuantity()));

            // Set the click listeners for the decrease and increase buttons
            holder.decreaseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Decrease the quantity by 1 if it's greater than 0
                    int currentQuantity = item.getQuantity();
                    if (currentQuantity > 0) {
                        item.setQuantity(currentQuantity - 1);
                        notifyDataSetChanged();
                    }
                }
            });

            holder.increaseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Increase the quantity by 1
                    item.setQuantity(item.getQuantity() + 1);
                    notifyDataSetChanged();
                }
            });

            return convertView;
        }

        static class ViewHolder {
            TextView productNameTextView;
            TextView quantityTextView;
            Button decreaseButton;
            Button increaseButton;
        }
    }

}
