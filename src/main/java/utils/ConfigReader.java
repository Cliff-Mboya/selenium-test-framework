package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties prop;

    // Load properties (lazy initialization)
    public static Properties initProperties() {
        if (prop == null) {
            prop = new Properties();
            try (FileInputStream ip = new FileInputStream("src/test/resources/config.properties")) {
                prop.load(ip);
            } catch (IOException e) {
                throw new RuntimeException("❌ Failed to load config.properties file", e);
            }
        }
        return prop;
    }

    // Safe get method (auto-initializes)
    public static String get(String key) {
        initProperties(); // ensures prop is always loaded
        return prop.getProperty(key);
    }

    // Browser resolution priority:
    // 1) System property -> -Dbrowser=chrome
    // 2) config.properties -> browser=chrome
    // 3) default -> chrome
    public static String getBrowser() {

        // 1. Check system property
        String browser = System.getProperty("browser");
        if (browser != null && !browser.trim().isEmpty()) {
            return browser.trim();
        }

        // 2. Check config.properties
        browser = get("browser");
        if (browser != null && !browser.trim().isEmpty()) {
            return browser.trim();
        }

        // 3. Default
        return "chrome";
    }
}
