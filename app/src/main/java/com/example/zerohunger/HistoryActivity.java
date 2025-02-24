package com.example.zerohunger;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private HistoryAdapter adapter;
    private List<history_item> historyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        recyclerView = findViewById(R.id.recycler_view_history);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Mock data (replace with actual data retrieval logic)
        historyList = new ArrayList<>();
        historyList.add(new history_item("Donation", "Food Name: Apples", "Quantity: 10", "12/01/2025"));
        historyList.add(new history_item("Received", "Food Name: Bananas", "Quantity: 15", "13/01/2025"));
        // Set up the adapter
        adapter = new HistoryAdapter(historyList);
        recyclerView.setAdapter(adapter);
    }
}
