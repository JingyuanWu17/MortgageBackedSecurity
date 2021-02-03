package DataLookup.BaseServicingMults;

import Util.Configuration.ConfigFile;
import DataLookup.TableLookup;
import MBSData.Pool;

import java.util.Date;

public abstract class BaseServicingMultsLookup extends TableLookup {

    public BaseServicingMultsLookup(ConfigFile cfg) {
        super(cfg);
    }

    public abstract double lookup(Pool pool, Date settlementDate);

}
