package com.example.androiduitesting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // UI
    private ListView cityListView;
    private EditText newName;
    private LinearLayout nameField;

    // Data
    private ArrayAdapter<String> cityAdapter;
    private ArrayList<String> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameField = findViewById(R.id.field_nameEntry);
        newName   = findViewById(R.id.editText_name);
        cityListView = findViewById(R.id.city_list);

        dataList = new ArrayList<>();
        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityListView.setAdapter(cityAdapter);

        // ✅ Click a city -> open ShowActivity and pass the city name
        cityListView.setOnItemClickListener((parent, view, position, id) -> {
            String clickedCity = cityAdapter.getItem(position);
            Intent intent = new Intent(MainActivity.this, ShowActivity.class);
            intent.putExtra(ShowActivity.EXTRA_CITY_NAME, clickedCity);
            startActivity(intent);
        });

        Button addButton = findViewById(R.id.button_add);
        addButton.setOnClickListener(v -> nameField.setVisibility(View.VISIBLE));

        Button confirmButton = findViewById(R.id.button_confirm);
        confirmButton.setOnClickListener(v -> {
            String cityName = newName.getText().toString().trim();
            if (!cityName.isEmpty()) {
                cityAdapter.add(cityName);
            }
            newName.getText().clear();
            nameField.setVisibility(View.INVISIBLE);
        });

        Button deleteButton = findViewById(R.id.button_clear);
        deleteButton.setOnClickListener(v -> cityAdapter.clear());
    }

    // ✅ For Espresso tests: guarantee there is at least one city to click
    public void addCityForTest(String name) {
        if (cityAdapter != null) {
            cityAdapter.add(name);
        }
    }
}