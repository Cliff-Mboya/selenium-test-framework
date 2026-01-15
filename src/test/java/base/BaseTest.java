package base;

import factory.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utils.ConfigReader;
import java.time.Duration;

public class BaseTest {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    protected WebDriver getDriver() {
        return driver.get();
    }

    @Parameters("browser")
    @BeforeMethod
    public void setup(@Optional String browser) {

        ConfigReader.initProperties();

        if (browser == null) {
            browser = ConfigReader.get("browser");
        }

        driver.set(DriverFactory.getDriver(browser));
        getDriver().manage().window().maximize();

        getDriver().get(ConfigReader.get("url"));
    }

    @AfterMethod
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }
}
