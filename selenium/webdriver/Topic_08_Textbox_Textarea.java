package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_08_Textbox_Textarea {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void Login_01_Empty() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.cssSelector("div[class='footer'] a[title='My Account'")).click();
        sleepInSecond(3);
        driver.findElement(By.cssSelector("button#send2")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-email")).getText(), "This is a required field.");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-pass")).getText(), "This is a required field.");
    }

    @Test
    public void Login_02_Invalid_Email() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.cssSelector("div[class='footer'] a[title='My Account'")).click();
        sleepInSecond(3);

        driver.findElement(By.cssSelector("input#email")).sendKeys("123@132.34");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("123456");

        driver.findElement(By.cssSelector("button#send2")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
    }

    @Test
    public void Login_03_Invalid_Pass() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.cssSelector("div[class='footer'] a[title='My Account'")).click();
        sleepInSecond(3);

        driver.findElement(By.cssSelector("input#email")).sendKeys("abc@gmail.co");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("123");

        driver.findElement(By.cssSelector("button#send2")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
    }

    @Test
    public void Login_04_Incorrect_Email_Pass() {


        //1. Dang ly bang tay (Manual) 1 tai khoan email r dung n de login  (Man-Automation)
        // Risk:
        // + Khi system bi reset data (delete DB) la phai dang ky lai
        // + Qua moi truong khac (Dev/Testing/Staging/Prod/...)

        //2. Dung tinh nang Register truoc (Auto) - email co dinh, kh doi
        // Register thi auto
        // Nhung Email dung de dang ky thi fix cung (Hardcode) - Doi browser, doi env la ngu nguoi

        //3. Dung tinh nang Register truoc - email random
        // Chay luon dung cho all cases
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.cssSelector("div[class='footer'] a[title='My Account'")).click();
        sleepInSecond(3);

        driver.findElement(By.cssSelector("input#email")).sendKeys("automationfc@gmail.net");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("fghdfgdfgdfgfdg");

        driver.findElement(By.cssSelector("button#send2")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("li.error-msg span")).getText(), "Invalid login or password.");

        driver.findElement(By.cssSelector("input#email")).clear();
        driver.findElement(By.cssSelector("input#pass")).clear();
        driver.findElement(By.cssSelector("input#email")).sendKeys("automationfc@gmail.org");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("123456");

        driver.findElement(By.cssSelector("button#send2")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("li.error-msg span")).getText(), "Invalid login or password.");


    }

    @Test
    public void Login_05_Success() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.cssSelector("div[class='footer'] a[title='My Account'")).click();
        sleepInSecond(2);

        //Register
        driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
        sleepInSecond(2);

        String firstName = "Automation", lastName = "FFCC", email = randomEmail(), pass = "12345678";
        String fullName = firstName + " " + lastName;
        driver.findElement(By.cssSelector("input#firstname")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input#lastname")).sendKeys(lastName);
        driver.findElement(By.cssSelector("input#email_address")).sendKeys(email);
        driver.findElement(By.cssSelector("input#password")).sendKeys(pass);
        driver.findElement(By.cssSelector("input#confirmation")).sendKeys(pass);

        driver.findElement(By.xpath("//button[@title='Register']")).click();
        sleepInSecond(2);

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "Thank you for registering with Main Website Store.");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.welcome-msg strong")).getText(), "Hello, " + fullName + "!");

        String contactInfo = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
        Assert.assertTrue(contactInfo.contains(fullName));
        Assert.assertTrue(contactInfo.contains(email));

        //Logout
        driver.findElement(By.cssSelector("a.skip-account")).click();
        driver.findElement(By.cssSelector("a[title='Log Out']")).click();
        sleepInSecond(5);

        driver.findElement(By.cssSelector("div[class='footer'] a[title='My Account'")).click();
        sleepInSecond(2);

        //Login
        driver.findElement(By.cssSelector("input#email")).sendKeys(email);
        driver.findElement(By.cssSelector("input#pass")).sendKeys(pass);
        driver.findElement(By.cssSelector("button#send2")).click();
        sleepInSecond(2);

        Assert.assertEquals(driver.findElement(By.cssSelector("div.welcome-msg strong")).getText(), "Hello, " + fullName + "!");

        contactInfo = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
        Assert.assertTrue(contactInfo.contains(fullName));
        Assert.assertTrue(contactInfo.contains(email));

        //Verify account
        driver.findElement(By.xpath("//a[text()='Account Information']")).click();
        sleepInSecond(2);

        Assert.assertEquals(driver.findElement(By.cssSelector("input#firstname")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#lastname")).getAttribute("value"), lastName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#email")).getAttribute("value"), email);

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

    public String randomEmail () {
        Random rand = new Random();
        return "automation" + rand.nextInt(9999) + "@gmail.net";
    }
}