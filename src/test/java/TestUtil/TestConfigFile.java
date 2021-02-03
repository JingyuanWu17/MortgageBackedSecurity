package TestUtil;

import Util.Configuration.ConfigFile;
import org.junit.Test;

import java.util.Map;

public class TestConfigFile {
    @Test
    public void test() {
        ConfigFile cfg = new ConfigFile();
        System.out.println(cfg.getDateFormat());
        System.out.println(cfg.getPoolsFileName());
        System.out.println(cfg.getPools());
        System.out.println(cfg.getHolidaysOfYears().get("2021"));
        Map map = cfg.getPools();
        System.out.println(map.getClass().getName());
        for (Object obj : map.keySet()) {
            System.out.println(obj);
        }
    }
}
