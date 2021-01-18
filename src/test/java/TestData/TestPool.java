package TestData;

import Data.Pool;
import com.opencsv.bean.CsvToBeanBuilder;
import org.junit.Test;

import java.io.FileReader;
import java.util.List;

public class TestPool {

    @Test
    public void test_creatObject() throws Exception {
        String fileName = "src/main/Input/pools.csv";

        List<Pool> pools = new CsvToBeanBuilder(new FileReader(fileName)).withType(Pool.class).build().parse();
        for (Pool pool : pools) {
            System.out.println(pool.getLoanPricerId());
        }
    }
}
