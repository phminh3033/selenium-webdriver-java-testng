package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_04_Xpath {
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

    @Test
    public void TC_Xpath_Tech_01() {
        driver.get("https://tiki.vn");
        driver.findElement(By.xpath("//div[@class='Userstyle__MenuItemRevamp-sc-6e6am-19 bhXqXQ']"));
    }

    @Test
    public void TC_Xpath_Tech_02() {
        driver.get("https://tiki.vn");
        //parrent + child
        driver.findElement(By.xpath("//a[@href='https://tiki.vn/khuyen-mai/hero-top-san-pham-ban-chay']//img[@class='styles__StyledImg-sc-p9s3t3-0 hbqSye']"));

    }

    @Test
    public void TC_Xpath_Tech_03() {
        driver.get("https://tiki.vn");
        //absolute
        driver.findElement(By.xpath("//div[text()='Sản phẩm mới']"));
        driver.findElement(By.xpath("//div[@data-view-id='home_deal']//p[(text()='Hàng xịn giá sốc')]"));
        //relative
        driver.findElement(By.xpath("//div[contains(text(),'Câu hỏi')]"));
    }

    @Test
    public void TC_Xpath_Tech_05 () {
        driver.get("https://automationfc.github.io/basic-form");

        //nestedText
        String nestedText = driver.findElement(By.xpath("//h5[@id='nested']")).getText();
        System.out.println(nestedText);
        Assert.assertEquals(nestedText, "Hello World! (Ignore Me) @04:45 PM");

        //concat
        //code kho doc
        //code kho doc
        driver.findElement(By.xpath("//span[text()=concat('Hello \"John\", What', \"'s happened?\")]")).isDisplayed();

        //code de hieu, de verify
        String concatText = driver.findElement(By.xpath("//span[@class='concat']")).getText();
        System.out.println(concatText);
        Assert.assertEquals(concatText, "Hello \"John\", What's happened?");
    }

    @Test
    public void TC_Xpath_Tech_AXES_08 () {
        driver.get("http://live.techpanda.org/index.php/mobile.html");
        driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button"));
        driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div/ancestor::*/li/child::*/following-sibling::div/div/p/parent::div"));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}