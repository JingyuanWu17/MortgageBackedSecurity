package Calculator.MBSCoupon;

import InputData.Loan;
import InputData.Pool;

import java.util.List;

public abstract class MBSCoupon {

    public abstract List<Double> calculate(Loan loan, Pool pool);

}
