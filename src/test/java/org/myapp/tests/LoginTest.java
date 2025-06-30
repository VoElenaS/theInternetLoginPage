package org.myapp.tests;

import org.junit.jupiter.api.Test;
import org.myapp.models.User;
import org.myapp.pages.ElementalSeleniumPage;
import org.myapp.pages.LoginPage;
import org.myapp.pages.SecureAreaPage;
import org.myapp.utils.DataGenerator;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LoginTest extends BaseTest {

    private final User validUser = User.builder()
            .name("tomsmith")
            .password("SuperSecretPassword!")
            .build();

    @Test
    public void testSuccessfulLogin() {
        SecureAreaPage secureAreaPage = new LoginPage(driver, wait)
                .open(driver)
                .loginAs(validUser);
        secureAreaPage.takeScreenshot("successfulLogin");
        String expected = secureAreaPage
                .getSuccessfulMessage();

        assertTrue(expected.contains("You logged into a secure area!"));
    }

    @Test
    public void testFailLogin() {
        User user = DataGenerator.realUser();
        String expected = new LoginPage(driver, wait)
                .open(driver)
                .loginWithInvalidCredentials(user)
                .getMessageUserInvalid();

        assertTrue(expected.contains("Your username is invalid!"));
    }

    @Test
    public void testLogout() {
        SecureAreaPage secureAreaPage = new LoginPage(driver, wait).open(driver).loginAs(validUser);
        LoginPage logout = secureAreaPage.logout();

        assertTrue(logout.isLoginPageHeaderVisible(), "Login page header should be visible after logout");
    }

    @Test
    public void elementalSeleniumLinkShouldBeActive() {
        SecureAreaPage secureAreaPage = new LoginPage(driver, wait).open(driver).loginAs(validUser);
        secureAreaPage.isElementalSeleniumLinkVisible();
        ElementalSeleniumPage elementalSeleniumPage = secureAreaPage.elementalSeleniumLinkClick();
        boolean elementalSeleniumPageHeaderVisible = elementalSeleniumPage.isElementalSeleniumPageHeaderVisible();

        assertTrue(elementalSeleniumPageHeaderVisible);
    }

}