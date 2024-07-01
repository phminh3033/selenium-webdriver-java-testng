 package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_21_Wait_01_Element_Status {
    WebDriver driver;
    WebDriverWait explicitWait;
    By regEmail = By.cssSelector("input[name='reg_email_confirmation__']");

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.facebook.com/");
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Visibile_Displayed() {
        // DK1: Element CO xuat hien tren UI va van CO trong cay HTML
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSecond(2);
        driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("am@gmail.com");
        sleepInSecond(2);

        // Tai thoi diem nay/step nay thi textbox dang visible
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(regEmail));
        Assert.assertTrue(driver.findElement(regEmail).isDisplayed());
    }

    @Test
    public void TC_02_Invisible_InDOM() {
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSecond(2);

        driver.findElement(By.cssSelector("input[name='reg_email__']")).clear();
        sleepInSecond(2);

        // DK2: Element KHONG xuat hien tren UI va van CO trong cay HTML
        // Tai thoi diem nay/step nay thi textbox dang invisible
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(regEmail));
        Assert.assertFalse(driver.findElement(regEmail).isDisplayed());
    }

    @Test
    public void TC_02_Invisible_NOT_InDOM() {
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSecond(2);

        // DK3: Element KHONG xuat hien tren UI va KHONG trong cay HTML
        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
        sleepInSecond(2);

        // Tai thoi diem nay/step nay thi textbox dang invisible
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(regEmail));
        //Assert.assertFalse(driver.findElement(regEmail).isDisplayed());
    }

    @Test
    public void TC_03_Presence() {
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSecond(2);

        driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("am@gmail.com");
        sleepInSecond(2);

        // DK1: Element CO xuat hien tren UI va van CO trong cay HTML
        // Tai thoi diem nay/step nay thi textbox dang presence
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(regEmail));

        driver.findElement(By.cssSelector("input[name='reg_email__']")).clear();
        sleepInSecond(2);

        // DK2: Element KHONG xuat hien tren UI va van CO trong cay HTML
        // Tai thoi diem nay/step nay thi textbox dang presence
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(regEmail));
    }

    @Test
    public void TC_04_Staleness() {
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSecond(2);

        //Attached to the DOM
        WebElement reconfirmEmail = driver.findElement(regEmail);

        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
        sleepInSecond(2);

        //Wait until an element is no longer attached to the DOM
        explicitWait.until(ExpectedConditions.stalenessOf(reconfirmEmail));
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