package TestLib;

import Configuration.ConfigFile;
import Util.BusinessDayUtil;
import org.junit.Test;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TestBusinessDayUtil {

    ConfigFile cfg = new ConfigFile();
    BusinessDayUtil businessDayUtil = new BusinessDayUtil(cfg);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

    @Test
    public void test_isBusinessDay() throws ParseException {

        Date date = sdf.parse("2021-01-18");
        assertFalse(businessDayUtil.isBusinessDay(date));

        date = sdf.parse("2021-01-17");
        assertFalse(businessDayUtil.isBusinessDay(date));

        date = sdf.parse("2021-01-19");
        assertTrue(businessDayUtil.isBusinessDay(date));

    }

    @Test
    public void test_getNextBusinessDay() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = sdf.parse("2021-01-17");
        Date nextDate = businessDayUtil.getNextBusinessDay(date);
        assertEquals(sdf.parse("2021-01-19"), nextDate);

        date = sdf.parse("2021-01-16");
        nextDate = businessDayUtil.getNextBusinessDay(date);
        assertEquals(sdf.parse("2021-01-19"), nextDate);
    }

}
