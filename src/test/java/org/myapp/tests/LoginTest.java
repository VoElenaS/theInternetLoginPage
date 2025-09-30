package org.myapp.tests;

import io.qameta.allure.*;
import org.myapp.models.User;
import org.myapp.pages.LoginPage;
import org.myapp.pages.SecureAreaPage;
import org.myapp.utils.DataGenerator;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private final User validUser = User.builder()
            .name("tomsmith")
            .password("SuperSecretPassword!")
            .build();

    @Test (description = "Verify user can login with valid credentials")
    @Description("Test checks that login works with correct username and password")
    @Epic("Authentication")
    @Feature("Login")
    @Story("Valid login")
    @Severity(SeverityLevel.CRITICAL)
    public void testSuccessfulLogin() {
        SecureAreaPage secureAreaPage = new LoginPage(driver, wait)
                .open()
                .loginAs(validUser);
        secureAreaPage.takeScreenshot("successfulLogin");
        String expected = secureAreaPage
                .getSuccessfulMessage();

        Assert.assertTrue(expected.contains("You logged into a secure area!"),
                "Expected success message was not displayed. Actual message: " + expected
        );
    }

    @Description("Test checks that login fails with invalid credentials")
    @Epic("Authentication")
    @Feature("Login")
    @Story("Invalid login")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void testFailLogin() {
        User user = DataGenerator.realUser();
        String expected = new LoginPage(driver, wait)
                .open()
                .loginWithInvalidCredentials(user)
                .getMessageUserInvalid();

        Assert.assertTrue(expected.contains("Your username is invalid!"));
    }

    @Description("Test checks that a logged-in user can successfully log out")
    @Epic("Authentication")
    @Feature("Logout")
    @Story("Successful logout")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testLogout() {
        SecureAreaPage secureAreaPage = new LoginPage(driver, wait).open().loginAs(validUser);
        LoginPage logout = secureAreaPage.logout();

        Assert.assertTrue(logout.isLoginPageHeaderVisible(), "Login page header should be visible after logout");
    }

    @Ignore
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