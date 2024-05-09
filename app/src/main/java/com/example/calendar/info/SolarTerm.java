package com.example.calendar.info;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SolarTerm {

    private static final double D = 0.2422;
    private final static Map<String, Integer[]> INCREASE_OFFSETMAP = new HashMap<>(); // +1偏移
    private final static Map<String, Integer[]> DECREASE_OFFSETMAP = new HashMap<>(); // -1偏移
    private enum SolarTermsEnum {
        LICHUN, YUSHUI, JINGZHE, CHUNFEN, QINGMING, GUYU, //立春 雨水 惊蛰 春分 清明 谷雨
        LIXIA, XIAOMAN, MANGZHONG, XIAZHI, XIAOSHU, DASHU, //立夏 小满 芒种 夏至 小暑 大暑
        LIQIU, CHUSHU, BAILU, QIUFEN, HANLU, SHUANGJIANG, //立秋 处暑 白露 秋分 寒露 霜降
        LIDONG, XIAOXUE, DAXUE, DONGZHI, XIAOHAN, DAHAN } // 立冬 小雪 大雪 冬至 小寒 大寒

    static {
        DECREASE_OFFSETMAP.put(SolarTermsEnum.YUSHUI.name(), new Integer[]{2026}); // 雨水
        INCREASE_OFFSETMAP.put(SolarTermsEnum.CHUNFEN.name(), new Integer[]{2084}); // 春分
        INCREASE_OFFSETMAP.put(SolarTermsEnum.XIAOMAN.name(), new Integer[]{2008}); // 小满
        INCREASE_OFFSETMAP.put(SolarTermsEnum.MANGZHONG.name(), new Integer[]{1902}); // 芒种
        INCREASE_OFFSETMAP.put(SolarTermsEnum.XIAZHI.name(), new Integer[]{1928}); // 夏至
        INCREASE_OFFSETMAP.put(SolarTermsEnum.XIAOSHU.name(), new Integer[]{1925, 2016}); // 小暑
        INCREASE_OFFSETMAP.put(SolarTermsEnum.DASHU.name(), new Integer[]{1922}); // 大暑
        INCREASE_OFFSETMAP.put(SolarTermsEnum.LIQIU.name(), new Integer[]{2002}); // 立秋
        INCREASE_OFFSETMAP.put(SolarTermsEnum.BAILU.name(), new Integer[]{1927}); // 白露
        INCREASE_OFFSETMAP.put(SolarTermsEnum.QIUFEN.name(), new Integer[]{1942}); // 秋分
        INCREASE_OFFSETMAP.put(SolarTermsEnum.SHUANGJIANG.name(), new Integer[]{2089}); // 霜降
        INCREASE_OFFSETMAP.put(SolarTermsEnum.LIDONG.name(), new Integer[]{2089}); // 立冬
        INCREASE_OFFSETMAP.put(SolarTermsEnum.XIAOXUE.name(), new Integer[]{1978}); // 小雪
        INCREASE_OFFSETMAP.put(SolarTermsEnum.DAXUE.name(), new Integer[]{1954}); // 大雪
        DECREASE_OFFSETMAP.put(SolarTermsEnum.DONGZHI.name(), new Integer[]{1918, 2021}); // 冬至
        INCREASE_OFFSETMAP.put(SolarTermsEnum.XIAOHAN.name(), new Integer[]{1982}); // 小寒
        DECREASE_OFFSETMAP.put(SolarTermsEnum.XIAOHAN.name(), new Integer[]{2019}); // 小寒
        INCREASE_OFFSETMAP.put(SolarTermsEnum.DAHAN.name(), new Integer[]{2082}); } // 大寒

    //第一维数组存储的是20世纪的节气C值，第二维数组存储的是21世纪的节气C值,0到23个，依次代表立春、雨水...大寒节气的C值
    private static final double[][] CENTURY_ARRAY = {
            {4.6295, 19.4599, 6.3826, 21.4155, 5.59, 20.888, 6.318, 21.86,
                    6.5, 22.2, 7.928, 23.65, 8.35, 23.95, 8.44, 23.822, 9.098,
                    24.218, 8.218, 23.08, 7.9, 22.6, 6.11, 20.84},
            {3.87, 18.73, 5.63, 20.646, 4.81, 20.1, 5.52, 21.04, 5.678, 21.37,
                    7.108, 22.83, 7.5, 23.13, 7.646, 23.042, 8.318, 23.438,
                    7.438, 22.36, 7.18, 21.94, 5.4055, 20.12}};

    public static int getSolarTermNum(int year, String name) { //返回节气是相应月份的第几天
        double centuryValue; // 节气的世纪值，每个节气的每个世纪值都不同
        name = name.trim().toUpperCase();
        int ordinal = SolarTermsEnum.valueOf(name).ordinal();
        int centuryIndex;
        if (year >= 1901 && year <= 2000) { centuryIndex = 0; } // 20世纪
        else if(year >= 2001 && year <= 2100) { centuryIndex = 1; } // 21世纪
        else {
            throw new RuntimeException("不支持此年份：" + year + "，目前只支持1901年到2100年的时间范围"); }
        centuryValue = CENTURY_ARRAY[centuryIndex][ordinal];
        int dateNum; //寿星通用公式num =[Y*D+C]-L,年数的后2位乘0.2422加C(即：centuryValue)取整数后，减闰年数
        int y = year % 100; // 步骤1:取年分的后两位数
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) { // 闰年
            if (ordinal == SolarTermsEnum.XIAOHAN.ordinal()
                    || ordinal == SolarTermsEnum.DAHAN.ordinal()
                    || ordinal == SolarTermsEnum.LICHUN.ordinal()
                    || ordinal == SolarTermsEnum.YUSHUI.ordinal()) {
                y = y - 1; } // 步骤2
        } // 凡闰年3月1日前闰年数要减一，即：L=[(Y-1)/4],因为小寒、大寒、立春、雨水这两个节气都小于3月1日
        dateNum = (int) (y * D + centuryValue) - (int) (y / 4); // 步骤3，使用公式[Y*D+C]-L计算
        dateNum += specialYearOffset(year, name); // 步骤4，加上特殊的年分的节气偏移量
        return dateNum;
    }

    private static int specialYearOffset(int year, String name) {
        int offset = 0; // 特殊年分的节气偏移量,由于公式不完善，所以算出的个别节气的第几天数并不准确，在此返回其偏移量
        offset += getOffset(DECREASE_OFFSETMAP, year, name, -1);
        offset += getOffset(INCREASE_OFFSETMAP, year, name, 1);
        return offset;
    }

    private static int getOffset(Map<String, Integer[]> map, int year, String name, int offset) {
        int off = 0;
        Integer[] years = map.get(name);
        if (null != years) {
            for (int i : years) {
                if (i == year) {
                    off = offset;
                    break; } } }
        return off;
    }

    private static int mYear;
    private static List<String> mSolarData = new ArrayList<>();
    private static List<String> mSolarName = new ArrayList<>();

    public static String getSolarTerm(LocalDate localDate) {
        int year = localDate.getYear();
        int month = localDate.getMonthOfYear();
        String data = ( month< 10 ? ("0" + month) : (month + "")) + localDate.getDayOfMonth();
        if (year != mYear) { solarTermToString(year); }
        if (mSolarData.contains(data)) { return mSolarName.get(mSolarData.indexOf(data)); }
        else { return ""; }
    }

    private static void solarTermToString(int year) {
        mYear = year;
        if (mSolarData != null) { mSolarData.clear(); }
        else { mSolarData = new ArrayList<>(); }
        if (mSolarName != null) { mSolarName.clear(); }
        else { mSolarName = new ArrayList<>(); }
        mSolarName.add("立春"); // 1
        mSolarData.add("02" + getSolarTermNum(year, SolarTermsEnum.LICHUN.name()));
        mSolarName.add("雨水"); // 2
        mSolarData.add("02" + getSolarTermNum(year, SolarTermsEnum.YUSHUI.name()));
        mSolarName.add("惊蛰"); // 3
        mSolarData.add("03" + getSolarTermNum(year, SolarTermsEnum.JINGZHE.name()));
        mSolarName.add("春分"); // 4
        mSolarData.add("03" + getSolarTermNum(year, SolarTermsEnum.CHUNFEN.name()));
        mSolarName.add("清明"); // 5
        mSolarData.add("04" + getSolarTermNum(year, SolarTermsEnum.QINGMING.name()));
        mSolarName.add("谷雨"); // 6
        mSolarData.add("04" + getSolarTermNum(year, SolarTermsEnum.GUYU.name()));
        mSolarName.add("立夏"); // 7
        mSolarData.add("05" + getSolarTermNum(year, SolarTermsEnum.LIXIA.name()));
        mSolarName.add("小满"); // 8
        mSolarData.add("05" + getSolarTermNum(year, SolarTermsEnum.XIAOMAN.name()));
        mSolarName.add("芒种"); // 9
        mSolarData.add("06" + getSolarTermNum(year, SolarTermsEnum.MANGZHONG.name()));
        mSolarName.add("夏至"); // 10
        mSolarData.add("06" + getSolarTermNum(year, SolarTermsEnum.XIAZHI.name()));
        mSolarName.add("小暑"); // 11
        mSolarData.add("07" + getSolarTermNum(year, SolarTermsEnum.XIAOSHU.name()));
        mSolarName.add("大暑"); // 12
        mSolarData.add("07" + getSolarTermNum(year, SolarTermsEnum.DASHU.name()));
        mSolarName.add("立秋"); // 13
        mSolarData.add("08" + getSolarTermNum(year, SolarTermsEnum.LIQIU.name()));
        mSolarName.add("处暑"); // 14
        mSolarData.add("08" + getSolarTermNum(year, SolarTermsEnum.CHUSHU.name()));
        mSolarName.add("白露"); // 15
        mSolarData.add("09" + getSolarTermNum(year, SolarTermsEnum.BAILU.name()));
        mSolarName.add("秋分"); // 16
        mSolarData.add("09" + getSolarTermNum(year, SolarTermsEnum.QIUFEN.name()));
        mSolarName.add("寒露"); // 17
        mSolarData.add("10" + getSolarTermNum(year, SolarTermsEnum.HANLU.name()));
        mSolarName.add("霜降"); // 18
        mSolarData.add("10" + getSolarTermNum(year, SolarTermsEnum.SHUANGJIANG.name()));
        mSolarName.add("立冬"); // 19
        mSolarData.add("11" + getSolarTermNum(year, SolarTermsEnum.LIDONG.name()));
        mSolarName.add("小雪"); // 20
        mSolarData.add("11" + getSolarTermNum(year, SolarTermsEnum.XIAOXUE.name()));
        mSolarName.add("大雪"); // 21
        mSolarData.add("12" + getSolarTermNum(year, SolarTermsEnum.DAXUE.name()));
        mSolarName.add("冬至"); // 22
        mSolarData.add("12" + getSolarTermNum(year, SolarTermsEnum.DONGZHI.name()));
        mSolarName.add("小寒"); // 23
        mSolarData.add("01" + getSolarTermNum(year, SolarTermsEnum.XIAOHAN.name()));
        mSolarName.add("大寒"); // 24
        mSolarData.add("01" + getSolarTermNum(year, SolarTermsEnum.DAHAN.name()));
    }

}
