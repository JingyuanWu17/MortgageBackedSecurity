package Data;

import com.opencsv.bean.CsvToBeanBuilder;
import org.junit.Test;

import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.List;

public class TestLoan {

    @Test
    public void test_creatObject() throws Exception {
        String fileName = "src/main/Input/loans.csv";

        List<Loan> loans = new CsvToBeanBuilder(new FileReader(fileName)).withType(Loan.class).build().parse();
        Loan loan = loans.get(0);
        Class cls = loan.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            System.out.println(field.get(loan));
        }
    }

}
