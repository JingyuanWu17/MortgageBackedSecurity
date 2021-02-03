package DataLookup.MarketPrice;

import Util.Configuration.ConfigFile;
import MBSData.MarketPriceData;
import MBSData.Pool;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MarketPriceLookupV1 extends MarketPriceLookup {
    private String dataFileName;
    private List<MarketPriceData> dataList;

    public MarketPriceLookupV1(ConfigFile cfg) {
        super(cfg);
        Map V1_cfg = (Map) cfg.getMarketPriceLookup().get("V1");
        dataFileName = (String) V1_cfg.get("dataFileName");

        try {
            dataList = new CsvToBeanBuilder(new FileReader(dataFileName)).withType(MarketPriceData.class).build().parse();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public double lookup(Pool pool, double MBSCoupon, Date settlementDate) {
        String loanPricerId = pool.getLoanPricerId();
        String packageTypeNumber = pool.getPackageTypeNumber();

        double marketPrice = 0;
        for (MarketPriceData data : dataList) {
            if (stringMatch(loanPricerId, data.getPool_name())
                    && stringMatch(packageTypeNumber, data.getPool_id())
                    && doubleMatch(String.valueOf(MBSCoupon), data.getCoupon())
                    && dateMatch(settlementDate, data.getSettle_date())) {
                marketPrice = Double.parseDouble(data.getMarket_price());
                break;
            }
        }

        return marketPrice;
    }

}
