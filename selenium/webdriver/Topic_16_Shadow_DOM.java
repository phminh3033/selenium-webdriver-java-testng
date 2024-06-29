package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_16_Shadow_DOM {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Shadow_DOM() {
        driver.get("https://automationfc.github.io/shadow-dom");
        WebElement shadowHost = driver.findElement(By.cssSelector("div#shadow_host"));
        SearchContext shadowRoot1 = shadowHost.getShadowRoot();
        String someText = shadowRoot1.findElement(By.cssSelector("span#shadow_content>span")).getText();
        System.out.println(someText);
        Assert.assertFalse(shadowRoot1.findElement(By.cssSelector("input[type='checkbox']")).isSelected());

        List<WebElement> allInput = shadowRoot1.findElements(By.cssSelector("input"));
        System.out.println(allInput.size());

        WebElement nestedShadowHost = shadowRoot1.findElement(By.cssSelector("div#nested_shadow_host"));
        SearchContext nestedShadowRoot = nestedShadowHost.getShadowRoot();
        Assert.assertEquals(nestedShadowRoot.findElement(By.cssSelector("div#nested_shadow_content>div")).getText(),"nested text");

    }

    @Test
    public void TC_02_Shadow_DOM_Shopee() {
        driver.get("https://shopee.vn/");
        WebElement shadowHost = driver.findElement(By.cssSelector("shopee-banner-popup-stateful"));
        SearchContext shadowRoot = shadowHost.getShadowRoot();
        if (shadowRoot.findElements(By.cssSelector("div.home-popup__content")).size() > 0 &&
                shadowRoot.findElements(By.cssSelector("div.home-popup__content")).get(0).isDisplayed()){

            shadowRoot.findElement(By.cssSelector("div.shopee-popup__close-btn")).click();
            sleepInSecond(2);
        }
        driver.findElement(By.cssSelector("input.shopee-searchbar-input__input")).sendKeys("Iphone");
        driver.findElement(By.cssSelector("button.shopee-searchbar__search-button")).click();

    }

    @Test
    public void TC_03_Form() {
        Assert.assertTrue(driver.findElement(By.xpath("//form[@data-testid='royal_login_form']")).isDisplayed());
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

