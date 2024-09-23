package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

/**
 * Utility class for handling screenshot operations in Selenium WebDriver.
 */
public class ScreenshotUtils {
    /**
     * Captures a screenshot of the current browser window and saves it to the specified file path.
     *
     * @param driver   the WebDriver instance controlling the browser session
     * @param filePath the path where the screenshot will be saved (including the file name and extension)
     * @throws IOException if there is an error while saving the screenshot file
     */
    public static void saveScreenshot(WebDriver driver, String filePath) throws IOException {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);

        // Specify the destination where the screenshot will be saved
        File destFile = new File(filePath);

        // Save the screenshot to the specified location
        FileUtils.copyFile(srcFile, destFile);
    }

}
