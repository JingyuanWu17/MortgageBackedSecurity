package OutputData;

import InputData.BuyUpDownData;
import InputData.Loan;
import InputData.Pool;

import java.util.Date;

public class Possibility {
    private Loan loan;
    private Pool eligPool;
    private Date settlementDate;
    private double MBSCoupon;
    private double marketPrize;
    private BuyUpDownData buyUpDownData;
    private double baseServicingMults;
    private double baseServicingFee;
    private double price;

    public Possibility(Loan loan, Pool eligPool, Date settlementDate, double MBSCoupon, double marketPrize, BuyUpDownData buyUpDownData, double baseServicingMults, double baseServicingFee, double price) {
        this.loan = loan;
        this.eligPool = eligPool;
        this.settlementDate = settlementDate;
        this.MBSCoupon = MBSCoupon;
        this.marketPrize = marketPrize;
        this.buyUpDownData = buyUpDownData;
        this.baseServicingMults = baseServicingMults;
        this.baseServicingFee = baseServicingFee;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Loan: " + loan.getLoanId()
                + " Pool: " + eligPool.getPackageTypeNumber()
                + " SettlementDate: " + settlementDate
                + " MBSCoupon: " + MBSCoupon
                + " MarketPrice: " + marketPrize
                + " Price: " + price;
    }

    public Loan getLoan() {
        return loan;
    }

    public Pool getEligPool() {
        return eligPool;
    }

    public Date getSettlementDate() {
        return settlementDate;
    }

    public double getMBSCoupon() {
        return MBSCoupon;
    }

    public double getMarketPrize() {
        return marketPrize;
    }

    public BuyUpDownData getBuyUpDownData() {
        return buyUpDownData;
    }

    public double getBaseServicingMults() {
        return baseServicingMults;
    }

    public double getBaseServicingFee() {
        return baseServicingFee;
    }

    public double getPrice() {
        return price;
    }
}
