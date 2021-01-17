package Calculator;

import org.junit.Test;
import static org.junit.Assert.*;

public class SettlementDateTest {
    @Test
    public void test_createObject() {
        SettlementDate sd = new SettlementDate();
        assertEquals("2", sd.getAgencyProcessDays());
        assertEquals("2", sd.getWarehouseDays());
        System.out.println(sd.getBMAs());
    }
}
