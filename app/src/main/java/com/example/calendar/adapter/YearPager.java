package com.example.calendar.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calendar.R;
import com.example.calendar.info.CalendarYear;
import com.example.calendar.view.MyGridView;
import com.example.calendar.view.YearView;

import java.util.ArrayList;

public class YearPager extends RecyclerView.Adapter<YearPager.ViewHolder> {

    private final YearView yearView;

    public YearPager(YearView yearView) { this.yearView = yearView; }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder
                (LayoutInflater.from(parent.getContext()).inflate(R.layout.year_grid, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder v, int position) {
        ArrayList<CalendarYear> years = yearView.getYears();

        yearView.getUnderline1().setText(years.get(position).getHEZ());
        v.yearGrid.setAdapter(new Year(v.yearGrid.getContext(),years.get(position).getYearData(),yearView));
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        MyGridView yearGrid;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            yearGrid = itemView.findViewById(R.id.year_grid); }
    }

}
