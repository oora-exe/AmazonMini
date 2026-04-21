package utils;

// Java IO and time imports
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Selenium imports
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

// Config reader import
import configReader.ObjectReader;

public class ScreenShot {

    // WebDriver instance
    private WebDriver driver;

    // Object repository reader
    private ObjectReader or;

    // Destination file reference
    private File dest;

    // Constructor to initialize driver and properties
    public ScreenShot(WebDriver driver) throws IOException {
        this.driver = driver;
        this.or = new ObjectReader();
    }

    // Generate screenshot path with timestamp
    public String generateScreenshotPath(String testName) {
        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
        return or.getScreenShotProperty()
                + testName + "_" + timestamp + ".png";
    }

    // Capture and save screenshot
    public String takeScreenshot(String testName) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver; // Cast driver
        File srcFile = ts.getScreenshotAs(OutputType.FILE); // Capture screenshot
        String path = generateScreenshotPath(testName); // Build file path
        dest = new File(path);
        FileUtils.copyFile(srcFile, dest); // Save screenshot
        return path;
    }
}