package Calculator.RemainingTerm;

import InputData.Loan;

import java.time.LocalDate;
import java.time.Period;

public class RemainingTermCalcV1 extends RemainingTermCalc {

    @Override
    public int calculate(Loan loan) {
        String loanStatus = loan.getProcessStatus();
        int remainingTerm = 0;
        int originalTerm = Integer.parseInt(loan.getOriginalTerm());

        if (loanStatus.equals("Pipe")) {
            remainingTerm = originalTerm;
        } else if (loanStatus.equals("Inv")) {
            String firstPaymentDueDate = loan.getFirstPaymentDueDate();
            int diffMonth = 0;
            LocalDate now = LocalDate.now().withDayOfMonth(1);
            LocalDate firstDueDate = LocalDate.parse(firstPaymentDueDate).withDayOfMonth(1);
            if (now.isAfter(firstDueDate)) {
                diffMonth = Period.between(firstDueDate, now).getMonths();
            }
            remainingTerm = originalTerm - diffMonth;
        } else if (loanStatus.equals("Sold")) {
            remainingTerm = Integer.parseInt(loan.getAmortizationTerm());
        }

        return remainingTerm;
    }
}
