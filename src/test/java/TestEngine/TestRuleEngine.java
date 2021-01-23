package TestEngine;

import InputData.Loan;
import InputData.Pool;
import Engine.RuleEngine;
import com.opencsv.bean.CsvToBeanBuilder;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import static org.junit.Assert.*;

public class TestRuleEngine {

    @Test
    public void test_checkEngine() throws FileNotFoundException {
        List<Pool> pools = new CsvToBeanBuilder(new FileReader("src/main/InputSamples/Pools.csv")).withType(Pool.class).build().parse();

        List<Loan> loans = new CsvToBeanBuilder(new FileReader("src/main/InputSamples/Loans.csv")).withType(Loan.class).build().parse();

        Loan loan1 = loans.get(0);
        Loan loan2 = loans.get(1);

        RuleEngine engine = new RuleEngine();

        List<Pool> eligPools1 = engine.run(loan1, pools);
        assertEquals(6, eligPools1.size());

        List<Pool> eligPools2 = engine.run(loan2, pools);
        assertEquals(7, eligPools2.size());
    }

//    @Test
//    public void test_checkElig() throws FileNotFoundException {
//        List<Loan> loans = new CsvToBeanBuilder(new FileReader("src/main/Input/Loans.csv")).withType(Loan.class).build().parse();
//        Loan loan = loans.get(0);
//
//        List<Pool> pools = new CsvToBeanBuilder(new FileReader("src/main/Input/Pools.csv")).withType(Pool.class).build().parse();
//        Pool pool = pools.get(2);
//
//        RuleEngine engine = new RuleEngine();
//
//        assertTrue(engine.checkElig(loan, pool));
//
//    }

}
