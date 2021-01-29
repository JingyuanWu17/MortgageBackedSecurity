package MBSFactory;

import MBSFactory.CalculatorFactories.MBSCouponFactory;
import MBSFactory.CalculatorFactories.RemainingTermFactory;
import MBSFactory.CalculatorFactories.SettlementDateFactory;
import MBSFactory.DataLookupFactories.*;

public class FactoryProducer {

    public static Factory getFactory(String packageName, String moduleName) {
        if (packageName.equals("DataLookup")) {
            return getDataLookupFactory(moduleName);
        } else if (packageName.equals("Calculator")) {
            return getCalculatorFactory(moduleName);
        }
        return null;
    }

    private static Factory getDataLookupFactory(String moduleName) {
        switch (moduleName) {
            case "BaseServicingFee":
                return new BaseServicingFeeFactory();
            case "BaseServicingMults":
                return new BaseServicingMultsFactory();
            case "BuyUpDownMults":
                return new BuyUpDownMultsFactory();
            case "GuaranteeFee":
                return new GuaranteeFeeFactory();
            case "MarketPrice":
                return new MarketPriceFactory();
        }
        return null;
    }

    private static Factory getCalculatorFactory(String moduleName) {
        switch (moduleName) {
            case "MBSCoupon":
                return new MBSCouponFactory();
            case "RemainingTerm":
                return new RemainingTermFactory();
            case "SettlementDate":
                return new SettlementDateFactory();
        }
        return null;
    }
}
