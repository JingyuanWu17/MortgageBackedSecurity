package Calculator.MBSCoupon;

import DataLookup.FeeLookup;
import InputData.Loan;
import InputData.Pool;
import Lib.ConfigLoader;

import java.util.ArrayList;
import java.util.List;

public class MBSCouponCalculatorBasic extends MBSCouponCalculator {
    private final static String configFileName = "src/main/resources/CalculatorConfig/MBSCouponCalculatorBasic.properties";
    private String maxSpread;

    public MBSCouponCalculatorBasic() {
        ConfigLoader.load(MBSCouponCalculatorBasic.class, this, configFileName);
    }

    @Override
    public List<Double> calculate(Loan loan, Pool pool) {
        double upperBound = getUpperBound(loan, pool);
        double lowerBound = getLowerBound(loan);

        return getHalfPointNumbers(lowerBound, upperBound);
    }

    private List<Double> getHalfPointNumbers(double lowerBound, double upperBound) {
        List<Double> res = new ArrayList<>();
        double floor = Math.floor(lowerBound);
        double ceiling = Math.floor(upperBound) + 1;
        for (double i = floor; i <= ceiling; i += 0.5) {
            if (i >= lowerBound && i <= upperBound) {
                res.add(i);
            }
        }

        return res;
    }

    private double getUpperBound(Loan loan, Pool pool) {
        FeeLookup feeLookup = new FeeLookup();
        double grossCoupon = Double.parseDouble(loan.getGrossCoupon());
        double guaranteeFee = feeLookup.getGuaranteeFee(pool);
        double baseServicingFee = feeLookup.getBaseServicingFee(pool);
        double buyUpRate = Double.parseDouble(loan.getBuyUpRate());
        double buyDownRate = Double.parseDouble(loan.getBuyDownRate());

        return grossCoupon - guaranteeFee - baseServicingFee - buyUpRate - buyDownRate;
    }

    private double getLowerBound(Loan loan) {
        return Double.parseDouble(loan.getGrossCoupon()) - Double.parseDouble(maxSpread);
    }

}
