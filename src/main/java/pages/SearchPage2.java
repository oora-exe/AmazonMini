/*package pages;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import configReader.ObjectReader;

public class SearchPage2 {

    private WebDriver driver;
    private ObjectReader or;

    public SearchPage2(WebDriver driver) throws IOException {
        this.driver = driver;
        or = new ObjectReader();
    }

    public WebElement getActualSearchPageText() {
        WebElement text = driver.findElement(By.xpath(or.getSearchTextObject()));
        return text;
    }

    public String getExpectedSearchPageText() {
        return or.getSearchPageTextVerifyProperty();
    }

    public String getSearchBoxValueO() {
        WebElement text = driver.findElement(By.xpath(or.getSearchBoxValueObject()));
        return text.getAttribute("value");
    }

    public String getActualSearchPageValidateText() {
        WebElement text = driver.findElement(By.xpath(or.getSearchPageValidateTextObject()));
        return text.getText();
    }

    public String getExpectedSearchPageValidateText(String value) {
        String s = String.format(or.getSearchPageValidateTextProperty(), value);
        return s;
    }

    public WebElement getSortlist() {
        WebElement clickable = driver.findElement(By.xpath(or.getSortListObject()));
        return clickable;
    }

    public WebElement getSortlistNewestArrival() {
        WebElement ele = driver.findElement(By.id(or.getSortListSelectObject()));
        return ele;
    }

    public String getActualSortlistResult() {
        String res = driver.findElement(By.xpath(or.getSortListResultObject())).getText();
        return res;
    }

    public String getExpectedSortListResult() {
        return or.getSortListResultProperty();
    }

    public Map<String, String> getTop5Products() {

        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath(or.getSearchResultObject())
        ));

        JavascriptExecutor js = (JavascriptExecutor) driver;

        List<WebElement> items = driver.findElements(
                By.xpath(or.getSearchResultObject())
        );

        System.out.println("Total items found: " + items.size());

        Map<String, String> topProducts = new LinkedHashMap<>();

        for (WebElement item : items) {

            if (topProducts.size() >= or.getTopProductsCount()) break;

            try {
                js.executeScript(
                        "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", item
                );

                wait.until(ExpectedConditions.visibilityOf(item));

                boolean isSponsored = !item.findElements(
                        By.xpath(or.getSponsoredLabelObject())
                ).isEmpty();

                if (isSponsored)
                    continue;

                List<WebElement> nameElements = item.findElements(
                        By.xpath(or.getProductNameObject())
                );

                if (nameElements.isEmpty()) continue;

                String name = nameElements.get(0).getText().trim();

                if (name.isEmpty()) continue;

                String price;
                try {
                    price = or.getProductPricePrefix() + item.findElement(
                            By.xpath(or.getProductPriceObject())
                    ).getText().trim();
                } catch (NoSuchElementException e) {
                    price = or.getProductPriceFallback();
                }

                topProducts.put(name, price);

            } catch (Exception e) {
                System.out.println("Skipped item: " + e.getMessage());
            }
        }

        return topProducts;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public ObjectReader getOr() {
        return or;
    }

    public void setOr(ObjectReader or) {
        this.or = or;
    }
} */