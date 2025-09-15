package org.myapp.config;

import org.myapp.drivers.DriverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BrowserConfig {

    private static final Logger logger = LoggerFactory.getLogger(BrowserConfig.class);
    private static final Properties DEFAULT = new Properties();

    static {
        try (InputStream resourceAsStream = BrowserConfig.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (resourceAsStream != null) {
                DEFAULT.load(resourceAsStream);
            }
        } catch (IOException e) {
            logger.error("WARNING: failed ro load config.properties. Proceeding with system/default values...");
        }
    }

    private final DriverFactory.Browsers browser;
    private final boolean headless;
    private final boolean incognito;

    public BrowserConfig(DriverFactory.Browsers browser, boolean headless, boolean incognito) {
        this.browser = browser;
        this.headless = headless;
        this.incognito = incognito;
    }

    public static BrowserConfig browserParameters(String browserParam, String headlessParam, String incognitoParam) {

        String browserStr = resolve("browser", browserParam, "CHROME");

        DriverFactory.Browsers browserEnum;

        try {
            browserEnum = DriverFactory.Browsers.valueOf(browserStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Unsupported browser: " + browserStr);
        }

        boolean headless = Boolean.parseBoolean(resolve("headless", headlessParam, "false"));
        boolean incognito = Boolean.parseBoolean(resolve("incognito", incognitoParam, "false"));

        return new BrowserConfig(browserEnum, headless, incognito);
    }

    private static String resolve(String key, String methodParam, String defautValue) {
        if (methodParam != null && !methodParam.isEmpty()) {
            return methodParam;
        }

        String sysProperty = System.getProperty(key);
        if (sysProperty != null && !sysProperty.isEmpty()) {
            return sysProperty;
        }

        String defaultProperty = DEFAULT.getProperty(key);
        if (defaultProperty != null && !defaultProperty.isEmpty()) {
            return defaultProperty;
        }

        return defautValue;
    }

    public DriverFactory.Browsers getBrowser() {
        return browser;
    }

    public boolean isHeadless() {
        return headless;
    }

    public boolean isIncognito() {
        return incognito;
    }
}
