package Calculator.MBSCoupon;

import MBSData.Loan;
import MBSData.Pool;

import java.util.List;

public abstract class MBSCouponCalc {

    public abstract List<Double> calculate(Loan loan, Pool pool);

}
