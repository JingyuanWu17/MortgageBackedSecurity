package MBSFactory.DataLookupFactories;

import Util.Configuration.ConfigFile;
import DataLookup.BuyUpDownMults.BuyUpDownMultsLookupV1;
import MBSData.Loan;
import MBSData.Pool;
import MBSFactory.Factory;

import java.util.Map;

public class BuyUpDownMultsFactory extends Factory {
    @Override
    public Object create(ConfigFile cfg, Loan loan, Pool pool) {
        Map agencyConfig = getAgencyConfig(cfg, pool);
        String version = (String) agencyConfig.get("BuyUpDownMultsLookup");

        if (version.equals("V1")) {
            return new BuyUpDownMultsLookupV1(cfg);
        }

        return null;
    }
}
