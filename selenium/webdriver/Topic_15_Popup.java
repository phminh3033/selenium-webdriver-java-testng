package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_15_Popup {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        //driver.manage().window().maximize();

    }

    @Test
    public void TC_01_Fixed_Popup_INDOM_1() {
        driver.get("https://ngoaingu24h.vn/");
        driver.findElement(By.cssSelector("button.login_")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("div#modal-login-v1>div.modal-dialog")).isDisplayed());

        driver.findElement(By.cssSelector("input#account-input")).sendKeys("auto");
        driver.findElement(By.cssSelector("input#password-input")).sendKeys("auto");
        driver.findElement(By.cssSelector("button.btn-login-v1")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#modal-login-v1>div.modal-dialog div.error-login-panel")).getText(), "Tài khoản không tồn tại!");

        driver.findElement(By.cssSelector("div#modal-login-v1>div.modal-dialog button.close")).click();
        // isDisplayed co the ktr popup dang co dinh INDOM
        Assert.assertFalse(driver.findElement(By.cssSelector("div#modal-login-v1>div.modal-dialog")).isDisplayed());


    }

    @Test
    public void TC_02_Fixed_Popup_INDOM_2() {
        driver.get("https://skills.kynaenglish.vn/dang-nhap");
        Assert.assertTrue(driver.findElement(By.cssSelector("div#k-popup-account-login div.modal-content")).isDisplayed());

        driver.findElement(By.cssSelector("input#user-login")).sendKeys("auto");
        driver.findElement(By.cssSelector("input#user-password")).sendKeys("auto");
        driver.findElement(By.cssSelector("button#btn-submit-login")).click();
        sleepInSecond(2);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(), "Sai tên đăng nhập hoặc mật khẩu");

    }

    @Test
    public void TC_03_Fixed_Popup_NOT_INDOM_1() {
        driver.get("https://tiki.vn/");
        driver.findElement(By.cssSelector("div[data-view-id='header_header_account_container']")).click();
        driver.findElement(By.cssSelector("p.login-with-email")).click();
        sleepInSecond(2);
        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        sleepInSecond(10);

        Assert.assertEquals(driver.findElement(By.xpath("//input[@type='email']/parent::div/following-sibling::span[1]")).getText(), "Email không được để trống");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@type='password']/parent::div/following-sibling::span")).getText(), "Mật khẩu không được để trống");

        driver.findElement(By.cssSelector("img.close-img")).click();

        // Khi cai popup close thi HTML kh con trong DOM nua

        //findElement should not be used to look for non-present elements, use findElements(By) and assert zero length response instead
        //Assert.assertFalse(driver.findElement(By.cssSelector("div.ReactModal__Content")).isDisplayed());

        //findElements(By) and assert zero length response instead
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Assert.assertEquals(driver.findElements(By.cssSelector("div.ReactModal__Content")).size(), 0);
    }

    @Test
    public void TC_04_Fixed_Popup_NOT_INDOM_2() {
        driver.get("https://www.facebook.com/");
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/parent::div")).isDisplayed());
        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Assert.assertEquals(driver.findElements(By.xpath("//div[text()='Sign Up']/parent::div/parent::div")).size(), 0);

    }

    // Part II

    @Test
    public void TC_01_Random_Popup_Not_INDOM_1() {
        driver.get("https://www.javacodegeeks.com/");
        sleepInSecond(10);
        By newsLetterPopup = By.cssSelector("div.lepopup-popup-container>div:not([style^='display:none'])");

        //Mo ta:
        // Luc dau mo app, popup chua render (kh co trong DOM)
        // popup render (Co trong DOM):
        // 1. Kh hien thi
        // 2. Hien thi
        // Neu HIEN THI, close -> van con trong DOM

        // Case 1: Neu hien thi -> Close
        // > 0 la duoc render ra nhung chua chac co hien thi hay kh???
        if (driver.findElements(newsLetterPopup).size() > 0 && driver.findElements(newsLetterPopup).get(0).isDisplayed()){
            System.out.println("Popup hien thi");
            driver.findElement(By.cssSelector("div.lepopup-popup-container>div:not([style^='display:none']) div.lepopup-element-html-content>a:not([class='lepopup-inherited lepopup-inherited'])")).click();
            sleepInSecond(2);
        } else {
            // Case 2: Kh hien thi popup
            System.out.println("Popup KHONG hien thi");
        }

        driver.findElement(By.cssSelector("input#search-input")).sendKeys("Agile Testing Explained");
        driver.findElement(By.cssSelector("button#search-submit")).click();
        sleepInSecond(3);
        Assert.assertEquals(driver.findElement(By.xpath("//a[text()='Agile Testing Explained']")).getText(), "Agile Testing Explained");


    }

    @Test
    public void TC_02_Random_Popup_INDOM_1() {
        driver.get("https://vnk.edu.vn/");
        sleepInSecond(10);

//        if (driver.findElement(By.cssSelector("div.pum-container")).isDisplayed()){
//            System.out.println("Popup hien thi");
//            driver.findElement(By.cssSelector("button.pum-close")).click();
//            sleepInSecond(2);
//        } else {
//            // Case 2: Kh hien thi popup
//            System.out.println("Popup KHONG hien thi");
//        }

        findElementVNK(By.xpath("//button[text()='Danh sách khóa học']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div.title-content>h1")).getText(), "Lịch khai giảng tháng 06");
    }

    // Case dang thao tac gi do, popup hien len -> close popup truoc khi lam tiep
    public WebElement findElementVNK (By locator) {
        if (driver.findElement(By.cssSelector("div.pum-container")).isDisplayed()){
            System.out.println("Popup hien thi");
            driver.findElement(By.cssSelector("button.pum-close")).click();
            sleepInSecond(2);
        }
        return driver.findElement(locator);
    }

    @Test
    public void TC_03_Random_Popup__NOT_INDOM() {
        driver.get("https://dehieu.vn/");
        sleepInSecond(5);
        By popup = By.cssSelector("div.modal-content");
        if (driver.findElements(popup).size() > 0 && driver.findElements(popup).get(0).isDisplayed()){
            System.out.println("Popup hien thi");
            int heighBrower = driver.manage().window().getSize().getHeight();
            if (heighBrower < 1920) {
                ((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("button.close")));
            } else {
                driver.findElement(By.cssSelector("button.close")).click();
            }
            sleepInSecond(2);
        }
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

