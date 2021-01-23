package TestCalculator;

import Calculator.MBSCoupon.MBSCouponCalculatorBasic;
import InputData.Loan;
import InputData.Pool;
import com.opencsv.bean.CsvToBeanBuilder;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class TestMBSCouponCalculatorBasic {

    @Test
    public void test_calculate() throws FileNotFoundException {
        List<Loan> loans = new CsvToBeanBuilder(new FileReader("src/main/InputSamples/Loans.csv")).withType(Loan.class).build().parse();
        Loan loan = loans.get(1);

        List<Pool> pools = new CsvToBeanBuilder(new FileReader("src/main/InputSamples/Pools.csv")).withType(Pool.class).build().parse();
        Pool pool = pools.get(2);

        MBSCouponCalculatorBasic mbsCouponCalculator = new MBSCouponCalculatorBasic();

        List<Double> MBSCoupons = mbsCouponCalculator.calculate(loan, pool);
        for (double MBSCoupon : MBSCoupons) {
            System.out.println(MBSCoupon);
        }
    }
}
