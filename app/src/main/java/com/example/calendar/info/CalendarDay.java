package com.example.calendar.info;

import org.joda.time.LocalDate;

import java.io.Serializable;
import java.util.ArrayList;

public class CalendarDay implements Serializable {

    public LocalDate localDate; //公历日期
    public LunarDate lunarDate; //农历日期
    public String solarFestival; //阳历节日
    public String lunarFestival; //农历节日
    public String solarTerm; //节气

    public CalendarDay(LocalDate localDate) {
        this.localDate = localDate;
        lunarDate = LunarDate.getLunar(this.localDate);
        solarFestival = Festival.getSolarHoliday(this.localDate);
        lunarFestival = Festival.getLunarHoliday(this.localDate);
        solarTerm = SolarTerm.getSolarTerm(this.localDate); }

    public LocalDate getLocalDate() { return localDate; }

    public int getSolarDay() { return localDate.getDayOfMonth(); }

    public int getWeekDay() { return localDate.getDayOfWeek(); }

    public int getSolarMonth() { return localDate.getMonthOfYear(); }

    public int getSolarYear() { return localDate.getYear(); }

    public int getLunarYear() { return lunarDate.getLunarYear(); }

    public int getLunarDay() { return lunarDate.getLunarDay(); }

    public String getLunarDisplay() { return lunarDate.getLunarDisplay(); }

    public String getSolarFestival() { return solarFestival; }

    public String getLunarFestival() { return lunarFestival; }

    public String getSolarTerm() { return solarTerm; }

    public String getLunarInformation() { return lunarDate.getLunarInformation(); }

    public ArrayList<String> getSpecialThings() {
        ArrayList<String> specialThings = new ArrayList<>();
        if(!solarFestival.equals(""))specialThings.add(solarFestival);
        if(!lunarFestival.equals(""))specialThings.add(lunarFestival);
        if(!solarTerm.equals(""))specialThings.add(solarTerm);
        return specialThings;
    }

    public String getSolarDate() {
        return localDate.getYear() + "年" + localDate.getMonthOfYear() + "月" + localDate.getDayOfMonth() + "日";
    }

}
