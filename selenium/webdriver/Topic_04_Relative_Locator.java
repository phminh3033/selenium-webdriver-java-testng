package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_04_Relative_Locator {
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

    //Relative it su dung
    @Test
    public void TC_01_Relative() {

        //login button
        By loginButtonBy = By.cssSelector("button.btn-signin");
        WebElement loginButtonElement = driver.findElement(By.cssSelector("button.btn-signin"));

        //input email
        By inputEmailBy = By.id("email");
        WebElement inputEmailElement = driver.findElement(By.id("email"));

        WebElement forgotTextElement = driver.findElement(
                RelativeLocator.with(By.tagName("a"))
                        .above(loginButtonBy)
                        .below(inputEmailBy)
        );


        //RelativeLocator.with(By.tagName("input")).above(loginButtonElement);
        System.out.println(forgotTextElement.getText());
    }

    @Test
    public void TC_02_Relative_leftRight(){
        //link try now
        By linkTryItBy = By.xpath("//a[@href='/users/general-register']");

        //button gg
        WebElement buttonGGgElement = driver.findElement(By.cssSelector("button.btn-social"));

        //get text
        WebElement newTextElement = driver.findElement(RelativeLocator.with(By.tagName("button")).toLeftOf(linkTryItBy));
        WebElement ggButtonElement = driver.findElement(RelativeLocator.with(By.tagName("button")).toRightOf(buttonGGgElement));

        System.out.println(newTextElement.getText());
        System.out.println(ggButtonElement.getText());
    }

    @Test
    public void TC_03_Relative_near(){

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}