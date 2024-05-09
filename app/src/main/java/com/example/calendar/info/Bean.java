package com.example.calendar.info;

public class Bean {
    public String startTime;
    public String finishTime;
    public String text;

    public Bean(){}

    public Bean(String startTime,String finishTime,String text) {
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.text = text;
    }
}
