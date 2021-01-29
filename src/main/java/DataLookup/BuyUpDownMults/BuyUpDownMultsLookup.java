package DataLookup.BuyUpDownMults;

import Configuration.ConfigFile;
import DataLookup.TableLookup;
import InputData.BuyUpDownData;
import InputData.Loan;
import InputData.Pool;

import java.util.Date;

public abstract class BuyUpDownMultsLookup extends TableLookup {

    public BuyUpDownMultsLookup(ConfigFile cfg) {
        super(cfg);
    }

    public abstract BuyUpDownData lookup(Loan loan, Pool pool, Date settlementDate);

}
