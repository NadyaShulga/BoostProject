package com.models;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(xpath = "//div[contains(@class,'pwr-header-right-bar__item')]/a[@aria-label = 'Button Get Started']")
    private WebElement getStartedButton;

    public HomePage(WebDriver driver) {

        super(driver);
    }

    public ContactPage clickGetStarted() {
        getStartedButton.click();

        return new ContactPage(getDriver());
    }
}