package Util.MBSCache;

import MBSData.Loan;
import MBSData.Pool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SettlementDateCache implements Cache<List<Date>> {
    protected Map<Node, List<Date>> map;

    public SettlementDateCache() {
        map = new HashMap<>();
    }

    @Override
    public boolean containsKey(Loan loan, Pool pool) {
        Node node = new Node(loan, pool);
        return map.containsKey(node);
    }

    @Override
    public List<Date> get(Loan loan, Pool pool) {
        Node node = new Node(loan, pool);
        return map.get(node);
    }

    @Override
    public void put(Loan loan, Pool pool, List<Date> value) {
        Node node = new Node(loan, pool);
        map.put(node, value);

    }

    private static class Node {
        private final String packageTypeNumber;
        private final String loanPricerId;
        private final String lockExpirationDate;
        private final String loanStatus;
        private final String currentDate;


        public Node(Loan loan, Pool pool) {
            packageTypeNumber = pool.getPackageTypeNumber();
            loanPricerId = pool.getLoanPricerId();
            lockExpirationDate = loan.getLockExpirationDate();
            loanStatus = loan.getProcessStatus();
            currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        }

        @Override
        public int hashCode() {
            int hash = 17;
            hash = hash * 31 + packageTypeNumber.hashCode();
            hash = hash * 31 + loanPricerId.hashCode();
            hash = hash * 31 + loanStatus.hashCode();
            if (loanStatus.equals("Sold")) {
                return hash * 31 + currentDate.hashCode();
            }
            return hash * 31 + lockExpirationDate.hashCode();

        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Node node = (Node) obj;
            if (packageTypeNumber.equals(node.packageTypeNumber) && loanPricerId.equals(node.loanPricerId) && loanStatus.equals(node.loanStatus)) {
                if (loanStatus.equals("Sold")) {
                    return currentDate.equals(node.currentDate);
                } else {
                    return lockExpirationDate.equals(node.lockExpirationDate);
                }
            }
            return false;
        }
    }
}
