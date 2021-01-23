package DataLookup;

import Calculator.RemainingTerm.RemainingTermCalculator;
import Calculator.RemainingTerm.RemainingTermCalculatorFactory;
import InputData.*;
import Lib.ConfigLoader;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Date;
import java.util.List;


public class BuyUpDownMultsLookup extends TableLookup {
    private final static String configFileName = "src/main/resources/DataLookupConfig/BuyUpDownMultsLookup.properties";
    private String remainingTermCalculationMethod;
    private String buyUpDownDataFileName;
    private List<BuyUpDownData> buyUpDownDataList;


    public BuyUpDownMultsLookup() {
        ConfigLoader.load(BuyUpDownMultsLookup.class, this, configFileName);
        try {
            buyUpDownDataList = new CsvToBeanBuilder(new FileReader(buyUpDownDataFileName)).withType(BuyUpDownData.class).build().parse();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public BuyUpDownData getBuyUpDownMults(Loan loan, Pool pool, Date settlementDate) {
        RemainingTermCalculator remainingTermCalculator = RemainingTermCalculatorFactory.getRemainingTermCalculator(remainingTermCalculationMethod);
        int remainingTerm = remainingTermCalculator.calculate(loan);

        String loanPricerId = pool.getLoanPricerId();
        String packageTypeNumber = pool.getPackageTypeNumber();
        String LPMIFlag = loan.getLPMIFlag();
        String grossCoupon = loan.getGrossCoupon();

        BuyUpDownData data = null;
        for (BuyUpDownData buyUpDownData : buyUpDownDataList) {
            if (stringContains(loanPricerId, buyUpDownData.getAgency())
                    && dateMatch(settlementDate, buyUpDownData.getIssue_month())
                    && stringMatch(packageTypeNumber, buyUpDownData.getPool_id())
                    && stringMatch(LPMIFlag, buyUpDownData.getLpmi_flag())
                    && inRange(grossCoupon, buyUpDownData.getNote_rate_min(), buyUpDownData.getNote_rate_max())
                    && inRange(String.valueOf(remainingTerm), buyUpDownData.getRemaining_term_min(), buyUpDownData.getRemaining_term_max())) {
                data = buyUpDownData;
                break;
            }
        }

        return data;
    }
}
