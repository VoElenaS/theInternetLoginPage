package org.myapp.utils;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.function.Supplier;

public final class ElementActions {
    private static final Logger logger = LoggerFactory.getLogger(ElementActions.class.getName());

    private ElementActions() {
    }

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
            logger.warn("Alert not present: " + e.getMessage());
            return false;
        }
    }

    public static void acceptAlert(WebDriverWait wait) {
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.accept();
        } catch (Exception e) {
            logger.warn("Alert not present: " + e.getMessage());
        }
    }

    public static String getAlertTextAndAccept(WebDriverWait wait) {
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String alertText = alert.getText();
            alert.accept();
            return alertText;
        } catch (Exception e) {
            logger.warn("Error handling alert: " + e.getMessage());
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

    private static boolean safeWait(Supplier<?> waitAction) {
        try {
            waitAction.get();
            return true;
        } catch (Exception e) {
            logger.warn("safeWait caught exception: " + e.getMessage());
            return false;
        }
    }

    public static void diagnoseSelect(WebDriver driver, String xpath, String textToSelect) {
    WebElement selectEl = driver.findElement(By.xpath(xpath));

    System.out.println("====== SELECT DIAGNOSTICS START ======");

    System.out.println("Displayed: " + selectEl.isDisplayed());
    System.out.println("Enabled : " + selectEl.isEnabled());
    System.out.println("Size : " + selectEl.getRect());
    System.out.println("Z-index : " + selectEl.getCssValue("z-index"));

    String html = selectEl.getAttribute("innerHTML");
    System.out.println("\nINNER HTML BEFORE:");
    System.out.println(html);

    List<WebElement> options = selectEl.findElements(By.tagName("option"));
    System.out.println("\nOPTIONS LIST:");
    for (int i = 0; i < options.size(); i++) {
        System.out.println(
            " [" + i + "] value='" + options.get(i).getAttribute("value") + 
            "', text='" + options.get(i).getText() + 
            "', displayed=" + options.get(i).isDisplayed());
    }

    boolean exists = options.stream().anyMatch(o -> o.getText().trim().equals(textToSelect));
    System.out.println("\nOption '" + textToSelect + "' exists? → " + exists);

    options.stream()
            .filter(o -> o.getAttribute("value").equals(textToSelect))
            .forEach(o -> System.out.println(
                "Found by value. Text NOW = '" + o.getText() + "' (may be late AJAX!)"));

    JavascriptExecutor js = (JavascriptExecutor) driver;
    Long overlays = (Long) js.executeScript(
        "let el = arguments[0];" +
        "let rect = el.getBoundingClientRect();" +
        "let centerX = rect.left + rect.width/2;" +
        "let centerY = rect.top + rect.height/2;" +
        "let topEl = document.elementFromPoint(centerX, centerY);" +
        "return (topEl === el) ? 0 : 1;", 
        selectEl
    );
    System.out.println("\nOverlay on top? → " + (overlays == 1 ? "YES! (covered!)" : "No"));

    System.out.println("====== SELECT DIAGNOSTICS END ======");
}

}
