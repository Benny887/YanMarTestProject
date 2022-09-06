package test;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.ComputersPage;
import pages.LaptopPage;
import pages.MainMarketPage;
import java.time.Duration;
import java.util.List;

public class TestSearchLaptops {
    public static final By MARKET_ID = By.id("logoPartMarket");
    public static final String SITE_TITLE_WORD = "Маркет";
    public static final int MAX_TIME_TO_WAIT = Integer.MAX_VALUE;
    public static final String MARKET_PAGE = "https://market.yandex.ru";
    public static WebDriverWait webDriverWait;
    public static WebDriver driver;

    private static MainMarketPage mainMarketPage;
    private static ComputersPage computersPage;
    private static LaptopPage laptopPage;


    @BeforeClass
    public static void setup() {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(MAX_TIME_TO_WAIT));
        System.setProperty("chromedriver", "chromedriver.exe");
        driver.manage().window().maximize();
        mainMarketPage = new MainMarketPage(driver);
        computersPage = new ComputersPage(driver);
        laptopPage = new LaptopPage(driver);
    }

    @Test()
    public void allFindItemsAreSuitableBrandLaptops() {
        boolean isSuitable = true;
        try {
            mainMarketPage.setSearchItem();
            computersPage.chooseLaptop();
            laptopPage.searchLaptops();
            List<WebElement> laptopSpecifications = laptopPage.getLaptopSpecifications();
            for (WebElement element : laptopSpecifications) {
                if (!(element.getText().toLowerCase().contains("ноутбук")) &&
                        !(element.getText().toLowerCase().contains(laptopPage.getLaptopBrand())))
                    isSuitable = false;
            }
        } catch (Exception e) {
            isSuitable = false;
        }
        Assert.assertTrue("Testing that all the items found are Lenovo brand laptops", isSuitable);
    }

    @Test
    public void allFindItemsAreInCorrectPriceRange() {
        List<WebElement> laptopPrices = laptopPage.getLaptopPrices();
        boolean isInRange = true;
        try {
            for (WebElement element : laptopPrices) {
                int price = Integer.parseInt(element.getText().trim().replaceAll(" ", ""));
                if (!(price >= Integer.parseInt(LaptopPage.MIN_PRICE)) && !(price < Integer.parseInt(LaptopPage.MAX_PRICE)))
                    isInRange = false;
            }
        } catch (NumberFormatException e) {
            isInRange = false;
        }
        Assert.assertTrue("Testing that the price of all found items is between 25,000 and 30,000", isInRange);
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

}
