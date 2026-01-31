package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class ProductsPage {
    WebDriver driver;
    WebDriverWait wait;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators
    By productsTitle = By.cssSelector(".title");
    By inventoryList = By.cssSelector(".inventory_list");
    By cartIcon = By.cssSelector(".shopping_cart_link");

    public void assertUserIsOnProductsPage() {
        Assert.assertTrue(driver.getCurrentUrl().contains("/inventory.html"), "URL mismatch");

        String titleText = wait.until(ExpectedConditions.visibilityOfElementLocated(productsTitle)).getText();
        Assert.assertEquals(titleText, "Products", "Products page title mismatch");

        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(inventoryList)).isDisplayed(),
                "Inventory not visible");

        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(cartIcon)).isDisplayed(),
                "Cart icon missing");
    }
}
