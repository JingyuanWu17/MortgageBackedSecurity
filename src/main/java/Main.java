import Calculator.MBSCoupon.MBSCouponCalc;
import Calculator.SettlementDate.SettlementDateCalc;
import Configuration.ConfigFile;
import DataLookup.BaseServicingFee.BaseServicingFeeLookup;
import DataLookup.BaseServicingMults.BaseServicingMultsLookup;
import DataLookup.BuyUpDownMults.BuyUpDownMultsLookup;
import DataLookup.MarketPrice.MarketPriceLookup;
import InputData.BuyUpDownData;
import InputData.Loan;
import InputData.Pool;
import Engine.RuleEngine;
import MBSFactory.Factory;
import MBSFactory.FactoryProducer;
import OutputData.Possibility;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    private static String loansFileName;
    private static String poolsFileName;

    public static void main(String[] args) throws FileNotFoundException {

        //Create configuration file object
        ConfigFile cfg = new ConfigFile();

        loansFileName = cfg.getLoansFileName();
        poolsFileName = cfg.getPoolsFileName();

        List<Loan> loans = new CsvToBeanBuilder(new FileReader(loansFileName)).withType(Loan.class).build().parse();

        List<Pool> pools = new CsvToBeanBuilder(new FileReader(poolsFileName)).withType(Pool.class).build().parse();

        RuleEngine engine = new RuleEngine();

        List<Possibility> possibilities = new ArrayList<>();

        Factory factory = null;

        for (Loan loan : loans) {

            //Find all eligible pools for each loan
            List<Pool> eligPools = engine.run(loan, pools);

            for (Pool eligPool : eligPools) {

                factory = FactoryProducer.getFactory("Calculator", "SettlementDate");
                SettlementDateCalc sDateCalc = (SettlementDateCalc) factory.create(cfg, loan, eligPool);
                List<Date> settlementDateList = sDateCalc.calculate(loan, eligPool);

                factory = FactoryProducer.getFactory("Calculator", "MBSCoupon");
                MBSCouponCalc mbsCouponCalc = (MBSCouponCalc) factory.create(cfg, loan, eligPool);
                List<Double> MBSCouponList = mbsCouponCalc.calculate(loan, eligPool);

                for (Date settlementDate : settlementDateList) {

                    for (double MBSCoupon : MBSCouponList) {

                        factory = FactoryProducer.getFactory("DataLookup", "MarketPrice");
                        MarketPriceLookup mPriceLookup = (MarketPriceLookup) factory.create(cfg, loan, eligPool);
                        double marketPrice = mPriceLookup.lookup(eligPool, MBSCoupon, settlementDate);

                        //Skip this possibility if there is not market data
                        if (marketPrice == 0) continue;

                        factory = FactoryProducer.getFactory("DataLookup", "BuyUpDownMults");
                        BuyUpDownMultsLookup bUDMultsLookup = (BuyUpDownMultsLookup) factory.create(cfg, loan, eligPool);
                        BuyUpDownData buyUpDownData = bUDMultsLookup.lookup(loan, eligPool, settlementDate);

                        double buyUpPrice = Double.parseDouble(loan.getBuyUpRate()) * Double.parseDouble(buyUpDownData.getBuy_up_mults());
                        double buyDownPrice = Double.parseDouble(loan.getBuyDownRate()) * Double.parseDouble(buyUpDownData.getBuy_down_mults());

                        factory = FactoryProducer.getFactory("DataLookup", "BaseServicingMults");
                        BaseServicingMultsLookup baseMultsLookup = (BaseServicingMultsLookup) factory.create(cfg, loan, eligPool);
                        double baseServicingMults = baseMultsLookup.lookup(eligPool, settlementDate);

                        factory = FactoryProducer.getFactory("DataLookup", "BaseServicingFee");
                        BaseServicingFeeLookup baseFeeLookup = (BaseServicingFeeLookup) factory.create(cfg, loan, eligPool);
                        double baseServicingFee = baseFeeLookup.lookup(eligPool);

                        double servicingPrice = baseServicingFee * baseServicingMults;

                        //Calculate the final price for this possibility
                        double price = marketPrice + buyUpPrice + buyDownPrice + servicingPrice;

                        //Save all data related to this possibility in an object
                        Possibility possibility = new Possibility(loan, eligPool, settlementDate, MBSCoupon, marketPrice, buyUpDownData, baseServicingMults, baseServicingFee, price);

                        System.out.println(possibility);

                        possibilities.add(possibility);

                    }
                }
            }
        }
    }
}
