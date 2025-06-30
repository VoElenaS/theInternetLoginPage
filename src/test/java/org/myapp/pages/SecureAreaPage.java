package org.myapp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.myapp.utils.ElementActions.*;

public class SecureAreaPage extends BasePage {
    private final By flashMessageSuccess = By.id("flash");
    private final By logout = By.cssSelector("a.button.secondary.radius");
    private final By elementalSeleniumLink = By.cssSelector("a[target='_blank'][href*='elementalselenium.com']");

    public SecureAreaPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public LoginPage logout() {
        click(wait, logout);
        return new LoginPage(driver, wait);
    }

    public String getSuccessfulMessage() {
        return getText(wait, flashMessageSuccess);
    }

    public boolean isElementalSeleniumLinkVisible() {
        return isVisible(wait, elementalSeleniumLink);
    }

    public ElementalSeleniumPage elementalSeleniumLinkClick() {
        click(wait, elementalSeleniumLink);
        switchToNewWindow(driver, wait, driver.getWindowHandle());
        return new ElementalSeleniumPage(driver, wait);
    }

    public boolean handlePossibleAlert() {
        return isAlertPresent(wait) && getAlertTextAndAccept(wait) != null;
    }

    public String handleAndGetAlertMessage() {
        return getAlertTextAndAccept(wait);
    }
}
