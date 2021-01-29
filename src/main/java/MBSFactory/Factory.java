package MBSFactory;

import Configuration.ConfigFile;
import InputData.Loan;
import InputData.Pool;

public abstract class Factory {

    public abstract Object create(ConfigFile cfg, Pool pool, Loan loan);

}
