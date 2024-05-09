package com.example.calendar.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.calendar.R;
import com.example.calendar.adapter.MemorandumList;
import com.example.calendar.database.DBHelper;
import com.example.calendar.info.Bean;

import java.util.ArrayList;

public class MemorandumView extends Fragment {

    private  View fragment;
    private ListView listView;

    public MemorandumView() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(fragment == null) {
            fragment = inflater.inflate(R.layout.memorandum_view, container, false);
            listView = fragment.findViewById(R.id.memorandum_list); }

        DBHelper helper = DBHelper.getInstance(this.getContext());
        helper.openRead();
        ArrayList<Bean> beans = helper.queryAll();

        listView.setHeaderDividersEnabled(true);
        listView.setFooterDividersEnabled(true);
        listView.setAdapter(new MemorandumList(listView.getContext(),beans));
        return fragment;
    }


}