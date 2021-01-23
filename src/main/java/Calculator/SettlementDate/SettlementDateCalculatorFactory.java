package Calculator.SettlementDate;

public class SettlementDateCalculatorFactory {

    public static SettlementDateCalculator getSettlementDateCalculator(String calculationMethod) {
        if (calculationMethod.equals("Basic")) {
            return new SettlementDateCalculatorBasic();
        }
        return null;
    }
}
