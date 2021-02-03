package DataLookup.GuaranteeFee;

import Util.Configuration.ConfigFile;
import DataLookup.TableLookup;
import MBSData.Pool;

public abstract class GuaranteeFeeLookup extends TableLookup {

    public GuaranteeFeeLookup(ConfigFile cfg) {
        super(cfg);
    }

    public abstract double lookup(Pool pool);

}
