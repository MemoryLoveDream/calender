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
import com.example.calendar.adapter.WeekPager;
import com.example.calendar.info.CalendarWeek;

import org.joda.time.LocalDate;

import java.util.ArrayList;

public class WeekView extends Fragment {

    private final MainActivity activity;
    private ArrayList<CalendarWeek> weeks;
    private View fragment;
    private ViewPager2 weekPager;
    private ImageButton backToday,addMemorandum;

    public WeekView(MainActivity activity) { this.activity = activity; }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        weeks = CalendarWeek.getSimpleWeeks(activity.getClickedDay(),activity.getFirstWeekday());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initializeControls(inflater,container);

        weekPager.setAdapter(new WeekPager(this));
        weekPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                LocalDate localDate = activity.getClickedDay().plusDays(7*(position-1));
                activity.setClickedDay(localDate);
                activity.getHeadTime().setText(weeks.get(position).getMonth());
                weeks = CalendarWeek.getSimpleWeeks(localDate,activity.getFirstWeekday());
                weekPager.setAdapter(new WeekPager(WeekView.this));
                weekPager.setCurrentItem(1,false); }
        });
        weekPager.setCurrentItem(1,false);
        backToday.setOnClickListener(view -> backToday());
        addMemorandum.setOnClickListener(view -> activity.toAddMemorandum());
        return fragment;
    }

    private void initializeControls(LayoutInflater inflater,ViewGroup container) {
        fragment = inflater.inflate(R.layout.week_view, container, false);
        weekPager = fragment.findViewById(R.id.week_pager);
        backToday = fragment.findViewById(R.id.back_today);
        addMemorandum = fragment.findViewById(R.id.add_memorandum);
    }

    public MainActivity getActivity1() { return activity; }

    public ArrayList<CalendarWeek> getWeeks() { return weeks; }

    public void backToday() {
        activity.setClickedDay(activity.getToday());
        weeks = CalendarWeek.getSimpleWeeks(activity.getToday(),activity.getFirstWeekday());
        activity.getHeadTime().setText(weeks.get(1).getMonth());
        weekPager.setAdapter(new WeekPager(WeekView.this));
        weekPager.setCurrentItem(1,false);
    }

}