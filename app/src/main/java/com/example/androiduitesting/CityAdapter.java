package com.example.androiduitesting;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> {

    private final List<City> cities;

    public CityAdapter(List<City> cities) {
        this.cities = cities;
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_city, parent, false);
        return new CityViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder, int position) {
        City city = cities.get(position);
        holder.tvRowCityName.setText(city.getCityName());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), ShowActivity.class);
            intent.putExtra(ShowActivity.EXTRA_CITY_NAME, city.getCityName());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    static class CityViewHolder extends RecyclerView.ViewHolder {
        TextView tvRowCityName;

        CityViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRowCityName = itemView.findViewById(R.id.tvRowCityName);
        }
    }
}
