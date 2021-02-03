package MBSFactory.DataLookupFactories;

import Util.Configuration.ConfigFile;
import DataLookup.BaseServicingFee.BaseServicingFeeLookupV1;
import MBSData.Loan;
import MBSData.Pool;
import MBSFactory.Factory;

import java.util.Map;

public class BaseServicingFeeFactory extends Factory {

    @Override
    public Object create(ConfigFile cfg, Loan loan, Pool pool) {
        Map agencyConfig = getAgencyConfig(cfg, pool);
        String version = (String) agencyConfig.get("BaseServicingFeeLookup");

        if (version.equals("V1")) {
            return new BaseServicingFeeLookupV1(cfg);
        }

        return null;
    }

}
