package org.myapp.pages;

import org.myapp.utils.DateUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    public void takeScreeShots(String prefix) throws IOException {
        Path path = Paths.get("./screenshots");

        if (!Files.exists(path)){
            Files.createDirectories(path);
        }

        String timestamp = DateUtils.getCurrentTimestamp();
    }
}
