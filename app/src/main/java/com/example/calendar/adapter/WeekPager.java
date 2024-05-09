package com.example.calendar.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calendar.R;
import com.example.calendar.info.CalendarWeek;
import com.example.calendar.view.WeekView;

import java.util.ArrayList;

public class WeekPager extends RecyclerView.Adapter<WeekPager.ViewHolder> {

    private final WeekView weekView;

    public WeekPager(WeekView weekView){ this.weekView = weekView; }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder
                (LayoutInflater.from(parent.getContext()).inflate(R.layout.week, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder v, int position) {
        ArrayList<CalendarWeek> weeks = weekView.getWeeks();
        int firstWeekday = weekView.getActivity1().getFirstWeekday();

        System.out.println(position);
        System.out.println(weekView.getWeeks().get(position).getWeek());
        System.out.println(weekView.getWeeks().get(position).getWeekData().get(0).localDate.dayOfWeek());


        v.weekBarLayout.setPadding(45,0,0,0);
        v.weekBar.setAdapter(new WeekBar(v.weekBar.getContext(),firstWeekday));
        v.weekBar.setBackgroundColor(v.weekBar.getResources().getColor(R.color.grey1));
        v.text1.setText(weekView.getWeeks().get(position).getWeek());
        v.text1.setTextSize(15);
        v.text1.setGravity(TextView.TEXT_ALIGNMENT_GRAVITY);
        v.text2.setText("周");
        v.text2.setTextSize(15);
        v.text2.setGravity(TextView.TEXT_ALIGNMENT_GRAVITY);
        v.weekGrid.setAdapter(new Week(v.weekGrid.getContext(),weeks.get(position).getWeekData()));
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout weekBarLayout;
        GridView weekBar;
        TextView text1,text2;
        GridView weekGrid;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            weekBarLayout = itemView.findViewById(R.id.week_bar_layout);
            weekBar = itemView.findViewById(R.id.week_bar);
            text1 = itemView.findViewById(R.id.solar_day_big);
            text2 = itemView.findViewById(R.id.lunar_display);
            weekGrid = itemView.findViewById(R.id.week_grid); }
    }

}
