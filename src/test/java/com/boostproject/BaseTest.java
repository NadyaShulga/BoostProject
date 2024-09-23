package com.boostproject;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public abstract class BaseTest {
    // Declare a private WebDriver instance to be used by all test classes extending BaseTest.
    private WebDriver driver;

    // This method is annotated with @BeforeTest, meaning it will run before any test methods are executed.
    @BeforeTest
    protected void setup() {
        // Setup ChromeDriver using WebDriverManager to ensure the correct driver version is available.
        WebDriverManager.chromedriver().setup();
        // Create a new instance of ChromeDriver if it hasn't been initialized yet.
        createChromeDriver();
        // Maximize the browser window for better visibility during test execution.
        this.driver.manage().window().maximize();
    }

    // This method is annotated with @AfterTest, meaning it will run after all test methods have completed.
    @AfterTest
    protected void tearDown() {
        // Check if the WebDriver instance exists. If it's not null, close the browser and cleanup resources.
        if (this.driver != null) {
            // Quit the WebDriver instance, closing all windows and terminating the WebDriver session.
            getDriver().quit();
            // Set the driver instance to null to ensure it's properly cleaned up.
            this.driver = null;
        }
    }

    // This private method ensures that the ChromeDriver is created only once for the test session.
    private void createChromeDriver() {
        // If the driver has not been initialized, create a new ChromeDriver instance.
        if (this.driver == null) {
            this.driver = new ChromeDriver();
        }
    }

    // This public method returns the WebDriver instance, allowing other test classes to access it.
    public WebDriver getDriver() {
        return this.driver;
    }

}
