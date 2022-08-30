package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import test.TestSearchLaptops;

import java.util.List;

public class LaptopPage {

    private static final String MIN_PRICE = "25000";
    private static final String MAX_PRICE = "30000";

    private final By showAllManufacturersButton = By.xpath("/html/body/div[4]/div[2]/div/div[1]/div/div[5]/div/div/div/div/div/div[2]/div/div[4]/div/div/div/div/div[4]/div/fieldset/div/div/span/span");
    private final By manufacturersField = By.xpath("/html/body/div[4]/div[2]/div/div[1]/div/div[5]/div/div/div/div/div/div[2]/div/div[4]/div/div/div/div/div[4]/div/fieldset/div/div/div[1]/div/div");
    private final By manufacturersInput = By.xpath("/html/body/div[4]/div[2]/div/div[1]/div/div[5]/div/div/div/div/div/div[2]/div/div[4]/div/div/div/div/div[4]/div/fieldset/div/div/div[1]/div/div/input");
    private final By laptopCheckBox = By.xpath("/html/body/div[4]/div[2]/div/div[1]/div/div[5]/div/div/div/div/div/div[2]/div/div[4]/div/div/div/div/div[4]/div/fieldset/div/div/div[2]/div/div/div/div/label/span");
    private final By minPriceInput = By.xpath("/html/body/div[4]/div[2]/div/div[1]/div/div[5]/div/div/div/div/div/div[2]/div/div[4]/div/div/div/div/div[1]/div/fieldset/div/div/div/span[1]/div/div/input");
    private final By maxPriceInput = By.xpath("/html/body/div[4]/div[2]/div/div[1]/div/div[5]/div/div/div/div/div/div[2]/div/div[4]/div/div/div/div/div[1]/div/fieldset/div/div/div/span[2]/div/div/input");
    private final By resultElementSpecifications = By.xpath("//span[@data-tid='2e5bde87']");
    private final By resultElementPrice = By.xpath("//span[@data-autotest-value]/span[starts-with(text(),'2')]");
    private final String laptopBrand = "Lenovo";
    private final WebDriver driver;

    public LaptopPage(WebDriver driver) {
        this.driver = driver;
    }

    private void showAllLaptopManufacturers() {
        TestSearchLaptops.getWebDriverWait().until(ExpectedConditions.textToBe(TestSearchLaptops.getMarketId(), TestSearchLaptops.getSiteTitleWord()));
        driver.findElement(showAllManufacturersButton).click();
        waitElementLoad(manufacturersField);
        }

    private void setLaptopManufacturer() {
        driver.findElement(manufacturersInput).sendKeys(laptopBrand);
        waitElementLoad(laptopCheckBox);
        driver.findElement(laptopCheckBox).click();
    }

    private void setMinPrice() {
        waitElementLoad(minPriceInput);
        driver.findElement(minPriceInput).sendKeys(MIN_PRICE);
    }

    private void setMaxPrice() {
        waitElementLoad(maxPriceInput);
        driver.findElement(maxPriceInput).sendKeys(MAX_PRICE);
    }

    private void waitElementLoad(By webElement){
        TestSearchLaptops.getWebDriverWait().until(ExpectedConditions.elementToBeClickable(webElement));
        TestSearchLaptops.getWebDriverWait().until(ExpectedConditions.visibilityOf(driver.findElement(webElement)));

    }

    public void searchLaptops() {
        showAllLaptopManufacturers();
        setLaptopManufacturer();
        setMinPrice();
        setMaxPrice();
    }


    public List<WebElement> getLaptopSpecifications() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        List<WebElement> laptopSpecifications = driver.findElements(resultElementSpecifications);
        try {
            for (WebElement w : laptopSpecifications)
                w.getText();
        } catch (StaleElementReferenceException e) {
            laptopSpecifications = driver.findElements(resultElementSpecifications);
        }
        return laptopSpecifications;
    }

    public List<WebElement> getLaptopPrices() {
        return driver.findElements(resultElementPrice);
    }

    public static String getMinPrice() {
        return MIN_PRICE;
    }

    public static String getMaxPrice() {
        return MAX_PRICE;
    }

    public String getLaptopBrand() {
        return laptopBrand;
    }
}
