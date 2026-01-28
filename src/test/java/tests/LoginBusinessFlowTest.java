package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;
import utils.ConfigReader;

public class LoginBusinessFlowTest extends BaseTest {

    @Test
    public void shouldLoginSuccessfullyAndDisplayProductsPage() {

        String username = ConfigReader.get("username");
        String password = ConfigReader.get("password");

        LoginPage loginPage = new LoginPage(getDriver());

        ProductsPage productsPage = loginPage
                .open()
                .login(username, password);

        productsPage.assertUserIsOnProductsPage();
    }
}
