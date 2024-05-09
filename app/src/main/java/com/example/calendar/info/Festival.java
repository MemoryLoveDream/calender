package com.example.calendar.info;

import org.joda.time.LocalDate;

public class Festival {

    public static String getSolarHoliday(LocalDate localDate) {
        int solarYear = localDate.getYear();
        int solarMonth = localDate.getMonthOfYear();
        int solarDay = localDate.getDayOfMonth();
        String message = "";
        if(solarMonth == 1 && solarDay == 1) { message = "元旦"; }
        else if(solarMonth == 2 && solarDay == 14) { message = "情人节"; }
        else if(solarMonth == 3 && solarDay == 8) { message = "妇女节"; }
        else if(solarMonth == 3 && solarDay == 12) { message = "植树节"; }
        else if(solarMonth == 3 && solarDay == 15) { message = "消费者"; }
        else if(solarMonth == 4) {
            if (solarDay == 1) { message = "愚人节"; }
            else if (solarDay >= 4 && solarDay <= 6) {
                int compare;
                if (solarYear <= 1999) {
                    compare = (int) (((solarYear - 1900) * 0.2422 + 5.59) - ((solarYear - 1900) / 4)); }
                else {
                    compare = (int) (((solarYear - 2000) * 0.2422 + 4.81) - ((solarYear - 2000) / 4)); }
                if (compare == solarDay) { message = "清明节"; }}}
        else if(solarMonth == 5 && solarDay == 1) { message = "劳动节"; }
        else if(solarMonth == 5 && solarDay == 4) { message = "青年节"; }
        else if(solarMonth == 6 && solarDay == 1) { message = "儿童节"; }
        else if(solarMonth == 7 && solarDay == 1) { message = "建党节"; }
        else if(solarMonth == 8 && solarDay == 1) { message = "建军节"; }
        else if(solarMonth == 9 && solarDay == 10) { message = "教师节"; }
        else if(solarMonth == 10) {
            if(solarDay == 1){ message = "国庆节"; }
            else if(solarDay == 31) { message = "万圣节"; }}
        else if(solarMonth == 12)
            if(solarDay == 24) { message = "平安夜"; }
            else if(solarDay == 25) { message = "圣诞节"; }
        return message;
    }

    public static String getLunarHoliday(LocalDate localDate) {
        LunarDate lunar = LunarDate.getLunar(localDate);
        LunarDate nextLunar = LunarDate.getLunar(localDate.plusDays(1));
        int lunarMonth = lunar.lunarMonth;
        int lunarDay = lunar.lunarDay;
        String message = "";
        if(lunarMonth == 1 && lunarDay == 1) { message = "春节"; }
        else if(lunarMonth == 1 && lunarDay == 15) { message = "元宵节"; }
        else if(lunarMonth == 2 && lunarDay == 2) { message = "龙抬头"; }
        else if(lunarMonth == 5 && lunarDay == 5) { message = "端午节"; }
        else if(lunarMonth == 7 && lunarDay == 7) { message = "七夕"; }
        else if(lunarMonth == 7 && lunarDay == 15) { message = "中元节"; }
        else if (lunarMonth == 8 && lunarDay == 15) { message = "中秋节"; }
        else if (lunarMonth == 9 && lunarDay == 9) { message = "重阳节"; }
        else if (lunarMonth == 12 && lunarDay == 8) { message = "腊八节"; }
        else if (lunarMonth == 12 && lunarDay == 23) { message = "小年"; }
        else if (lunarMonth == 12 && lunar.lunarMonth != nextLunar.lunarMonth) { message = "除夕"; }
        return message;
    }

}
