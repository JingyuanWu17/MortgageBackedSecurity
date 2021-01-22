package Engine.Condition;

import InputData.Loan;
import InputData.Pool;

import java.lang.reflect.Field;

public class Condition {

    public boolean check(Loan loan, Pool pool, String fieldToCheck) {
        String poolValue = getFieldValue(pool.getClass(), pool, fieldToCheck);
        //Return true early if there is no value for this field in pool
        if (poolValue.isEmpty()) return true;

        String loanValue = getFieldValue(loan.getClass(), loan, fieldToCheck);
        //Return true if there is no such field in loan or their values are equal.
        return loanValue.isEmpty() || loanValue.equals(poolValue);
    }


    protected String getFieldValue(Class cls, Object obj, String fieldName) {
        String value = "";
        try {
            Field field = cls.getDeclaredField(fieldName);
            field.setAccessible(true);
            value = (String) field.get(obj);
        } catch (NoSuchFieldException ignored) {
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return value;
    }

}
