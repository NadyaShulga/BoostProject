package com.models;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ContactPage extends HomePage {

    @FindBy(id = "hs-eu-confirmation-button")
    private WebElement acceptCookiesButton;

    @FindBy(name = "firstname")
    private WebElement firstNameField;

    @FindBy(name = "lastname")
    private WebElement lastNameField;

    @FindBy(name = "email")
    private WebElement emailField;

    @FindBy(name = "jobtitle")
    private WebElement jobTitleField;

    @FindBy(name = "company")
    private WebElement companyField;

    @FindBy(name = "country")
    private WebElement countryField;

    @FindBy(name = "message")
    private WebElement messageField;

    @FindBy(xpath = "//*[contains(@name,'contact_interest_reason')]/option[7]")
    private WebElement contactInterestReasonDropdown;

    @FindBy(xpath = "//input[@type='checkbox']/..")
    private WebElement marketingCheckbox;

    @FindBy(xpath = "//div[2]/div[2]/input")
    private WebElement sendButton;

    public ContactPage(WebDriver driver) {

        super(driver);
    }

    public void acceptCookies() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(acceptCookiesButton)).click();
    }

    public void fillForm(String firstName, String lastName, String email, String jobTitle, String company, String country, String message) {
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        emailField.sendKeys(email);
        jobTitleField.sendKeys(jobTitle);
        companyField.sendKeys(company);
        countryField.sendKeys(country);
        messageField.sendKeys(message);
        contactInterestReasonDropdown.click();
    }

    public void checkMarketingCheckbox() {

        marketingCheckbox.click();
    }

    public void clickSend() {

        sendButton.click();
    }
}
