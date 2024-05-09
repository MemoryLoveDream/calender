package com.example.calendar.adapter;

import androidx.annotation.NonNull;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ViewTabs implements TabLayoutMediator.TabConfigurationStrategy {

    private static final String[] tabs = {"年","月","周","日","备忘"};

    @Override
    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
        tab.setText(tabs[position]);
    }

}
