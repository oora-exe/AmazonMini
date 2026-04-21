/*
package testcases;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import browserImplementation.BrowserFactory;
import configReader.ObjectReader;
import pages.HomePage;
import pages.SearchPage;
import utils.Report;
import utils.ScreenShot;

public class AmazonTestcases {


    BrowserFactory browserFactory;
    HomePage homePage;
    SearchPage searchPage;
    WebDriver driver;
    WebDriverWait wait;
    ObjectReader objReader;
    ScreenShot screenShot;
    Report report;
    ExtentReports extent;

    @Test(priority = 0)
    public void HomePageVerify() throws InterruptedException, IOException {
        ExtentTest test = extent.createTest("This is HomePage Report");
        try {
            driver.get(homePage.getHomeUrl());
            String expectedResult = homePage.verify_Title();
            wait.until(ExpectedConditions.titleIs(expectedResult));
            String actualResult = driver.getTitle();
            Assert.assertEquals(actualResult, expectedResult);
            String path = screenShot.takeScreenshot("HomePageVerify_Pass");
            test.addScreenCaptureFromPath(path);
            test.pass("HomePage_reterived");
        } catch (AssertionError e) {
            String path = screenShot.takeScreenshot("HomePageVerify_Fail");
            test.addScreenCaptureFromPath(path);
            test.fail("HomePageFail too retrived");
            throw e;
        }
    }

    @Test(dependsOnMethods = {"HomePageVerify"})
    public void SearchBox() throws IOException {
        ExtentTest test = extent.createTest("This is SearchBox Report");
        try {
            WebElement searchBox = homePage.searchBoxLocate();
            wait.until(ExpectedConditions.visibilityOf(searchBox));
            homePage.searchProduct(searchBox);
            String path = screenShot.takeScreenshot("SearchBox_Pass");
            test.addScreenCaptureFromPath(path);
            test.pass("SearchBox_retrieved");
        } catch (AssertionError e) {
            String path = screenShot.takeScreenshot("SearchBox_Fail");
            test.addScreenCaptureFromPath(path);
            test.fail("SearchBox_Fail retrieved");
            throw e;
        }
    }

    @Test(dependsOnMethods = {"SearchBox"})
    public void SearchPage() throws IOException {
        ExtentTest test = extent.createTest("This is SearchPage Report");
        try {
            WebElement actualElement = searchPage.getActualSearchPageText();
            wait.until(ExpectedConditions.visibilityOf(actualElement));
            String expectedResult1 = searchPage.getExpectedSearchPageText();
            String actualResult1 = actualElement.getText();
            Assert.assertEquals(expectedResult1, actualResult1);
            String path = screenShot.takeScreenshot("SearchPage_Pass");
            test.addScreenCaptureFromPath(path);
            test.pass("SearchPage_retrieved");
        } catch (AssertionError e) {
            String path = screenShot.takeScreenshot("SearchPage_Fail");
            test.addScreenCaptureFromPath(path);
            test.fail("SearchPage_Fail retrieved");
            throw e;
        }
    }

    @Test(dependsOnMethods = {"SearchPage"})
    public void SearchPageValidate() throws IOException {
        ExtentTest test = extent.createTest("This is SearchPageValidate Report");
        try {
            String value = searchPage.getSearchBoxValueO();
            System.out.println("Search Input : " + value); 
            String actual = searchPage.getActualSearchPageValidateText();
            System.out.println("Actual Text      : " + actual);  
            String expected = searchPage.getExpectedSearchPageValidateText(value);
             System.out.println("Expected Regex   : " + expected);
            boolean assertValue = actual.matches(expected);
            Assert.assertTrue(assertValue);
            String path = screenShot.takeScreenshot("SearchPageValidate_Pass");
            test.addScreenCaptureFromPath(path);
            test.pass("SearchPageValidate_retrieved");
        } catch (AssertionError e) {
            String path = screenShot.takeScreenshot("SearchPageValidate_Fail");
            test.addScreenCaptureFromPath(path);
            test.fail("SearchPageValidate_Fail retrieved");
            throw e;
        }
    }
        
//
    @Test(dependsOnMethods = {"SearchPageValidate"})
    public void SearchPageSort() throws IOException {
        ExtentTest test = extent.createTest("This is SearchPageSort Report");
        try {
            WebElement sort = searchPage.getSortlist();
            sort.click();
            WebElement sortNew = searchPage.getSortlistNewestArrival();
            sortNew.click();
            String actual = searchPage.getActualSortlistResult();
            String expected = searchPage.getExpectedSortListResult();
            Assert.assertEquals(actual, expected);
            String path = screenShot.takeScreenshot("SearchPageSort_Pass");
            test.addScreenCaptureFromPath(path);
            test.pass("SearchPageSort_retrieved");
        } catch (AssertionError e) {
            String path = screenShot.takeScreenshot("SearchPageSort_Fail");
            test.addScreenCaptureFromPath(path);
            test.fail("SearchPageSort_Fail retrieved");
            throw e;
        }
    }
    //
    
    @Test(dependsOnMethods = {"SearchPage"})
    public void SearchPageSort() throws IOException {
        ExtentTest test = extent.createTest("This is SearchPageValidate Report");
        try {
            String value = searchPage.getSearchBoxValueO();
            String actual = searchPage.getActualSearchPageValidateText();

            System.out.println("Search Input : " + value);
            System.out.println("Actual Text  : " + actual);

            // Example actual text: "1-16 of 510 results for"
            Pattern pattern = Pattern.compile("(\\d+)-(\\d+) of (\\d+) results");
            Matcher matcher = pattern.matcher(actual);

            int itemsPerPage = 0;
            int totalItems = 0;
            int totalPages = 0;

            if (matcher.find()) {
                int start = Integer.parseInt(matcher.group(1));
                int end = Integer.parseInt(matcher.group(2));
                totalItems = Integer.parseInt(matcher.group(3));

                itemsPerPage = end - start + 1;
                totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);
            }

            System.out.println("Items per page : " + itemsPerPage);
            System.out.println("Total items   : " + totalItems);
            System.out.println("Total pages   : " + totalPages);

            Assert.assertTrue(itemsPerPage > 0);
            Assert.assertTrue(totalPages > 0);

            String path = screenShot.takeScreenshot("SearchPageValidate_Pass");
            test.addScreenCaptureFromPath(path);
            test.pass("SearchPageValidate retrieved");

        } catch (AssertionError e) {
            String path = screenShot.takeScreenshot("SearchPageValidate_Fail");
            test.addScreenCaptureFromPath(path);
            test.fail("SearchPageValidate failed");
            throw e;
        }
    }

    @Test(dependsOnMethods = {"SearchPageSort"})
    public void GetTop5Phones() throws IOException {
        ExtentTest test = extent.createTest("This is GetTop5Phones Report");
        try {
            Map<String, String> top5 = searchPage.getTop5Products();
            int i = 1;
            for (Map.Entry<String, String> entry : top5.entrySet()) {
                System.out.println(i++ + ". " + entry.getKey() + " - " + entry.getValue());
            }
            Assert.assertEquals(top5.size(), 5);
            String path = screenShot.takeScreenshot("_Top5Phones_Pass");
            test.addScreenCaptureFromPath(path);
            test.pass("GetTop5Phones_retrieved");
        } catch (AssertionError e) {
            String path = screenShot.takeScreenshot("_Top5Phones_Fail");
            test.addScreenCaptureFromPath(path);
            test.fail("GetTop5Phones_Fail retrieved");
            throw e;
        }
    } 

    @BeforeClass
    public void beforeClass() throws IOException, InterruptedException {
        browserFactory = new BrowserFactory();        
        System.out.println("Enter the preferred browser(Chrome/Edge):");
        Scanner sc=new Scanner(System.in);
        String browser=sc.next();
        switch(browser.toLowerCase()) {
	        case "chrome":
	        	driver = browserFactory.chromeLaunch();
	        	break;
	        case "edge":
	        	driver=browserFactory.Launch_Edge();
	        	break;
	        default:
	        	System.out.println("Please enter a valid browser name");
	        	break;
        }
        driver.manage().window().maximize();
        searchPage = new SearchPage(driver);
        homePage = new HomePage(driver);
        screenShot = new ScreenShot(driver);
        wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        report = new Report();
        extent = report.reportGenerator();
        sc.close();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();  
        extent.flush();
    }
}
*/