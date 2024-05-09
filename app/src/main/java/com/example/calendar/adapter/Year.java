package com.example.calendar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.calendar.R;
import com.example.calendar.info.Calendar;
import com.example.calendar.view.YearView;

import java.util.ArrayList;

public class Year extends BaseAdapter {

    private final YearView yearView;
    private final Context context;
    private final ArrayList<ArrayList<Calendar.SimpleDay2>> yearData;
    private static class ViewHolder{
        LinearLayout layout;
        TextView month;
        GridView weekBar;
        GridView monthGrid; }

    public Year(Context context,ArrayList<ArrayList<Calendar.SimpleDay2>> yearData,YearView yearView) {
        this.context = context;
        this.yearData = yearData;
        this.yearView = yearView; }

    @Override
    public int getCount() { return yearData.size(); }

    @Override
    public Object getItem(int i) { return yearData.get(i); }

    @Override
    public long getItemId(int i) { return i; }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder v;
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.small_month,viewGroup,false);
            v = new ViewHolder();
            v.layout = convertView.findViewById(R.id.small_month);
            v.month = convertView.findViewById(R.id.month_name);
            v.weekBar = convertView.findViewById(R.id.week_bar);
            v.monthGrid = convertView.findViewById(R.id.small_month_grid);
            convertView.setTag(v);}
        else { v = (ViewHolder) convertView.getTag(); }

        String monthName = yearData.get(i).get(7).localDate.getMonthOfYear() + "月";

        v.month.setText(monthName);
        v.weekBar.setAdapter(new WeekBar(v.weekBar.getContext(),yearView.getActivity1().getFirstWeekday()));
        v.weekBar.setBackgroundColor(v.weekBar.getResources().getColor(R.color.white));
        v.monthGrid.setAdapter(new SmallMonth(v.monthGrid.getContext(),yearData.get(i)));
        v.layout.setOnClickListener(view -> {
            yearView.getActivity1().setClickedDay(yearData.get(i).get(7).localDate.withDayOfMonth(1));
            yearView.getActivity1().changeFragment(true,1); });

        return convertView;
    }

}

