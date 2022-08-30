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
    private static final By MARKET_ID = By.id("logoPartMarket");
    private static final String SITE_TITLE_WORD = "Маркет";
    private static final int MAX_TIME_TO_WAIT = Integer.MAX_VALUE;
    private static final String MARKET_PAGE = "https://market.yandex.ru";
    private static WebDriverWait webDriverWait;
    private static WebDriver driver;

    private static MainMarketPage mainMarketPage;
    private static ComputersPage computersPage;
    private static LaptopPage laptopPage;



    @BeforeClass
    public static void setup() {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(TestSearchLaptops.getMaxTimeToWait()));
        System.setProperty("chromedriver", "chromedriver.exe");
        driver.manage().window().maximize();
        mainMarketPage = new MainMarketPage(driver);
        computersPage = new ComputersPage(driver);
        laptopPage = new LaptopPage(driver);
    }

    @Test
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
        Assert.assertTrue(isSuitable);
    }

    @Test
    public void allFindItemsAreInCorrectPriceRange() {
        List<WebElement> laptopPrices = laptopPage.getLaptopPrices();
        boolean isInRange = true;
        try {
            for (WebElement element : laptopPrices) {
                int price = Integer.parseInt(element.getText().trim().replaceAll(" ", ""));
                if (!(price >= Integer.parseInt(LaptopPage.getMinPrice())) && !(price < Integer.parseInt(LaptopPage.getMaxPrice())))
                    isInRange = false;
            }
        } catch (NumberFormatException e) {
            isInRange = false;
        }
        Assert.assertTrue(isInRange);
    }

    @AfterClass
    public static void tearDown(){
        driver.quit();
    }


    public static By getMarketId() {
        return MARKET_ID;
    }

    public static String getSiteTitleWord() {
        return SITE_TITLE_WORD;
    }

    public static int getMaxTimeToWait() {
        return MAX_TIME_TO_WAIT;
    }

    public static WebDriverWait getWebDriverWait() {
        return webDriverWait;
    }

    public static String getMarketPage() {
        return MARKET_PAGE;
    }
}
