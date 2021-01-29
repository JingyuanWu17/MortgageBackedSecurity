package MBSFactory.DataLookupFactories;

import Configuration.ConfigFile;
import DataLookup.MarketPrice.MarketPriceLookupV1;
import InputData.Loan;
import InputData.Pool;
import MBSFactory.Factory;

import java.util.Map;

public class MarketPriceFactory extends Factory {
    @Override
    public Object create(ConfigFile cfg, Loan loan, Pool pool) {
        String packageTypeNumber = pool.getPackageTypeNumber();
        Map poolConfig = (Map) cfg.getPools().get(packageTypeNumber);
        String version = (String) poolConfig.get("MarketPriceLookup");

        if (version.equals("V1")) {
            return new MarketPriceLookupV1(cfg);
        }

        return null;
    }
}
