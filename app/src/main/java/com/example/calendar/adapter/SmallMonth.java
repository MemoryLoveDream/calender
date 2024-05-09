package com.example.calendar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.calendar.R;
import com.example.calendar.info.Calendar;

import java.util.ArrayList;

public class SmallMonth extends BaseAdapter {

    private final Context context;
    private final ArrayList<Calendar.SimpleDay2> monthDate;
    private static class ViewHolder{ TextView text; }

    public SmallMonth(Context context, ArrayList<Calendar.SimpleDay2> monthDate) {
        this.context = context;
        this.monthDate = monthDate; }

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
            convertView = LayoutInflater.from(context).inflate(R.layout.one_line,viewGroup,false);
            v = new ViewHolder();
            v.text = convertView.findViewById(R.id.only_context);
            convertView.setTag(v);}
        else { v = (ViewHolder) convertView.getTag(); }

        if(monthDate.get(i).localDate.getMonthOfYear() != monthDate.get(7).localDate.getMonthOfYear()) {
            v.text.setText(""); }
        else v.text.setText(String.valueOf(monthDate.get(i).localDate.getDayOfMonth()));
        if(monthDate.get(i).solarDayColor == Calendar.black) {
            v.text.setTextColor(v.text.getResources().getColor(R.color.black)); }
        else if(monthDate.get(i).solarDayColor == Calendar.blue) {
            v.text.setTextColor(v.text.getResources().getColor(R.color.blue1)); }

        return convertView;
    }

}

