package DataLookup;

import InputData.BaseServicingMultsData;
import InputData.Pool;
import Lib.ConfigLoader;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Date;
import java.util.List;

public class BaseServicingMultsLookup extends TableLookup{
    private final static String configFileName = "src/main/resources/DataLookupConfig/BaseServicingMultsLookup.properties";
    private String baseServicingMultsDataFileName;
    private List<BaseServicingMultsData> baseServicingMultsDataList;

    public BaseServicingMultsLookup() {
        ConfigLoader.load(BaseServicingMultsLookup.class, this, configFileName);
        try {
            baseServicingMultsDataList = new CsvToBeanBuilder(new FileReader(baseServicingMultsDataFileName)).withType(BaseServicingMultsData.class).build().parse();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public double getBaseServicingMults(Pool pool, Date settlementDate) {
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
