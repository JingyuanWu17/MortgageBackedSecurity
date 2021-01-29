package DataLookup.BaseServicingMults;

import Configuration.ConfigFile;
import InputData.BaseServicingMultsData;
import InputData.Pool;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class BaseServicingMultsLookupV1 extends BaseServicingMultsLookup {
    private String dataFileName;
    private List<BaseServicingMultsData> dataList;

    public BaseServicingMultsLookupV1(ConfigFile cfg) {
        super(cfg);
        Map V1_cfg = (Map) cfg.getBaseServicingMultsLookup().get("V1");
        dataFileName = (String) V1_cfg.get("dataFileName");

        try {
            dataList = new CsvToBeanBuilder(new FileReader(dataFileName)).withType(BaseServicingMultsData.class).build().parse();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public double lookup(Pool pool, Date settlementDate) {
        String loanPricerId = pool.getLoanPricerId();
        String packageTypeNumber = pool.getPackageTypeNumber();

        double baseMults = 0;
        for (BaseServicingMultsData data : dataList) {
            if (stringContains(loanPricerId, data.getAgency())
                    && stringMatch(packageTypeNumber, data.getPool_id())
                    && dateMatch(settlementDate, data.getIssue_date())) {
                baseMults = Double.parseDouble(data.getBase_mults());
                break;
            }
        }

        return baseMults;
    }
}
