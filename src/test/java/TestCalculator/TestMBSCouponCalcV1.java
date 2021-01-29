package TestCalculator;

import Calculator.MBSCoupon.MBSCouponCalcV1;
import Configuration.ConfigFile;
import InputData.Loan;
import InputData.Pool;
import com.opencsv.bean.CsvToBeanBuilder;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class TestMBSCouponCalcV1 {

    ConfigFile cfg = new ConfigFile();
    String loansFileName = cfg.getLoansFileName();
    String poolsFileName = cfg.getPoolsFileName();

    @Test
    public void test_calculate() throws FileNotFoundException {
        List<Loan> loans = new CsvToBeanBuilder(new FileReader(loansFileName)).withType(Loan.class).build().parse();
        Loan loan = loans.get(0);

        List<Pool> pools = new CsvToBeanBuilder(new FileReader(poolsFileName)).withType(Pool.class).build().parse();
        Pool pool = pools.get(2);

        MBSCouponCalcV1 mbsCouponCalcV1 = new MBSCouponCalcV1(cfg);

        List<Double> MBSCoupons = mbsCouponCalcV1.calculate(loan, pool);
        for (double MBSCoupon : MBSCoupons) {
            System.out.println(MBSCoupon);
        }
    }
}
