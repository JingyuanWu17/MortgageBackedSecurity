package Calculator.RemainingTerm;

public class RemainingTermCalculatorFactory {

    public static RemainingTermCalculatorBasic getRemainingTermCalculator(String calculationMethod) {
        if (calculationMethod.equals("Basic")) {
            return new RemainingTermCalculatorBasic();
        }
        return null;
    }
}
