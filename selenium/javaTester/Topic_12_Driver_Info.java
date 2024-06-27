package javaTester;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Topic_12_Driver_Info {
    WebDriver driver;

    @Test
    public void testDriverInformation() {
        driver = new FirefoxDriver();
        System.out.println(driver.toString());

        driver = new ChromeDriver();
        System.out.println(driver.toString());

        if (driver.toString().contains("firefox")) {
            //scroll
        }

        driver.quit();
    }
}
