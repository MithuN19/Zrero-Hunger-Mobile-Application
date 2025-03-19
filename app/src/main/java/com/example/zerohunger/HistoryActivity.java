package com.example.zerohunger;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private HistoryAdapter adapter;
    private List<history_item> historyList;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        recyclerView = findViewById(R.id.recycler_view_history);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize Firestore
        db = FirebaseFirestore.getInstance();

        // Fetch data from Firestore
        fetchHistoryData();
    }

    private void fetchHistoryData() {
        historyList = new ArrayList<>();

        db.collection("donations")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String foodName = document.getString("foodName");
                            String foodItem = document.getString("foodItem");
                            String quantity = document.getString("quantity");
                            String date = document.getString("pickupAddress"); // Change this to actual timestamp if needed

                            historyList.add(new history_item("Donation", "Food Name: " + foodName + "\nItem: " + foodItem, "Quantity: " + quantity, date));
                        }

                        // Set adapter after fetching data
                        adapter = new HistoryAdapter(historyList);
                        recyclerView.setAdapter(adapter);
                    } else {
                        Toast.makeText(this, "Error fetching data", Toast.LENGTH_SHORT).show();
                        Log.e("HistoryActivity", "Error getting documents: ", task.getException());
                    }
                });
    }
}


//package com.example.zerohunger;
//
//import android.os.Bundle;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class HistoryActivity extends AppCompatActivity {
//
//    private RecyclerView recyclerView;
//    private HistoryAdapter adapter;
//    private List<history_item> historyList;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_history);
//
//        recyclerView = findViewById(R.id.recycler_view_history);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        // Mock data (replace with actual data retrieval logic)
//        historyList = new ArrayList<>();
//        historyList.add(new history_item("Donation", "Food Name: Apples", "Quantity: 10", "12/01/2025"));
//        historyList.add(new history_item("Received", "Food Name: Bananas", "Quantity: 15", "13/01/2025"));
//        // Set up the adapter
//        adapter = new HistoryAdapter(historyList);
//        recyclerView.setAdapter(adapter);
//    }
//}
