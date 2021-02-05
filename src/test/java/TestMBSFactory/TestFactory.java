package TestMBSFactory;

import Calculator.MBSCoupon.MBSCouponCalc;
import Calculator.RemainingTerm.RemainingTermCalc;
import Util.Configuration.ConfigFile;
import MBSData.Loan;
import MBSData.Pool;
import MBSFactory.CalculatorFactories.MBSCouponFactory;
import MBSFactory.CalculatorFactories.RemainingTermFactory;
import com.opencsv.bean.CsvToBeanBuilder;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class TestFactory {
    ConfigFile cfg = new ConfigFile();
    String loansFileName = cfg.getLoansFileName();
    String poolsFileName = cfg.getPoolsFileName();

    List<Loan> loans = new CsvToBeanBuilder(new FileReader(loansFileName)).withType(Loan.class).build().parse();
    Loan loan = loans.get(0);

    List<Pool> pools = new CsvToBeanBuilder(new FileReader(poolsFileName)).withType(Pool.class).build().parse();
    Pool pool = pools.get(2);

    public TestFactory() throws FileNotFoundException {
    }

    @Test
    public void test_MBSCouponFactory() {
        MBSCouponFactory mbsCouponFactory = new MBSCouponFactory();
        MBSCouponCalc mbsCouponCalc = (MBSCouponCalc) mbsCouponFactory.create(cfg, loan, pool);

        String className = mbsCouponCalc.getClass().getName();
        assertEquals("Calculator.MBSCoupon.MBSCouponCalcV1", className);
    }

    @Test
    public void test_RemainingTermFactory() {
        RemainingTermFactory remainingTermFactory = new RemainingTermFactory();
        RemainingTermCalc remainingTermCalc = (RemainingTermCalc) remainingTermFactory.create(cfg, loan, pool);

        String className = remainingTermCalc.getClass().getName();
        assertEquals("Calculator.RemainingTerm.RemainingTermCalcV1", className);
    }
}
