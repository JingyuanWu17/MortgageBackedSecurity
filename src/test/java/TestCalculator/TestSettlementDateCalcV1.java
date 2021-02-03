package TestCalculator;

import Calculator.SettlementDate.SettlementDateCalcV1;
import Util.Configuration.ConfigFile;
import MBSData.Loan;
import MBSData.Pool;
import com.opencsv.bean.CsvToBeanBuilder;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Date;
import java.util.List;

public class TestSettlementDateCalcV1 {

    ConfigFile cfg = new ConfigFile();
    String loansFileName = cfg.getLoansFileName();
    String poolsFileName = cfg.getPoolsFileName();

//    @Test
//    public void test_createObject() {
//        SettlementDateCalcV1 sd = new SettlementDateCalcV1(cfg);
//        assertEquals("2", sd.getAgencyProcessDays());
//        assertEquals("2", sd.getWarehouseDays());
//        System.out.println(sd.getBMAsFileName());
//        System.out.println(sd.getSettlementControlDataFileName());
//    }

    @Test
    public void test_calculate() throws FileNotFoundException {
        List<Loan> loans = new CsvToBeanBuilder(new FileReader(loansFileName)).withType(Loan.class).build().parse();
        Loan loan = loans.get(0);

        List<Pool> pools = new CsvToBeanBuilder(new FileReader(poolsFileName)).withType(Pool.class).build().parse();
        Pool pool = pools.get(2);

        SettlementDateCalcV1 sdc = new SettlementDateCalcV1(cfg);

        List<Date> dates = sdc.calculate(loan, pool);
        for (Date date : dates) {
            System.out.println(date);
        }
    }

    @Test
    public void test_calculate2() throws FileNotFoundException {
        List<Loan> loans = new CsvToBeanBuilder(new FileReader(loansFileName)).withType(Loan.class).build().parse();
        Loan loan = loans.get(1);

        List<Pool> pools = new CsvToBeanBuilder(new FileReader(poolsFileName)).withType(Pool.class).build().parse();
        Pool pool = pools.get(1);

        SettlementDateCalcV1 sdc = new SettlementDateCalcV1(cfg);

        List<Date> dates = sdc.calculate(loan, pool);
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