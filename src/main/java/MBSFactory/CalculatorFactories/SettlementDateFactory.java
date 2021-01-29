package MBSFactory.CalculatorFactories;

import Calculator.SettlementDate.SettlementDateCalcV1;
import Configuration.ConfigFile;
import InputData.Loan;
import InputData.Pool;
import MBSFactory.Factory;

import java.util.Map;

public class SettlementDateFactory extends Factory {

    @Override
    public Object create(ConfigFile cfg, Loan loan, Pool pool) {
        Map agencyConfig = getAgencyConfig(cfg, pool);
        String version = (String) agencyConfig.get("SettlementDateCalc");

        if (version.equals("V1")) {
            return new SettlementDateCalcV1(cfg);
        }

        return null;
    }

}
