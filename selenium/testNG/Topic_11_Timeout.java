package testNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;


public class Topic_11_Timeout {

    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test(timeOut = 10000)
    public void Register() {
        driver.get("http://live.techpanda.org/");

        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
//        sleepInSecond(2);

        //Dky 1 tài khoản
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
//        sleepInSecond(2);

        String firstName = "Hom Nay",
                middleName = "An",
                lastName = "Gi",
                emailAddres = getEmailAddress(),
                password = "123456789";
        String fullName = firstName + " " + middleName + " " + lastName;

        driver.findElement(By.id("firstname")).sendKeys(firstName);
        driver.findElement(By.id("middlename")).sendKeys(middleName);
        driver.findElement(By.id("lastname")).sendKeys(lastName);
        driver.findElement(By.id("email_address")).sendKeys(emailAddres);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("confirmation")).sendKeys(password);

        driver.findElement(By.xpath("//button[@title='Register']")).click();
//        sleepInSecond(2);

        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']")).getText(), "Thank you for registering with Main Website Store.");
        Assert.assertEquals(driver.findElement(By.xpath("//p[@class='hello']")).getText(), "Hello, " + fullName + "!");

        String contactInfo = driver.findElement(By.xpath("//h3[text()='Contact Information']//parent::div//following-sibling::div[@class='box-content']")).getText();
        Assert.assertTrue(contactInfo.contains(fullName));
        Assert.assertTrue(contactInfo.contains(emailAddres));
        //h3[text()='Contact Information']//parent::div//following-sibling::div/p[@class='box-content']

        driver.findElement(By.xpath("//a[@class='skip-link skip-account']")).click();
//        sleepInSecond(2);
        driver.findElement(By.xpath("//a[@title='Log Out']")).click();
//        sleepInSecond(2);

        //Login
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
//        sleepInSecond(2);

        driver.findElement(By.id("email")).sendKeys(emailAddres);
        driver.findElement(By.id("pass")).sendKeys(password);

        driver.findElement(By.xpath("//button[@title='Login']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//p[@class='hello']")).getText(), "Hello, " + fullName + "!");

        contactInfo = driver.findElement(By.xpath("//h3[text()='Contact Information']//parent::div//following-sibling::div[@class='box-content']")).getText();
        Assert.assertTrue(contactInfo.contains(fullName));
        Assert.assertTrue(contactInfo.contains(emailAddres));

        //Verify ACcount
        driver.findElement(By.xpath("//a[text()='Account Information']")).click();
//        sleepInSecond(2);

        Assert.assertEquals(driver.findElement(By.id("firstname")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.id("middlename")).getAttribute("value"), middleName);
        Assert.assertEquals(driver.findElement(By.id("lastname")).getAttribute("value"), lastName);
        Assert.assertEquals(driver.findElement(By.id("email")).getAttribute("value"), emailAddres);

        System.out.println("Email Adress/ Password = " + emailAddres + " - " + password);

        //Log out
        driver.findElement(By.xpath("//a[@class='skip-link skip-account']")).click();
//        sleepInSecond(2);
        driver.findElement(By.xpath("//a[@title='Log Out']")).click();
//        sleepInSecond(2);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }


    public String getEmailAddress() {
        Random rand = new Random();
        return "logintest" + rand.nextInt(999) + "@gmail.net";
    }
}
