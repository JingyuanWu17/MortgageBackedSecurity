package TestLib;

import Lib.BusinessDayUtil;
import org.junit.Test;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TestBusinessDayUtil {

    @Test
    public void test_isBusinessDay() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = sdf.parse("2021-01-18");
        assertFalse(BusinessDayUtil.isBusinessDay(date));

        date = sdf.parse("2021-01-17");
        assertFalse(BusinessDayUtil.isBusinessDay(date));

        date = sdf.parse("2021-01-19");
        assertTrue(BusinessDayUtil.isBusinessDay(date));

    }

    @Test
    public void test_getNextBusinessDay() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = sdf.parse("2021-01-17");
        Date nextDate = BusinessDayUtil.getNextBusinessDay(date);
        assertEquals(sdf.parse("2021-01-19"), nextDate);

        date = sdf.parse("2021-01-16");
        nextDate = BusinessDayUtil.getNextBusinessDay(date);
        assertEquals(sdf.parse("2021-01-19"), nextDate);
    }

}
