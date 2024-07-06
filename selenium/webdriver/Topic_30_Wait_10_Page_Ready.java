package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Driver;
import java.time.Duration;


public class Topic_30_Wait_10_Page_Ready {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void TC_01_Nopcommerce() {
        driver.get("https://admin-demo.nopcommerce.com");

        driver.findElement(By.cssSelector("input#Email")).clear();
        driver.findElement(By.cssSelector("input#Password")).clear();
        driver.findElement(By.cssSelector("input#Email")).sendKeys("admin@yourstore.com");
        driver.findElement(By.cssSelector("input#Password")).sendKeys("admin");
        driver.findElement(By.cssSelector("button.login-button")).click();
        sleepInSecond(2);

        Assert.assertTrue(isPageLoadedSuccess());

        driver.findElement(By.xpath("//i[contains(@class,'fa-user')]/following-sibling::p")).click();
        driver.findElement(By.xpath("//ul[@style='display: block;']//i[contains(@class,'fa-dot-circle')]/following-sibling::p[contains(string(),'Customers')]")).click();
        Assert.assertTrue(isPageLoadedSuccess());

        driver.findElement(By.xpath("//i[contains(@class,'fa-shopping-cart')]/following-sibling::p")).click();
        driver.findElement(By.xpath("//ul[@style='display: block;']//i[contains(@class,'fa-dot-circle')]/following-sibling::p[contains(string(),'Shipments')]")).click();
        Assert.assertTrue(isPageLoadedSuccess());

    }

    @Test
    public void TC_02_Class() {
        driver.get("https://api.orangehrm.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        //explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='OrangeHRM REST API Documentation']")).isDisplayed());

        // KHONG xai duoc isPageLoadedSuccess() vi dang trong trang thai load van true

    }

    @Test
    public void TC_03_Name() {

    }

    public boolean isPageLoadedSuccess() {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };
        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void sleepInSecond (long timeSleeping) {
        try {
            Thread.sleep(timeSleeping * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}