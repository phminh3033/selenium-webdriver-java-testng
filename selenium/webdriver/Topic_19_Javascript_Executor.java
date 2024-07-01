package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_19_Javascript_Executor {
    WebDriver driver;
    Random random;
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        random = new Random();
        jsExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_JE_TechPanda() {
        executeForBrowser("window.location = 'http://live.techpanda.org/'");
        sleepInSecond(2);
        String webDomain = (String) executeForBrowser("return document.domain;");
        Assert.assertEquals(webDomain, "live.techpanda.org");

        String webURL = (String) executeForBrowser("return document.URL");
        Assert.assertEquals(webURL,"http://live.techpanda.org/");

        hightlightElement("//a[text()='Mobile']");
        clickToElementByJS("//a[text()='Mobile']");
        sleepInSecond(2);
        clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");

        Assert.assertTrue(getInnerText().contains("Samsung Galaxy was added to your shopping cart."));

        hightlightElement("//a[text()='Customer Service']");
        clickToElementByJS("//a[text()='Customer Service']");
        sleepInSecond(2);
        Assert.assertEquals((String)executeForBrowser("return document.title"), "Customer Service");

        scrollToBottomPage();
        sleepInSecond(5);
        sendkeyToElementByJS("//input[@id='newsletter']", randomEmail());
        sleepInSecond(5);
        clickToElementByJS("//span[text()='Subscribe']");

        Assert.assertTrue(isExpectedTextInInnerText("Thank you for your subscription."));

        navigateToUrlByJS("https://www.fahasa.com/");
        //Auto ep kieu qua string
        Assert.assertEquals(executeForBrowser("return document.domain;"), "www.fahasa.com");

    }

    @Test
    public void TC_02_JE_SieuThi() {
        driver.get("https://sieuthimaymocthietbi.com/account/register");

        driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();
        sleepInSecond(3);
        Assert.assertEquals(getElementValidationMessage("//input[@id='lastName']"), "Please fill out this field.");
        driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys("Auto");

        driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();
        sleepInSecond(3);
        Assert.assertEquals(getElementValidationMessage("//input[@id='firstName']"), "Please fill out this field.");
        driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys("mation");

        driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();
        sleepInSecond(3);
        Assert.assertEquals(getElementValidationMessage("//input[@id='email']"), "Please fill out this field.");
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("aa@asd@afasfds");
        sleepInSecond(3);
        Assert.assertEquals(getElementValidationMessage("//input[@id='email']"), "Please enter an email address.");
    }

    @Test
    public void TC_03_JE_TechPanda_Create() {
        driver.get("http://live.techpanda.org/");
        clickToElementByJS("//div[@id='header-account']//a[text()='My Account']");
        sleepInSecond(2);
        clickToElementByJS("//a[@class='button']");
        sleepInSecond(2);

        sendkeyToElementByJS("//input[@id='firstname']", "Auto");
        sendkeyToElementByJS("//input[@id='lastname']", "FC");
        sendkeyToElementByJS("//input[@id='email_address']", randomEmail());
        sendkeyToElementByJS("//input[@id='password']", "123465");
        sendkeyToElementByJS("//input[@id='confirmation']", "123465");

        clickToElementByJS("//div[@class='buttons-set']//button[@class='button']");
        sleepInSecond(2);

        Assert.assertTrue(isExpectedTextInInnerText("Thank you for registering with Main Website Store."));

        clickToElementByJS("//a[text()='Log Out']");
        sleepInSecond(2);
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

    //random mail
    public String randomEmail (){
        String email = "auto" + random.nextInt(999) + "@gmail.net";
        return email;
    }

    //jsExecutor
    public Object executeForBrowser(String javaScript) {
        return jsExecutor.executeScript(javaScript);
    }

    public String getInnerText() {
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    public boolean isExpectedTextInInnerText(String textExpected) {
        String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage() {
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void navigateToUrlByJS(String url) {
        jsExecutor.executeScript("window.location = '" + url + "'");
        sleepInSecond(3);
    }

    public void hightlightElement(String locator) {
        WebElement element = getElement(locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(2);
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(String locator) {
        jsExecutor.executeScript("arguments[0].click();", getElement(locator));
        sleepInSecond(3);
    }

    public void scrollToElementOnTop(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
    }

    public void scrollToElementOnDown(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
    }

    public void setAttributeInDOM(String locator, String attributeName, String attributeValue) {
        jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getElement(locator));
    }

    public void removeAttributeInDOM(String locator, String attributeRemove) {
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
    }

    public void sendkeyToElementByJS(String locator, String value) {
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
    }

    public String getAttributeInDOM(String locator, String attributeName) {
        return (String) jsExecutor.executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(locator));
    }

    public String getElementValidationMessage(String locator) {
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
    }

    public boolean isImageLoaded(String locator) {
        boolean status = (boolean) jsExecutor.executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
        return status;
    }

    public WebElement getElement(String locator) {
        return driver.findElement(By.xpath(locator));
    }
}