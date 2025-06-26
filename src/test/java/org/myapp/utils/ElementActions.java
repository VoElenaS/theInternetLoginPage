package org.myapp.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
