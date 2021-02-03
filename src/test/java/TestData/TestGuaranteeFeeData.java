package TestData;

import MBSData.GuaranteeFeeData;
import com.opencsv.bean.CsvToBeanBuilder;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class TestGuaranteeFeeData {
    @Test
    public void test_createObject() throws FileNotFoundException {
        List<GuaranteeFeeData> dataList = new CsvToBeanBuilder(new FileReader("src/main/InputSamples/GuaranteeFee.csv")).withType(GuaranteeFeeData.class).build().parse();
        for(GuaranteeFeeData data : dataList) {
            System.out.println(data.getAgency());
            System.out.println(data.getGurantee_fee());
        }
    }
}
