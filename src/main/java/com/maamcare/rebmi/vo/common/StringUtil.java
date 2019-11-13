package com.maamcare.rebmi.vo.common;

import com.maamcare.rebmi.exception.MyException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class StringUtil {
    private static Calendar calendar = Calendar.getInstance();
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static {
        dateFormat.setLenient(false);//这个的功能是不把1996-13-3 转换为1997-1-3
        dateTimeFormat.setLenient(false);
    }

    public static Date parseStringToDate(String dateStr) {
        if (dateStr == null || dateStr.trim().equals(""))
            return null;
        dateStr = dateStr.trim();
        Date date = null;
        try {
            if (dateStr.length() <= 10) {
                date = dateFormat.parse(dateStr);
            } else {
                date = dateTimeFormat.parse(dateStr);
            }
        } catch (Exception e) {
            throw new MyException(-10,"你输入的日期不合法，请重新输入");
        }
        return date;
    }
}
