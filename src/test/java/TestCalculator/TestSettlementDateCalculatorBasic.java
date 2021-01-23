package TestCalculator;

import Calculator.SettlementDate.SettlementDateCalculatorBasic;
import InputData.Loan;
import InputData.Pool;
import com.opencsv.bean.CsvToBeanBuilder;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class TestSettlementDateCalculatorBasic {

    @Test
    public void test_createObject() {
        SettlementDateCalculatorBasic sd = new SettlementDateCalculatorBasic();
        assertEquals("2", sd.getAgencyProcessDays());
        assertEquals("2", sd.getWarehouseDays());
    }

    @Test
    public void test_calculate() throws FileNotFoundException {

        List<Loan> loans = new CsvToBeanBuilder(new FileReader("src/main/InputSamples/Loans.csv")).withType(Loan.class).build().parse();
        Loan loan = loans.get(0);

        List<Pool> pools = new CsvToBeanBuilder(new FileReader("src/main/InputSamples/Pools.csv")).withType(Pool.class).build().parse();
        Pool pool = pools.get(2);

        SettlementDateCalculatorBasic sd = new SettlementDateCalculatorBasic();

        List<Date> dates = sd.calculate(loan, pool);
        for (Date date : dates) {
            System.out.println(date);
        }

    }

    @Test
    public void test_calculate2() throws FileNotFoundException {

        List<Loan> loans = new CsvToBeanBuilder(new FileReader("src/main/InputSamples/Loans.csv")).withType(Loan.class).build().parse();
        Loan loan = loans.get(1);

        List<Pool> pools = new CsvToBeanBuilder(new FileReader("src/main/InputSamples/Pools.csv")).withType(Pool.class).build().parse();
        Pool pool = pools.get(1);

        SettlementDateCalculatorBasic sd = new SettlementDateCalculatorBasic();

        List<Date> dates = sd.calculate(loan, pool);
        for (Date date : dates) {
            System.out.println(date);
        }

    }


//    @Test
//    public void test_calculateEPSD() throws FileNotFoundException {
//        List<Loan> loans = new CsvToBeanBuilder(new FileReader("src/main/Input/Loans.csv")).withType(Loan.class).build().parse();
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