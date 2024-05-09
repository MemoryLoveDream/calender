package com.example.calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;

public class Monitor extends AppCompatActivity {

    private MyObserver myObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor);

        myObserver=new MyObserver(new Handler());
        Uri uri=Uri.parse("content://com.example.calculator/phonelist");
        getContentResolver().registerContentObserver(uri,true,myObserver);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getContentResolver().unregisterContentObserver(myObserver);

    }


}