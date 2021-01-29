package Calculator.SettlementDateCalc;

import Configuration.ConfigFile;
import InputData.Loan;
import InputData.Pool;
import InputData.SettlementControlData;
import Util.BusinessDayUtil;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class SettlementDateV1 extends SettlementDate {
    private String warehouseDays;
    private String agencyProcessDays;
    private String settlementControlDataFileName;
    private String BMAsFileName;
    private SimpleDateFormat sdf;
    private BusinessDayUtil businessDayUtil;

    public SettlementDateV1(ConfigFile cfg) {
        Map settlementDateV1Config = (Map) cfg.getSettlementDateCalc().get("SettlementDateV1");
        warehouseDays = (String) settlementDateV1Config.get("warehouseDays");
        agencyProcessDays = (String) settlementDateV1Config.get("agencyProcessDays");
        settlementControlDataFileName = (String) settlementDateV1Config.get("settlementControlDataFileName");
        BMAsFileName = (String) settlementDateV1Config.get("BMAsFileName");
        sdf = new SimpleDateFormat(cfg.getDateFormat(), Locale.ENGLISH);
        businessDayUtil = new BusinessDayUtil(cfg);
    }

    public String getWarehouseDays() {
        return warehouseDays;
    }

    public String getAgencyProcessDays() {
        return agencyProcessDays;
    }

    public String getSettlementControlDataFileName() {
        return settlementControlDataFileName;
    }

    public String getBMAsFileName() {
        return BMAsFileName;
    }

    /**
     * @return All possible settlement dates of this loan under this pool
     */
    @Override
    public List<Date> calculate(Loan loan, Pool pool) {
        List<Date> outputDates = new ArrayList<>();

        Date epsd = calculateEPSD(loan);
        List<Date> fourMonthsBMAs = getFourMonthsBMAs(epsd);

        List<SettlementControlData> settlementControls = null;
        try {
            settlementControls = new CsvToBeanBuilder(new FileReader(settlementControlDataFileName)).withType(SettlementControlData.class).build().parse();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Search corresponding settlement date control object for this loan and pool.
        SettlementControlData settlementControl = searchSettlementControl(settlementControls, loan, pool);

        if (settlementControl == null) {
            throw new RuntimeException("No matching settlementControl object!");
        }

        int idx = 0;
        Date currBMA = fourMonthsBMAs.get(idx);

        //Calculate possible settlement date for first issue
        if (epsd.compareTo(currBMA) <= 0) { //EPSD is earlier than BMA

            String first_issue_pre_bma = settlementControl.getFirst_issue_pre_bma();

            if (first_issue_pre_bma.equals("EPSD")) {
                outputDates.add(epsd);
            } else if (first_issue_pre_bma.equals("BMA")) {
                outputDates.add(currBMA);
            }

        } else { //EPSD is later than BMA
            epsd = getNextMonthFirstDayAsEPSD(epsd);
            currBMA = fourMonthsBMAs.get(++idx);

            String first_issue_post_bma = settlementControl.getFirst_issue_post_bma();

            if (first_issue_post_bma.equals("EPSD")) {
                outputDates.add(epsd);
            } else if (first_issue_post_bma.equals("BMA")) {
                outputDates.add(currBMA);
            }
        }
        idx++;

        //Second issue
        String second_issue = settlementControl.getSecond_issue();
        epsd = getNextMonthFirstDayAsEPSD(epsd);
        currBMA = fourMonthsBMAs.get(idx);

        if (second_issue.equals("EPSD")) {
            outputDates.add(epsd);
        } else if (second_issue.equals("BMA")) {
            outputDates.add(currBMA);
        }
        idx++;

        //Third issue
        String third_issue = settlementControl.getThird_issue();
        epsd = getNextMonthFirstDayAsEPSD(epsd);
        currBMA = fourMonthsBMAs.get(idx);

        if (third_issue.equals("EPSD")) {
            outputDates.add(epsd);
        } else if (third_issue.equals("BMA")) {
            outputDates.add(currBMA);
        }

        return outputDates;
    }


    private Date calculateEPSD(Loan loan) {
        Date res = null;
        String status = loan.getProcessStatus();
        switch (status) {
            case "Inv":
                res = calcInvEPSD(loan);
                break;
            case "Pipe":
                res = calcPipeEPSD(loan);
                break;
            case "Sold":
                res = calcSoldEPSD(loan);
                break;
        }
        return res;
    }

    private Date calcInvEPSD(Loan loan) {
        Date lockDate = getLockDate(loan);
        int whDays = Integer.parseInt(warehouseDays);
        int apDays = Integer.parseInt(agencyProcessDays);
        Date epsd = businessDayUtil.addDays(lockDate, whDays);
        epsd = businessDayUtil.addDays(epsd, apDays);

        return businessDayUtil.isBusinessDay(epsd) ? epsd : businessDayUtil.getNextBusinessDay(epsd);
    }

    private Date calcPipeEPSD(Loan loan) {
        Date lockDate = getLockDate(loan);
        Date now = Calendar.getInstance().getTime();
        Date epsd = lockDate.after(now) ? lockDate : now;
        int apDays = Integer.parseInt(agencyProcessDays);
        epsd = businessDayUtil.addDays(epsd, apDays);

        return businessDayUtil.isBusinessDay(epsd) ? epsd : businessDayUtil.getNextBusinessDay(epsd);
    }

    private Date calcSoldEPSD(Loan loan) {
        Date epsd = Calendar.getInstance().getTime();
        return businessDayUtil.isBusinessDay(epsd) ? epsd : businessDayUtil.getNextBusinessDay(epsd);
    }


    private Date getLockDate(Loan loan) {
        String lockDateStr = loan.getLockExpirationDate();
        Date lockDate = null;
        try {
            lockDate = sdf.parse(lockDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return lockDate;
    }

    /**
     * @return BMAs for four months from the current month
     */
    private List<Date> getFourMonthsBMAs(Date epsd) {
        List<Date> res = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(epsd);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);

        String[] currYearBmas = null;
        String[] nextYearBmas = null;
        try (CSVReader reader = new CSVReader(new FileReader(BMAsFileName))) {
            List<String[]> list = reader.readAll();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i)[0].equals(String.valueOf(year))) {
                    currYearBmas = list.get(i);
                    nextYearBmas = list.get(i + 1);
                    break;
                }
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        int idx = month + 1;
        for (int i = 0; i < 4; i++) {
            try {
                assert currYearBmas != null;
                res.add(sdf.parse(currYearBmas[idx]));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            idx++;

            if (idx == currYearBmas.length) {
                idx = 1;
                currYearBmas = nextYearBmas;
            }
        }

        return res;
    }

    private SettlementControlData searchSettlementControl(List<SettlementControlData> settlementControls, Loan loan, Pool pool) {
        String loanStatus = loan.getProcessStatus();
        String poolFamilyId = getPoolFamilyId(pool);
        settlementControls.get(1).getFirst_issue_pre_bma();
        for (SettlementControlData sc : settlementControls) {
            boolean b1 = sc.getPool_family_id().equals(poolFamilyId);
            boolean b2 = sc.getLoan_status().equals(loanStatus);
            if (b1 && b2) {
                return sc;
            }
        }

        return null;
    }

    private String getPoolFamilyId(Pool pool) {
        String loanPricerId = pool.getLoanPricerId();
        if (loanPricerId.contains("fhlmc")) {
            return "1";
        } else if (loanPricerId.contains("fnma")) {
            return "2";
        }

        return "3";
    }

    private Date getNextMonthFirstDayAsEPSD(Date epsd) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(epsd);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        return calendar.getTime();
    }


}
