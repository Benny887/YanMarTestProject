package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import test.TestSearchLaptops;

import java.util.List;

public class LaptopPage {

    public static final String MIN_PRICE = "25000";
    public static final String MAX_PRICE = "30000";

    private final By showAllManufacturersButton = By.xpath("//span[contains(text(),'Показать всё')]");
    private final By manufacturersField = By.xpath("//label[text()='Найти производителя']");
    private final By manufacturersInput = By.xpath("(//label[text()='Найти производителя']/following::input)[1]");
    private final By laptopCheckBox = By.xpath("//span[text()='Lenovo']");
    private final By minPriceInput = By.xpath("(//label[contains(text(),'от')]/following::input)[1]");
    private final By maxPriceInput = By.xpath("(//label[contains(text(),'до')]/following::input)[1]");
    private final By resultElementSpecifications = By.xpath("//h3[@data-zone-name='title']");
    private final By resultElementPrice = By.xpath("//span[@data-autotest-value]/span[starts-with(text(),'2')]");
    private final String laptopBrand = "Lenovo";
    private final WebDriver driver;

    public LaptopPage(WebDriver driver) {
        this.driver = driver;
    }

    private void showAllLaptopManufacturers() {
        TestSearchLaptops.webDriverWait.until(ExpectedConditions.textToBe(TestSearchLaptops.MARKET_ID, TestSearchLaptops.SITE_TITLE_WORD));
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

    private void waitElementLoad(By webElement) {
        TestSearchLaptops.webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement));
        TestSearchLaptops.webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(webElement)));

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

    public String getLaptopBrand() {
        return laptopBrand;
    }
}
