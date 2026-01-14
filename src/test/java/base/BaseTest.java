package base;

import factory.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utils.ConfigReader;
import java.time.Duration;



public class BaseTest {

    protected WebDriver driver;

    @Parameters("browser")
    @BeforeMethod
    public void setup() {

        ConfigReader.initProperties();   // load config file

        String browser = ConfigReader.get("browser");

        driver = DriverFactory.getDriver(browser);

        driver.manage().timeouts().implicitlyWait(
                Duration.ofSeconds(Integer.parseInt(ConfigReader.get("timeout")))
        );

        driver.get(ConfigReader.get("url"));
    }



    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
