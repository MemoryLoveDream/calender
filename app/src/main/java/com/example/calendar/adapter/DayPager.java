package com.example.calendar.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calendar.R;
import com.example.calendar.info.CalendarWeek;
import com.example.calendar.view.DayView;

import java.util.ArrayList;

public class DayPager extends RecyclerView.Adapter<DayPager.ViewHolder> {

    private final DayView dayView;

    public DayPager(DayView dayView){ this.dayView = dayView; }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder
                (LayoutInflater.from(parent.getContext()).inflate(R.layout.day, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder v, int position) {
        ArrayList<CalendarWeek> weeks = dayView.getWeeks();
        int firstWeekday = dayView.getActivity1().getFirstWeekday();

        v.weekBar.setAdapter(new WeekBar(v.weekBar.getContext(),firstWeekday));
        v.weekBar.setBackgroundColor(v.weekBar.getResources().getColor(R.color.grey1));
        v.weekGrid.setAdapter(new Week(v.weekGrid.getContext(),weeks.get(position).getWeekData()));
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        GridView weekBar;
        GridView weekGrid;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            weekBar = itemView.findViewById(R.id.week_bar);
            weekGrid = itemView.findViewById(R.id.week_grid); }
    }

}
