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

public class SpecialDay extends BaseAdapter {

    private final Context context;
    private final ArrayList<String> specialThings;
    private static class ViewHolder{ TextView text; }

    public SpecialDay(Context context, ArrayList<String> specialThings) {
        this.context = context;
        this.specialThings = specialThings; }

    @Override
    public int getCount() { return specialThings.size(); }

    @Override
    public Object getItem(int i) { return specialThings.get(i); }

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

        v.text.setText(specialThings.get(i));
        v.text.setTextSize(20);
        v.text.setGravity(View.TEXT_ALIGNMENT_VIEW_END);
        v.text.setPadding(10,20,0,20);
        v.text.setBackgroundResource(R.drawable.background_white);

        return convertView;
    }

}
