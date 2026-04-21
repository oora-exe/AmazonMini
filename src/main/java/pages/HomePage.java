package pages;

// Java and Selenium imports
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

// Config reader import
import configReader.ObjectReader;

public class HomePage {

    // WebDriver instance
    private WebDriver driver;

    // Object repository reader
    private ObjectReader or;

    // Constructor to initialize driver and properties
    public HomePage(WebDriver driver) throws IOException {
        this.driver = driver;
        or = new ObjectReader();
    }

    // Get home page URL
    public String getHomeUrl() {
        return or.getBaseUrl();
    }

    // Get expected home page title
    public String verify_Title() {
        return or.getHomeTitle();
    }

    // Locate search box element
    public WebElement searchBoxLocate() {
        return driver.findElement(By.id(or.getSearchObject()));
    }

    // Enter product name and click search button
    public void searchProduct(WebElement searchBox) {
        searchBox.sendKeys(or.getSearchKeyValue());
        driver.findElement(By.id(or.getSearchButtonObject())).click();
    }
}
