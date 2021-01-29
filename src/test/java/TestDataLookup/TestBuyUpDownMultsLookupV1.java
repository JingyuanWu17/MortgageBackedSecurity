//package TestDataLookup;
//
//import Calculator.SettlementDateCalc.SettlementDateV1;
//import InputData.BuyUpDownData;
//import InputData.Loan;
//import InputData.Pool;
//import DataLookup.BuyUpDownMultsLookup.BuyUpDownMultsV1;
//import com.opencsv.bean.CsvToBeanBuilder;
//import org.junit.Test;
//
//import static org.junit.Assert.*;
//
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.util.Date;
//import java.util.List;
//
//public class TestBuyUpDownMultsLookup {
//
//    @Test
//    public void test_creatObject() throws FileNotFoundException {
//        List<Loan> loans = new CsvToBeanBuilder(new FileReader("src/main/InputSamples/Loans.csv")).withType(Loan.class).build().parse();
//        Loan loan = loans.get(0);
//
//        List<Pool> pools = new CsvToBeanBuilder(new FileReader("src/main/InputSamples/Pools.csv")).withType(Pool.class).build().parse();
//        Pool pool = pools.get(2);
//
//        SettlementDateV1 sdc = new SettlementDateV1();
//        List<Date> settlementDates = sdc.calculate(loan, pool);
//        Date settlementDate = settlementDates.get(0);
//
//        BuyUpDownMultsV1 buyUpDownMultsSearch = new BuyUpDownMultsV1();
//        BuyUpDownData buyUpDownData = buyUpDownMultsSearch.getBuyUpDownMults(loan, pool, settlementDate);
//
//        assertEquals("4.98", buyUpDownData.getBuy_up_mults());
//        assertEquals("6.38", buyUpDownData.getBuy_down_mults());
//    }
//}
