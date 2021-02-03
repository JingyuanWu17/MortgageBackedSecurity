package DataLookup.BuyUpDownMults;

import Util.Configuration.ConfigFile;
import DataLookup.TableLookup;
import MBSData.BuyUpDownData;
import MBSData.Loan;
import MBSData.Pool;

import java.util.Date;

public abstract class BuyUpDownMultsLookup extends TableLookup {

    public BuyUpDownMultsLookup(ConfigFile cfg) {
        super(cfg);
    }

    public abstract BuyUpDownData lookup(Loan loan, Pool pool, Date settlementDate);

}
