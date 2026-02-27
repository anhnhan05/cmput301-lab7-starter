package com.example.androiduitesting;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

public class ShowActivity extends AppCompatActivity {

    public static final String EXTRA_CITY_NAME = "extra_city_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        TextView tvCityName = findViewById(R.id.tvCityName);
        Button btnBack = findViewById(R.id.btnBack);

        Intent intent = getIntent();
        String cityName = intent.getStringExtra(EXTRA_CITY_NAME);
        if (cityName == null) cityName = "";

        tvCityName.setText(cityName);

        btnBack.setOnClickListener(v -> finish()); // goes back to MainActivity
    }
}