package Calculator;

import Data.Loan;
import Data.Pool;
import Data.SettlementControl;
import Lib.ConfigLoader;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Calculate the settlement date by calendar
 */
public class SettlementDateCalculator {
    private String warehouseDays;
    private String agencyProcessDays;
    private String BMAs;
    private String inputFileName;

    public SettlementDateCalculator() {
        ConfigLoader.load(SettlementDateCalculator.class, this, "src/main/resources/settlementDateCalculator.properties");
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

    public List<Date> calculate(Loan loan, Pool pool){

        return null;
    }

    private Date calcESPD(Loan loan) {

        return null;
    }


}
