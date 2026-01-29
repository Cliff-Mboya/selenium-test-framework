package base;

import factory.DriverFactory;
import factory.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    @BeforeMethod
    public void setUp() {
        WebDriver driver = DriverFactory.createDriver();
        DriverManager.setDriver(driver);

// checking: WebDriver is created, DriverManager is storing the correct driver,Setup is happening per test method
//        System.out.println("THREAD: " + Thread.currentThread().getId());
//        System.out.println("DRIVER: " + DriverManager.getDriver());

        DriverManager.getDriver().manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }

}
