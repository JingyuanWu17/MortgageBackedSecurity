package DataLookup.BaseServicingFee;

import Configuration.ConfigFile;
import DataLookup.TableLookup;
import InputData.Pool;

public abstract class BaseServicingFeeLookup extends TableLookup {

    public BaseServicingFeeLookup(ConfigFile cfg) {
        super(cfg);
    }

    public abstract double lookup(Pool pool);

}
