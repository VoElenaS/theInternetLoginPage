package org.myapp.pages;

import org.myapp.utils.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementalSeleniumPage extends BasePage {

    private final By elementalSeleniumPageHeader = By.xpath("//h1[@class='hero__title'][text()='Elemental Selenium']");

    public ElementalSeleniumPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public boolean isElementalSeleniumPageHeaderVisible() {
        return ElementActions.isVisible(wait, elementalSeleniumPageHeader);
    }
}
