package com.example.zerohunger;

public class history_item {
    private final String type;
    private final String details;
    private final String quantity;
    private final String date;


    public history_item(String type, String details, String quantity, String date) {
        this.type = type;
        this.details = details;
        this.quantity = quantity;
        this.date = date;
    }

    public String getType() {
        return type;
    }
    public String getDetails() {
        return details;
    }
    public String getQuantity() {
        return quantity;
    }

    public String getDate() {
        return date;
    }
}