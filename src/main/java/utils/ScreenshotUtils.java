package utils;

import factory.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtils {

    private static final String SCREENSHOT_FOLDER = "target/screenshots";

    public static String takeScreenshot(String testName) {

        WebDriver driver = DriverManager.getDriver();

        if (driver == null) {
            throw new RuntimeException("WebDriver is null. Cannot take screenshot.");
        }

        //Create folder if it doesn't exist
        try {
            Files.createDirectories(Paths.get(SCREENSHOT_FOLDER));
        } catch (IOException e) {
            throw new RuntimeException("Failed to create screenshot directory", e);
        }

        //Thread-safe naming (no overwrite in parallel)
        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));

        String threadId = String.valueOf(Thread.currentThread().getId());

        String fileName = testName + "_thread-" + threadId + "_" + timestamp + ".png";
        Path destinationPath = Paths.get(SCREENSHOT_FOLDER, fileName);

        //Take screenshot
        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            Files.copy(sourceFile.toPath(), destinationPath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save screenshot to: " + destinationPath, e);
        }

        return destinationPath.toString();
    }
}
