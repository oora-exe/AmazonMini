package configReader;

// Java IO and utility imports
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ObjectReader {

    // Properties object to store key-value pairs
    private Properties pro;

    // Getter for Properties
    public Properties getPro() {
        return pro;
    }

    // Setter for Properties
    public void setPro(Properties pro) {
        this.pro = pro;
    }

    // Constructor to load Object.properties file
    public ObjectReader() throws IOException {
        pro = new Properties();

        // Get current project directory
        String projectDir = System.getProperty("user.dir");

        // Load properties file
        FileInputStream fis = new FileInputStream(
                projectDir + "/Object Repository/Object.properties");

        pro.load(fis);
    }

    // Base URL of the application
    public String getBaseUrl() {
        return pro.getProperty("BaseUrl");
    }

    // Home page title
    public String getHomeTitle() {
        return pro.getProperty("HomePageTitle");
    }

    // Search box locator
    public String getSearchObject() {
        return pro.getProperty("Amazon.Search.id");
    }

    // Search button locator
    public String getSearchButtonObject() {
        return pro.getProperty("Amazon.SearchButton.id");
    }

    // Search keyword value
    public String getSearchKeyValue() {
        return pro.getProperty("Amazon.Search.Key");
    }

    // Search result text locator
    public String getSearchTextObject() {
        return pro.getProperty("Amazon.SearchPage.ResultText.xpath");
    }

    // Expected search page text
    public String getSearchPageTextVerifyProperty() {
        return pro.getProperty("Amazon.SearchPage.ResultText");
    }

    // Search box value locator
    public String getSearchBoxValueObject() {
        return pro.getProperty("Amazon.SearchPage.SearchBoxValue.xpath");
    }

    // Validation text locator
    public String getSearchPageValidateTextObject() {
        return pro.getProperty("Amazon.SearchPage.validateText.xpath");
    }

    // Regex for validating search text
    public String getSearchPageValidateTextProperty() {
        return pro.getProperty("Amazon.SearchPage.validateText.regex");
    }

    // Sort list locator
    public String getSortListObject() {
        return pro.getProperty("Amazon.SearchPage.Sortlist.xpath");
    }

    // Newest arrival sort option
    public String getSortListSelectObject() {
        return pro.getProperty("Amazon.Searchpage.Sortlist.newestArrival.id");
    }

    // Sort result verification locator
    public String getSortListResultObject() {
        return pro.getProperty("Amazon.SearchPage.Sortlist.resultVerify.xpath");
    }

    // Expected sort result text
    public String getSortListResultProperty() {
        return pro.getProperty("Amazon.SearchPage.Sortlist.resultVerify.text");
    }

    // Search results container locator
    public String getSearchResultObject() {
        return pro.getProperty("Amazon.SearchPage.SearchResult.xpath");
    }

    // Sponsored label locator
    public String getSponsoredLabelObject() {
        return pro.getProperty("Amazon.SearchPage.SponsoredLabel.xpath");
    }

    // Product name locator
    public String getProductNameObject() {
        return pro.getProperty("Amazon.SearchPage.ProductName.xpath");
    }

    // Product price locator
    public String getProductPriceObject() {
        return pro.getProperty("Amazon.SearchPage.ProductPrice.xpath");
    }

    // Product price prefix
    public String getProductPricePrefix() {
        return pro.getProperty("Amazon.SearchPage.ProductPrice.prefix");
    }

    // Fallback price value
    public String getProductPriceFallback() {
        return pro.getProperty("Amazon.SearchPage.ProductPrice.fallback");
    }

    // Number of top products to fetch
    public int getTopProductsCount() {
        return Integer.parseInt(
            pro.getProperty("Amazon.SearchPage.TopProducts.count"));
    }

    // Screenshot storage path
    public String getScreenShotProperty() {
        return pro.getProperty("Amazon.ScreenShot.path");
    }
}