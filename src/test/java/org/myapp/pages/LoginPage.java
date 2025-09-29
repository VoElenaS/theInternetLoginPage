package org.myapp.pages;

import org.myapp.models.User;
import org.myapp.utils.Urls;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.myapp.utils.ElementActions.*;

public class LoginPage extends BasePage {
    private final By userNameInputLocator = By.id("username");
    private final By passwordInputLocator = By.id("password");
    private final By loginButton = By.className("radius");
    private final By flashMessageInvalid = By.id("flash");
    //private final By loginPageHeader = By.xpath("//h2[text()='Login Page']");
    private final By loginPageHeader = By.tagName("h2");

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public LoginPage open() {
        driver.get(Urls.LOGIN_URL);
        return this;
    }

    public SecureAreaPage loginAs(User user) {
        return enterUserName(user.getName())
                .enterPassword(user.getPassword())
                .submitSuccessfulLogin();
    }

    public LoginPage loginWithInvalidCredentials(User user) {
        return enterUserName(user.getName())
                .enterPassword(user.getPassword())
                .submitForFailure();
    }

    public SecureAreaPage submitSuccessfulLogin() {
        click(wait, loginButton);
        return new SecureAreaPage(driver, wait);
    }

    private LoginPage submitForFailure() {
        click(wait, loginButton);
        return this;
    }

    private LoginPage enterUserName(String userName) {
        typeText(wait, userNameInputLocator, userName);
        return this;
    }

    private LoginPage enterPassword(String password) {
        typeText(wait, passwordInputLocator, password);
        return this;
    }

    public String getMessageUserInvalid() {
        return getText(wait, flashMessageInvalid);
    }

    public boolean isLoginPageHeaderVisible() {
        return isVisible(wait, loginPageHeader);
    }

}
