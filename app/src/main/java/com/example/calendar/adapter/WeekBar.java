package com.example.calendar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.calendar.R;

public class WeekBar extends BaseAdapter {

    private static final String[] Week = {"日","一","二","三","四","五","六"};
    private final Context context;
    private final int firstWeekday;
    public static class ViewHolder{ TextView text; }

    public WeekBar(Context context,int firstWeekday) {
        this.context = context;
        this.firstWeekday = firstWeekday; }

    @Override
    public int getCount() { return 7; }

    @Override
    public Object getItem(int i) { return null; }

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

            v.text.setText(Week[(firstWeekday+i)%7]);
            v.text.setTextColor(v.text.getResources().getColor(R.color.grey2));
            v.text.setGravity(TextView.TEXT_ALIGNMENT_GRAVITY);

        return convertView;
    }

}

