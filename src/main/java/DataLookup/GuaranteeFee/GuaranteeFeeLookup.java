package DataLookup.GuaranteeFee;

import Configuration.ConfigFile;
import DataLookup.TableLookup;
import InputData.Pool;

import java.util.Date;

public abstract class GuaranteeFee extends TableLookup {

    public GuaranteeFee(ConfigFile cfg) {
        super(cfg);
    }

    public abstract double lookup(Pool pool, Date settlementDate);

}
