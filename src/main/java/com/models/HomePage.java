package com.models;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(xpath = "//*[@id='pwr-js-header-right-bar']/div[3]/a")
    private WebElement getStartedButton;

    public HomePage(WebDriver driver) {

        super(driver);
    }

    public ContactPage clickGetStarted() {
        getStartedButton.click();

        return new ContactPage(getDriver());
    }
}