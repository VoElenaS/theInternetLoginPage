package org.myapp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SecureAreaPage extends BasePage {
    private final By flash = By.id("flash");
    private final By logout = By.cssSelector("a.button.secondary.radius");

    public SecureAreaPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void logoutVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(logout));
    }

    public String getSuccessfulMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(flash)).getText();
    }
}
