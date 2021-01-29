package Configuration;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ConfigFile {

    private static String configFileName = "src/main/resources/config.json";

    private String dateFormat;

    private String loansFileName;

    private String poolsFileName;

    private Map fhlmc;

    private Map fnma;

    private Map gnma;

    private Map Pools;

    private Map MBSCouponCalc;

    private Map SettlementDateCalc;

    private Map BaseServicingMultsLookup;

    private Map BuyUpDownMultsLookup;

    private Map GuaranteeFeeLookup;

    private Map BaseServicingFeeLookup;

    private Map MarketPriceLookup;

    private Map holidaysOfYears;

    private JSONObject configObj;

    public ConfigFile() {
        fhlmc = new HashMap();
        fnma = new HashMap();
        gnma = new HashMap();
        Pools = new HashMap();
        SettlementDateCalc = new HashMap();
        BaseServicingMultsLookup = new HashMap();
        BuyUpDownMultsLookup = new HashMap();
        GuaranteeFeeLookup = new HashMap();
        BaseServicingFeeLookup = new HashMap();
        MarketPriceLookup = new HashMap();
        holidaysOfYears = new HashMap();

        JSONParser parser = new JSONParser();
        try {
            configObj = (JSONObject) parser.parse(new FileReader(configFileName));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        loadFields();
    }

    private void loadFields() {
        for (Field field : ConfigFile.class.getDeclaredFields()) {
            try {
                loadField(field);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadField(Field field) throws IllegalAccessException {
        String fieldName = field.getName();
        field.setAccessible(true);
        if (configObj.get(fieldName) instanceof String) {
            field.set(this, configObj.get(fieldName));
        } else if (configObj.get(fieldName) instanceof JSONObject) {
            field.set(this, buildFieldMap(configObj.get(fieldName)));
        }
    }

    //Build the map recursively
    private Map buildFieldMap(Object object) {
        JSONObject jsonObject = (JSONObject) object;
        Map map = new HashMap();
        for (Object obj : jsonObject.keySet()) {
            String key = (String) obj;
            if (jsonObject.get(key) instanceof String) {
                map.put(key, jsonObject.get(key));
            } else if (jsonObject.get(key) instanceof JSONObject) {
                map.put(key, buildFieldMap(jsonObject.get(key)));
            }
        }
        return map;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public String getLoansFileName() {
        return loansFileName;
    }

    public String getPoolsFileName() {
        return poolsFileName;
    }

    public Map getFhlmc() {
        return fhlmc;
    }

    public Map getFnma() {
        return fnma;
    }

    public Map getGnma() {
        return gnma;
    }

    public Map getPools() {
        return Pools;
    }

    public Map getMBSCouponCalc() {
        return MBSCouponCalc;
    }

    public Map getSettlementDateCalc() {
        return SettlementDateCalc;
    }

    public Map getBaseServicingMultsLookup() {
        return BaseServicingMultsLookup;
    }

    public Map getBuyUpDownMultsLookup() {
        return BuyUpDownMultsLookup;
    }

    public Map getGuaranteeFeeLookup() {
        return GuaranteeFeeLookup;
    }

    public Map getBaseServicingFeeLookup() {
        return BaseServicingFeeLookup;
    }

    public Map getMarketPriceLookup() {
        return MarketPriceLookup;
    }

    public Map getHolidaysOfYears() {
        return holidaysOfYears;
    }
}
