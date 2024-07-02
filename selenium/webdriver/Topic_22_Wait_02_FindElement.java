package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;
import java.util.List;

public class Topic_22_Wait_02_FindElement {
    WebDriver driver;
    WebDriverWait explicitWait;
    FluentWait<WebDriver> fluentWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();

        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // ver 3
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));    // ver 4

        //explicitWait = new WebDriverWait(driver, 10); // ver 3
        //explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10)); // ver 4

        //fluentWait = new FluentWait(driver);
        //fluentWait.withTimeout(5000, TimeUnit.MILLISECONDS).pollingEvery(250, TimeUnit.MILLISECONDS); // ver 3
        //fluentWait.withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofMillis(500)); // ver 4

        driver.get("https://www.facebook.com/");
    }

    @Test
    public void TC_01_FindElement() {
        /*  Case 1: Tim duoc chi co 1 Element
            + KHONG can cho het timeout 10s
            + Tim thay se tra ve 1 Element
            + Qua next step
        */
        System.out.println("Start: " + getDateTimeNow());
        driver.findElement(By.cssSelector("input#email"));
        System.out.println("End: " + getDateTimeNow());

        /* Case 2: Tim duoc > 1 Element
            + KHONG can cho het timeout 10s
            + Lay cai first element du co N node
            + Qua next step
        */
        driver.findElement(By.cssSelector("input[type='text'],[type='password']")).sendKeys("asd@gmai.com");

        /* Case 3: KHONG tim duoc Element
            + Cho het timeout la 10s
            + Trong time 10s nay cu moi 1/2(s) se tim lai 1 lan (polling)
              # Tim lai ma THAY -> Tra ve element -> next step
              # Tim lai KHONG THAY -> FAILED + throw NoSuchElementException
        */
        driver.findElement(By.cssSelector("input#not=found"));
    }

    @Test
    public void TC_02_FindElements() {
        List<WebElement> elementList;

        /*  Case 1: Tim duoc chi co 1 Element
            + KHONG can cho het timeout 10s
            + Tim thay se tra ve List Element chua dung 1 element
            + Qua next step
        */
//        System.out.println("Start: " + getDateTimeNow());
//        elementList = driver.findElements(By.cssSelector("input#email"));
//        System.out.println("End: " + getDateTimeNow());
//        System.out.println("Element in list: " + elementList.size());

        /* Case 2: Tim duoc > 1 Element
            + KHONG can cho het timeout 10s
            + Tim thay se tra ve List Element chua NHIEU element
            + Qua next step
        */
//        System.out.println("Start: " + getDateTimeNow());
//        elementList = driver.findElements(By.cssSelector("input[type='text'],[type='password']"));
//        System.out.println("End: " + getDateTimeNow());
//        System.out.println("Element in list: " + elementList.size());

        /* Case 3: KHONG tim duoc Element
            + Cho het timeout la 10s
            + Trong time 10s nay cu moi 1/2(s) se tim lai 1 lan (polling)
              # Tim lai ma THAY -> Tra ve List element -> next step
              # Tim lai KHONG THAY -> Tra ve EMPTY LIST + NOT FAILED -> next step
        */
        System.out.println("Start: " + getDateTimeNow());
        elementList = driver.findElements(By.cssSelector("input[name='reg_email__]"));
        System.out.println("End: " + getDateTimeNow());
        System.out.println("Element in list: " + elementList.size());
    }


    @AfterClass
    public void afterClass() {
        //driver.quit();
    }


    public String getDateTimeNow () {
        Date date = new Date();
        return date.toString();
    }
}