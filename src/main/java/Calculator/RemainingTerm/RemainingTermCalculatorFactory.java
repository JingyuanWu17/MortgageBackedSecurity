package Calculator.RemainingTerm;

public class RemainingTermCalculatorFactory {

    public static RemainingTermCalculator getRemainingTermCalculator(String calculationMethod) {
        if (calculationMethod.equals("basic")) {
            return new RemainingTermCalculator();
        }
        return null;
    }
}
