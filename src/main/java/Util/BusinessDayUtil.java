package Util;

import Configuration.ConfigFile;
import org.apache.commons.lang3.time.DateUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class BusinessDay {
    private String dateFormat;
    private Map holidaysOfYears;
    private Map<Integer, Set<Date>> yearHolidaysCache = new HashMap<>();

    public BusinessDay(ConfigFile cfg) {
        dateFormat = cfg.getDateFormat();
        holidaysOfYears = (Map) cfg.getHolidaysOfYears();
    }

    /**
     * @return true if dateToCheck is not on weekends and not on holidays
     */
    public boolean isBusinessDay(Date dateToCheck) {
        Calendar baseCal = Calendar.getInstance();
        baseCal.setTime(DateUtils.truncate(dateToCheck, Calendar.DATE));

        int year = baseCal.get(Calendar.YEAR);

        if (!yearHolidaysCache.containsKey(year)) {
            readYearHolidays(year);
        }
        Set<Date> yearHolidays = yearHolidaysCache.get(year);

        int dayOfWeek = baseCal.get(Calendar.DAY_OF_WEEK);

        boolean onWeekend = dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY;

        return !(onWeekend || yearHolidays.contains(baseCal.getTime()));
    }

    /**
     * @return the next business day calculated from base date
     */
    public Date getNextBusinessDay(Date baseDate) {
        Date nextDay = DateUtils.truncate(addDays(baseDate, 1), Calendar.DATE);

        if (isBusinessDay(nextDay)) {
            return nextDay;
        } else {
            return getNextBusinessDay(nextDay);
        }
    }

    public Date addDays(Date date, int days) {
        if (date == null) {
            throw new IllegalArgumentException("Input date can't be null!");
        }

        Calendar tempCal = Calendar.getInstance();
        tempCal.setTime(date);
        tempCal.add(Calendar.DATE, days);

        return tempCal.getTime();
    }

    /**
     * Read holidays of one specific year from configuration file
     */
    private void readYearHolidays(int year) {
        String yearHolidays = (String) holidaysOfYears.get(year);
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
        String[] holidays = yearHolidays.split(",");
        Set<Date> holidaySet = new HashSet<>();
        for (String holiday : holidays) {
            try {
                Date date = sdf.parse(holiday.trim());
                holidaySet.add(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        yearHolidaysCache.put(year, holidaySet);
    }

}
