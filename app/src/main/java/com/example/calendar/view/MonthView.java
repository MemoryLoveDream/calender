package com.example.calendar.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.calendar.MainActivity;
import com.example.calendar.R;
import com.example.calendar.info.CalendarMonth;
import com.example.calendar.adapter.MonthPager;

import org.joda.time.LocalDate;

import java.util.ArrayList;

public class MonthView extends Fragment {

    private final MainActivity activity;
    private ArrayList<CalendarMonth> months;
    private View fragment;
    private ViewPager2 monthPager;
    private ImageButton backToday,addMemorandum;

    public MonthView(MainActivity activity) { this.activity = activity; }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        months = CalendarMonth.getSimpleMonths(activity.getClickedDay(),activity.getFirstWeekday());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initializeControls(inflater,container);

        monthPager.setAdapter(new MonthPager(this));
        monthPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                LocalDate localDate = activity.getClickedDay().plusMonths(position-1);
                activity.setClickedDay(localDate);
                activity.getHeadTime().setText(months.get(position).getMonth());
                months = CalendarMonth.getSimpleMonths(localDate,activity.getFirstWeekday());
                monthPager.setAdapter(new MonthPager(MonthView.this));
                monthPager.setCurrentItem(1,false); }
        });
        monthPager.setCurrentItem(1,false);
        backToday.setOnClickListener(view -> jumpToDay(activity.getToday()));
        addMemorandum.setOnClickListener(view -> activity.toAddMemorandum());
        return fragment;
    }

    private void initializeControls(LayoutInflater inflater,ViewGroup container) {
        fragment = inflater.inflate(R.layout.month_view, container, false);
        monthPager = fragment.findViewById(R.id.month_pager);
        backToday = fragment.findViewById(R.id.back_today);
        addMemorandum = fragment.findViewById(R.id.add_memorandum);
    }

    public MainActivity getActivity1() { return activity; }

    public ArrayList<CalendarMonth> getMonths() { return months; }

    public void jumpToDay(LocalDate localDate) {
        activity.setClickedDay(localDate);
        months = CalendarMonth.getSimpleMonths(localDate,activity.getFirstWeekday());
        activity.getHeadTime().setText(months.get(1).getMonth());
        monthPager.setAdapter(new MonthPager(MonthView.this));
        monthPager.setCurrentItem(1,false);
    }

}