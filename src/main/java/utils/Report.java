package utils;

// Extent report imports
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Report {

    // Generate and configure Extent report
    public ExtentReports reportGenerator() {

        // Create ExtentReports instance
        ExtentReports extent = new ExtentReports();

        // Configure Spark reporter with output path
        ExtentSparkReporter spark =
            new ExtentSparkReporter("target/Spark.html");

        // Attach reporter to extent
        extent.attachReporter(spark);

        // Add system information to report
        extent.setSystemInfo("os", "Windows");

        return extent;
    }
}