package su.jet.yandexsearch.utils;


import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

/**
 * PropertyHelper - load property from file and system properties.
 *
 * @author Pavel_Markin
 */
@Slf4j
@SuppressWarnings("WeakerAccess")
public class PropertyHelper {

    @Getter
    static Properties properties;

    static {
        String filePath = "config/test.properties";
        properties = new Properties();
        loadProperties(filePath);
    }

    public static String getProperty(String nameProperty) {
        return Optional.ofNullable(properties.getProperty(nameProperty))
                       .orElseThrow(() -> new RuntimeException("Property '" + nameProperty + "' is null, check properties files."));
    }

    public static void loadProperties(String filePath) {
        if (Thread.currentThread().getContextClassLoader().getResource(filePath) == null) {
            throw new RuntimeException("Не найден файл по пути resources/" + filePath);
        }
        try {
            Properties fileProperties = new Properties();
            fileProperties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath));
            PropertyHelper.properties.putAll(fileProperties);
            PropertyHelper.properties.putAll(System.getProperties());
            properties.forEach((key, val) -> log.debug(key + " = " + val));
        } catch (IOException e) {
            log.error("Can't load properties from in PropertyHelper", e);
        }
    }

}
