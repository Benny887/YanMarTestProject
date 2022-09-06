package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import test.TestSearchLaptops;

public class ComputersPage {

    private final By laptopXpath = By.xpath("//a[.='Ноутбуки']");
    private final WebDriver driver;

    public ComputersPage(WebDriver driver) {
        this.driver = driver;
    }

    public void chooseLaptop() {
        TestSearchLaptops.webDriverWait.until(ExpectedConditions.textToBe(TestSearchLaptops.MARKET_ID, TestSearchLaptops.SITE_TITLE_WORD));
        driver.findElement(laptopXpath).click();
    }
}
