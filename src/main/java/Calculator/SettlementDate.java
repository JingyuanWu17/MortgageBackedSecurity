package Calculator;

import Lib.ConfigLoader;

public class SettlementDate {
    private String warehouseDays;
    private String agencyProcessDays;
    private String BMAs;

    public SettlementDate() {
        ConfigLoader.load(SettlementDate.class, this, "src/main/resources/SettlementDateConfig.properties");
    }

    public String getWarehouseDays() {
        return warehouseDays;
    }

    public String getAgencyProcessDays() {
        return agencyProcessDays;
    }

    public String getBMAs() {
        return BMAs;
    }
}
