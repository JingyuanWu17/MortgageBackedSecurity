import Calculator.MBSCoupon.MBSCouponCalculator;
import Calculator.MBSCoupon.MBSCouponCalculatorFactory;
import Calculator.SettlementDate.SettlementDateCalculator;
import Calculator.SettlementDate.SettlementDateCalculatorFactory;
import DataLookup.BaseServicingMultsLookup;
import DataLookup.BuyUpDownMultsLookup;
import DataLookup.FeeLookup;
import DataLookup.MarketPriceLookup;
import InputData.BuyUpDownData;
import InputData.Loan;
import InputData.Pool;
import Engine.RuleEngine;
import Lib.ConfigLoader;
import OutputData.Possibility;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    private static String loanFileName;
    private static String poolFileName;
    private static String MBSCouponCalculationMethod;
    private static String settlementDateCalculationMethod;

    public static void main(String[] args) throws FileNotFoundException {

        ConfigLoader.load(Main.class, null, "src/main/resources/Main.properties");

        List<Loan> loans = new CsvToBeanBuilder(new FileReader(loanFileName)).withType(Loan.class).build().parse();

        List<Pool> pools = new CsvToBeanBuilder(new FileReader(poolFileName)).withType(Pool.class).build().parse();

        RuleEngine engine = new RuleEngine();

        List<Possibility> possibilities = new ArrayList<>();

        //Iterate through loans
        for (Loan loan : loans) {

            //Find all eligible pools for each loan
            List<Pool> eligPools = engine.run(loan, pools);

            //Select the corresponding settlement date calculator according to the configuration file
            SettlementDateCalculator settlementDateCalculator = SettlementDateCalculatorFactory.getSettlementDateCalculator(settlementDateCalculationMethod);

            for (Pool eligPool : eligPools) {

                //Calculate a list of possible settlement dates for each eligible pool
                List<Date> settlementDateList = settlementDateCalculator.calculate(loan, eligPool);

                //Select the corresponding MBSCoupon calculator according to the configuration file
                MBSCouponCalculator mbsCouponCalculator = MBSCouponCalculatorFactory.getMBSCouponCalculator(MBSCouponCalculationMethod);

                //Calculate a list of possible MBSCoupons for each eligible pool
                List<Double> MBSCouponList = mbsCouponCalculator.calculate(loan, eligPool);

                for (Date settlementDate : settlementDateList) {

                    for (double MBSCoupon : MBSCouponList) {

                        //According to pool, MBSCoupon and settlement date information, find the corresponding market price data
                        MarketPriceLookup marketPriceLookup = new MarketPriceLookup();
                        double marketPrice = marketPriceLookup.getMarketPrice(eligPool, MBSCoupon, settlementDate);

                        //Skip this possibility if there is not market data
                        if (marketPrice == 0) continue;

                        //According to pool, MBSCoupon and settlement date information, find the corresponding buy_up_mults and buy_down_mults data
                        BuyUpDownMultsLookup buyUpDownMultsLookup = new BuyUpDownMultsLookup();
                        BuyUpDownData buyUpDownData = buyUpDownMultsLookup.getBuyUpDownMults(loan, eligPool, settlementDate);

                        //Use the buy_up_mults and buy_down_mults to calculate buy_up_price and buy_down_price
                        double buyUpPrice = Double.parseDouble(loan.getBuyUpRate()) * Double.parseDouble(buyUpDownData.getBuy_up_mults());
                        double buyDownPrice = Double.parseDouble(loan.getBuyDownRate()) * Double.parseDouble(buyUpDownData.getBuy_down_mults());

                        //According to pool and settlement date information, find the corresponding base servicing mults data
                        BaseServicingMultsLookup baseServicingMultsLookup = new BaseServicingMultsLookup();
                        double baseServicingMults = baseServicingMultsLookup.getBaseServicingMults(eligPool, settlementDate);

                        //According to pool information, find the corresponding base servicing fee data
                        FeeLookup feeLookup = new FeeLookup();
                        double baseServicingFee = feeLookup.getBaseServicingFee(eligPool);

                        //Use base servicing fee and base servicing mults to calculate servicing price
                        double servicingPrice = baseServicingFee * baseServicingMults;

                        //Calculate the final price for this possibility
                        double price = marketPrice + buyUpPrice + buyDownPrice + servicingPrice;

                        //Save all data related to this possibility in an object
                        Possibility possibility = new Possibility(loan, eligPool, settlementDate, MBSCoupon, marketPrice, buyUpDownData, baseServicingMults, baseServicingFee, price);

                        //Print this possibility
                        System.out.println(possibility);

                        //Add it to the result list
                        possibilities.add(possibility);

                    }
                }
            }
        }
    }
}
