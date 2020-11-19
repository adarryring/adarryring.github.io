package io.github.darryring.util.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFormatUtil {

    public static String today(String pattern) {
        return (new SimpleDateFormat(pattern)).format(new Date());
    }

    public static String years(Long years) {
        return (new SimpleDateFormat(IDateFormat.YEARS)).format(new Date(years));
    }

    public static String years(Date date) {
        return (new SimpleDateFormat(IDateFormat.YEARS)).format(date);
    }

    public static String lastMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        date = calendar.getTime();
        return (new SimpleDateFormat(IDateFormat.YEARS)).format(date);
    }
}
