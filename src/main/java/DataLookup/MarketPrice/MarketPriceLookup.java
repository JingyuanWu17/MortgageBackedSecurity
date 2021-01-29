package DataLookup.MarketPrice;

import Configuration.ConfigFile;
import DataLookup.TableLookup;
import InputData.Pool;

import java.util.Date;

public abstract class MarketPriceLookup extends TableLookup {

    public MarketPriceLookup(ConfigFile cfg) {
        super(cfg);
    }

    public abstract double lookup(Pool pool, double MBSCoupon, Date settlementDate);

}
