package browserImplementation;

// Selenium imports
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class BrowserFactory {

    // WebDriver reference
    private WebDriver driver;

    // Get current driver instance
    public WebDriver getDriver() {
        return driver;
    }

    // Set driver instance
    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    // Launch Chrome browser
    public WebDriver chromeLaunch() {
        driver = new ChromeDriver();
        return driver;
    }

    // Launch Edge browser
    public WebDriver Launch_Edge() {
        return driver = new EdgeDriver();
    }
}
