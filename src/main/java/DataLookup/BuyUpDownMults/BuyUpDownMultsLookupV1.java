package DataLookup.BuyUpDownMults;

import Calculator.RemainingTerm.RemainingTermCalc;
import Util.Configuration.ConfigFile;
import MBSData.BuyUpDownData;
import MBSData.Loan;
import MBSData.Pool;
import MBSFactory.Factory;
import MBSFactory.FactoryProducer;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class BuyUpDownMultsLookupV1 extends BuyUpDownMultsLookup {
    private ConfigFile cfg;
    private String dataFileName;
    private List<BuyUpDownData> dataList;

    public BuyUpDownMultsLookupV1(ConfigFile cfg) {
        super(cfg);
        this.cfg = cfg;
        Map V1_cfg = (Map) cfg.getBuyUpDownMultsLookup().get("V1");
        dataFileName = (String) V1_cfg.get("dataFileName");

        try {
            dataList = new CsvToBeanBuilder(new FileReader(dataFileName)).withType(BuyUpDownData.class).build().parse();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public BuyUpDownData lookup(Loan loan, Pool pool, Date settlementDate) {
        Factory rTermFactory = FactoryProducer.getFactory("Calculator", "RemainingTerm");
        RemainingTermCalc rTermCal = (RemainingTermCalc) rTermFactory.create(cfg, loan, pool);
        int remainingTerm = rTermCal.calculate(loan);

        String loanPricerId = pool.getLoanPricerId();
        String packageTypeNumber = pool.getPackageTypeNumber();
        String LPMIFlag = loan.getLPMIFlag();
        String grossCoupon = loan.getGrossCoupon();

        BuyUpDownData resData = null;
        for (BuyUpDownData data : dataList) {
            if (stringContains(loanPricerId, data.getAgency())
                    && dateMatch(settlementDate, data.getIssue_month())
                    && stringMatch(packageTypeNumber, data.getPool_id())
                    && stringMatch(LPMIFlag, data.getLpmi_flag())
                    && inRange(grossCoupon, data.getNote_rate_min(), data.getNote_rate_max())
                    && inRange(String.valueOf(remainingTerm), data.getRemaining_term_min(), data.getRemaining_term_max())) {
                resData = data;
                break;
            }
        }

        return resData;
    }
}
