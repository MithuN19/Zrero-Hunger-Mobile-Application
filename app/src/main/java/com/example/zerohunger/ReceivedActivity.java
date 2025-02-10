package com.example.zerohunger;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class ReceivedActivity extends AppCompatActivity {

    private TextInputEditText etDateReceived;
    private TextInputEditText etFoodName;
    private TextInputEditText etItem;
    private TextInputEditText etNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_received);

        etFoodName = findViewById(R.id.et_food_name);
        etItem = findViewById(R.id.et_item);
        etDateReceived = findViewById(R.id.et_datereceive);
        etNote = findViewById(R.id.et_note);
        MaterialButton btnSubmit = findViewById(R.id.btn_submit);
        MaterialButton btnCancel = findViewById(R.id.btn_cancel);

        // Add date picker functionality to the DateReceived field
        etDateReceived.setOnClickListener(v -> showDatePicker());

        // Submit button logic
        btnSubmit.setOnClickListener(v -> {
            String foodName = etFoodName.getText().toString().trim();
            String itemName = etItem.getText().toString().trim();
            String dateReceived = etDateReceived.getText().toString().trim();
            String note = etNote.getText().toString().trim();

            if (foodName.isEmpty() || itemName.isEmpty() || dateReceived.isEmpty()) {
                Toast.makeText(this, "Please fill all required fields!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Food Received Successfully!", Toast.LENGTH_SHORT).show();
                // Handle form submission logic here
            }
        });

        // Cancel button logic
        btnCancel.setOnClickListener(v -> {
            etFoodName.setText("");
            etItem.setText("");
            etDateReceived.setText("");
            etNote.setText("");
            Toast.makeText(this, "Form Reset", Toast.LENGTH_SHORT).show();
        });
    }

    // Method to show the Date Picker dialog
    private void showDatePicker() {
        // Get current date
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Show DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    // Format the selected date
                    String formattedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                    etDateReceived.setText(formattedDate);
                },
                year,
                month,
                day
        );
        datePickerDialog.show();
    }
}
