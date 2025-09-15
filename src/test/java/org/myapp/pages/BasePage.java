package org.myapp.pages;

import org.myapp.utils.DateUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

public abstract class BasePage {
    protected final WebDriver driver;
    protected final WebDriverWait wait;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

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

            String timestamp = DateUtils.getCurrentTimestamp();
            String fileName = prefix + "_" + timestamp + ".png";

            TakesScreenshot ts = (TakesScreenshot) driver;
            File screenshot = ts.getScreenshotAs(OutputType.FILE);
            Files.copy(screenshot.toPath(), path.resolve(fileName));

            logger.info("Screenshot saved: " + path.resolve(fileName));
        } catch (IOException e) {
            logger.error("Screenshot failed: " + e.getMessage());
        }
    }
}
