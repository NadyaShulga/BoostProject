package com.boostproject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.CSVUtils;
import utils.ScreenshotUtils;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

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

    @Test
    public void validateSubMenuSolutions() {
        WebDriver driver = getDriver();
        driver.get(EXPECTED_URL);
        WebElement element = driver.findElement(By.xpath("//li[contains(@class, 'has-mega-menu')]/a[text() = 'Solutions']"));
        element.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        List<String> expectedLinks = Arrays.asList(
                "For Buyers",
                "For Suppliers",
                "For Merchants",
                "For Issuers",
                "Boost 100",
                "Boost Intercept",
                "Dynamic Boost",
                "U.S. Domestic",
                "U.S. Cross-Border",
                "International Companies",
                "Global Insights"
        );

        List<WebElement> actualLinks = driver.findElements(By.xpath("//*[@id=\"hs_menu_wrapper_header_page_\"]//li[contains(@class, 'hs-menu-item hs-menu-depth-3')]//a[@role='menuitem']"));

        for (String expectedLink : expectedLinks) {
            boolean linkFound = false;
            for (WebElement link : actualLinks) {
                String linkName = link.getAttribute("innerText").trim();
                if (linkName.equals(expectedLink)) {
                    linkFound = true;
                    break; // Exit the loop once the link is found
                }
            }
            assertTrue(expectedLink + " was not found in the Solutions sub-menu.", linkFound);
            assertEquals(expectedLinks.size(), actualLinks.size());
        }
    }
}
