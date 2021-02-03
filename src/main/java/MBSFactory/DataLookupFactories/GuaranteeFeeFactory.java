package MBSFactory.DataLookupFactories;

import Util.Configuration.ConfigFile;
import DataLookup.GuaranteeFee.GuaranteeFeeLookupV1;
import MBSData.Loan;
import MBSData.Pool;
import MBSFactory.Factory;

import java.util.Map;

public class GuaranteeFeeFactory extends Factory {
    @Override
    public Object create(ConfigFile cfg, Loan loan, Pool pool) {
        Map agencyConfig = getAgencyConfig(cfg, pool);
        String version = (String) agencyConfig.get("GuaranteeFeeLookup");

        if (version.equals("V1")) {
            return new GuaranteeFeeLookupV1(cfg);
        }

        return null;
    }
}
