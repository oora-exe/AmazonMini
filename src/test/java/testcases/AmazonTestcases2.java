package testcases;

// Java utility imports
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Selenium imports
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// TestNG imports
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

// Extent report imports
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

// Framework imports
import browserImplementation.BrowserFactory;
import pages.HomePage;
import pages.SearchPage;
import utils.Report;
import utils.ScreenShot;

public class AmazonTestcases2 {

    // Object declarations
    BrowserFactory browserFactory;
    HomePage homePage;
    SearchPage searchPage;
    WebDriver driver;
    WebDriverWait wait;
    ScreenShot screenShot;
    Report report;
    ExtentReports extent;

    // Verify home page title
    @Test(priority = 0)
    public void HomePageVerify() throws InterruptedException, IOException {
        ExtentTest test = extent.createTest("This is HomePage Report");
        try {
            driver.get(homePage.getHomeUrl()); // Open Amazon home page
            String expectedResult = homePage.verify_Title(); // Expected title
            wait.until(ExpectedConditions.titleIs(expectedResult)); // Wait for title
            Assert.assertEquals(driver.getTitle(), expectedResult); // Validate title

            test.addScreenCaptureFromPath(
                screenShot.takeScreenshot("HomePageVerify_Pass"));
            test.pass("HomePage_retrieved");
        } catch (AssertionError e) {
            test.addScreenCaptureFromPath(
                screenShot.takeScreenshot("HomePageVerify_Fail"));
            test.fail("HomePageFail too retrieved");
            throw e;
        }
    }

    // Verify search box functionality
    @Test(dependsOnMethods = {"HomePageVerify"})
    public void SearchBox() throws IOException {
        ExtentTest test = extent.createTest("This is SearchBox Report");
        try {
            WebElement searchBox = homePage.searchBoxLocate(); // Locate search box
            wait.until(ExpectedConditions.visibilityOf(searchBox)); // Wait for visibility
            homePage.searchProduct(searchBox); // Search product

            test.addScreenCaptureFromPath(
                screenShot.takeScreenshot("SearchBox_Pass"));
            test.pass("SearchBox_retrieved");
        } catch (AssertionError e) {
            test.addScreenCaptureFromPath(
                screenShot.takeScreenshot("SearchBox_Fail"));
            test.fail("SearchBox_Fail retrieved");
            throw e;
        }
    }

    // Verify search results page
    @Test(dependsOnMethods = {"SearchBox"})
    public void SearchPage() throws IOException {
        ExtentTest test = extent.createTest("This is SearchPage Report");
        try {
            WebElement actualElement =
                searchPage.getActualSearchPageText(); // Get result text
            wait.until(ExpectedConditions.visibilityOf(actualElement));

            Assert.assertEquals(
                actualElement.getText(),
                searchPage.getExpectedSearchPageText()); // Validate page text

            test.addScreenCaptureFromPath(
                screenShot.takeScreenshot("SearchPage_Pass"));
            test.pass("SearchPage_retrieved");
        } catch (AssertionError e) {
            test.addScreenCaptureFromPath(
                screenShot.takeScreenshot("SearchPage_Fail"));
            test.fail("SearchPage_Fail retrieved");
            throw e;
        }
    }

    // Validate search result text using regex
    @Test(dependsOnMethods = {"SearchPage"})
    public void SearchPageValidate() throws IOException {
        ExtentTest test =
            extent.createTest("This is SearchPageValidate Report");
        try {
            String value = searchPage.getSearchBoxValueO(); // Search keyword
            String actual =
                searchPage.getActualSearchPageValidateText(); // Actual text
            String expected =
                searchPage.getExpectedSearchPageValidateText(value); // Regex pattern

            Assert.assertTrue(actual.matches(expected)); // Regex validation

            test.addScreenCaptureFromPath(
                screenShot.takeScreenshot("SearchPageValidate_Pass"));
            test.pass("SearchPageValidate_retrieved");
        } catch (AssertionError e) {
            test.addScreenCaptureFromPath(
                screenShot.takeScreenshot("SearchPageValidate_Fail"));
            test.fail("SearchPageValidate_Fail retrieved");
            throw e;
        }
    }

    // Verify sorting functionality
    @Test(dependsOnMethods = {"SearchPageValidate"})
    public void SearchPageSort() throws IOException {
        ExtentTest test =
            extent.createTest("This is SearchPageSort Report");
        try {
            searchPage.getSortlist().click(); // Open sort dropdown
            searchPage.getSortlistNewestArrival().click(); // Select newest

            Assert.assertEquals(
                searchPage.getActualSortlistResult(),
                searchPage.getExpectedSortListResult()); // Validate sort

            test.addScreenCaptureFromPath(
                screenShot.takeScreenshot("SearchPageSort_Pass"));
            test.pass("SearchPageSort_retrieved");
        } catch (AssertionError e) {
            test.addScreenCaptureFromPath(
                screenShot.takeScreenshot("SearchPageSort_Fail"));
            test.fail("SearchPageSort_Fail retrieved");
            throw e;
        }
    }

    // Validate pagination details
    @Test(dependsOnMethods = {"SearchPageSort"})
    public void SearchPagePagination() throws IOException {
        ExtentTest test =
            extent.createTest("This is SearchPagePagination Report");
        try {
            String actual =
                searchPage.getActualSearchPageValidateText(); // Result summary text

            Pattern pattern =
                Pattern.compile("(\\d+)-(\\d+) of (\\d+) results");
            Matcher matcher = pattern.matcher(actual);

            int itemsPerPage = 0;
            int totalItems = 0;
            int totalPages = 0;

            if (matcher.find()) {
                int start = Integer.parseInt(matcher.group(1));
                int end = Integer.parseInt(matcher.group(2));
                totalItems = Integer.parseInt(matcher.group(3));

                itemsPerPage = end - start + 1;
                totalPages =
                    (int) Math.ceil((double) totalItems / itemsPerPage);
            }

            Assert.assertTrue(itemsPerPage > 0); // Validate items count
            Assert.assertTrue(totalPages > 0);   // Validate pages count

            test.addScreenCaptureFromPath(
                screenShot.takeScreenshot("SearchPagePagination_Pass"));
            test.pass("SearchPagePagination retrieved");
        } catch (AssertionError e) {
            test.addScreenCaptureFromPath(
                screenShot.takeScreenshot("SearchPagePagination_Fail"));
            test.fail("SearchPagePagination failed");
            throw e;
        }
    }

    // Setup before test execution
    @BeforeClass
    public void beforeClass() throws IOException {
        browserFactory = new BrowserFactory();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the preferred browser (Chrome/Edge):");
        String browser = sc.next().toLowerCase();

        // Launch selected browser
        if (browser.equals("edge")) {
            driver = browserFactory.Launch_Edge();
        } else {
            driver = browserFactory.chromeLaunch();
        }

        driver.manage().window().maximize(); // Maximize browser
        wait = new WebDriverWait(driver,
                java.time.Duration.ofSeconds(10));

        // Initialize page objects and utilities
        homePage = new HomePage(driver);
        searchPage = new SearchPage(driver);
        screenShot = new ScreenShot(driver);

        report = new Report();
        extent = report.reportGenerator();

        sc.close();
    }

    // Cleanup after test execution
    @AfterClass
    public void afterClass() throws InterruptedException {
        Thread.sleep(5000); // Wait before closing
        driver.quit();       // Close browser
        extent.flush();      // Generate report
    }
}