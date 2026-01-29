package base;

import factory.DriverManager;
import org.openqa.selenium.WebDriver;

public class BasePage {
    protected WebDriver driver;

    public BasePage() {
        this.driver = DriverManager.getDriver();
    }


}
