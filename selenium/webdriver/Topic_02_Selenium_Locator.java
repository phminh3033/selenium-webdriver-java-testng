package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_02_Selenium_Locator {
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
        driver.get("https://auth.applitools.com/users/login");
    }

    @Test
    public void TC_01_ID() {
        //<input type="email" placeholder="Enter email" name="email" id="email" aria-required="true" aria-describedby="email-err-msg" class="textbox">
        driver.findElement(By.id("email")).sendKeys("??????");
        System.out.print(driver.findElement(By.id("email")));
    }

    @Test
    public void TC_02_Class() {
        //<h1 class="title">Sign in</h1>
        driver.findElement(By.className("title"));
        System.out.println(driver.findElement(By.className("title")));
    }

    @Test
    public void TC_03_Name() {
        driver.findElement(By.name("password"));
        System.out.println(driver.findElement(By.name("password")));
    }

    @Test
    public void TC_04_tagName() {
        driver.findElements(By.tagName("input"));
        System.out.println(driver.findElements(By.tagName("input")));
    }

    @Test
    public void TC_05_link() {
        //Độ chinh xác cao
        //driver.findElement(By.linkText("Learn More"));
        driver.findElement(By.linkText("Forgot?"));

    }

    @Test
    public void TC_05_partialLinkText() {
        //D chính xác kh cao: Lấy 1 phần
        driver.findElement(By.partialLinkText("cookie policy"));
    }

    @Test
    public void TC_06_css() {
        //Css vs ID
        driver.findElement(By.cssSelector("input[id='email']"));
        driver.findElement(By.cssSelector("input#email"));
        driver.findElement(By.cssSelector("#email"));

        //Css vs class
        driver.findElement(By.cssSelector("a[class='link']"));
        driver.findElement(By.cssSelector("a.link"));
        driver.findElement(By.cssSelector(".link"));

        //Css vs Name
        driver.findElement(By.cssSelector("input[name='password']"));

        //Css vs tagname
        driver.findElements(By.cssSelector("input"));

        //Css vs link
        driver.findElement(By.cssSelector("a[href='/users/general-register']"));

        //Css vs partial link
        driver.findElement(By.cssSelector("a[href*='general-register']")); //giua
        //driver.findElement(By.cssSelector("a[href^='users']")); //dau
        driver.findElement(By.cssSelector("a[href$='general-register']")); //cuoi
    }

    @Test
    public void TC_07_xpath() {
        //Css vs ID
        driver.findElement(By.xpath("//input[@id='email']"));

        //Css vs class
        driver.findElement(By.xpath("//a[@class='link']"));

        //Css vs Name
        driver.findElement(By.xpath("//input[@name='password']"));

        //Css vs tagname
        driver.findElements(By.xpath("//input"));

        //Css vs link
        driver.findElement(By.xpath("//a[@href='/users/general-register']"));
        driver.findElement(By.xpath("//a[text()='Try now']"));

        //Css vs partial link
        driver.findElement(By.xpath("//a[contains(@href,'users')]")); //giua
        driver.findElement(By.xpath("//a[contains(text(),'Try now')]"));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}