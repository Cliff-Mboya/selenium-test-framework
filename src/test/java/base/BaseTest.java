package base;

import factory.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utils.ConfigReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseTest {

    private static final Logger log = LoggerFactory.getLogger(BaseTest.class);

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    protected WebDriver getDriver() {
        return driver.get();
    }

    @BeforeSuite
    public void loadConfig() {
        ConfigReader.initProperties();
        log.info("Config properties loaded successfully");
    }

    @Parameters("browser")
    @BeforeMethod
    public void setup(@Optional String browser) {
        log.info("Starting test on browser={}", browser);

        if (browser == null) {
            browser = ConfigReader.get("browser");
        }

        driver.set(DriverFactory.getDriver(browser));

        log.info("THREAD: {} | Browser: {} | Driver instance: {}",
                Thread.currentThread().getId(), browser, getDriver());

        getDriver().manage().window().maximize();

        // Removed forced navigation so tests/pages control where they start
        // getDriver().get(ConfigReader.get("url"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        log.info("Closing driver for thread={}", Thread.currentThread().getId());

        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }
}
