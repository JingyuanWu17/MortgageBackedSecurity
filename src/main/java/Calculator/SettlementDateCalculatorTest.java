package Calculator;

import org.junit.Test;
import static org.junit.Assert.*;

public class SettlementDateCalculatorTest {
    @Test
    public void test_createObject() {
        SettlementDateCalculator sd = new SettlementDateCalculator();
        assertEquals("2", sd.getAgencyProcessDays());
        assertEquals("2", sd.getWarehouseDays());
        System.out.println(sd.getBMAs());
    }
}