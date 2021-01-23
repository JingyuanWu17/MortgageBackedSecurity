package DataLookup;

import InputData.MarketPriceData;
import InputData.Pool;
import Lib.ConfigLoader;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Date;
import java.util.List;

public class MarketPriceLookup extends TableLookup {
    private final static String configFileName = "src/main/resources/DataLookupConfig/MarketPriceLookup.properties";
    private String marketPriceDataFileName;
    private List<MarketPriceData> marketPriceDataList;

    public MarketPriceLookup() {
        ConfigLoader.load(MarketPriceLookup.class, this, configFileName);
        try {
            marketPriceDataList = new CsvToBeanBuilder(new FileReader(marketPriceDataFileName)).withType(MarketPriceData.class).build().parse();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public double getMarketPrice(Pool pool, double MBSCoupon, Date settlementDate) {
        String loanPricerId = pool.getLoanPricerId();
        String packageTypeNumber = pool.getPackageTypeNumber();

        double marketPrice = 0;
        for (MarketPriceData marketPriceData : marketPriceDataList) {
            if (stringMatch(loanPricerId, marketPriceData.getPool_name())
                    && stringMatch(packageTypeNumber, marketPriceData.getPool_id())
                    && doubleMatch(String.valueOf(MBSCoupon), marketPriceData.getCoupon())
                    && dateMatch(settlementDate, marketPriceData.getSettle_date())) {
                marketPrice = Double.parseDouble(marketPriceData.getMarket_price());
                break;
            }
        }

        return marketPrice;
    }

}
