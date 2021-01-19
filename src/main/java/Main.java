import Calculator.SettlementDateCalculator;
import Data.Loan;
import Data.Pool;
import Engine.RuleEngine;
import Lib.ConfigLoader;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Date;
import java.util.List;

public class Main {

    private static String loanFileName;
    private static String poolFileName;

    public static void main(String[] args) throws FileNotFoundException {

        ConfigLoader.load(Main.class, null, "src/main/resources/mainConfig.properties");

        List<Loan> loans = new CsvToBeanBuilder(new FileReader(loanFileName)).withType(Loan.class).build().parse();

        List<Pool> pools = new CsvToBeanBuilder(new FileReader(poolFileName)).withType(Pool.class).build().parse();

        RuleEngine engine = new RuleEngine();

        for (Loan loan : loans) {

            System.out.println(loan.getLoanId());

            List<Pool> eligPools = engine.run(loan, pools);

            SettlementDateCalculator settlementDateCalculator = new SettlementDateCalculator();

            for (Pool eligPool : eligPools) {
                List<Date> settlementDates = settlementDateCalculator.calculate(loan, eligPool);

                System.out.println(eligPool.getPackageTypeNumber());

                for (Date date : settlementDates) {
                    System.out.println(date);
                }
            }


        }


    }
}
