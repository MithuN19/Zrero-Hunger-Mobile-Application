package com.example.zerohunger;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private final List<history_item> historyList;

    // Constructor
    public HistoryAdapter(List<history_item> historyList) {
        this.historyList = historyList;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the correct layout for RecyclerView items
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_history_item, parent, false); // Ensure this layout is correct
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        // Get the current HistoryItem
        history_item item = historyList.get(position);

        // Safely bind data to the views
        if (item != null) {
            holder.tvType.setText(item.getType() != null ? item.getType() : "N/A");
            holder.tvDetails.setText(item.getDetails() != null ? item.getDetails() : "N/A");
            holder.tvQuantity.setText(item.getQuantity() != null ? item.getQuantity() : "N/A");
            holder.tvDate.setText(item.getDate() != null ? item.getDate() : "N/A");
        }
    }

    @Override
    public int getItemCount() {
        // Return the size of the history list
        return historyList != null ? historyList.size() : 0;
    }

    // ViewHolder class
    static class HistoryViewHolder extends RecyclerView.ViewHolder {

        TextView tvType, tvDetails, tvQuantity, tvDate;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize views
            tvType = itemView.findViewById(R.id.tv_type);
            tvDetails = itemView.findViewById(R.id.tv_details);
            tvQuantity = itemView.findViewById(R.id.tv_quantity);
            tvDate = itemView.findViewById(R.id.tv_date);

            // Add null checks for safety
            if (tvType == null || tvDetails == null || tvQuantity == null || tvDate == null) {
                throw new IllegalStateException("One or more TextView references are null. Check the layout file.");
            }
        }
    }
}
