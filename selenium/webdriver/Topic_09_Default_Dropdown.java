package webdriver;

import com.beust.ah.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_09_Default_Dropdown {
    WebDriver driver;
    String firstName = "Auto", lastName = "code", email = randomEmail(), pass = "123456";
    String companyName = "Selenium Java";
    String day = "21", month = "June", year = "2024";

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();

    }

    @Test
    public void TC_01_Register() {
        driver.get("https://demo.nopcommerce.com/");
        driver.findElement(By.cssSelector("a.ico-register")).click();

        driver.findElement(By.id("FirstName")).sendKeys(firstName);
        driver.findElement(By.id("LastName")).sendKeys(lastName);

        // Chon option theo index
        // Nếu dùng index sẽ kh biết đc đó là item nào. Khi mà thêm/xóa item mới thì index bị thay đổi
        //day.selectByIndex(10);

        //Nếu value và text giống nhau thì kh sao, nhưng nếu value # text là nhìn kh biết ra item gì
        //Nếu option đó kh có value, hoặc từ có value thành kh có value
        // 4548 = tỉnh Hà Tay
        //day.selectByValue("4548");

        //Khi mà item thêm/sửa/xóa thì item đó cũng đc cập nhật theo
        //day.selectByVisibleText("tỉnh Hà Tay");

        Select dayDropdown = new Select(driver.findElement(By.name("DateOfBirthDay")));
        dayDropdown.selectByVisibleText(day); //Select day
        Assert.assertFalse(dayDropdown.isMultiple()); //Verify dropdown la single hay multible

        //List<WebElement> dayOptions = day.getOptions();
        //Assert.assertEquals(dayOptions.size(), 32); //Verify so luong item
        Assert.assertEquals(dayDropdown.getOptions().size(), 32);//Verify so luong item


        new Select(driver.findElement(By.name("DateOfBirthMonth"))).selectByVisibleText(month);
        new Select(driver.findElement(By.name("DateOfBirthYear"))).selectByVisibleText(year);


        driver.findElement(By.id("Email")).sendKeys(email);
        driver.findElement(By.id("Company")).sendKeys(companyName);
        driver.findElement(By.id("Password")).sendKeys(pass);
        driver.findElement(By.id("ConfirmPassword")).sendKeys(pass);

        driver.findElement(By.id("register-button")).click();
        sleepInSecond(2);

        Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");

    }

    @Test
    public void TC_02_Login() {
        driver.get("https://demo.nopcommerce.com/");
        driver.findElement(By.className("ico-logout")).click();

        //Login
        driver.findElement(By.className("ico-login")).click();
        sleepInSecond(2);
        driver.findElement(By.cssSelector("input#Email")).sendKeys(email);
        driver.findElement(By.cssSelector("input#Password")).sendKeys(pass);
        driver.findElement(By.cssSelector("button.login-button")).click();
        sleepInSecond(2);

        //Verify
        driver.findElement(By.cssSelector("a.ico-account")).click();
        Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), lastName);

        Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthDay"))).getFirstSelectedOption().getText(), day);
        Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthMonth"))).getFirstSelectedOption().getText(), month);
        Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthYear"))).getFirstSelectedOption().getText(), year);

        Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"), email);
        Assert.assertEquals(driver.findElement(By.id("Company")).getAttribute("value"), companyName);
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void sleepInSecond(long timeSleeping) {
        try {
            Thread.sleep(timeSleeping * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String randomEmail() {
        Random rand = new Random();
        return "automation" + rand.nextInt(9999) + "@gmail.net";
    }
}