package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class Topic_20_Upload_File {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");

    String benz = "benz.jpg";
    String car = "car.jpg";
    String monitor = "monitor.jpg";
    String character = File.separator;

    String benzPath = projectPath + character + "uploadFiles" + character + benz;
    String carPath = projectPath + character + "uploadFiles" + character + car;
    String monitorPath = projectPath + character + "uploadFiles" + character + monitor;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Single_Upload() {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        // Kh click vao nut upload, vi se mo Open Dialog cua Window len. Selenium kh ho tro Desktop app
        By uploadButton = By.cssSelector("input[name='files[]']");

        driver.findElement(uploadButton).sendKeys(benzPath);
        driver.findElement(uploadButton).sendKeys(carPath);
        driver.findElement(uploadButton).sendKeys(monitorPath);

        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + benz +"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + car +"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + monitor +"']")).isDisplayed());

        List<WebElement> starButtons = driver.findElements(By.cssSelector("td>button.start"));
        for (WebElement button : starButtons) {
            button.click();
            sleepInSecond(2);
        }
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + benz +"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + car +"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + monitor +"']")).isDisplayed());
    }

    @Test
    // 2 file la multiple roi
    public void TC_02_Multi_Upload() {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        // Kh click vao nut upload, vi se mo Open Dialog cua Window len. Selenium kh ho tro Desktop app
        By uploadButton = By.cssSelector("input[name='files[]']");

        driver.findElement(uploadButton).sendKeys(benzPath + "\n" + carPath + "\n" + monitorPath);

        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + benz +"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + car +"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + monitor +"']")).isDisplayed());

        List<WebElement> starButtons = driver.findElements(By.cssSelector("td>button.start"));
        for (WebElement button : starButtons) {
            button.click();
            sleepInSecond(2);
        }
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + benz +"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + car +"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + monitor +"']")).isDisplayed());

    }

    @Test
    public void TC_03_Name() {

    }

    @AfterClass
    public void afterClass() {
        //driver.quit();
    }

    public void sleepInSecond (long timeSleeping) {
        try {
            Thread.sleep(timeSleeping * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}