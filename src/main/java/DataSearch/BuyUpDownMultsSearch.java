package DataSearch;

import Calculator.RemainingTerm.RemainingTermCalculator;
import Calculator.RemainingTerm.RemainingTermCalculatorFactory;
import Data.*;
import Lib.ConfigLoader;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class BuyUpDownMultsSearch {

    private String remainingTermCalculationMethod;
    private String buyUpDownDataFileName;
    private List<BuyUpDownData> buyUpDownDataList;
    private String dateFormat;
    private SimpleDateFormat sdf;

    public BuyUpDownMultsSearch() {
        ConfigLoader.load(BuyUpDownMultsSearch.class, this, "src/main/resources/BuyUpDownMultsSearch.properties");
        try {
            buyUpDownDataList = new CsvToBeanBuilder(new FileReader(buyUpDownDataFileName)).withType(BuyUpDownData.class).build().parse();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        sdf = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
    }

    public BuyUpDownMults getBuyUpDownMults(Loan loan, Pool pool, Date settlementDate) {
        RemainingTermCalculator rtCalculator = RemainingTermCalculatorFactory.getRemainingTermCalculator(remainingTermCalculationMethod);
        int remainingTerm = rtCalculator.calculate(loan);
        BuyUpDownMults buyUpDownMults = null;
        for (BuyUpDownData buyUpDownData : buyUpDownDataList) {
            if (agency_isCorrect(pool, buyUpDownData)
                    && issueMonth_isCorrect(settlementDate, buyUpDownData)
                    && poolId_isCorrect(pool, buyUpDownData)
                    && LPMIflag_isCorrect(loan, buyUpDownData)
                    && noteRate_isInRange(loan, buyUpDownData)
                    && remainingTerm_isInRange(remainingTerm, buyUpDownData)) {
                double buy_up_mults = Double.parseDouble(buyUpDownData.getBuy_up_mults());
                double buy_down_mults = Double.parseDouble(buyUpDownData.getBuy_down_mults());
                buyUpDownMults = new BuyUpDownMults(buy_up_mults, buy_down_mults);
                break;
            }

        }

        return buyUpDownMults;
    }

    private boolean agency_isCorrect(Pool pool, BuyUpDownData buyUpDownData) {
        return pool.getLoanPricerId().contains(buyUpDownData.getAgency());
    }

    private boolean issueMonth_isCorrect(Date settlementDate, BuyUpDownData buyUpDownData) {
        Calendar settlementCal = Calendar.getInstance();
        settlementCal.setTime(settlementDate);

        String issue_month = buyUpDownData.getIssue_month();
        Date issueDate = null;
        try {
            issueDate = sdf.parse(issue_month);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar issueCal = Calendar.getInstance();
        issueCal.setTime(issueDate);

        boolean yearEqual = settlementCal.get(Calendar.YEAR) == issueCal.get(Calendar.YEAR);
        boolean monthEqual = settlementCal.get(Calendar.MONTH) == issueCal.get(Calendar.MONTH);

        return yearEqual && monthEqual;
    }

    private boolean poolId_isCorrect(Pool pool, BuyUpDownData buyUpDownData) {
        return pool.getPackageTypeNumber().equals(buyUpDownData.getPool_id());
    }

    private boolean LPMIflag_isCorrect(Loan loan, BuyUpDownData buyUpDownData) {
        return loan.getLPMIFlag().equals(buyUpDownData.getLpmi_flag());
    }

    private boolean noteRate_isInRange(Loan loan, BuyUpDownData buyUpDownData) {
        double note_rate = Double.parseDouble(loan.getGrossCoupon());
        double note_rate_min = Double.parseDouble(buyUpDownData.getNote_rate_min());
        double note_rate_max = Double.parseDouble(buyUpDownData.getNote_rate_max());

        return note_rate >= note_rate_min && note_rate <= note_rate_max;
    }

    private boolean remainingTerm_isInRange(int remainingTerm, BuyUpDownData buyUpDownData) {
        int remaining_term_min = Integer.parseInt(buyUpDownData.getRemaining_term_min());
        int remaining_term_max = Integer.parseInt(buyUpDownData.getRemaining_term_max());

        return remainingTerm >= remaining_term_min && remainingTerm <= remaining_term_max;
    }
}
