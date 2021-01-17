package Engine;

import Data.Loan;
import Data.Pool;

import java.lang.reflect.Field;
import java.util.*;

public class RuleEngine {

    //Return eligible pools for loan
    public List<Pool> run(Loan loan, List<Pool> pools) {
        List<Pool> eligPools = new ArrayList<>();
        for (Pool pool : pools) {
            if (checkElig(loan, pool)) {
                eligPools.add(pool);
            }
        }
        return eligPools;
    }

    public boolean checkElig(Loan loan, Pool pool) {
        Class poolClass = pool.getClass();
        Field[] poolFields = poolClass.getDeclaredFields();
        for (Field poolField : poolFields) {
            poolField.setAccessible(true);
            try {
                String poolValue = (String) poolField.get(pool);
                if (poolValue.equals("")) continue;
                boolean isValid;
                String poolFieldName = poolField.getName();
                if (poolFieldName.contains("Range")) {
                    isValid = checkRange(loan, poolFieldName, poolValue);
                } else if (poolFieldName.contains("List")) {
                    isValid = checkList(loan, poolFieldName, poolValue);
                } else {
                    isValid = checkString(loan, poolFieldName, poolValue);
                }
                if (!isValid) return false;

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public boolean checkString(Loan loan, String poolFieldName, String poolValue) {
        String loanValue = getLoanValue(loan, poolFieldName);
        if (loanValue.isEmpty()) return true;
        return loanValue.equals(poolValue);
    }

    public boolean checkRange(Loan loan, String poolFieldName, String poolValue) {
        String loanFieldName = poolFieldName.substring(0, poolFieldName.indexOf("Range"));
        String loanValue = getLoanValue(loan, loanFieldName);
        if (loanValue.isEmpty()) return true;
        int n = poolValue.length();
        boolean leftInclusive = poolValue.charAt(0) == '[';
        boolean rightInclusive = poolValue.charAt(n - 1) == ']';
        String[] arr = poolValue.substring(1, n - 1).split("-");
        double leftBound = Double.parseDouble(arr[0]);
        double rightBound = Double.parseDouble(arr[1]);
        double num = Double.parseDouble(loanValue);

        return (num > leftBound && num < rightBound) || (num == leftBound && leftInclusive) || (num == rightBound && rightInclusive);
    }

    public boolean checkList(Loan loan, String poolFieldName, String poolValue) {
        String loanFieldName = poolFieldName.substring(0, poolFieldName.indexOf("List"));
        String loanValue = getLoanValue(loan, loanFieldName);
        if (loanValue.isEmpty()) return true;
        String[] arr = poolValue.split(",");
        Set<String> set = new HashSet<>(Arrays.asList(arr));
        return set.contains(loanValue);
    }


    private String getLoanValue(Loan loan, String loanFieldName) {
        Class loanClass = loan.getClass();
        String loanValue = "";
        try {
            Field field = loanClass.getDeclaredField(loanFieldName);
            field.setAccessible(true);
            loanValue = (String) field.get(loan);
        } catch (NoSuchFieldException ignored) {
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return loanValue;
    }
}
