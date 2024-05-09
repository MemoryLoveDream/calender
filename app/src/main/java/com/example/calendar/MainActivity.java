package com.example.calendar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import com.example.calendar.info.CalendarDay;
import com.example.calendar.info.Calendar;
import com.example.calendar.listener.SearchMemoranda;
import com.example.calendar.listener.ShowMoreFeatures;
import com.example.calendar.listener.ShowViewOptions;
import com.example.calendar.view.DayView;
import com.example.calendar.view.MemorandumView;
import com.example.calendar.view.MonthView;
import com.example.calendar.view.WeekView;
import com.example.calendar.view.YearView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private final LocalDate today = new LocalDate();
    private LocalDate clickedDay = today;
    private int firstWeekday = Calendar.Sunday;

    private YearView yearView;
    private MonthView monthView;
    private WeekView weekView;
    private DayView dayView;
    private MemorandumView memorandumView;
    private Fragment currentView;
    private Fragment newView;

    private TextView headTime;
    private ImageView upDown,search,more;
    private TabLayout viewOptions;

    public void toP() {
        Intent intent = new Intent();
        intent.setClass(this, Monitor.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeVariables();
        initializeControls();
        initializeFragment();
        setListener();
    }

    private void initializeVariables() {}

    private void initializeControls() {
        headTime = findViewById(R.id.date);
        upDown = findViewById(R.id.up_down);
        search = findViewById(R.id.search);
        more = findViewById(R.id.more);
        viewOptions = findViewById(R.id.view_options);
        String headDate = today.getYear() + "年";
        headTime.setText(headDate);
        viewOptions.addTab(viewOptions.newTab().setText("年"));
        viewOptions.addTab(viewOptions.newTab().setText("月"));
        viewOptions.addTab(viewOptions.newTab().setText("周"));
        viewOptions.addTab(viewOptions.newTab().setText("日"));
        viewOptions.addTab(viewOptions.newTab().setText("备忘"));
    }

    private void initializeFragment() {
        yearView = new YearView(MainActivity.this);
        monthView = new MonthView(MainActivity.this);
        weekView = new WeekView(MainActivity.this);
        dayView = new DayView(MainActivity.this);
        memorandumView = new MemorandumView();
        currentView = monthView;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.views,currentView).commit();
        viewOptions.selectTab(viewOptions.getTabAt(1));
    }

    public void changeFragment(boolean create,int i) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch(i) {
            case 0:newView = yearView;break;
            case 1:newView = monthView;break;
            case 2:newView = weekView;break;
            case 3:newView = dayView;break;
            case 4:newView = memorandumView;break; }
        if(!create) {
            if(!newView.isAdded()) {
                fragmentTransaction.hide(currentView).add(R.id.views,newView).commit(); }
            else fragmentTransaction.hide(currentView).show(newView).commit(); }
        else {
            fragmentTransaction.remove(newView);
            switch(i) {
                case 0:yearView = new YearView(MainActivity.this);newView = yearView;break;
                case 1:monthView = new MonthView(MainActivity.this);newView = monthView;break;
                case 2:weekView = new WeekView(MainActivity.this);newView = weekView;break;
                case 3:dayView = new DayView(MainActivity.this);newView = dayView;break;
                case 4:memorandumView = new MemorandumView();newView = memorandumView;break; }
            fragmentTransaction.hide(currentView).add(R.id.views,newView).commit(); }
        currentView = newView;
        String headDate1 = clickedDay.getYear() + "年";
        String headDate2 = headDate1 + clickedDay.getMonthOfYear() + "月";
        if(i == 0) headTime.setText(headDate1);
        else headTime.setText(headDate2);
        viewOptions.selectTab(viewOptions.getTabAt(i));
    }

    private void setListener() {
        headTime.setOnClickListener(new ShowViewOptions(MainActivity.this));
        upDown.setOnClickListener(new ShowViewOptions(MainActivity.this));
        search.setOnClickListener(new SearchMemoranda());
        more.setOnClickListener(new ShowMoreFeatures());
        viewOptions.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override public void onTabSelected(TabLayout.Tab tab) { changeFragment(true,tab.getPosition()); }
            @Override public void onTabUnselected(TabLayout.Tab tab) {}
            @Override public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    public LocalDate getToday() { return today; }

    public LocalDate getClickedDay() { return clickedDay; }

    public int getFirstWeekday() { return firstWeekday; }

    public TextView getHeadTime() { return headTime; }

    public ImageView getUpDown() { return upDown; }

    public TabLayout getViewOptions() { return viewOptions; }

    public void setClickedDay(LocalDate clickedDay) { this.clickedDay = clickedDay; }

    public void setFirstWeekday(int firstWeekday) { this.firstWeekday = firstWeekday; }

    public void toAddMemorandum() {
        Intent intent = new Intent();
        intent.setClass(this,AddActivity.class);
        startActivity(intent);
    }

}