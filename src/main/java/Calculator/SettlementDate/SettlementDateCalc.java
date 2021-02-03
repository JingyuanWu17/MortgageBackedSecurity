package Calculator.SettlementDate;

import MBSData.Loan;
import MBSData.Pool;

import java.util.Date;
import java.util.List;

public abstract class SettlementDateCalc {

    public abstract List<Date> calculate(Loan loan, Pool pool);

}
