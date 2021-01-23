package DataLookup;

import InputData.Pool;
import Lib.ConfigLoader;

public class FeeLookup extends TableLookup{
    private final static String configFileName = "src/main/resources/DataLookupConfig/FeeLookup.properties";
    private String fhlmcGuaranteeFee;
    private String fnmaGuaranteeFee;
    private String gnmaGuaranteeFee;
    private String baseServicingFee;
    private String fhlmcBaseServicingFee;
    private String fnmaBaseServicingFee;
    private String gnmaBaseServicingFee;

    public FeeLookup() {
        ConfigLoader.load(FeeLookup.class, this, configFileName);
    }

    public double getGuaranteeFee(Pool pool) {
        String loanPricerId = pool.getLoanPricerId();
        double guaranteeFee = 0;
        if (stringContains(loanPricerId, "fhlmc")) {
            guaranteeFee = Double.parseDouble(fhlmcGuaranteeFee);
        } else if (stringContains(loanPricerId, "fnma")) {
            guaranteeFee = Double.parseDouble(fnmaGuaranteeFee);
        } else if (stringContains(loanPricerId, "gnma")) {
            guaranteeFee = Double.parseDouble(gnmaGuaranteeFee);
        }

        return guaranteeFee;
    }

    public double getBaseServicingFee(Pool pool) {
        String loanPricerId = pool.getLoanPricerId();
        double baseServicingFee = 0;
        if (stringContains(loanPricerId, "fhlmc")) {
            baseServicingFee = Double.parseDouble(fhlmcBaseServicingFee);
        } else if (stringContains(loanPricerId, "fnma")) {
            baseServicingFee = Double.parseDouble(fnmaBaseServicingFee);
        } else if (stringContains(loanPricerId, "gnma")) {
            baseServicingFee = Double.parseDouble(gnmaBaseServicingFee);
        }

        return baseServicingFee;
    }



}
