package com.example.calendar.view;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.calendar.MainActivity;
import com.example.calendar.R;
import com.example.calendar.adapter.YearPager;
import com.example.calendar.info.CalendarYear;

import org.joda.time.LocalDate;

import java.util.ArrayList;

public class YearView extends Fragment {

    private final MainActivity activity;
    private ArrayList<CalendarYear> years;
    private View fragment;
    private TextView underline1,underline2;
    private ViewPager2 yearPager;

    public YearView(MainActivity activity) { this.activity = activity; }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        years = CalendarYear.getSimpleYears(activity.getClickedDay(),activity.getFirstWeekday());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initializeControls(inflater,container);

        underline1.setTextSize(10);
        underline2.setTextSize(10);
        yearPager.setAdapter(new YearPager(this));
        yearPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                LocalDate localDate = activity.getClickedDay().plusYears(position-1);
                activity.setClickedDay(localDate);
                activity.getHeadTime().setText(years.get(position).getYear());
                underline1.setText(years.get(position).getHEZ());
                years = CalendarYear.getSimpleYears(localDate,activity.getFirstWeekday());
                yearPager.setAdapter(new YearPager(YearView.this));
                yearPager.setCurrentItem(1,false); }
        });
        yearPager.setCurrentItem(1,false);

        return fragment;
    }

    private void initializeControls(LayoutInflater inflater,ViewGroup container) {
        fragment = inflater.inflate(R.layout.year_view, container, false);
        underline1 = fragment.findViewById(R.id.hez);
        underline2 = fragment.findViewById(R.id.lunar_month_first_day);
        yearPager = fragment.findViewById(R.id.year_pager);
    }

    public MainActivity getActivity1() { return activity; }

    public TextView getUnderline1() { return underline1; }

    public ArrayList<CalendarYear> getYears() { return years; }

}