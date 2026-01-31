package tests;

import base.BaseTest;
import factory.DriverManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;
import utils.ConfigReader;

public class ProductsPageTest extends BaseTest {

    @Test
    public void shouldDisplayProductsAfterLogin() {

        String username = ConfigReader.get("username");
        String password = ConfigReader.get("password");

        LoginPage loginPage = new LoginPage(DriverManager.getDriver());

        ProductsPage productsPage = loginPage
                .open()
                .login(username, password);

        Assert.assertTrue(productsPage.isOnProductsPage(), "URL mismatch");
        Assert.assertEquals(productsPage.getProductsTitleText(), "Products", "Products page title mismatch");
        Assert.assertTrue(productsPage.isInventoryVisible(), "Inventory not visible");
        Assert.assertTrue(productsPage.isCartIconVisible(), "Cart icon missing");

    }
}
