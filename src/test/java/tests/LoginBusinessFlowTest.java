package tests;

import base.BaseTest;
import factory.DriverManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;
import utils.ConfigReader;

public class LoginBusinessFlowTest extends BaseTest {

    @Test
    public void shouldOpenLoginPageSuccessfully() {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.open();
    }


    @Test
    public void shouldLoginSuccessfullyAndDisplayProductsPage() {
        String username = ConfigReader.get("username");
        String password = ConfigReader.get("password");

       //Checking config.properties:
        // System.out.println("CONFIG: " + username + " / " + password);

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
