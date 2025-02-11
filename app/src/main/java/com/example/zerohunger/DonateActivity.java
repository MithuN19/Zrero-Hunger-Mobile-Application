package com.example.zerohunger;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class DonateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);

        // Initialize the UI elements
        TextInputEditText etFoodName = findViewById(R.id.et_food_name);
        TextInputEditText etFoodItem = findViewById(R.id.et_fooditem);
        TextInputEditText etQuantity = findViewById(R.id.et_quantity);
        TextInputEditText etPickupAddress = findViewById(R.id.et_pickup_address);
        TextInputEditText etContactNumber = findViewById(R.id.et_contact_number);
        MaterialButton btnSubmit = findViewById(R.id.btn_submit);
        MaterialButton btnCancel = findViewById(R.id.btn_cancel);

        // Handle submit button click
        btnSubmit.setOnClickListener(v -> {
            String foodName = etFoodName.getText().toString().trim();
            String foodItem = etFoodItem.getText().toString().trim();
            String quantity = etQuantity.getText().toString().trim();
            String address = etPickupAddress.getText().toString().trim();
            String contactNumber = etContactNumber.getText().toString().trim();

            // Check if all fields are filled
            if (foodName.isEmpty() || foodItem.isEmpty() || quantity.isEmpty() || address.isEmpty() || contactNumber.isEmpty()) {
                Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Donation Submitted!", Toast.LENGTH_SHORT).show();
                // Handle form submission logic here
                // Example: Save the data to a database or send it to a server
            }
        });

        // Handle cancel button click
        btnCancel.setOnClickListener(v -> {
            // Clear all input fields when cancel is pressed
            etFoodName.setText("");
            etFoodItem.setText("");
            etQuantity.setText("");
            etPickupAddress.setText("");
            etContactNumber.setText("");
            Toast.makeText(this, "Form Reset", Toast.LENGTH_SHORT).show();
        });
    }
}
