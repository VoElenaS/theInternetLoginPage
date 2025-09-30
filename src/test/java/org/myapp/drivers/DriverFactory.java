package org.myapp.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class DriverFactory {
    private final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class);
    private static volatile DriverFactory instance;
    private DriverFactory() {
    }

    public static DriverFactory getInstance() {
        if (instance == null) {
            synchronized (DriverFactory.class) {
                if (instance == null) {
                    instance = new DriverFactory();
                }
            }
        }
        return instance;
    }

    private static WebDriver createChromeDriver(boolean headless, boolean incognito) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        if (headless) chromeOptions.addArguments("--headless=new");
        if (incognito) chromeOptions.addArguments("--incognito");
        return new ChromeDriver(chromeOptions);
    }

    private static WebDriver createEdgeDriver(boolean headless, boolean incognito) {
        System.setProperty("webdriver.edge.driver", "C:\\drivers\\msedgedriver.exe");
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        if (headless) edgeOptions.addArguments("--headless");
        if (incognito) edgeOptions.addArguments("--inprivate");
        return new EdgeDriver(edgeOptions);
    }

    private static WebDriver createFirefoxDriver(boolean headless, boolean incognito) {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        if (headless) firefoxOptions.addArguments("-headless");
        if (incognito) firefoxOptions.addArguments("-private");
        return new FirefoxDriver(firefoxOptions);
    }

    public void setDriver(Browsers browser, boolean headless, boolean incognito) {
        logger.info("Initializing WebDriver for browser: {} (headless={}, incognito={})", browser, headless, incognito);
        WebDriver webDriver;
        switch (browser) {
            case CHROME -> {
                webDriver = createChromeDriver(headless, incognito);
            }
            case EDGE -> {
                webDriver = createEdgeDriver(headless, incognito);
            }
            case FIREFOX -> {
                webDriver = createFirefoxDriver(headless, incognito);
            }
            default -> {
                throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
        }

        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driverThreadLocal.set(webDriver);
        logger.info("{} WebDriver started successfully", browser);
    }

    public WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
            logger.info("WebDriver quite successfully");
        }
    }

    public enum Browsers {
        CHROME, EDGE, FIREFOX
    }
}
