package TestData;

import InputData.MarketPriceData;
import com.opencsv.bean.CsvToBeanBuilder;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class TestMarketPriceData {

    @Test
    public void test_creatObject() throws FileNotFoundException {
        String fileName = "src/main/InputSamples/MarketPriceData.csv";
        List<MarketPriceData> marketPriceDataList = new CsvToBeanBuilder(new FileReader(fileName)).withType(MarketPriceData.class).build().parse();
        System.out.println(marketPriceDataList.size());
        for (MarketPriceData marketPriceData : marketPriceDataList) {
            System.out.println(marketPriceData.getMarket_price());
        }
    }
}
