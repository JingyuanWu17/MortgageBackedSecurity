import Calculator.MBSCoupon.MBSCouponCalc;
import Calculator.SettlementDate.SettlementDateCalc;
import Util.MBSCache.Cache;
import Util.Configuration.ConfigFile;
import DataLookup.BaseServicingFee.BaseServicingFeeLookup;
import DataLookup.BaseServicingMults.BaseServicingMultsLookup;
import DataLookup.BuyUpDownMults.BuyUpDownMultsLookup;
import DataLookup.MarketPrice.MarketPriceLookup;
import MBSData.BuyUpDownData;
import MBSData.Loan;
import MBSData.Pool;
import Engine.RuleEngine;
import MBSFactory.Factory;
import MBSFactory.FactoryProducer;
import Output.Possibility;
import Util.MBSCache.MBSCouponCache;
import Util.MBSCache.SettlementDateCache;
import Util.MBSContainer.PoolsContainer;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        //Create configuration file object
        ConfigFile cfg = new ConfigFile();

        List<Loan> loans = new CsvToBeanBuilder(new FileReader(cfg.getLoansFileName())).withType(Loan.class).build().parse();
        List<Pool> pools = new CsvToBeanBuilder(new FileReader(cfg.getPoolsFileName())).withType(Pool.class).build().parse();

        Cache<List<Date>> settlementDateCache = new SettlementDateCache();
        Cache<List<Double>> mbsCouponCache = new MBSCouponCache();

        RuleEngine engine = new RuleEngine();

        Factory factory = null;

        for (Loan loan : loans) {

            List<Possibility> loanPossibilities = new ArrayList<>();

            //Find all eligible pools for each loan
            PoolsContainer eligPools = engine.run(loan, pools);

            Iterator<Pool> iterator = eligPools.iterator();
            while (iterator.hasNext()) {
                Pool eligPool = iterator.next();

                List<Date> settlementDateList;
                if (settlementDateCache.containsKey(loan, eligPool)) {
                    settlementDateList = settlementDateCache.get(loan, eligPool);
                } else {
                    factory = FactoryProducer.getFactory("Calculator", "SettlementDate");
                    SettlementDateCalc sDateCalc = (SettlementDateCalc) factory.create(cfg, loan, eligPool);
                    settlementDateList = sDateCalc.calculate(loan, eligPool);
                    settlementDateCache.put(loan, eligPool, settlementDateList);
                }

                List<Double> mbsCouponList;
                if (mbsCouponCache.containsKey(loan, eligPool)) {
                    mbsCouponList = mbsCouponCache.get(loan, eligPool);
                } else {
                    factory = FactoryProducer.getFactory("Calculator", "MBSCoupon");
                    MBSCouponCalc mbsCouponCalc = (MBSCouponCalc) factory.create(cfg, loan, eligPool);
                    mbsCouponList = mbsCouponCalc.calculate(loan, eligPool);
                    mbsCouponCache.put(loan, eligPool, mbsCouponList);
                }

                for (Date settlementDate : settlementDateList) {

                    for (double MBSCoupon : mbsCouponList) {

                        factory = FactoryProducer.getFactory("DataLookup", "MarketPrice");
                        MarketPriceLookup mPriceLookup = (MarketPriceLookup) factory.create(cfg, loan, eligPool);
                        double marketPrice = mPriceLookup.lookup(eligPool, MBSCoupon, settlementDate);

                        //Skip this possibility if there is not market price data
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

                        loanPossibilities.add(possibility);

                    }
                }
            }

            loanPossibilities.sort((a, b) -> Double.compare(b.getPrice(), a.getPrice()));
            //Print the rank of the possibility according to the price from high to low
            for (Possibility p : loanPossibilities) {
                System.out.println(p);
            }

        }
    }
}
