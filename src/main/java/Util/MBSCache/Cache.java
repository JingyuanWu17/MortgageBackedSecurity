package Util.MBSCache;

import MBSData.Loan;
import MBSData.Pool;

public interface Cache<V> {

    boolean containsKey(Loan loan, Pool pool);

    V get(Loan loan, Pool pool);

    void put(Loan loan, Pool pool, V v);

}
