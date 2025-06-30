package org.myapp.utils;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;
import java.util.logging.Logger;

public class ElementActions {

    private static final Logger logger = Logger.getLogger(ElementActions.class.getName());

    public static void typeText(WebDriverWait wait, By locator, String inputText) {
        WebElement webElement = waitForVisibility(wait, locator);
        clearAndType(webElement, inputText);
    }

    public static void typeText(WebDriverWait wait, WebElement webElement, String inputText) {
        waitForVisibility(wait, webElement);
        clearAndType(webElement, inputText);
    }

    private static void clearAndType(WebElement webElement, String inputText) {
        webElement.clear();
        webElement.sendKeys(inputText);
    }

    public static String getText(WebDriverWait wait, By locator) {
        return waitForVisibility(wait, locator).getText();
    }

    public static String getText(WebDriverWait wait, WebElement webElement) {
        return waitForVisibility(wait, webElement).getText();
    }

    public static void click(WebDriverWait wait, By locator) {
        waitForClickability(wait, locator).click();
    }

    public static void click(WebDriverWait wait, WebElement webElement) {
        waitForClickability(wait, webElement).click();
    }

    public static boolean isVisible(WebDriverWait wait, By locator) {
        return safeWait(() -> waitForVisibility(wait, locator));
    }

    public static boolean isVisible(WebDriverWait wait, WebElement webelement) {
        return safeWait(() -> waitForClickability(wait, webelement));
    }

    public static boolean isClickable(WebDriverWait wait, By locator) {
        return safeWait(() -> waitForClickability(wait, locator));
    }

    public static boolean isClickable(WebDriverWait wait, WebElement webElement) {
        return safeWait(() -> waitForClickability(wait, webElement));
    }

    public static WebElement find(WebDriverWait wait, By locator) {
        return waitForPresence(wait, locator);
    }

    public static boolean isAlertPresent(WebDriverWait wait) {
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (Exception e) {
            logger.warning("Alert not present: " + e.getMessage());
            return false;
        }
    }

    public static void acceptAlert(WebDriverWait wait) {
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.accept();
        } catch (Exception e) {
            logger.warning("Alert not present: " + e.getMessage());
        }
    }

    public static String getAlertTextAndAccept(WebDriverWait wait) {
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String alertText = alert.getText();
            alert.accept();
            return alertText;
        } catch (Exception e) {
            logger.warning("Error handling alert: " + e.getMessage());
            return null;
        }
    }

    public static void switchToNewWindow(WebDriver driver, WebDriverWait wait, String currentWindowHandle) {
        wait.until(driverInstance -> {
            Set<String> handles = driverInstance.getWindowHandles();
            return handles.size() > 1;
        });

        Set<String> windowHandles = driver.getWindowHandles();

        for (String handle : windowHandles) {
            if (!currentWindowHandle.equals(handle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
    }

    private static WebElement waitForPresence(WebDriverWait wait, By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    private static WebElement waitForClickability(WebDriverWait wait, WebElement webElement) {
        return wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    private static WebElement waitForClickability(WebDriverWait wait, By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    private static WebElement waitForVisibility(WebDriverWait wait, By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private static WebElement waitForVisibility(WebDriverWait wait, WebElement webElement) {
        return wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    private static boolean safeWait(RunnableWait waitAction) {
        try {
            waitAction.run();
            return true;
        } catch (Exception e) {
            logger.warning("safeWait caught exception: " + e.getMessage());
            return false;
        }
    }

    @FunctionalInterface
    private interface RunnableWait {
        void run();
    }
}
