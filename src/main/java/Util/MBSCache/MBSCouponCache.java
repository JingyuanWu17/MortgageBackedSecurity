package Util.MBSCache;

import MBSData.Loan;
import MBSData.Pool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MBSCouponCache implements Cache<List<Double>> {
    private Map<Node, List<Double>> map;

    public MBSCouponCache() {
        map = new HashMap<>();
    }

    @Override
    public boolean containsKey(Loan loan, Pool pool) {
        Node node = new Node(loan, pool);
        return map.containsKey(node);
    }

    @Override
    public List<Double> get(Loan loan, Pool pool) {
        Node node = new Node(loan, pool);
        return map.get(node);
    }

    @Override
    public void put(Loan loan, Pool pool, List<Double> value) {
        Node node = new Node(loan, pool);
        map.put(node, value);

    }

    private static class Node {
        private final String agency;
        private final double grossCoupon;
        private final double buyUpRate;
        private final double buyDownRate;

        public Node(Loan loan, Pool pool) {
            String loanPricerId = pool.getLoanPricerId();
            if (loanPricerId.contains("fhlmc")) {
                agency = "fhlmc";
            } else if (loanPricerId.contains("fnma")) {
                agency = "fnma";
            } else {
                agency = "gnma";
            }
            grossCoupon = Double.parseDouble(loan.getGrossCoupon());
            buyUpRate = Double.parseDouble(loan.getBuyUpRate());
            buyDownRate = Double.parseDouble(loan.getBuyDownRate());
        }

        @Override
        public int hashCode() {
            int hash = 17;
            hash = hash * 31 + agency.hashCode();
            hash = hash * 31 + String.valueOf(grossCoupon).hashCode();
            hash = hash * 31 + String.valueOf(buyUpRate).hashCode();
            hash = hash * 31 + String.valueOf(buyDownRate).hashCode();
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Node node = (Node) obj;
            return agency.equals(node.agency) && grossCoupon == node.grossCoupon && buyUpRate == node.buyUpRate && buyDownRate == node.buyDownRate;
        }
    }
}
