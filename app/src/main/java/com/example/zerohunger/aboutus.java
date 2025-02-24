package com.example.zerohunger;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class aboutus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus); // Ensure this layout exists

        // Example: Properly initializing views before applying operations
        // If you're using custom handling for WindowInsets or other advanced features,
        // ensure the view exists and is not null before applying the listener.

        // Example (if necessary):
        // View someView = findViewById(R.id.some_view);
        // if (someView != null) {
        //     ViewCompat.setOnApplyWindowInsetsListener(someView, (v, insets) -> insets);
        // }
    }
}