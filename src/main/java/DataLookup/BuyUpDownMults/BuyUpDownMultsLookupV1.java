package DataLookup.BuyUpDownMults;

//
//public class BuyUpDownMultsV1 extends BuyUpDownMults {
//    private String dataFileName;
//    private List<BuyUpDownData> dataList;
//
//    public BuyUpDownMultsV1(ConfigFile cfg) {
//        super(cfg);
//        Map V1_cfg = (Map) cfg.getBuyUpDownMultsLookup().get("V1");
//        dataFileName = (String) V1_cfg.get("dataFileName");
//
//        try {
//            dataList = new CsvToBeanBuilder(new FileReader(dataFileName)).withType(BuyUpDownData.class).build().parse();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

//    @Override
//    public BuyUpDownData lookup(Loan loan, Pool pool, Date settlementDate) {
//        RemainingTerm remainingTermCalculator = RemainingTermFactory.getRemainingTermCalculator(remainingTermCalculationMethod);
//        int remainingTerm = remainingTermCalculator.calculate(loan);
//
//        String loanPricerId = pool.getLoanPricerId();
//        String packageTypeNumber = pool.getPackageTypeNumber();
//        String LPMIFlag = loan.getLPMIFlag();
//        String grossCoupon = loan.getGrossCoupon();
//
//        BuyUpDownData resData = null;
//        for (BuyUpDownData data : dataList) {
//            if (stringContains(loanPricerId, data.getAgency())
//                    && dateMatch(settlementDate, data.getIssue_month())
//                    && stringMatch(packageTypeNumber, data.getPool_id())
//                    && stringMatch(LPMIFlag, data.getLpmi_flag())
//                    && inRange(grossCoupon, data.getNote_rate_min(), data.getNote_rate_max())
//                    && inRange(String.valueOf(remainingTerm), data.getRemaining_term_min(), data.getRemaining_term_max())) {
//                resData = data;
//                break;
//            }
//        }
//
//        return resData;
//    }
//}
