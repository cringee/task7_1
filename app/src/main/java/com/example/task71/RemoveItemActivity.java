package com.example.task71;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class RemoveItemActivity extends AppCompatActivity {
    private TextView nameTextView;
    private TextView phoneTextView;
    private TextView descriptionTextView;
    private TextView dateTextView;
    private TextView locationTextView;
    private Button removeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remove_the_item);

        // Get references to the views
        nameTextView = findViewById(R.id.name_text_view);
        phoneTextView = findViewById(R.id.phone_text_view);
        descriptionTextView = findViewById(R.id.description_text_view);
        dateTextView = findViewById(R.id.date_text_view);
        locationTextView = findViewById(R.id.location_text_view);
        removeButton = findViewById(R.id.remove_button);

        // Get the selected item from the intent
        Item item = (Item) getIntent().getSerializableExtra("item");

        // Set the text of the views to the item's properties
        nameTextView.setText("Name: " + item.getName());
        phoneTextView.setText("Phone: " + item.getPhone());
        descriptionTextView.setText("Description: " + item.getDescription());
        dateTextView.setText("Date: " + item.getDate());
        locationTextView.setText("Location: " + item.getLocation());

        // Set the click listener for the remove button
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remove the item from the database
                ItemDatabaseHelper dbHelper = new ItemDatabaseHelper(RemoveItemActivity.this);
                dbHelper.removeItem(item);

                // Return to the ShowItemsActivity
                Intent intent = new Intent(RemoveItemActivity.this, ShowItemsActivity.class);
                startActivity(intent);
            }
        });
    }
}