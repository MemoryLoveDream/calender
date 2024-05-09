package com.example.calendar;

import android.database.ContentObserver;
import android.os.Handler;
import android.util.Log;

public class MyObserver extends ContentObserver {
    public MyObserver(Handler handler) {
        super(handler);
    }

    @Override
    public void onChange(boolean selfChange){
        Log.i("监测数据变化","有人动了你的数据库");
        super.onChange(selfChange);
    }

}
