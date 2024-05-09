package com.example.calendar.info;

import static com.example.calendar.info.CalendarMonth.getSimpleMonth;

import org.joda.time.LocalDate;

import java.io.Serializable;
import java.util.ArrayList;

public class CalendarWeek implements Serializable {

    private final String week;
    private final String month;
    private final ArrayList<Calendar.SimpleDay> weekData;

    public CalendarWeek(CalendarDay calendarDay,int firstWeekday) {
        week = getWeekOfYear(calendarDay.getLocalDate(),firstWeekday) + "";
        month = calendarDay.getSolarYear() + "年" + calendarDay.getSolarMonth() + "月";
        weekData = getSimpleWeek(calendarDay.getLocalDate(),firstWeekday); }

    public CalendarWeek(LocalDate localDate,int firstWeekday) { this(new CalendarDay(localDate),firstWeekday); }

    private static int getWeekOfYear(LocalDate currentDate,int firstWeekday) {
        int firstDayWeekday = currentDate.withDayOfMonth(1).getDayOfWeek();
        int lastYearDays;
        if(firstDayWeekday < firstWeekday) lastYearDays = firstDayWeekday + 7 - firstWeekday;
        else lastYearDays = firstDayWeekday - firstWeekday;
        int week = (lastYearDays + currentDate.getDayOfYear() + 6) / 7;
        if(currentDate.plusDays(lastYearDays).getYear() != currentDate.getYear()) week = 1;
        return week;
    }

    private static ArrayList<Calendar.SimpleDay> getSimpleWeek(LocalDate currentDate,int firstWeekday) {
        ArrayList<Calendar.SimpleDay> simpleWeek = new ArrayList<>();
        ArrayList<Calendar.SimpleDay> simpleMonth = getSimpleMonth(currentDate,firstWeekday);
        int firstDayWeekday = currentDate.withDayOfMonth(1).getDayOfWeek();
        int lastMonthDays;
        if(firstDayWeekday < firstWeekday) lastMonthDays = firstDayWeekday + 7 - firstWeekday;
        else lastMonthDays = firstDayWeekday - firstWeekday;
        int line = (lastMonthDays + currentDate.getDayOfMonth() + 6) / 7;
        for(int i=7*(line-1); i<7*line; i++) { simpleWeek.add(simpleMonth.get(i)); }
        return simpleWeek;
    }

    public static ArrayList<CalendarWeek> getSimpleWeeks(LocalDate localDate, int firstWeekday) {
        ArrayList<CalendarWeek> simpleWeeks = new ArrayList<>();
        simpleWeeks.add(new CalendarWeek(localDate.plusDays(-7),firstWeekday));
        simpleWeeks.add(new CalendarWeek(localDate,firstWeekday));
        simpleWeeks.add(new CalendarWeek(localDate.plusDays(7),firstWeekday));
        return simpleWeeks;
    }

    public String getWeek() { return week; }

    public String getMonth() { return month; }

    public ArrayList<Calendar.SimpleDay> getWeekData() { return weekData; }

}
