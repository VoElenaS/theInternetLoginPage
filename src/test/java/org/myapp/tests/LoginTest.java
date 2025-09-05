package org.myapp.tests;

import org.myapp.models.User;
import org.myapp.pages.LoginPage;
import org.myapp.pages.SecureAreaPage;
import org.myapp.utils.DataGenerator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private final User validUser = User.builder()
            .name("tomsmith")
            .password("SuperSecretPassword!")
            .build();

    @Test
    public void testSuccessfulLogin() {
        SecureAreaPage secureAreaPage = new LoginPage(driver, wait)
                .open()
                .loginAs(validUser);
        secureAreaPage.takeScreenshot("successfulLogin");
        String expected = secureAreaPage
                .getSuccessfulMessage();

        Assert.assertTrue(expected.contains("You logged into a secure area!"));
    }

    @Test
    public void testFailLogin() {
        User user = DataGenerator.realUser();
        String expected = new LoginPage(driver, wait)
                .open()
                .loginWithInvalidCredentials(user)
                .getMessageUserInvalid();

        Assert.assertTrue(expected.contains("Your username is invalid!"));
    }

    @Test
    public void testLogout() {
        SecureAreaPage secureAreaPage = new LoginPage(driver, wait).open().loginAs(validUser);
        LoginPage logout = secureAreaPage.logout();

        Assert.assertTrue(logout.isLoginPageHeaderVisible(), "Login page header should be visible after logout");
    }

    @Test
    public void elementalSeleniumLinkShouldBeActive() {
        SecureAreaPage secureAreaPage = new LoginPage(driver, wait)
                .open()
                .loginAs(validUser);
        boolean elementalSeleniumPageHeaderVisible = secureAreaPage
                .elementalSeleniumLinkClick()
                .isElementalSeleniumPageHeaderVisible();

        Assert.assertTrue(elementalSeleniumPageHeaderVisible, "The Elemental Selenium page fails to load.");
    }

}