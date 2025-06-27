package org.myapp.pages;

import org.myapp.utils.DateUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

abstract class BasePage {
    protected final WebDriver driver;
    protected final WebDriverWait wait;

    public BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void takeScreenshots(String prefix) throws IOException {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
