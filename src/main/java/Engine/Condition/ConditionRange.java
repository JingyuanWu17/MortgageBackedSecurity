package Engine.Condition;

import InputData.Loan;
import InputData.Pool;

public class ConditionRange extends Condition {

    @Override
    public boolean check(Loan loan, Pool pool, String fieldToCheck) {
        String poolValue = getFieldValue(pool.getClass(), pool, fieldToCheck);
        if (poolValue.isEmpty()) return true;

        String loanFieldName = fieldToCheck.substring(0, fieldToCheck.indexOf("Range"));
        String loanValue = getFieldValue(loan.getClass(), loan, loanFieldName);
        if (loanValue.isEmpty()) return true;

        poolValue = poolValue.trim();
        boolean leftInclusive = poolValue.charAt(0) == '[';
        boolean rightInclusive = poolValue.charAt(poolValue.length() - 1) == ']';
        String[] arr = poolValue.substring(1, poolValue.length() - 1).split("-");
        double leftBound = Double.parseDouble(arr[0].trim());
        double rightBound = Double.parseDouble(arr[1].trim());
        double num = Double.parseDouble(loanValue);

        return (num > leftBound && num < rightBound) || (num == leftBound && leftInclusive) || (num == rightBound && rightInclusive);
    }
}
