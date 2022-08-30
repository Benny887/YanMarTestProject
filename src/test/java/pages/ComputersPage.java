package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import test.TestSearchLaptops;

public class ComputersPage {

    private final By laptopSelector = By.cssSelector("body > div._111XI.main > div:nth-child(5) > div > div > div:nth-child(2) > div > div:nth-child(2) > div > div > div > div.sxrtt.a6Vij > div > div > div > div > div > div > div:nth-child(1) > div:nth-child(1) > a");
    private final WebDriver driver;

    public ComputersPage(WebDriver driver) {
        this.driver = driver;
    }

    public void chooseLaptop() {
        TestSearchLaptops.getWebDriverWait().until(ExpectedConditions.textToBe(TestSearchLaptops.getMarketId(), TestSearchLaptops.getSiteTitleWord()));
        driver.findElement(laptopSelector).click();
    }
}
