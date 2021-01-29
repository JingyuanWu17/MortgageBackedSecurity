package TestIO;

import Configuration.ConfigFile;
import org.junit.Test;

public class TestConfigFile {
    @Test
    public void test() {
        ConfigFile cfg = new ConfigFile();
        System.out.println(cfg.getDateFormat());
        System.out.println(cfg.getPoolsFileName());
        System.out.println(cfg.getPools());
        System.out.println(cfg.getHolidaysOfYears().get("2021"));
    }
}
