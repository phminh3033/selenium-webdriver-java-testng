package testNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class Topic_09_Parameter {
    WebDriver driver;

    @Parameters({"browser","device"})
    @BeforeClass
    public void beforeClass(String browserName, String browserVersion) {
        driver = getBrowserName(browserName);
        System.out.println("The thread ID for "+ browserName +" is "+ Thread.currentThread().getId());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("Browser name: " + browserName + " with ver " + browserVersion);
    }

    //@Parameters("env")
    @Test
    public void TC_01_LoginToSystem(@Optional("live") String envName)  {
        System.out.println("The thread ID is "+ Thread.currentThread().getId());
        System.out.println("Url: " + getEnvName(envName));
        driver.get(getEnvName(envName) + "/index.php/customer/account/login/");

        driver.findElement(By.xpath("//*[@id='email']")).sendKeys("selenium_11_01@gmail.com");
        driver.findElement(By.xpath("//*[@id='pass']")).sendKeys("111111");
        driver.findElement(By.xpath("//*[@id='send2']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains("selenium_11_01@gmail.com"));

    }

    public WebDriver getBrowserName (String browserName) {
        WebDriver driver;
        if (browserName.equals("firefox")){
            driver = new FirefoxDriver();
        } else if (browserName.equals("chrome")){
            driver = new ChromeDriver();
        } else if (browserName.equals("edge")){
            driver = new EdgeDriver();
        } else {
            throw new RuntimeException("URL name is not valid");
        }
        return driver;
    }

    public String getEnvName (String env) {
        String urlValue;
        if (env.equals("dev")){
            urlValue = "http://dev.techpanda.org";
        } else if (env.equals("stg")){
            urlValue = "http://stg.techpanda.org";
        } else if (env.equals("live")){
            urlValue = "http://live.techpanda.org";
        } else {
            throw new RuntimeException("Browser name is not valid");
        }
        return urlValue;
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
