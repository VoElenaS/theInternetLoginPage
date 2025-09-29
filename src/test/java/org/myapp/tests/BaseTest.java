package org.myapp.tests;

import org.myapp.config.BrowserConfig;
import org.myapp.drivers.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeMethod
    @Parameters({"browser", "headless", "incognito"})
    public void setUp(
            @Optional("") String browserParam,
            @Optional("") String headlessParam,
            @Optional("") String incognitoParam
    ) {
        BrowserConfig browserConfig = BrowserConfig.browserParameters(browserParam, headlessParam, incognitoParam);
        DriverFactory.getInstance().setDriver(browserConfig.getBrowser(), browserConfig.isHeadless(), browserConfig.isIncognito());
        driver = DriverFactory.getInstance().getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.getInstance().quitDriver();
    }
}
