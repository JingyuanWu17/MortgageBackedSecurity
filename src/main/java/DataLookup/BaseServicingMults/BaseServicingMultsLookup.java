package DataLookup.BaseServicingMultsLookup;

import DataLookup.TableLookup;
import InputData.Pool;

import java.util.Date;

public abstract class BaseServicingMults extends TableLookup {

    public abstract double lookup(Pool pool, Date settlementDate);

}
