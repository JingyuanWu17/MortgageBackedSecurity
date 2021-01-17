package Calculator;

import org.junit.Test;

public class SettlementDateTest {
    @Test
    public void test_createObject() {
        SettlementDate sd = new SettlementDate();
        System.out.println(sd.getAgencyProcessDays());
        System.out.println(sd.getWarehouseDays());
        System.out.println(sd.getBMAs());
    }
}
