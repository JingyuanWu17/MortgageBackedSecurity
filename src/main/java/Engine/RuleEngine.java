package Engine;

import Data.Loan;
import Data.Pool;
import Engine.Condition.Condition;
import Engine.Condition.ConditionFactory;

import java.lang.reflect.Field;
import java.util.*;

public class RuleEngine {

    /**
     *
     * @param loan one specific loan
     * @param pools all available pools on market
     * @return eligible pools selected from pools for this loan
     */
    public List<Pool> run(Loan loan, List<Pool> pools) {
        List<Pool> eligPools = new ArrayList<>();
        for (Pool pool : pools) {
            if (checkElig(loan, pool)) {
                eligPools.add(pool);
            }
        }

        return eligPools;
    }

    private boolean checkElig(Loan loan, Pool pool) {
        Class poolClass = pool.getClass();
        Field[] poolFields = poolClass.getDeclaredFields();

        for (Field poolField : poolFields) {
            String poolFieldName = poolField.getName();
            /*
            Use pool's field name to get corresponding Condition object,
            Use this object to check if the values of loan and pool on this
            field match.
             */
            Condition condition = ConditionFactory.getCondition(poolFieldName);
            if (!condition.check(loan, pool, poolFieldName)) {
                return false;
            }
        }

        return true;
    }
}
