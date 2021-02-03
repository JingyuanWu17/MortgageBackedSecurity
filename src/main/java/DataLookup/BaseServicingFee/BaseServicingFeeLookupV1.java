package DataLookup.BaseServicingFee;

import Util.Configuration.ConfigFile;
import MBSData.BaseServicingFeeData;
import MBSData.Pool;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Map;

public class BaseServicingFeeLookupV1 extends BaseServicingFeeLookup {
    private String dataFileName;
    private List<BaseServicingFeeData> dataList;

    public BaseServicingFeeLookupV1(ConfigFile cfg) {
        super(cfg);
        Map V1_cfg = (Map) cfg.getBaseServicingFeeLookup().get("V1");
        dataFileName = (String) V1_cfg.get("dataFileName");

        try {
            dataList = new CsvToBeanBuilder(new FileReader(dataFileName)).withType(BaseServicingFeeData.class).build().parse();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public double lookup(Pool pool) {
        String loanPricerId = pool.getLoanPricerId();

        double baseFee = 0;
        for (BaseServicingFeeData data : dataList) {
            if (stringContains(loanPricerId, data.getAgency())) {
                baseFee = Double.parseDouble(data.getBase_servicing_fee());
                break;
            }
        }

        return baseFee;
    }
}
