package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import test.TestSearchLaptops;

public class MainMarketPage {
    private final WebDriver driver;
    private final By searchInputId = By.id("header-search");
    private final String searchItem = "Компьютеры";
    private final By searchButton = By.xpath("/html/body/div[4]/header/div/div/div/div/div/div/form/div[1]/button/span");

    public MainMarketPage(WebDriver driver) {
        this.driver = driver;
    }

    private void getMainPage() {
        driver.get(TestSearchLaptops.getMarketPage());
        TestSearchLaptops.getWebDriverWait().until(ExpectedConditions.textToBe(TestSearchLaptops.getMarketId(), TestSearchLaptops.getSiteTitleWord()));
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
