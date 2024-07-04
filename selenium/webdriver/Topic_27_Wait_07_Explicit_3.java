package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

public class Topic_27_Wait_07_Explicit_3 {
    WebDriver driver;
    WebDriverWait explicitWait;

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
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Test
    public void TC_01_AjaxLoading() {
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
        By selectedDateBy = By.cssSelector("span#ctl00_ContentPlaceholder1_Label1");

        driver.findElement(By.xpath("//a[text()='12']")).click();

        // Wait cho Loading icon bien mat
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*=RadCalendar1]>div.raDiv")));


        Assert.assertEquals(driver.findElement(selectedDateBy).getText(), "Friday, July 12, 2024");
    }

    @Test
    public void TC_02_Upload_File() {
        driver.get("https://gofile.io/welcome");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Wait + Verify Spinner icon bien mat
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.spinner-border"))));

        // Wait + Click
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.ajaxLink>button"))).click();

        // Wait + Verify Spinner icon bien mat
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.spinner-border")))));

        driver.findElement(By.cssSelector("input[type='file']")).sendKeys(benzPath + "\n" + carPath + "\n" +  monitorPath);

        // Wait + Verify Spinner icon bien mat
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.spinner-border")))));

        // Wait + Verify process bar bien mat
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.progress")))));

        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.mainUploadSuccessLink a.ajaxLink"))).click();

        // Wait + Verify Spinner icon bien mat
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.spinner-border"))));

        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + benz + "']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Play']"))).isDisplayed());
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + car + "']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Play']"))).isDisplayed());
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + monitor + "']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Play']"))).isDisplayed());
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}