package MBSFactory;

import Util.Configuration.ConfigFile;
import MBSData.Loan;
import MBSData.Pool;

import java.util.Map;

public abstract class Factory {

    public abstract Object create(ConfigFile cfg, Loan loan, Pool pool);

    protected Map getAgencyConfig(ConfigFile cfg, Pool pool) {
        String loanPricerId = pool.getLoanPricerId();
        Map agencyConfig = null;

        if (loanPricerId.contains("fhlmc")) {
            agencyConfig = cfg.getFhlmc();
        } else if (loanPricerId.contains("fnma")) {
            agencyConfig = cfg.getFnma();
        } else if (loanPricerId.contains("gnma")) {
            agencyConfig = cfg.getGnma();
        }

        return agencyConfig;
    }

}
