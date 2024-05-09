package com.example.calendar.info;

import org.joda.time.LocalDate;

import java.io.Serializable;
import java.util.ArrayList;

public class CalendarMonth implements Serializable {

    private final String month;
    private final ArrayList<Calendar.SimpleDay> monthData;

    public CalendarMonth(CalendarDay calendarDay,int firstWeekday) {
        month = calendarDay.getSolarYear() + "年" + calendarDay.getSolarMonth() + "月";
        monthData = getSimpleMonth(calendarDay.getLocalDate(),firstWeekday); }

    public CalendarMonth(LocalDate localDate,int firstWeekday) { this(new CalendarDay(localDate),firstWeekday); }

    private static ArrayList<CalendarDay> getMonthDate(LocalDate localDate) {
        ArrayList<CalendarDay> monthData = new ArrayList<>();
        for(int i=1; i<=localDate.dayOfMonth().getMaximumValue(); i++) {
            CalendarDay calendarDay = new CalendarDay(localDate.withDayOfMonth(i));
            monthData.add(calendarDay); }
        return monthData;
    }

    public static ArrayList<CalendarDay> getDetailedMonth(LocalDate currentDate,int firstWeekday) {
        ArrayList<CalendarDay> detailedMonth = new ArrayList<>();
        ArrayList<CalendarDay> currentMonth = getMonthDate(currentDate);
        ArrayList<CalendarDay> lastMonth = getMonthDate(currentDate.plusMonths(-1));
        ArrayList<CalendarDay> nextMonth = getMonthDate(currentDate.plusMonths(1));
        int firstDayWeekday = currentMonth.get(0).getWeekDay();
        int lastDayWeekday = currentMonth.get(currentMonth.size()-1).getWeekDay();
        int lastMonthDays,nextMonthDays;
        if(firstDayWeekday < firstWeekday) lastMonthDays = firstDayWeekday + 7 - firstWeekday;
        else lastMonthDays = firstDayWeekday - firstWeekday;
        if(lastDayWeekday < firstWeekday) nextMonthDays = firstWeekday - lastDayWeekday - 1;
        else nextMonthDays = firstWeekday + 6 - lastDayWeekday;
        for(int i=lastMonth.size()-lastMonthDays; i<lastMonth.size(); i++) {
            detailedMonth.add(lastMonth.get(i)); }
        detailedMonth.addAll(currentMonth);
        for(int i=0; i<nextMonthDays; i++) {
            detailedMonth.add(nextMonth.get(i)); }
        return detailedMonth;
    }

    public static ArrayList<Calendar.SimpleDay> getSimpleMonth(LocalDate currentDate,int firstWeekday) {
        ArrayList<Calendar.SimpleDay> simpleMonth = new ArrayList<>();
        ArrayList<CalendarDay> displayedMonth = getDetailedMonth(currentDate,firstWeekday);
        LocalDate today = new LocalDate();
        int currentMonth1 = displayedMonth.get(7).getSolarMonth();
        for(int i=0; i<displayedMonth.size(); i++) {
            Calendar.SimpleDay simpleDay = new Calendar.SimpleDay();
            CalendarDay calendarDay = displayedMonth.get(i);
            simpleDay.localDate = calendarDay.getLocalDate();
            if(!calendarDay.getLunarFestival().equals("")) {
                simpleDay.lunarDisplay = calendarDay.getLunarFestival(); }
            else if(!calendarDay.getSolarFestival().equals("")) {
                simpleDay.lunarDisplay = calendarDay.getSolarFestival(); }
            else if(!calendarDay.getSolarTerm().equals("")) {
                simpleDay.lunarDisplay = calendarDay.getSolarTerm(); }
            else simpleDay.lunarDisplay = calendarDay.getLunarDisplay();
            if(simpleDay.localDate.equals(today)) { simpleDay.solarDayColor = Calendar.blue; }
            else if(calendarDay.getSolarMonth() == currentMonth1) {
                simpleDay.solarDayColor = Calendar.black; }
            else simpleDay.solarDayColor = Calendar.grey1;
            if(calendarDay.getSolarFestival().equals("") && calendarDay.getLunarFestival().equals("") &&
                    calendarDay.getSolarTerm().equals("")) {
                    simpleDay.lunarDisplayColor = Calendar.black; }
            else simpleDay.lunarDisplayColor = Calendar.blue;
            simpleMonth.add(simpleDay); }
        return simpleMonth;
    }

    public static ArrayList<CalendarMonth> getSimpleMonths(LocalDate localDate, int firstWeekday) {
        ArrayList<CalendarMonth> simpleMonths = new ArrayList<>();
        simpleMonths.add(new CalendarMonth(localDate.plusMonths(-1),firstWeekday));
        simpleMonths.add(new CalendarMonth(localDate,firstWeekday));
        simpleMonths.add(new CalendarMonth(localDate.plusMonths(1),firstWeekday));
        return simpleMonths;
    }

    public String getMonth() { return month; }

    public ArrayList<Calendar.SimpleDay> getMonthData() { return monthData; }

}
