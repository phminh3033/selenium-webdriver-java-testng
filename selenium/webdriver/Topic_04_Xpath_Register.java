package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_04_Xpath_Register {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        }

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    String userName, pass;

//    @Test
//    public void TC_Redirect (){
//        driver.get("https://demo.guru99.com/v4/");
//        driver.findElement(By.xpath("//li[text()='Visit - ']/a[text()='here']")).click();
//    }
    @Test
    public void TC_01_Xpath_Register() {
        driver.get("https://demo.guru99.com");

        //enter a random email
        driver.findElement(By.xpath("//td[text()='Email ID']/following-sibling::td/input[@name='emailid']")).sendKeys("a@a.com");

        //click submit
        driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

        //Get userName , pass
        userName = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
        pass = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();


    }

    @Test
    public void TC_02_Xpath_Login() {
        driver.get("https://demo.guru99.com/v4");
        driver.findElement(By.name("uid")).sendKeys(userName);
        driver.findElement(By.name("password")).sendKeys(pass);

        //click submit
        driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}