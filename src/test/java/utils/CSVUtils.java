package utils;

import org.openqa.selenium.WebElement;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Utility class for handling CSV file operations.
 */
public class CSVUtils {
    /**
     * Writes the text content of a list of WebElements to a CSV file.
     * Each WebElement's text will be separated by a comma in the resulting CSV file.
     *
     * @param filePath the path to the CSV file where the data should be written
     * @param links    the list of WebElements whose text content is to be written to the CSV file
     */
    public static void writeDataToCsv(String filePath, List<WebElement> links) {
        try (FileWriter writer = new FileWriter(filePath)) {
            // Loop through the data and write each entry to the file
            for (WebElement link : links) {
                writer.append(link.getText());
                writer.append(",");
            }
            // Remove the last comma and add a newline at the end
            writer.append("\n");

            System.out.println("CSV file created successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
