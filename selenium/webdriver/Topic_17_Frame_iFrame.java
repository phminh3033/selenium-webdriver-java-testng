package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_17_Frame_iFrame {
    WebDriver driver;
    Actions actions;

    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        actions = new Actions(driver);
    }

    @Test
    public void TC_01_Form_Site_iFrame() {

        /*//Chứa iframe - trang B
        //Từ A vào B
        driver.switchTo().frame("frame-one85593366");
        driver.findElement(By.cssSelector("")).click();
        //Từ B vào C
        driver.switchTo().frame("frame-23232");
        driver.findElement(By.cssSelector("")).click();
        //Từ C quay lại B
        driver.switchTo().parentFrame();
        //Đang ở B
        //Từ B quay lại A
        driver.switchTo().defaultContent();*/

        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");
        sleepInSecond(5);
        driver.findElement(By.cssSelector("div#imageTemplateContainer")).click();
        sleepInSecond(3);
        WebElement formFrame = driver.findElement(By.cssSelector("div#formTemplateContainer>iframe"));

        //Switch frame/ iframe trước khi thao tác với các element bên trong
        // driver.switchTo().frame(0); //index không chính xác
        // driver.switchTo().frame(""); //có trường hợp frame không có name/ id
        driver.switchTo().frame(formFrame);

        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-2"))).selectByVisibleText("Senior");

        driver.switchTo().defaultContent();

        WebElement loginButton = driver.findElement(By.cssSelector("nav.header--desktop-floater a.menu-item-login"));
        actions.moveToElement(loginButton).perform();
        sleepInSecond(2);
        Assert.assertEquals(Color.fromString(loginButton.getCssValue("background-color")).asHex().toLowerCase(), "#4a709a");
        loginButton.click();
    }

    @Test
    public void TC_02_Kyna_iFrame() {
        driver.get("https://skills.kynaenglish.vn/");
        /* 1. Swtich vao iFrame FB
            2. Verify so luong subscribe
            3. Swtich vao Wechat
            4. Nhap info
            5. Search khoa hoc
            6. Verify 1 khoa bat ky
         */
    }

    @Test
    public void TC_03_Hdfcbank_Frame() {
        driver.get("https://netbanking.hdfcbank.com/netbanking/");
        driver.switchTo().frame("login_page");
        driver.findElement(By.cssSelector("input[name='fldLoginUserId']")).sendKeys("Auto");
        driver.findElement(By.cssSelector("a.login-btn")).click();
        sleepInSecond(5);

        // Switch ra hay kh switch cung chay dc
        //driver.switchTo().defaultContent();

        Assert.assertTrue(driver.findElement(By.cssSelector("input#keyboard")).isDisplayed());
        sleepInSecond(5);
        driver.findElement(By.cssSelector("input#keyboard")).sendKeys("123453126");
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

