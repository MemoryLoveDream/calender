package com.example.calendar.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calendar.R;
import com.example.calendar.info.CalendarDay;
import com.example.calendar.info.CalendarMonth;
import com.example.calendar.view.MonthView;

import org.joda.time.LocalDate;

import java.util.ArrayList;

public class MonthPager extends RecyclerView.Adapter<MonthPager.ViewHolder> {

    private final MonthView monthView;
    private int i = -1;

    public MonthPager(MonthView monthView){ this.monthView = monthView; }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder
                (LayoutInflater.from(parent.getContext()).inflate(R.layout.big_month, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder v, int position) {
        ArrayList<CalendarMonth> months = monthView.getMonths();
        int firstWeekday = monthView.getActivity1().getFirstWeekday();
        String information = new CalendarDay(monthView.getActivity1().getClickedDay()).getLunarInformation();
        ArrayList<String> specialThings = new CalendarDay(monthView.getActivity1().getClickedDay()).getSpecialThings();

        v.weekBar.setAdapter(new WeekBar(v.weekBar.getContext(),firstWeekday));
        v.weekBar.setBackgroundColor(v.weekBar.getResources().getColor(R.color.grey1));
        v.monthGrid.setAdapter(
                new BigMonth(
                        v.monthGrid.getContext(),months.get(position).getMonthData(),v.lunarInformation,v.specialDay,monthView));
        v.monthGrid.setOnItemClickListener((parent, view, position1, id) -> {
            LinearLayout layout = view.findViewById(R.id.two_lines_layout);
            layout.setBackgroundResource(R.drawable.day_selected);
            if(i != position1 && i != -1) {
                LinearLayout lastLayout = parent.getChildAt(position1).findViewById(R.id.two_lines_layout);
                lastLayout.setBackgroundResource(R.drawable.day_cancelled); }
            i = position1;
        });
        v.lunarInformation.setText(information);
        v.specialDay.setAdapter(new SpecialDay(v.specialDay.getContext(),specialThings));

    }

    @Override
    public int getItemCount() { return 3; }

    static class ViewHolder extends RecyclerView.ViewHolder {
        GridView weekBar;
        GridView monthGrid;
        TextView lunarInformation;
        ListView specialDay;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            weekBar = itemView.findViewById(R.id.week_bar);
            monthGrid = itemView.findViewById(R.id.big_month_grid);
            lunarInformation = itemView.findViewById(R.id.day_lunar);
            specialDay = itemView.findViewById(R.id.day_special); }
    }

}
