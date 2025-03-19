package com.example.zerohunger;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private final List<history_item> historyList;

    public HistoryAdapter(List<history_item> historyList) {
        this.historyList = historyList;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_history_item, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        history_item item = historyList.get(position);

        holder.tvType.setText(item.getType());
        holder.tvDetails.setText(item.getDetails());
        holder.tvQuantity.setText(item.getQuantity());
        holder.tvDate.setText(item.getDate());

        // Edit Button
        holder.btnEdit.setOnClickListener(v -> {
            int pos = holder.getAdapterPosition();
            if (pos != RecyclerView.NO_POSITION) {
                showEditDialog(historyList.get(pos), pos, v.getContext());
            }
        });

        // Delete Button
        holder.btnDelete.setOnClickListener(v -> {
            int pos = holder.getAdapterPosition();
            if (pos != RecyclerView.NO_POSITION) {
                deleteItem(pos);
            }
        });
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    public static class HistoryViewHolder extends RecyclerView.ViewHolder {
        TextView tvType, tvDetails, tvQuantity, tvDate;
        Button btnEdit, btnDelete;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvType = itemView.findViewById(R.id.tv_type);
            tvDetails = itemView.findViewById(R.id.tv_details);
            tvQuantity = itemView.findViewById(R.id.tv_quantity);
            tvDate = itemView.findViewById(R.id.tv_date);
            btnEdit = itemView.findViewById(R.id.btn_edit);
            btnDelete = itemView.findViewById(R.id.btn_delete);
        }
    }

    // DELETE FUNCTION
    private void deleteItem(int position) {
        history_item itemToDelete = historyList.get(position);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("donations")
                .whereEqualTo("foodName", itemToDelete.getDetails().split("\n")[0].replace("Food Name: ", ""))
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    for (DocumentSnapshot document : queryDocumentSnapshots) {
                        db.collection("donations").document(document.getId())
                                .delete()
                                .addOnSuccessListener(aVoid -> {
                                    historyList.remove(position);
                                    notifyItemRemoved(position);
                                });
                    }
                });
    }

    // EDIT FUNCTION
    private void showEditDialog(history_item item, int position, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Edit Donation");

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_edit_item, null);
        builder.setView(view);

        EditText etFoodName = view.findViewById(R.id.et_food_name);
        EditText etFoodItem = view.findViewById(R.id.et_food_item);
        EditText etQuantity = view.findViewById(R.id.et_quantity);

        // Pre-fill data
        String[] details = item.getDetails().split("\n");
        etFoodName.setText(details[0].replace("Food Name: ", ""));
        etFoodItem.setText(details[1].replace("Item: ", ""));
        etQuantity.setText(item.getQuantity().replace("Quantity: ", ""));

        builder.setPositiveButton("Update", (dialog, which) -> {
            String newFoodName = etFoodName.getText().toString();
            String newFoodItem = etFoodItem.getText().toString();
            String newQuantity = etQuantity.getText().toString();

            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("donations")
                    .whereEqualTo("foodName", details[0].replace("Food Name: ", ""))
                    .get()
                    .addOnSuccessListener(queryDocumentSnapshots -> {
                        for (DocumentSnapshot document : queryDocumentSnapshots) {
                            db.collection("donations").document(document.getId())
                                    .update("foodName", newFoodName, "foodItem", newFoodItem, "quantity", newQuantity)
                                    .addOnSuccessListener(aVoid -> {
                                        historyList.set(position, new history_item("Donation", "Food Name: " + newFoodName + "\nItem: " + newFoodItem, "Quantity: " + newQuantity, item.getDate()));
                                        notifyItemChanged(position);
                                    });
                        }
                    });
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        builder.create().show();
    }
}


//package com.example.zerohunger;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.List;
//
//public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
//
//    private final List<history_item> historyList;
//
//    public HistoryAdapter(List<history_item> historyList) {
//        this.historyList = historyList;
//    }
//
//    @NonNull
//    @Override
//    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        // Inflate the layout for each card item
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_history_item, parent, false);
//        return new HistoryViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
//        // Get the current item
//        history_item item = historyList.get(position);
//
//        // Bind the data to the views in the layout
//        holder.tvType.setText(item.getType());
//        holder.tvDetails.setText(item.getDetails());
//        holder.tvQuantity.setText(item.getQuantity());
//        holder.tvDate.setText(item.getDate());
//
//        // Optionally, set listeners for the buttons if needed
//        holder.btnEdit.setOnClickListener(v -> {
//            // Handle the Edit button click
//            // e.g., open a dialog to edit the donation details
//        });
//
//        holder.btnDelete.setOnClickListener(v -> {
//            // Handle the Delete button click
//            // e.g., remove the item from Firestore and update the list
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return historyList.size();
//    }
//
//    public static class HistoryViewHolder extends RecyclerView.ViewHolder {
//        TextView tvType, tvDetails, tvQuantity, tvDate;
//        Button btnEdit, btnDelete;
//
//        public HistoryViewHolder(@NonNull View itemView) {
//            super(itemView);
//            // Initialize the views from the layout
//            tvType = itemView.findViewById(R.id.tv_type);
//            tvDetails = itemView.findViewById(R.id.tv_details);
//            tvQuantity = itemView.findViewById(R.id.tv_quantity);
//            tvDate = itemView.findViewById(R.id.tv_date);
//            btnEdit = itemView.findViewById(R.id.btn_edit);
//            btnDelete = itemView.findViewById(R.id.btn_delete);
//        }
//    }
//}

//package com.example.zerohunger;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.List;
//
//public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
//
//    private final List<history_item> historyList;
//
//    // Constructor
//    public HistoryAdapter(List<history_item> historyList) {
//        this.historyList = historyList;
//    }
//
//    @NonNull
//    @Override
//    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        // Inflate the correct layout for RecyclerView items
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.activity_history_item, parent, false); // Ensure this layout is correct
//        return new HistoryViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
//        // Get the current HistoryItem
//        history_item item = historyList.get(position);
//
//        // Safely bind data to the views
//        if (item != null) {
//            holder.tvType.setText(item.getType() != null ? item.getType() : "N/A");
//            holder.tvDetails.setText(item.getDetails() != null ? item.getDetails() : "N/A");
//            holder.tvQuantity.setText(item.getQuantity() != null ? item.getQuantity() : "N/A");
//            holder.tvDate.setText(item.getDate() != null ? item.getDate() : "N/A");
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        // Return the size of the history list
//        return historyList != null ? historyList.size() : 0;
//    }
//
//    // ViewHolder class
//    static class HistoryViewHolder extends RecyclerView.ViewHolder {
//
//        TextView tvType, tvDetails, tvQuantity, tvDate;
//
//        public HistoryViewHolder(@NonNull View itemView) {
//            super(itemView);
//            // Initialize views
//            tvType = itemView.findViewById(R.id.tv_type);
//            tvDetails = itemView.findViewById(R.id.tv_details);
//            tvQuantity = itemView.findViewById(R.id.tv_quantity);
//            tvDate = itemView.findViewById(R.id.tv_date);
//
//            // Add null checks for safety
//            if (tvType == null || tvDetails == null || tvQuantity == null || tvDate == null) {
//                throw new IllegalStateException("One or more TextView references are null. Check the layout file.");
//            }
//        }
//    }
//}
