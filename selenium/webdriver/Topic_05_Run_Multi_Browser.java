package webdriver;

import org.bouncycastle.asn1.cryptopro.GOST3410NamedParameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_05_Run_Multi_Browser {
    WebDriver driver;

    String osName = System.getProperty("os.name");

    @Test
    public void TC_01_Firefox() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://www.youtube.com");
        driver.quit();
    }

    @Test
    public void TC_02_Chrome () {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://www.youtube.com");
        driver.quit();
    }

    @Test
    public void TC_03_Edge() {
        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://www.youtube.com");
        driver.quit();
    }

    public void TC_04_Safari() {
        if (osName.contains("Mac")) {
            driver = new SafariDriver();
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.get("https://www.youtube.com");
            driver.quit();
        }
    }
}