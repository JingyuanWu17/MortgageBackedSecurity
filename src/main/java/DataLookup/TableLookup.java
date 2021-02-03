package DataLookup;

import Util.Configuration.ConfigFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public abstract class TableLookup {
    protected String dateFormat;
    protected SimpleDateFormat sdf;

    public TableLookup(ConfigFile cfg) {
        dateFormat = cfg.getDateFormat();
        sdf = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
    }

    protected boolean stringMatch(String str1, String str2) {
        return str1.equals(str2);
    }

    protected boolean stringContains(String str1, String str2) {
        return str1.contains(str2);
    }

    protected boolean doubleMatch(String str1, String str2) {
        return Double.parseDouble(str1) == Double.parseDouble(str2);
    }

    /**
     * @return true is num is within the range of lowerBound and upperBound
     */
    protected boolean inRange(String num, String lowerBound, String upperBound) {
        double n = Double.parseDouble(num);
        double lo = Double.parseDouble(lowerBound);
        double hi = Double.parseDouble(upperBound);

        return n >= lo && n <= hi;
    }

    /**
     * @return true if both date1 and date2 have the same year and month
     */
    protected boolean dateMatch(Date date1, String date2_str) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Date date2 = null;
        try {
            date2 = sdf.parse(date2_str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal2 = Calendar.getInstance();
        assert date2 != null;
        cal2.setTime(date2);

        boolean yearEqual = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
        boolean monthEqual = cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);

        return yearEqual && monthEqual;
    }
}
