package IO;

import Data.Pool;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;
import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class ReadCSVTest {

    @Test
    public void test_read() {
        String fileName = "src/main/Input/pools.csv";
        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            reader.skip(1);
            List<String[]> r = reader.readAll();
            r.forEach(x -> System.out.println(Arrays.toString(x)));
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_creatObject() throws Exception{
        String fileName = "src/main/Input/pools.csv";

        List<Pool> pools = new CsvToBeanBuilder(new FileReader(fileName)).withType(Pool.class).build().parse();
        for(Pool pool : pools) {
            System.out.println(pool.getLoanPricerId());
        }

    }
}
