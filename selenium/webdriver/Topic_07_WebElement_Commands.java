package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver; //Tương tác với Browser
import org.openqa.selenium.WebElement; //Tương tác với Element
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_07_WebElement_Commands {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Element() {
        // HTML Element
        //Tìm element
        driver.findElement(By.id(""));

        //Tìm và tương tác
        driver.findElement(By.id("")).click();
        driver.findElement(By.id("")).sendKeys();

        //Tìm và lưu vào 1 biến WebElement
        //Khi sử dụng biến này >2 lần
        WebElement fullNameTextbox = driver.findElement(By.id(""));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}