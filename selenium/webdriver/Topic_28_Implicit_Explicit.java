package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;


public class Topic_28_Implicit_Explicit {
    WebDriver driver;
    WebDriverWait explicit;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
    }

    @Test
    public void TC_01_Implicit_Found() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.facebook.com/");

        // Khi tim se thay ngay element
        // KHONG can cho het timeout
        driver.findElement(By.cssSelector("input#email"));
    }

    @Test
    public void TC_02_Implicit_NOT_Found() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.facebook.com/");

        // Khi tim se KHONG thay element
        // Polling moi 1/2s 1 lan
        // Khi het timeout se FAILED testcase va throw exception: NoSuchElementException
        driver.findElement(By.cssSelector("input#auto"));
    }

    @Test
    public void TC_03_Explicit_Found() {
        driver.get("https://www.facebook.com/");
        explicit = new WebDriverWait(driver,Duration.ofSeconds(10));
        explicit.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));

    }

    @Test
    public void TC_04_Explicit_NOT_Found_Param_By() {
        driver.get("https://www.facebook.com/");
        explicit = new WebDriverWait(driver,Duration.ofSeconds(10));

        // Khi tim se KHONG thay element
        // Polling moi 1/2s 1 lan
        // Khi het timeout se FAILED testcase va throw exception: TimeOutException
        explicit.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#auto")));

    }

    @Test
    public void TC_04_Explicit_NOT_Found_Param_WebElement() {
        driver.get("https://www.facebook.com/");
        explicit = new WebDriverWait(driver,Duration.ofSeconds(10));

        System.out.println("Start: " + dateTimeToNow());
        try {
            explicit.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input#auto"))));
        } catch (Exception e) {
            System.out.println("End: " + dateTimeToNow());
            e.printStackTrace();
        }
    }

    @Test
    public void TC_05_Mix_Implicit_Explicit_Found() {
        driver.get("https://www.facebook.com/");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
        explicit = new WebDriverWait(driver,Duration.ofSeconds(5));

        System.out.println("Start: " + dateTimeToNow());

        try {
            explicit.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#auto")));
        } catch (Exception e) {
            System.out.println("End: " + dateTimeToNow());
            e.printStackTrace();
        }

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public String dateTimeToNow () {
        Date date = new Date();
        return date.toString();
    }

}