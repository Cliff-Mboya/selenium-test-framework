package tests;

import base.BaseTest;
import factory.DriverManager;
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

        productsPage.assertUserIsOnProductsPage();
    }
}
