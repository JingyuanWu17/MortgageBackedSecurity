package DataLookup.MarketPrice;

import Configuration.ConfigFile;
import DataLookup.TableLookup;
import InputData.Pool;

import java.util.Date;

public abstract class MarketPrice extends TableLookup {

    public MarketPrice(ConfigFile cfg) {
        super(cfg);
    }

    public abstract double lookup(Pool pool, double MBSCoupon, Date settlementDate);

}
