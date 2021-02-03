package Calculator.MBSCoupon;

import Util.Configuration.ConfigFile;
import DataLookup.BaseServicingFee.BaseServicingFeeLookup;
import DataLookup.GuaranteeFee.GuaranteeFeeLookup;
import MBSData.Loan;
import MBSData.Pool;
import MBSFactory.Factory;
import MBSFactory.FactoryProducer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MBSCouponCalcV1 extends MBSCouponCalc {
    private ConfigFile cfg;
    private String maxSpread;

    public MBSCouponCalcV1(ConfigFile cfg) {
        this.cfg = cfg;
        Map V1_cfg = (Map) cfg.getMBSCouponCalc().get("V1");
        maxSpread = (String) V1_cfg.get("maxSpread");
    }

    @Override
    public List<Double> calculate(Loan loan, Pool pool) {
        double upperBound = getUpperBound(loan, pool);
        double lowerBound = getLowerBound(loan);

        return getHalfPointNumbers(lowerBound, upperBound);
    }

    private List<Double> getHalfPointNumbers(double lowerBound, double upperBound) {
        List<Double> res = new ArrayList<>();
        double floor = Math.floor(lowerBound);
        double ceiling = Math.floor(upperBound) + 1;
        for (double i = floor; i <= ceiling; i += 0.5) {
            if (i >= lowerBound && i <= upperBound) {
                res.add(i);
            }
        }

        return res;
    }

    private double getUpperBound(Loan loan, Pool pool) {
        Factory gGeeFactory = FactoryProducer.getFactory("DataLookup", "GuaranteeFee");
        GuaranteeFeeLookup gfeeLookup = (GuaranteeFeeLookup) gGeeFactory.create(cfg, loan, pool);

        Factory baseFeeFactory = FactoryProducer.getFactory("DataLookup", "BaseServicingFee");
        BaseServicingFeeLookup baseFeeLookup = (BaseServicingFeeLookup) baseFeeFactory.create(cfg, loan, pool);

        double gFee = gfeeLookup.lookup(pool);
        double baseFee = baseFeeLookup.lookup(pool);

        double grossCoupon = Double.parseDouble(loan.getGrossCoupon());
        double buyUpRate = Double.parseDouble(loan.getBuyUpRate());
        double buyDownRate = Double.parseDouble(loan.getBuyDownRate());

        return grossCoupon - gFee - baseFee - buyUpRate - buyDownRate;
    }

    private double getLowerBound(Loan loan) {
        return Double.parseDouble(loan.getGrossCoupon()) - Double.parseDouble(maxSpread);
    }

}
