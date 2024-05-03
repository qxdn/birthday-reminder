package org.qxdn.birthdayreminder.utils;

import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    private static SimpleDateFormat yMd = new SimpleDateFormat("yyyy-MM-dd");
    public static Date now(){
        return new Date();
    }

    @SneakyThrows
    public static Date parseYMD(String date){
        return yMd.parse(date);
    }

    public static int getDay(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return  calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static int getMonth(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return  calendar.get(Calendar.MONTH)+1;
    }
}
