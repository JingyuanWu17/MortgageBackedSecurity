package DataLookup;

import Calculator.RemainingTerm.RemainingTermCalculator;
import Calculator.RemainingTerm.RemainingTermCalculatorFactory;
import InputData.*;
import Lib.ConfigLoader;
import OutputData.BuyUpDownMults;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Date;
import java.util.List;


public class BuyUpDownMultsLookup extends TableLookup {
    private final static String configFileName = "src/main/resources/BuyUpDownMultsLookup.properties";
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

    public BuyUpDownMults getBuyUpDownMults(Loan loan, Pool pool, Date settlementDate) {
        RemainingTermCalculator rtCalculator = RemainingTermCalculatorFactory.getRemainingTermCalculator(remainingTermCalculationMethod);
        int remainingTerm = rtCalculator.calculate(loan);

        String loanPricerId = pool.getLoanPricerId();
        String packageTypeNumber = pool.getPackageTypeNumber();
        String LPMIFlag = loan.getLPMIFlag();
        String grossCoupon = loan.getGrossCoupon();

        BuyUpDownMults buyUpDownMults = null;
        for (BuyUpDownData buyUpDownData : buyUpDownDataList) {
            if (stringContains(loanPricerId, buyUpDownData.getAgency())
                    && dateMatch(settlementDate, buyUpDownData.getIssue_month())
                    && stringMatch(packageTypeNumber, buyUpDownData.getPool_id())
                    && stringMatch(LPMIFlag, buyUpDownData.getLpmi_flag())
                    && inRange(grossCoupon, buyUpDownData.getNote_rate_min(), buyUpDownData.getNote_rate_max())
                    && inRange(String.valueOf(remainingTerm), buyUpDownData.getRemaining_term_min(), buyUpDownData.getRemaining_term_max())) {
                double buy_up_mults = Double.parseDouble(buyUpDownData.getBuy_up_mults());
                double buy_down_mults = Double.parseDouble(buyUpDownData.getBuy_down_mults());
                buyUpDownMults = new BuyUpDownMults(buy_up_mults, buy_down_mults);
                break;
            }
        }

        return buyUpDownMults;
    }
}
