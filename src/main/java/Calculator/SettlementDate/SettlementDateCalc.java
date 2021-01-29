package Calculator.SettlementDateCalc;

import InputData.Loan;
import InputData.Pool;

import java.util.Date;
import java.util.List;

public abstract class SettlementDate {

    public abstract List<Date> calculate(Loan loan, Pool pool);

}
