package TestDataLookup;

import Calculator.SettlementDateCalculator;
import DataLookup.BaseServicingMultsLookup;
import InputData.Loan;
import InputData.Pool;
import com.opencsv.bean.CsvToBeanBuilder;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestBaseServicingMultsLookup {

    @Test
    public void test_creatObject() throws FileNotFoundException {
        List<Loan> loans = new CsvToBeanBuilder(new FileReader("src/main/InputSamples/Loans.csv")).withType(Loan.class).build().parse();
        Loan loan = loans.get(0);

        List<Pool> pools = new CsvToBeanBuilder(new FileReader("src/main/InputSamples/Pools.csv")).withType(Pool.class).build().parse();
        Pool pool = pools.get(2);

        SettlementDateCalculator sdc = new SettlementDateCalculator();
        List<Date> settlementDates = sdc.calculate(loan, pool);
        Date settlementDate = settlementDates.get(0);

        BaseServicingMultsLookup baseServicingMultsLookup = new BaseServicingMultsLookup();

        double baseMults = baseServicingMultsLookup.getBaseServicingMults(pool, settlementDate);

        assertEquals(1.1, baseMults, 0.01);
    }
}
