package com.example.task71;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreateAdvertActivity extends AppCompatActivity {
    private ItemDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_a_new_advert);

        // Initialize the database helper
        databaseHelper = new ItemDatabaseHelper(this);

        // Set up submit button click listener
        Button submitButton = findViewById(R.id.btn_submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get reference to the radio group
                RadioGroup radioGroup = findViewById(R.id.radio_group_post_type);

                // Get the ID of the checked radio button
                String postType = ((RadioButton) findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();
                String name = ((EditText) findViewById(R.id.edit_text_name)).getText().toString();
                String phone = ((EditText) findViewById(R.id.edit_text_phone)).getText().toString();
                String description = ((EditText) findViewById(R.id.edit_text_description)).getText().toString();
                String date = ((EditText) findViewById(R.id.edit_text_date)).getText().toString();
                String location = ((EditText) findViewById(R.id.edit_text_location)).getText().toString();

                // Validate form
                if (name.isEmpty() || phone.isEmpty() || description.isEmpty() || date.isEmpty() || location.isEmpty()) {
                    Toast.makeText(CreateAdvertActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Save post to database
                    //databaseHelper.addItem(new Item(postType, name, phone, description, date, location));
                    // Create a new Item object without specifying the ID
                    Item newItem = new Item(postType, name, phone, description, date, location);

                    // Add the item to the database and get the generated ID
                    long itemId = databaseHelper.addItem(newItem);

                    // Set the generated ID back to the item object
                    newItem.setId(itemId);

                    // Show success message
                    Toast.makeText(CreateAdvertActivity.this, "Post submitted successfully", Toast.LENGTH_SHORT).show();

                    // Clear the form fields
                    ((EditText) findViewById(R.id.edit_text_name)).setText("");
                    ((EditText) findViewById(R.id.edit_text_phone)).setText("");
                    ((EditText) findViewById(R.id.edit_text_description)).setText("");
                    ((EditText) findViewById(R.id.edit_text_date)).setText("");
                    ((EditText) findViewById(R.id.edit_text_location)).setText("");

                    // Return to main activity
                    Intent intent = new Intent(CreateAdvertActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close the database helper
        databaseHelper.close();
    }
}
