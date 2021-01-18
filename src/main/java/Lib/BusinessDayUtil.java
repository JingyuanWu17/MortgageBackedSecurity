package Lib;

import org.apache.commons.lang.time.DateUtils;

import java.util.Calendar;
import java.util.Date;


public class BusinessDayUtil {

    public static boolean isBusinessDay(Date dateToCheck) {
        Calendar baseCal = Calendar.getInstance();
        baseCal.setTime(DateUtils.truncate(dateToCheck, Calendar.DATE));

        int dayOfWeek = baseCal.get(Calendar.DAY_OF_WEEK);

        return dayOfWeek != Calendar.SATURDAY && dayOfWeek != Calendar.SUNDAY;
    }

    //Get the next business day after baseDate
    public static Date getNextBusinessDay(Date baseDate) {
        Date nextDay = DateUtils.truncate(addOneDay(baseDate), Calendar.DATE);

        if (isBusinessDay(nextDay)) {
            return nextDay;
        } else {
            return getNextBusinessDay(nextDay);
        }
    }

    private static Date addOneDay(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("Input date can't be null!");
        }

        Calendar tempCal = Calendar.getInstance();
        tempCal.setTime(date);
        tempCal.add(Calendar.DATE, 1);

        return tempCal.getTime();
    }

}
