package com.example.task71;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class ShowItemsActivity extends AppCompatActivity {
    private LinearLayout itemsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_all_lost_and_found_items);

        // Get reference to the items container
        itemsContainer = findViewById(R.id.items_container);

        // Populate the items container with items from the database
        populateItems();
    }


    private List<Item> getSampleItems() {
        return List.of(
                new Item("Lost", "IPhone", "999999999", "iPhone 12", "2021-10-01", "Deakin Library"),
                new Item("Found", "Wallet", "8888888", "Wallet", "2021-09-30", "Burwood Campus")
        );
    }

    private void populateItems() {
        // Clear the items container
        itemsContainer.removeAllViews();

        // Get all items from the database
        ItemDatabaseHelper dbHelper = new ItemDatabaseHelper(this);
        List<Item> items = dbHelper.getAllItems();

        // Add each item to the container as a clickable TextView
        for (Item item : items) {
            TextView itemView = new TextView(this);
            itemView.setText(item.getPostType() + ": " + item.getName());
            itemView.setTextSize(18);
            itemView.setPadding(16, 16, 16, 16);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Launch the RemoveItemActivity with the selected item
                    Intent intent = new Intent(ShowItemsActivity.this, RemoveItemActivity.class);
                    intent.putExtra("item", item);
                    startActivity(intent);
                }
            });
            itemsContainer.addView(itemView);
        }
    }
}