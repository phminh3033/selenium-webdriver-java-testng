package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.function.Function;

public class Topic_29_Wait_9_Fluent {
    WebDriver driver;
    FluentWait<WebDriver> fluentDriver;
    FluentWait<WebElement> fluentElement;
    FluentWait<String> fluentString;

    private long timeOutISeconds = 30;
    private long pollingInMillis = 200;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        fluentDriver = new FluentWait<WebDriver>(driver);

        // Timeout - Default Polling Time: 0.5s
        //explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Timeout - Custom Polling Time: X s
        //explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(300));
    }

    @Test
    public void TC_01_Knowledge() {

        // KHOI TAO
        fluentDriver = new FluentWait<WebDriver>(driver);
        fluentElement = new FluentWait<WebElement>(driver.findElement(By.cssSelector("")));
        fluentString = new FluentWait<String>("driver");

        // SETTING
        // Tong time
        fluentDriver.withTimeout(Duration.ofSeconds(10));

        // Polling time
        fluentDriver.pollingEvery(Duration.ofMillis(300));

        // Ignore NoSuchElement exceptions
        fluentDriver.ignoring(NoSuchElementException.class);

        //
        fluentDriver.ignoring(TimeoutException.class);

        // CONDITION
        fluentDriver.until(new Function<WebDriver, String>() {
            @Override
            public String apply(WebDriver webDriver) {
                return webDriver.findElement(By.cssSelector("")).getText();
            }
        });

        // Viet theo cach Chaining
        fluentDriver.withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(400))
                .ignoring(NoSuchElementException.class, TimeoutException.class)
                .until(new Function<WebDriver, Boolean>() {
                    @Override
                    public Boolean apply(WebDriver webDriver) {
                        return webDriver.findElement(By.cssSelector("")).isDisplayed();
                    }
                });
    }

    @Test
    public void TC_01_Start() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
//        driver.findElement(By.cssSelector("div#start>button")).click();
//
//        fluentDriver.withTimeout(Duration.ofSeconds(10))
//                    .pollingEvery(Duration.ofMillis(1000))
//                    .ignoring(NoSuchElementException.class);
//
//        fluentDriver.until(new Function<WebDriver, Boolean>() {
//            @Override
//            public Boolean apply(WebDriver webDriver) {
//                System.out.println("haha");
//                return webDriver.findElement(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")).isDisplayed();
//            }
//        });
//
//        String helloText = fluentDriver.until(new Function<WebDriver, String>() {
//            @Override
//            public String apply(WebDriver webDriver) {
//                return webDriver.findElement(By.xpath("//div[@id='finish']/h4")).getText();
//            }
//        });


        // Viet theo cach Wrapper, custom lai ham
        waitAndFindElement(By.cssSelector("div#start>button")).click();
        waitAndFindElement(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")).isDisplayed();
        String helloText2 = waitAndFindElement(By.xpath("//div[@id='finish']/h4")).getText();



        Assert.assertEquals(helloText2, "Hello World!");
    }

    @Test
    public void TC_02_CountDown() {
        driver.get("https://automationfc.github.io/fluent-wait/");
        WebElement countdownText = driver.findElement(By.cssSelector("div#javascript_countdown_time"));

        fluentElement = new FluentWait<WebElement>(countdownText);

        fluentElement.withTimeout(Duration.ofSeconds(15))
                    .pollingEvery(Duration.ofMillis(1000))
                    .ignoring(NoSuchElementException.class);

        fluentElement.until(new Function<WebElement, Boolean>() {
            @Override
            public Boolean apply(WebElement webElement) {
                String text = webElement.getText();
                System.out.println(text);
                return text.endsWith("00");
            }
        });

    }

    public WebElement waitAndFindElement (By locator) {
        FluentWait<WebDriver> fluentDriver = new FluentWait<WebDriver>(driver);
        fluentDriver.withTimeout(Duration.ofSeconds(timeOutISeconds))
                .pollingEvery(Duration.ofMillis(pollingInMillis))
                .ignoring(NoSuchElementException.class);

        return fluentDriver.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return webDriver.findElement(locator);
            }
        });
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}