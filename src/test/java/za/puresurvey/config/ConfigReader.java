package za.puresurvey.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Reads settings from config.properties on the classpath.
 * Call getProperty("base.url") anywhere you need configuration values.
 */
public class ConfigReader {

    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream input = ConfigReader.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("config.properties not found in src/test/resources");
            }
            PROPERTIES.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Could not load config.properties", e);
        }
    }

    /**
     * Returns a property value as text.
     */
    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }

    /**
     * Returns a property as a whole number (used for timeout).
     */
    public static int getIntProperty(String key) {
        return Integer.parseInt(getProperty(key));
    }
}
