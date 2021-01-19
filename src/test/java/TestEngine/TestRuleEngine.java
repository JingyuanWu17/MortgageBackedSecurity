package TestEngine;

import Data.Loan;
import Data.Pool;
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
        List<Pool> pools = new CsvToBeanBuilder(new FileReader("src/main/Input/pools.csv")).withType(Pool.class).build().parse();

        List<Loan> loans = new CsvToBeanBuilder(new FileReader("src/main/Input/loans.csv")).withType(Loan.class).build().parse();
        Loan loan = loans.get(1);

        RuleEngine engine = new RuleEngine();

        List<Pool> eligPools = engine.run(loan, pools);

        assertEquals(6, eligPools.size());

        for (Pool pool : eligPools) {
            System.out.println(pool.getPackageTypeNumber());
        }
    }

//    @Test
//    public void test_checkString() throws FileNotFoundException {
//        List<Loan> loans = new CsvToBeanBuilder(new FileReader("src/main/Input/loans.csv")).withType(Loan.class).build().parse();
//        Loan loan = loans.get(0);
//
//        RuleEngine engine = new RuleEngine();
//
//        assertTrue(engine.checkString(loan, "GrossCoupon", "4.25"));
//
//        assertTrue(engine.checkString(loan, "FICO", "709"));
//
//        assertFalse(engine.checkString(loan, "SecuritizeHfiFlag", "1"));

//    }
//
//    @Test
//    public void test_checkRange() throws FileNotFoundException {
//        List<Loan> loans = new CsvToBeanBuilder(new FileReader("src/main/Input/loans.csv")).withType(Loan.class).build().parse();
//        Loan loan = loans.get(0);
//
//        RuleEngine engine = new RuleEngine();
//
//        assertFalse(engine.checkRange(loan, "AmortizationTermRange", "[0-180]"));
//
//        assertTrue(engine.checkRange(loan, "AmortizationTermRange", "(240-360]"));
//
//    }
//
//    @Test
//    public void test_checkList() throws FileNotFoundException {
//        List<Loan> loans = new CsvToBeanBuilder(new FileReader("src/main/Input/loans.csv")).withType(Loan.class).build().parse();
//        Loan loan = loans.get(0);
//
//        RuleEngine engine = new RuleEngine();
//
//        assertTrue(engine.checkList(loan, "LoanPurposeTypeList", "P, Q, R"));
//
//    }
//
//    @Test
//    public void test_checkElig() throws FileNotFoundException {
//        List<Loan> loans = new CsvToBeanBuilder(new FileReader("src/main/Input/loans.csv")).withType(Loan.class).build().parse();
//        Loan loan = loans.get(0);
//
//        List<Pool> pools = new CsvToBeanBuilder(new FileReader("src/main/Input/pools.csv")).withType(Pool.class).build().parse();
//        Pool pool = pools.get(2);
//
//        RuleEngine engine = new RuleEngine();
//
//        assertTrue(engine.checkElig(loan, pool));
//
//    }


}
