package DataLookup.BaseServicingMultsLookup;

import Configuration.ConfigFile;
import InputData.BaseServicingMultsData;
import InputData.Pool;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class BaseServicingMultsV1 extends BaseServicingMults {

    private String baseServicingMultsDataFileName;
    private List<BaseServicingMultsData> baseServicingMultsDataList;

    public BaseServicingMultsV1(ConfigFile cfg) {
        Map baseServicingMultsV1Config = (Map) cfg.getBaseServicingMultsLookup().get("BaseServicingMultsV1");
        baseServicingMultsDataFileName = (String) baseServicingMultsV1Config.get(baseServicingMultsDataFileName);
        try {
            baseServicingMultsDataList = new CsvToBeanBuilder(new FileReader(baseServicingMultsDataFileName)).withType(BaseServicingMultsData.class).build().parse();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public double lookup(Pool pool, Date settlementDate) {
        String loanPricerId = pool.getLoanPricerId();
        String packageTypeNumber = pool.getPackageTypeNumber();

        double baseMults = 0;
        for (BaseServicingMultsData baseServicingMultsData : baseServicingMultsDataList) {
            if (stringContains(loanPricerId, baseServicingMultsData.getAgency())
                    && stringMatch(packageTypeNumber, baseServicingMultsData.getPool_id())
                    && dateMatch(settlementDate, baseServicingMultsData.getIssue_date())) {
                baseMults = Double.parseDouble(baseServicingMultsData.getBase_mults());
                break;
            }
        }

        return baseMults;
    }
}
