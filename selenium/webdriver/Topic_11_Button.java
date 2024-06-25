package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_11_Button {
    WebDriver driver;

    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Egov() {
        driver.get("https://egov.danang.gov.vn/reg");

        WebElement registerButton = driver.findElement(By.cssSelector("input.egov-button"));

        // Verify button khi chua click checkbox
        Assert.assertFalse(registerButton.isEnabled());

        // Verify button khi chua click checkbox
        driver.findElement(By.cssSelector("input#chinhSach")).click();
        Assert.assertTrue(registerButton.isEnabled());

        // Lay ma mau background-color cua button
        String registerButtonColorRGB = registerButton.getCssValue("background-color");
        System.out.println(registerButtonColorRGB);

        // Convert tu String sang Color
        Color registerButtonColour = Color.fromString(registerButtonColorRGB);
        // Convert sang Hexa
        String registerButtonColorHexa = registerButtonColour.asHex();
        System.out.println(registerButtonColorHexa);

        registerButtonColorHexa= registerButtonColorHexa.toLowerCase();

        Assert.assertEquals(registerButtonColorHexa, "#ef5a00");

    }

    @Test
    public void TC_02_Fahasa() {
        driver.get("https://www.fahasa.com/customer/account/create");
        // Chuyen qua tab login
        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();

        WebElement loginButton = driver.findElement(By.cssSelector("button.fhs-btn-login"));

        // Verify login button is disabled
        Assert.assertFalse(loginButton.isEnabled());
        Assert.assertEquals(Color.fromString(loginButton.getCssValue("background-color")).asHex().toLowerCase(), "#000000");

        // Input email + pass
        driver.findElement(By.cssSelector("input#login_username")).sendKeys("auto@gmail.com");
        driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456");

        // Verify login button is enabled
        Assert.assertTrue(loginButton.isEnabled());
        //
        Assert.assertEquals(Color.fromString(loginButton.getCssValue("background-color")).asHex().toLowerCase(), "#c92127");
    }

    @Test
    public void TC_03_Goconsensus () {
        driver.get("https://play.goconsensus.com/u5d5156df");
        sleepInSecond(5);
        driver.findElement(By.cssSelector("div#onetrust-close-btn-container button.onetrust-close-btn-handler")).click();
        WebElement buttonSubmit = driver.findElement(By.cssSelector("button[data-testid='lead form continue']"));
        Assert.assertFalse(buttonSubmit.isEnabled());

        driver.findElement(By.id("firstName")).sendKeys("Auto");
        driver.findElement(By.id("lastName")).sendKeys("Auto");
        driver.findElement(By.id("email")).sendKeys("auto@gmai.com");
        driver.findElement(By.id("confirmEmail")).sendKeys("auto@gmai.com");
        driver.findElement(By.id("phoneNumber")).sendKeys("03465116");
        driver.findElement(By.id("organization")).sendKeys("Auto");
        selectItemInDropdown("//label[text()='Country']/following-sibling::div//input","div[data-testid='select menu item']", "AE");
        selectItemInDropdown("//label[text()='State']/following-sibling::div//input","div[data-testid='select menu item']", "AJ");

        Assert.assertTrue(buttonSubmit.isEnabled());
        Assert.assertEquals(Color.fromString(buttonSubmit.getCssValue("background-color")).asHex().toLowerCase(), "#673ab7");

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

    public void selectItemInDropdown (String parentXpath, String childCss, String itemExpected) {
        driver.findElement(By.xpath(parentXpath)).clear();
        driver.findElement(By.xpath(parentXpath)).sendKeys(itemExpected);
        List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childCss))); //div[data-testid='select menu item']
        for (WebElement item : allItems) {
            String textOfItem = item.getText();
            if (textOfItem.equals(itemExpected)) {
                System.out.println(textOfItem);
                item.click();
                sleepInSecond(5);
                break;
            }
        }
    }
}