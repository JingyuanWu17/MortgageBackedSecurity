package TestData;

import MBSData.Pool;
import com.opencsv.bean.CsvToBeanBuilder;
import org.junit.Test;

import java.io.FileReader;
import java.util.List;

public class TestPool {

    @Test
    public void test_creatObject() throws Exception {
        String fileName = "src/main/InputSamples/Pools.csv";

        List<Pool> pools = new CsvToBeanBuilder(new FileReader(fileName)).withType(Pool.class).build().parse();
        for (Pool pool : pools) {
            System.out.println(pool.getLoanPricerId());
        }
    }
}
