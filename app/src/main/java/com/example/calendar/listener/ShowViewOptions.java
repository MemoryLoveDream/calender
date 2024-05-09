package com.example.calendar.listener;

import android.view.View;
import android.widget.ImageView;

import com.example.calendar.MainActivity;
import com.example.calendar.R;
import com.google.android.material.tabs.TabLayout;

public class ShowViewOptions implements View.OnClickListener {

    private final ImageView upDown;
    private final TabLayout viewOptions;
    private boolean Visibility;

    public ShowViewOptions(MainActivity activity) {
        Visibility = true;
        upDown = activity.getUpDown();
        viewOptions = activity.getViewOptions(); }

    @Override
    public void onClick(View view) {
        if(!Visibility) {
            Visibility = true;
            upDown.setImageResource(R.mipmap.down);
            viewOptions.setVisibility(View.VISIBLE); }
        else {
            Visibility = false;
            upDown.setImageResource(R.mipmap.up);
            viewOptions.setVisibility(View.GONE); }
    }

}
