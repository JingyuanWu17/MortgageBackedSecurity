package Engine.Condition;

import InputData.Loan;
import InputData.Pool;

import java.util.HashSet;
import java.util.Set;

public class ConditionList extends Condition {

    @Override
    public boolean check(Loan loan, Pool pool, String fieldToCheck) {
        String poolValue = getFieldValue(pool.getClass(), pool, fieldToCheck);
        if (poolValue.isEmpty()) return true;

        String loanFieldName = fieldToCheck.substring(0, fieldToCheck.indexOf("List"));
        String loanValue = getFieldValue(loan.getClass(), loan, loanFieldName);
        if (loanValue.isEmpty()) return true;

        poolValue = poolValue.trim();
        String[] arr = poolValue.split(",");
        Set<String> set = new HashSet<>();
        for (String str : arr) {
            set.add(str.trim());
        }

        return set.contains(loanValue);
    }
}
