package InputData;

import com.opencsv.bean.CsvBindByName;

public class MarketPriceData {

    @CsvBindByName(column = "pool_name")
    private String pool_name;

    @CsvBindByName(column = "pool_id")
    private String pool_id;

    @CsvBindByName(column = "coupon")
    private String coupon;

    @CsvBindByName(column = "settle_date")
    private String settle_date;

    @CsvBindByName(column = "market_price")
    private String market_price;

    public String getPool_name() {
        return pool_name;
    }

    public void setPool_name(String pool_name) {
        this.pool_name = pool_name;
    }

    public String getPool_id() {
        return pool_id;
    }

    public void setPool_id(String pool_id) {
        this.pool_id = pool_id;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public String getSettle_date() {
        return settle_date;
    }

    public void setSettle_date(String settle_date) {
        this.settle_date = settle_date;
    }

    public String getMarket_price() {
        return market_price;
    }

    public void setMarket_price(String market_price) {
        this.market_price = market_price;
    }
}
