package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators
    private By productsTitle = By.cssSelector(".title");
    private By inventoryList = By.cssSelector(".inventory_list");
    private By cartIcon = By.cssSelector(".shopping_cart_link");

    public boolean isOnProductsPage() {
        return driver.getCurrentUrl().contains("/inventory.html");
    }

    public String getProductsTitleText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productsTitle)).getText();
    }

    public boolean isInventoryVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(inventoryList)).isDisplayed();
    }

    public boolean isCartIconVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cartIcon)).isDisplayed();
    }
}
