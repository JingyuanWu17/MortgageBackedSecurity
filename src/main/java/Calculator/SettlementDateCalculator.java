package Calculator;

import Data.Loan;
import Data.Pool;
import Lib.ConfigLoader;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Calculate the settlement date by calendar
 */
public class SettlementDateCalculator {
    private String warehouseDays;
    private String agencyProcessDays;
    private String BMAs;

    public SettlementDateCalculator() {
        ConfigLoader.load(SettlementDateCalculator.class, this, "src/main/resources/SettlementDateCalculator.properties");
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

    public List<LocalDate> calculate(Loan loan, Pool pool) {
        List<LocalDate> res = new ArrayList<>();
        


        return res;
    }

}
