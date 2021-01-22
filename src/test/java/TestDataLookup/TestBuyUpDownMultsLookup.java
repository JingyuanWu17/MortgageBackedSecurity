package TestDataLookup;

import Calculator.SettlementDateCalculator;
import OutputData.BuyUpDownMults;
import InputData.Loan;
import InputData.Pool;
import DataLookup.BuyUpDownMultsLookup;
import com.opencsv.bean.CsvToBeanBuilder;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Date;
import java.util.List;

public class TestBuyUpDownMultsLookup {

    @Test
    public void test_creatObject() throws FileNotFoundException {
        List<Loan> loans = new CsvToBeanBuilder(new FileReader("src/main/InputSamples/Loans.csv")).withType(Loan.class).build().parse();
        Loan loan = loans.get(0);

        List<Pool> pools = new CsvToBeanBuilder(new FileReader("src/main/InputSamples/Pools.csv")).withType(Pool.class).build().parse();
        Pool pool = pools.get(2);

        SettlementDateCalculator sdc = new SettlementDateCalculator();
        List<Date> settlementDates = sdc.calculate(loan, pool);
        Date settlementDate = settlementDates.get(0);

        BuyUpDownMultsLookup buyUpDownMultsSearch = new BuyUpDownMultsLookup();
        BuyUpDownMults buyUpDownMults = buyUpDownMultsSearch.getBuyUpDownMults(loan, pool, settlementDate);

        assertEquals(4.98, buyUpDownMults.getBuy_up_mults(), 0.0001);
        assertEquals(6.38, buyUpDownMults.getBuy_down_mults(), 0.0001);
    }
}
