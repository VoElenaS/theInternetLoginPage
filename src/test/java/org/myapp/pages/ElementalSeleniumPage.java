package org.myapp.pages;

import org.myapp.utils.ElementActions;
import org.myapp.utils.Urls;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementalSeleniumPage extends BasePage {
    private final By elementalSeleniumPageHeader = By.xpath("//h1[@class='hero__title'][text()='Elemental Selenium']");

    public ElementalSeleniumPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public ElementalSeleniumPage open(WebDriver driver) {
        driver.get(Urls.ELEMENTALSELENIUM_URL);
        return this;
    }

    public boolean isElementalSeleniumPageHeaderVisible() {
        return ElementActions.isVisible(wait, elementalSeleniumPageHeader);
    }

}
