package utils;

import org.openqa.selenium.WebElement;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVUtils {
    public static void writeDataToCsv(String filePath, List<WebElement> links) {
        try (FileWriter writer = new FileWriter(filePath)) {
            // Loop through the data and write each entry to the file
            for (WebElement link : links) {
                writer.append(link.getText());
                writer.append(",");  // Separate entries with a comma
            }
            // Remove the last comma and add a newline at the end
            writer.append("\n");

            System.out.println("CSV file created successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
