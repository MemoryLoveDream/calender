package com.example.calendar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.TextView;

import com.example.calendar.R;
import com.example.calendar.info.Bean;

import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MemorandumList extends BaseAdapter {

    private final Context context;
    private final ArrayList<Bean> beans;
    private static class ViewHolder{ TextView tv1,tv2,tv3; }

    public MemorandumList(Context context, ArrayList<Bean> beans) {
        this.context = context;
        this.beans = beans; }

    @Override
    public int getCount() { return beans.size(); }

    @Override
    public Object getItem(int i) { return beans.get(i); }

    @Override
    public long getItemId(int i) { return i; }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder v;
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.three_lines,viewGroup,false);
            v = new ViewHolder();
            v.tv1 = convertView.findViewById(R.id.tv1);
            v.tv2 = convertView.findViewById(R.id.tv2);
            v.tv3 = convertView.findViewById(R.id.tv3);

            convertView.setTag(v);}
        else { v = (ViewHolder) convertView.getTag(); }

        String s1 = "开始时间：" + beans.get(i).startTime;
        String s2 = "结束时间：" + beans.get(i).finishTime;
        String s3 = "内容：" + beans.get(i).text;

        


        v.tv1.setText(s1);
        v.tv1.setTextSize(18);
        v.tv1.setPadding(10,5,0,20);
        v.tv2.setText(s2);
        v.tv2.setTextSize(18);
        v.tv2.setPadding(10,5,0,20);
        v.tv3.setText(s3);
        v.tv3.setTextSize(18);
        v.tv3.setPadding(10,5,0,20);

        return convertView;
    }

}
