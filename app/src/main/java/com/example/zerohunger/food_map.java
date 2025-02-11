package com.example.zerohunger;

import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class food_map extends AppCompatActivity {

    private WebView webView;
    private EditText searchEditText;
    private Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_map); // Ensure this XML corresponds with your layout

        // Initialize WebView
        webView = findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        // Load the interactive map HTML into WebView
        String htmlData = "<!DOCTYPE html>" +
                "<html lang='en'>" +
                "<head>" +
                "    <meta name='viewport' content='width=device-width, initial-scale=1.0'>" +
                "    <meta charset='UTF-8'>" +
                "    <title>Food Map</title>" +
                "    <link rel='stylesheet' href='https://unpkg.com/leaflet@1.9.3/dist/leaflet.css'/>" +
                "    <script src='https://unpkg.com/leaflet@1.9.3/dist/leaflet.js'></script>" +
                "    <script src='https://unpkg.com/leaflet-control-geocoder/dist/Control.Geocoder.js'></script>" +
                "    <style> body, html { margin: 0; padding: 0; height: 100%; } #map { width: 100%; height: 100vh; } </style>" +
                "</head>" +
                "<body>" +
                "    <div id='map'></div>" +
                "    <script>" +
                "        var map = L.map('map').setView([37.7749, -122.4194], 12);" +
                "        L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', { attribution: '&copy; OpenStreetMap contributors' }).addTo(map);" +
                "        var locations = [{ name: 'Food Center 1', lat: 37.7749, lon: -122.4194 }," +
                "                        { name: 'Food Center 2', lat: 37.7849, lon: -122.4094 }," +
                "                        { name: 'Food Center 3', lat: 37.7649, lon: -122.4294 }];" +
                "        locations.forEach(function(location) { L.marker([location.lat, location.lon]).addTo(map).bindPopup('<b>' + location.name + '</b>'); });" +
                "        var geocoder = L.Control.Geocoder.nominatim();" +
                "        // Add the search bar control to the map" +
                "        L.Control.Geocoder({position: 'topright'}).addTo(map);" +
                "        function locateUser() { if (navigator.geolocation) { navigator.geolocation.getCurrentPosition(function(position) { var userLat = position.coords.latitude;" +
                "        var userLon = position.coords.longitude; L.marker([userLat, userLon]).addTo(map).bindPopup('You are here').openPopup(); map.setView([userLat, userLon], 14); }); } }" +
                "        locateUser();" +
                "    </script>" +
                "</body>" +
                "</html>";

        // Load the HTML data into the WebView
        webView.loadDataWithBaseURL("https://unpkg.com", htmlData, "text/html", "UTF-8", null);

        // Initialize Search Bar and Button
        searchEditText = findViewById(R.id.searchEditText);
        searchButton = findViewById(R.id.searchButton);

        // Handle Search Button Click
        searchButton.setOnClickListener(v -> {
            String searchQuery = searchEditText.getText().toString().trim();
            if (!TextUtils.isEmpty(searchQuery)) {
                // Implement your search functionality here (for example, using the query to search locations)
                Toast.makeText(food_map.this, "Searching for: " + searchQuery, Toast.LENGTH_SHORT).show();
                // Optionally, you can reload the map or use the query to focus on a specific location.
            } else {
                Toast.makeText(food_map.this, "Please enter a search query", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
