package TestCalculator;

import Calculator.SettlementDateCalculator;
import Data.Loan;
import Data.Pool;
import com.opencsv.bean.CsvToBeanBuilder;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class SettlementDateCalculatorTest {

    @Test
    public void test_createObject() {
        SettlementDateCalculator sd = new SettlementDateCalculator();
        assertEquals("2", sd.getAgencyProcessDays());
        assertEquals("2", sd.getWarehouseDays());
    }

    @Test
    public void test_calculate() throws FileNotFoundException {

        List<Loan> loans = new CsvToBeanBuilder(new FileReader("src/main/Input/loans.csv")).withType(Loan.class).build().parse();
        Loan loan = loans.get(0);

        List<Pool> pools = new CsvToBeanBuilder(new FileReader("src/main/Input/pools.csv")).withType(Pool.class).build().parse();
        Pool pool = pools.get(2);

        SettlementDateCalculator sd = new SettlementDateCalculator();

        List<Date> dates = sd.calculate(loan, pool);
        for (Date date : dates) {
            System.out.println(date);
        }

    }

    @Test
    public void test_calculate2() throws FileNotFoundException {

        List<Loan> loans = new CsvToBeanBuilder(new FileReader("src/main/Input/loans.csv")).withType(Loan.class).build().parse();
        Loan loan = loans.get(1);

        List<Pool> pools = new CsvToBeanBuilder(new FileReader("src/main/Input/pools.csv")).withType(Pool.class).build().parse();
        Pool pool = pools.get(1);

        SettlementDateCalculator sd = new SettlementDateCalculator();

        List<Date> dates = sd.calculate(loan, pool);
        for (Date date : dates) {
            System.out.println(date);
        }

    }


//    @Test
//    public void test_calculateEPSD() throws FileNotFoundException {
//        List<Loan> loans = new CsvToBeanBuilder(new FileReader("src/main/Input/loans.csv")).withType(Loan.class).build().parse();
//        Loan loan = loans.get(0);
//        Date epsd = sd.calculateEPSD(loan);
//        System.out.println(epsd);
//    }
//
//    @Test
//    public void test_getBma() {
//        List<Date> list = sd.getFourMonthsBMAs();
//        for (Date date : list) {
//            System.out.println(date);
//        }
//    }


}