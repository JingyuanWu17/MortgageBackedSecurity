package DataLookup.BaseServicingFee;

import Util.Configuration.ConfigFile;
import DataLookup.TableLookup;
import MBSData.Pool;

public abstract class BaseServicingFeeLookup extends TableLookup {

    public BaseServicingFeeLookup(ConfigFile cfg) {
        super(cfg);
    }

    public abstract double lookup(Pool pool);

}
