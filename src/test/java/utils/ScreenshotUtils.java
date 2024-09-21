package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtils {

    public static void saveScreenshot(WebDriver driver, String filePath) throws IOException {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);

        // Specify the destination where the screenshot will be saved
        File destFile = new File(filePath);

        // Save the screenshot to the specified location
        FileUtils.copyFile(srcFile, destFile);
    }

}
