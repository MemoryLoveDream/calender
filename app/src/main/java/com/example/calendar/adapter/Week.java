package com.example.calendar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.calendar.R;
import com.example.calendar.info.Calendar;
import com.example.calendar.info.CalendarDay;

import java.util.ArrayList;

public class Week extends BaseAdapter {


    private final Context context;
    private final ArrayList<Calendar.SimpleDay> monthDate;
    private static class ViewHolder{ LinearLayout layout; TextView tv1,tv2; }

    public Week(Context context, ArrayList<Calendar.SimpleDay> monthDate) {
        this.context = context;
        this.monthDate = monthDate;}

    @Override
    public int getCount() { return monthDate.size(); }

    @Override
    public Object getItem(int i) { return monthDate.get(i); }

    @Override
    public long getItemId(int i) { return i; }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder v;
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.two_lines,viewGroup,false);
            v = new ViewHolder();
            v.layout = convertView.findViewById(R.id.two_lines_layout);
            v.tv1 = convertView.findViewById(R.id.solar_day_big);
            v.tv2 = convertView.findViewById(R.id.lunar_display);
            convertView.setTag(v);}
        else { v = (ViewHolder) convertView.getTag(); }
        v.tv1.setText(String.valueOf(monthDate.get(i).localDate.getDayOfMonth()));
        v.tv2.setText(monthDate.get(i).lunarDisplay);
        v.tv1.setTextColor(v.tv1.getResources().getColor(R.color.black));
        if(monthDate.get(i).lunarDisplayColor == Calendar.blue) {
            v.tv2.setTextColor(v.tv2.getResources().getColor(R.color.blue1)); }
        else if(monthDate.get(i).lunarDisplayColor == Calendar.grey1) {
            v.tv2.setTextColor(v.tv2.getResources().getColor(R.color.grey3)); }

        return convertView;
    }

}
