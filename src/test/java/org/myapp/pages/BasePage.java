package org.myapp.pages;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

public abstract class BasePage {
    private static final Logger logger = Logger.getLogger(BasePage.class.getName());
    protected final WebDriver driver;
    protected final WebDriverWait wait;

    public BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void refresh() {
        logger.info("Refreshing the page");
        driver.navigate().refresh();
    }

    public void goBack() {
        logger.info("Navigating back");
        driver.navigate().back();
    }

    public void goForward() {
        logger.info("Navigating forward");
        driver.navigate().forward();
    }

    public void takeScreenshot(String prefix) {
        try {
            Path path = Paths.get("./screenshots");
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }

            String timestamp = org.myapp.utils.DateUtils.getCurrentTimestamp();
            String fileName = prefix + "_" + timestamp + ".png";

            TakesScreenshot ts = (TakesScreenshot) driver;
            File screenshot = ts.getScreenshotAs(OutputType.FILE);
            Files.copy(screenshot.toPath(), path.resolve(fileName));

            logger.info("Screenshot saved: " + path.resolve(fileName));
        } catch (IOException e) {
            logger.severe("Screenshot failed: " + e.getMessage());
        }
    }
}
