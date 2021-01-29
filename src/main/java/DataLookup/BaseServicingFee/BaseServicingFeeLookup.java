package DataLookup.BaseServicingFeeLookup;

import DataLookup.TableLookup;
import InputData.Pool;

public abstract class BaseServicingFee extends TableLookup {

    public abstract double lookup(Pool pool);

}
