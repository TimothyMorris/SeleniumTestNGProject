package base;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.testng.Assert;

/**
 * Simple properties manager class to keep the loading of configuration file in one place.
 */
public class PropertyManager {

    /** Location of configuration file. **/
    private static final String CONFIG_FILENAME = "scratch.properties";

    /** Property object used for getting the properties. **/
    private final Properties properties;

    /**
     * s Default constructor.
     */
    public PropertyManager() {
        properties = new Properties();
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream stream = classLoader.getResourceAsStream(CONFIG_FILENAME);
            Assert.assertNotNull(stream, String.format("Could not find config file %s", CONFIG_FILENAME));
            try {
                properties.load(stream);
            } finally {
                stream.close();
            }
        } catch (IOException e) {
            Assert.fail(String.format("Could not read config file %s. Exception: \n%s", CONFIG_FILENAME, e.toString()));
        }
    }

    /**
     * Fetches property value given the key.
     * 
     * @param key the key used to identify the requested value
     * @return value associated with key or null if key doesn't exist
     */
    public String getProperty(final String key) {
        final String fileProperty = properties.getProperty(key);
        final String envProperty = System.getProperty(key);
        return (envProperty == null) ? fileProperty : envProperty;
    }

    /**
     * Fetches property value given the key, with a default value.
     * 
     * @param key the key used to identify the requested value
     * @param defaultValue in case neither fileProperty or envProperty get what we need.
     * @return value associated with key or null if key doesn't exist
     */
    public String getProperty(final String key, final String defaultValue) {
        final String fileProperty = properties.getProperty(key, defaultValue);
        final String envProperty = System.getProperty(key);
        return (envProperty == null) ? fileProperty : envProperty;
    }

}
