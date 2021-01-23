package Calculator.MBSCoupon;

public class MBSCouponCalculatorFactory {

    public static MBSCouponCalculator getMBSCouponCalculator(String calculationMethod) {
        if (calculationMethod.equals("Basic")) {
            return new MBSCouponCalculatorBasic();
        }
        return null;
    }
}
