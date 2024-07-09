package testNG;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_12_Parameter_Method {
    WebDriver driver;

    @BeforeMethod
    public void beforeMethod () throws InterruptedException {
        driver = new FirefoxDriver();
        driver.get("https://demo.nopcommerce.com/");
        Thread.sleep(1000);
    }

    @Test
    public void TC_01() {
        System.out.println("TC 1");
    }

    @Test
    public void TC_02() {
        System.out.println("TC 2");
    }

    @Test
    public void TC_03() {
        System.out.println("TC 3");
    }

    @Test
    public void TC_04() {
        System.out.println("TC 4");
    }

    @AfterMethod
    public void afterMethod () {
        driver.quit();
    }
}
