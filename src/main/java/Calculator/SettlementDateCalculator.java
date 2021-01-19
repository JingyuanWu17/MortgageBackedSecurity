package Calculator;

import Data.Loan;
import Data.Pool;
import Data.SettlementControl;
import Lib.BusinessDayUtil;
import Lib.ConfigLoader;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class SettlementDateCalculator {

    private String dateFormat;
    private String warehouseDays;
    private String agencyProcessDays;
    private String settlementControlFileName;
    private String bmaFileName;
    private SimpleDateFormat sdf;

    public SettlementDateCalculator() {
        ConfigLoader.load(SettlementDateCalculator.class, this, "src/main/resources/settlementDateCalculator.properties");
        sdf = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
    }

    public String getWarehouseDays() {
        return warehouseDays;
    }

    public String getAgencyProcessDays() {
        return agencyProcessDays;
    }


    /**
     * @return All possible settlement dates of this loan under this pool
     */
    public List<Date> calculate(Loan loan, Pool pool) {
        List<Date> outputDates = new ArrayList<>();

        Date epsd = calculateEPSD(loan);
        List<Date> fourMonthsBMAs = getFourMonthsBMAs(epsd);

        List<SettlementControl> settlementControls = null;
        try {
            settlementControls = new CsvToBeanBuilder(new FileReader(settlementControlFileName)).withType(SettlementControl.class).build().parse();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Search corresponding settlement date control object for this loan and pool.
        SettlementControl settlementControl = searchSettlementControl(settlementControls, loan, pool);

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
        Date epsd = BusinessDayUtil.addDays(lockDate, whDays);
        epsd = BusinessDayUtil.addDays(epsd, apDays);

        return BusinessDayUtil.isBusinessDay(epsd) ? epsd : BusinessDayUtil.getNextBusinessDay(epsd);
    }

    private Date calcPipeEPSD(Loan loan) {
        Date lockDate = getLockDate(loan);
        Date now = Calendar.getInstance().getTime();
        Date epsd = lockDate.after(now) ? lockDate : now;
        int apDays = Integer.parseInt(agencyProcessDays);
        epsd = BusinessDayUtil.addDays(epsd, apDays);

        return BusinessDayUtil.isBusinessDay(epsd) ? epsd : BusinessDayUtil.getNextBusinessDay(epsd);
    }

    private Date calcSoldEPSD(Loan loan) {
        Date epsd = Calendar.getInstance().getTime();
        return BusinessDayUtil.isBusinessDay(epsd) ? epsd : BusinessDayUtil.getNextBusinessDay(epsd);
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
        try (CSVReader reader = new CSVReader(new FileReader(bmaFileName))) {
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

    private SettlementControl searchSettlementControl(List<SettlementControl> settlementControls, Loan loan, Pool pool) {
        String loanStatus = loan.getProcessStatus();
        String poolFamilyId = getPoolFamilyId(pool);
        settlementControls.get(1).getFirst_issue_pre_bma();
        for (SettlementControl sc : settlementControls) {
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
