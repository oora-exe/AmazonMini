package pages;

// Java import
import java.io.IOException;

// Selenium imports
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

// Config reader import
import configReader.ObjectReader;

public class SearchPage {

    // WebDriver instance
    private WebDriver driver;

    // Object repository reader
    private ObjectReader or;

    // Constructor to initialize driver and properties
    public SearchPage(WebDriver driver) throws IOException {
        this.driver = driver;
        or = new ObjectReader();
    }

    // Get search page result text element
    public WebElement getActualSearchPageText() {
        WebElement text =
            driver.findElement(By.xpath(or.getSearchTextObject()));
        return text;
    }

    // Get expected search page text
    public String getExpectedSearchPageText() {
        return or.getSearchPageTextVerifyProperty();
    }

    // Get value entered in search box
    public String getSearchBoxValueO() {
        WebElement text =
            driver.findElement(By.xpath(or.getSearchBoxValueObject()));
        return text.getAttribute("value");
    }

    // Get actual validation text from search page
    public String getActualSearchPageValidateText() {
        WebElement text =
            driver.findElement(By.xpath(or.getSearchPageValidateTextObject()));
        return text.getText();
    }

    // Build expected validation regex text
    public String getExpectedSearchPageValidateText(String value) {
        String s =
            String.format(or.getSearchPageValidateTextProperty(), value);
        return s;
    }

    // Get sort dropdown element
    public WebElement getSortlist() {
        WebElement clickable =
            driver.findElement(By.xpath(or.getSortListObject()));
        return clickable;
    }

    // Get newest arrival sort option
    public WebElement getSortlistNewestArrival() {
        WebElement ele =
            driver.findElement(By.id(or.getSortListSelectObject()));
        return ele;
    }

    // Get actual sorted result text
    public String getActualSortlistResult() {
        String res =
            driver.findElement(By.xpath(or.getSortListResultObject()))
                  .getText();
        return res;
    }

    // Get expected sorted result text
    public String getExpectedSortListResult() {
        return or.getSortListResultProperty();
    }

    // Get WebDriver instance
    public WebDriver getDriver() {
        return driver;
    }

    // Set WebDriver instance
    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    // Get ObjectReader instance
    public ObjectReader getOr() {
        return or;
    }

    // Set ObjectReader instance
    public void setOr(ObjectReader or) {
        this.or = or;
    }
}