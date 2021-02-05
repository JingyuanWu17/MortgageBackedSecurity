package TestDataLookup;

import Calculator.SettlementDate.SettlementDateCalcV1;
import Util.Configuration.ConfigFile;
import DataLookup.MarketPrice.MarketPriceLookup;
import DataLookup.MarketPrice.MarketPriceLookupV1;
import MBSData.Loan;
import MBSData.Pool;
import com.opencsv.bean.CsvToBeanBuilder;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Date;
import java.util.List;

public class TestMarketPriceLookup {

    @Test
    public void test_getMarketPrice() throws FileNotFoundException {
        List<Loan> loans = new CsvToBeanBuilder(new FileReader("src/main/InputSamples/Loans.csv")).withType(Loan.class).build().parse();
        Loan loan = loans.get(0);

        List<Pool> pools = new CsvToBeanBuilder(new FileReader("src/main/InputSamples/Pools.csv")).withType(Pool.class).build().parse();
        Pool pool = pools.get(2);


        ConfigFile cfg = new ConfigFile();
        SettlementDateCalcV1 sdc = new SettlementDateCalcV1(cfg);
        List<Date> settlementDates = sdc.calculate(loan, pool);
        Date settlementDate = settlementDates.get(0);

        MarketPriceLookup mPriceLookup = new MarketPriceLookupV1(cfg);
        double price = mPriceLookup.lookup(pool, 4.5, settlementDate);
        assertEquals(100.9565571, price, 0.000000001);
    }
}
