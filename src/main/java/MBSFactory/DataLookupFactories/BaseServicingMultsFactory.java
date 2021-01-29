package MBSFactory.DataLookupFactories;

import Configuration.ConfigFile;
import DataLookup.BaseServicingMults.BaseServicingMultsLookupV1;
import InputData.Loan;
import InputData.Pool;
import MBSFactory.Factory;

import java.util.Map;

public class BaseServicingMultsFactory extends Factory {
    @Override
    public Object create(ConfigFile cfg, Loan loan, Pool pool) {
        Map agencyConfig = getAgencyConfig(cfg, pool);
        String version = (String) agencyConfig.get("BaseServicingMultsLookup");

        if (version.equals("V1")) {
            return new BaseServicingMultsLookupV1(cfg);
        }

        return null;
    }
}
