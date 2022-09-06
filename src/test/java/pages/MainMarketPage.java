package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import test.TestSearchLaptops;

public class MainMarketPage {
    private final WebDriver driver;
    private final By searchInputId = By.id("header-search");
    private final String searchItem = "Компьютеры";
    private final By searchButton = By.xpath("//button[.='Найти']");

    public MainMarketPage(WebDriver driver) {
        this.driver = driver;
    }

    private void getMainPage() {
        driver.get(TestSearchLaptops.MARKET_PAGE);
        TestSearchLaptops.webDriverWait.until(ExpectedConditions.textToBe(TestSearchLaptops.MARKET_ID, TestSearchLaptops.SITE_TITLE_WORD));
    }

    private void setInitSearchItem() {
        driver.findElement(searchInputId).sendKeys(searchItem);
    }

    private void clickSearch() {
        driver.findElement(searchButton).click();
    }

    public void setSearchItem() {
        getMainPage();
        setInitSearchItem();
        clickSearch();
    }

}
