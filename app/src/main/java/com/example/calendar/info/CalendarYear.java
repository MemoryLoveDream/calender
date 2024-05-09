package com.example.calendar.info;

import org.joda.time.LocalDate;

import java.io.Serializable;
import java.util.ArrayList;

public class CalendarYear implements Serializable {

    private final String year;
    private final String HEZ;
    private final ArrayList<ArrayList<Calendar.SimpleDay2>> yearData;

    public CalendarYear(CalendarDay calendarDay,int firstWeekday) {
        year = calendarDay.getSolarYear() + "年";
        HEZ = LunarDate.getHEZ(calendarDay.getLunarYear());
        yearData = getSimpleYear(calendarDay.getLocalDate(),firstWeekday); }

    public CalendarYear(LocalDate localDate,int firstWeekday) { this(new CalendarDay(localDate),firstWeekday); }

    private static ArrayList<Calendar.SimpleDay2> getSimpleMonth2(LocalDate localDate,int firstWeekday) {
        ArrayList<Calendar.SimpleDay2> simpleMonth = new ArrayList<>();
        ArrayList<CalendarDay> displayedMonth = CalendarMonth.getDetailedMonth(localDate,firstWeekday);
        for(int i = 0; i < displayedMonth.size(); i ++) {
            Calendar.SimpleDay2 simpleDay = new Calendar.SimpleDay2();
            simpleDay.localDate = displayedMonth.get(i).getLocalDate();
            if(displayedMonth.get(i).getSolarFestival().equals("") &&
                    displayedMonth.get(i).getLunarFestival().equals("")) {
                simpleDay.solarDayColor = Calendar.black; }
            else simpleDay.solarDayColor = Calendar.blue;
            if(displayedMonth.get(i).getLunarDay() == 1) {
                simpleDay.underlineColor = Calendar.blue; }
            simpleMonth.add(simpleDay); }
        return simpleMonth;
    }

    private static ArrayList<ArrayList<Calendar.SimpleDay2>> getSimpleYear(LocalDate localDate,int firstWeekday) {
        ArrayList<ArrayList<Calendar.SimpleDay2>> simpleYear = new ArrayList<>();
        for(int i=1; i<=12; i++) {
            simpleYear.add(getSimpleMonth2(localDate.withMonthOfYear(i),firstWeekday)); }
        return simpleYear;
    }

    public static ArrayList<CalendarYear> getSimpleYears(LocalDate localDate, int firstWeekday) {
        ArrayList<CalendarYear> simpleYears = new ArrayList<>();
        simpleYears.add(new CalendarYear(localDate.plusYears(-1),firstWeekday));
        simpleYears.add(new CalendarYear(localDate,firstWeekday));
        simpleYears.add(new CalendarYear(localDate.plusYears(1),firstWeekday));
        return simpleYears;
    }

    public String getYear() { return year; }

    public String getHEZ() { return HEZ; }

    public ArrayList<ArrayList<Calendar.SimpleDay2>> getYearData() { return yearData; }

}
