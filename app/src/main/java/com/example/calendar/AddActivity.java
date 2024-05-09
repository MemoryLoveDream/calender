package com.example.calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.calendar.database.DBHelper;
import com.example.calendar.info.Bean;

public class AddActivity extends AppCompatActivity {

    private ImageView close;
    private ImageView check;
    private Button b11,b12,b21,b22;
    private TextView e1;
    private TextView e2;
    private EditText e3;
    private DatePicker dp1,dp2;
    private TimePicker tp1,tp2;
    private Bean bean;
    private DBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        close = findViewById(R.id.close);
        check = findViewById(R.id.check);
        b11 = findViewById(R.id.set_time1);
        b12 = findViewById(R.id.ok1);
        b21 = findViewById(R.id.set_time2);
        b22 = findViewById(R.id.ok2);
        e1 = findViewById(R.id.start_time);
        e2 = findViewById(R.id.finish_time);
        e3 = findViewById(R.id.text2);
        dp1 = findViewById(R.id.date_picker1);
        dp2 = findViewById(R.id.date_picker2);
        tp1 = findViewById(R.id.time_picker1);
        tp2 = findViewById(R.id.time_picker2);

        
        close.setOnClickListener(view -> finish());
        check.setOnClickListener(view -> {
            bean = new Bean(e1.getText().toString(),e2.getText().toString(),e3.getText().toString());
            if(!bean.startTime.equals("")&&!bean.finishTime.equals("")&&!bean.text.equals("")) {
                if(helper.insert(bean) > 0)
                    Toast.makeText(this,"成功",Toast.LENGTH_SHORT).show();
                finish(); }
            else Toast.makeText(this,"有空没填",Toast.LENGTH_SHORT).show();
        });
        b11.setOnClickListener(view -> {
            dp1.setVisibility(View.VISIBLE);
            tp1.setVisibility(View.VISIBLE);
            b12.setVisibility(View.VISIBLE);
        });
        b12.setOnClickListener(view -> {
            String s1 = dp1.getYear() + "年" + (dp1.getMonth()+1) + "月" + dp1.getDayOfMonth() + "日";
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                s1 += tp1.getHour() + "时" + tp1.getMinute() + "分"; }
            e1.setText(s1);
            dp1.setVisibility(View.GONE);
            tp1.setVisibility(View.GONE);
            b12.setVisibility(View.GONE);
        });
        b21.setOnClickListener(view -> {
            dp2.setVisibility(View.VISIBLE);
            tp2.setVisibility(View.VISIBLE);
            b22.setVisibility(View.VISIBLE);
        });
        b22.setOnClickListener(view -> {
            String s1 = dp2.getYear() + "年" + (dp2.getMonth()+1) + "月" + dp2.getDayOfMonth() + "日";
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                s1 += tp2.getHour() + "时" + tp2.getMinute() + "分"; }
            e2.setText(s1);
            dp2.setVisibility(View.GONE);
            tp2.setVisibility(View.GONE);
            b22.setVisibility(View.GONE);
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        helper = DBHelper.getInstance(this);
        helper.openRead();
        helper.openWrite();
    }

    @Override
    protected void onStop() {
        super.onStop();
        helper.closeLink();
    }


}