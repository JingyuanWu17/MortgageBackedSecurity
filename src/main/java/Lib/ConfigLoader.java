package Lib;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

public class ConfigLoader {
    public static void load(Class configclass, Object object, String fileName) {
        Properties pro = new Properties();
        try {
            FileInputStream proStream = new FileInputStream(fileName);
            pro.load(proStream);
            for (Field field : configclass.getDeclaredFields()) {
                field.setAccessible(true);
                field.set(object, pro.getProperty(field.getName()));
            }
        } catch (IOException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
