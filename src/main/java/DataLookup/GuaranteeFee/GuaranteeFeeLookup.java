package DataLookup.GuaranteeFee;

import Configuration.ConfigFile;
import DataLookup.TableLookup;
import InputData.Pool;

public abstract class GuaranteeFeeLookup extends TableLookup {

    public GuaranteeFeeLookup(ConfigFile cfg) {
        super(cfg);
    }

    public abstract double lookup(Pool pool);

}
