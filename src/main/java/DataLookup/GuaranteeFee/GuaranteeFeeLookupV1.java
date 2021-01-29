package DataLookup.GuaranteeFee;

import Configuration.ConfigFile;
import InputData.GuaranteeFeeData;
import InputData.Pool;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class GuaranteeFeeV1 extends GuaranteeFee {
    private String dataFileName;
    private List<GuaranteeFeeData> dataList;

    public GuaranteeFeeV1(ConfigFile cfg) {
        super(cfg);
        Map V1_cfg = (Map) cfg.getGuaranteeFeeLookup().get("V1");
        dataFileName = (String) V1_cfg.get("dataFileName");

        try {
            dataList = new CsvToBeanBuilder(new FileReader(dataFileName)).withType(GuaranteeFeeData.class).build().parse();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public double lookup(Pool pool, Date settlementDate) {
        String loanPricerId = pool.getLoanPricerId();

        double gFee = 0;
        for (GuaranteeFeeData data : dataList) {
            if (stringContains(loanPricerId, data.getAgency()) && dateMatch(settlementDate, data.getSettle_date())) {
                gFee = Double.parseDouble(data.getGurantee_fee());
                break;
            }
        }

        return gFee;
    }
}
