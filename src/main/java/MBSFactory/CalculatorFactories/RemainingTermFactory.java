package MBSFactory.CalculatorFactories;

import Calculator.RemainingTerm.RemainingTermCalcV1;
import Configuration.ConfigFile;
import InputData.Loan;
import InputData.Pool;
import MBSFactory.Factory;

import java.util.Map;

public class RemainingTermFactory extends Factory {

    @Override
    public Object create(ConfigFile cfg, Loan loan, Pool pool) {
        String packageTypeNumber = pool.getPackageTypeNumber();
        Map poolConfig = (Map) cfg.getPools().get(packageTypeNumber);
        String version = (String) poolConfig.get("RemainingTermCalc");

        if (version.equals("V1")) {
            return new RemainingTermCalcV1();
        }

        return null;
    }

}
