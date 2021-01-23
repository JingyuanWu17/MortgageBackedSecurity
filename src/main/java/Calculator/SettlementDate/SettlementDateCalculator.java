package Calculator.SettlementDate;

import InputData.Loan;
import InputData.Pool;

import java.util.Date;
import java.util.List;

public abstract class SettlementDateCalculator {

    public abstract List<Date> calculate(Loan loan, Pool pool);

}
