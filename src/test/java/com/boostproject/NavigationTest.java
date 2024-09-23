package com.boostproject;

import com.models.ContactPage;
import com.models.HomePage;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.CSVUtils;
import utils.ScreenshotUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


public class NavigationTest extends BaseTest {
    // Define the expected URL as a constant. This is the URL of the BoostB2B website.
    private final static String EXPECTED_URL = "https://www.boostb2b.com/";

    @Test
    public void saveLinksTest() throws IOException {
        // Initialize the WebDriver to launch the browser and navigate to the website.
        WebDriver driver = getDriver();
        // Navigate to the expected URL.
        driver.get(EXPECTED_URL);
        // Get the current URL of the loaded page.
        final String actualURL = driver.getCurrentUrl();
        // Verify that the actual URL matches the expected URL to ensure we're on the correct page.
        Assert.assertEquals(actualURL, EXPECTED_URL);

        // Locate all the navigation links in the header by identifying the element using an XPath selector.
        List<WebElement> links = driver.findElements(By.xpath("//div[contains(@class, 'pwr-header-full')]//ul[@role='menu']//a"));
        // Write the extracted links to a CSV file.
        // The utility method 'writeDataToCsv' is responsible for handling the file writing operation.
        CSVUtils.writeDataToCsv("BoostB2B_NavigationListing.csv", links);
        // Capture a screenshot of the current page.
        // The utility method 'saveScreenshot' is responsible for saving the screenshot as a PNG file.
        ScreenshotUtils.saveScreenshot(driver, "BoostB2B_ Navigation.png");
    }

    @Test
    public void validateSubMenuSolutions() {
        // Get the WebDriver instance
        WebDriver driver = getDriver();
        // Navigate to the expected URL
        driver.get(EXPECTED_URL);
        // Locate the 'Solutions' menu item in the navigation bar using XPath
        WebElement element = driver.findElement(By.xpath("//li[contains(@class, 'has-mega-menu')]/a[text() = 'Solutions']"));
        // Click the 'Solutions' menu to open the dropdown/submenu
        element.click();

        // Define the expected list of links under the 'Solutions' submenu
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
        // Find all submenu links under 'Solutions' using XPath and store them in a list of WebElements
        List<WebElement> actualLinks = driver.findElements(By.xpath("(//div[contains(@class, 'pwr-adc-main__cols')])[1]//a"));
        // Extract the text from each WebElement, trim it, and collect them into a list of strings
        List<String> actual_links_subtitle = actualLinks.stream().map(e -> e.getAttribute("innerText").trim()).collect(Collectors.toList());
        // Assert that the extracted submenu text is not null and matches exactly with the expected list of links
        assertThat(actual_links_subtitle)
                .isNotNull() // Ensure that the list of actual links is not null
                .containsExactlyElementsOf(expectedLinks); // Ensure that all expected links are present and in the correct order
    }

    @Test
    public void fillInForm() throws InterruptedException {
        // Get the WebDriver instance
        WebDriver driver = getDriver();
        // Navigate to the expected URL
        driver.get(EXPECTED_URL);

        // Create an instance of the com.models.HomePage
        HomePage homePage = new HomePage(driver);

        // Click the 'Get Started' button and navigate to the com.models.ContactPage
        ContactPage contactPage = homePage.clickGetStarted();

        // Accept cookies on the com.models.ContactPage
        contactPage.acceptCookies();

        // Fill in the contact form with provided details
        contactPage.fillForm(
                "Nadya",                         // First Name
                "Shulga",                                // Last Name
                "qa.shulga@gmail.com",                   // Email
                "Testing Technical Assessment",          // Job Title
                "Testing Technical Assessment",          // Company
                "United States",                         // Country
                "• Using timeapi.io GET /api/Time/current/zone, convert the returned date e.g. “02/23/2024” to “February 23, 2024” and paste in the comments box.\n" +
                        "• Link to API: https://timeapi.io/swagger/index.html" // Message
        );
        Thread.sleep(10000);
        // Check the marketing checkbox
        contactPage.checkMarketingCheckbox();

        // Click the 'Send' button
        contactPage.clickSend();
    }
}
