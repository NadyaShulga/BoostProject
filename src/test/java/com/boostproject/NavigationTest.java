package com.boostproject;

import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.CSVUtils;
import utils.ScreenshotUtils;

import java.io.IOException;
import java.util.List;

public class NavigationTest extends BaseTest {

    private final static String EXPECTED_URL = "https://www.boostb2b.com/";

    @Test
    public void saveLinksTest() throws IOException {
        WebDriver driver = getDriver();
        driver.get(EXPECTED_URL);
        final String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL, EXPECTED_URL);

        List<WebElement> links = driver.findElements(By.xpath("//div[contains(@class, 'pwr-header-full')]//ul[@role='menu']//a"));
        CSVUtils.writeDataToCsv("BoostB2B_NavigationListing.csv", links);

        ScreenshotUtils.saveScreenshot(driver, "BoostB2B_ Navigation.png");

    }

}

