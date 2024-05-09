package com.example.calendar.info;

import org.joda.time.LocalDate;

import java.io.Serializable;

public class Calendar implements Serializable {

    public static final int Monday = 1;
    public static final int Tuesday = 2;
    public static final int Wednesday = 3;
    public static final int Thursday = 4;
    public static final int Friday = 5;
    public static final int Saturday = 6;
    public static final int Sunday = 7;
    public static final int black = 1;
    public static final int blue = 2;
    public static final int red = 3;
    public static final int grey1 = 4;
    public static final int gery2 = 5;

    public static class SimpleDay implements Serializable {
        public LocalDate localDate;
        public int solarDayColor;
        public String lunarDisplay;
        public int lunarDisplayColor; }

    public static class SimpleDay2 implements Serializable {
        public LocalDate localDate;
        public int solarDayColor;
        public int underlineColor; }

}
