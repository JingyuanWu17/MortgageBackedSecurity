package MBSFactory.CalculatorFactories;

import Calculator.MBSCoupon.MBSCouponCalcV1;
import Util.Configuration.ConfigFile;
import MBSData.Loan;
import MBSData.Pool;
import MBSFactory.Factory;

import java.util.Map;

public class MBSCouponFactory extends Factory {

    @Override
    public Object create(ConfigFile cfg, Loan loan, Pool pool) {
        Map agencyConfig = getAgencyConfig(cfg, pool);
        String version = (String) agencyConfig.get("MBSCouponCalc");

        if (version.equals("V1")) {
            return new MBSCouponCalcV1(cfg);
        }

        return null;
    }

}
