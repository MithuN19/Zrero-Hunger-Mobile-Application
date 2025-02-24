package com.example.zerohunger;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class dashboard extends AppCompatActivity {

    private CardView donatecard;
    private CardView receiveCard;

    private CardView historycard;

    private CardView foodmapCard;

    private CardView aboutusCard;

    private CardView logoutCard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);


        donatecard= findViewById(R.id.donatecard);
        receiveCard= findViewById(R.id.receiveCard);
        historycard= findViewById(R.id.historycard);
        foodmapCard= findViewById(R.id.foodmapCard);
        aboutusCard= findViewById(R.id.aboutusCard);
        logoutCard= findViewById(R.id.logoutCard);

        donatecard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard.this, DonateActivity.class);
                startActivity(intent);
            }
        });

        receiveCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard.this, ReceivedActivity.class);
                startActivity(intent);
            }
        });

        foodmapCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard.this, food_map.class);
                startActivity(intent);
            }
        });

        historycard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard.this, HistoryActivity.class);
                startActivity(intent);
            }
        });


        aboutusCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard.this, aboutus.class);
                startActivity(intent);
            }
        });


        logoutCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard.this, MainActivity.class);
                startActivity(intent);
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}