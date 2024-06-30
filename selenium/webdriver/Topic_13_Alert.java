package webdriver;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class Topic_13_Alert {
    WebDriver driver;
    WebDriverWait exelicitWait;
    String projLocation = System.getProperty("user.dir");

    By resultText = By.cssSelector("p#result");

    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        exelicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Accept_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();


        //Alert alert = driver.switchTo().alert();

        // Cho cho alert present
        // Neu trong thoi gian cho ma xuat hien thi tu switch vao
        // Neu het thoi gian cho chua xuat hien quang NoAlertPresentException -> FAILED
        Alert alert = exelicitWait.until(ExpectedConditions.alertIsPresent()); // vua wait vua switch toi uu cho la chi swich, vi co case alert chua prsent ma switch la FAILED
        Assert.assertEquals(alert.getText(), "I am a JS Alert");

        alert.accept();
        Assert.assertEquals(driver.findElement(resultText).getText(), "You clicked an alert successfully");


        // Cancel alert
        //        void dismiss();
        // Accept alert
        //        void accept();
        // Lay text trong alert ra
        //        String getText();
        // Nhap text vao alert
        //        void sendKeys(String keysToSend);
    }

    @Test
    public void TC_02_Confirm_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
        Alert alert = exelicitWait.until(ExpectedConditions.alertIsPresent()); // vua wait vua switch toi uu cho la chi swich, vi co case alert chua prsent ma switch la FAILED
        Assert.assertEquals(alert.getText(), "I am a JS Confirm");
        alert.dismiss();
        Assert.assertEquals(driver.findElement(resultText).getText(), "You clicked: Cancel");

        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
        alert.accept();
        Assert.assertEquals(driver.findElement(resultText).getText(), "You clicked: Ok");
    }

    @Test
    public void TC_03_Prompt_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
        Alert alert = exelicitWait.until(ExpectedConditions.alertIsPresent()); // vua wait vua switch toi uu cho la chi swich, vi co case alert chua prsent ma switch la FAILED
        Assert.assertEquals(alert.getText(), "I am a JS prompt");

        String text = "Auto";
        alert.sendKeys(text);
        alert.accept();
        Assert.assertEquals(driver.findElement(resultText).getText(), "You entered: " + text);

    }

    @Test
    public void TC_04_Authentication_Alert_Pass_To_Url() {
        String userName = "admin", pass = "admin";

        // Cach1: truyen thang user/pass vaof url
        // Trick ByPass
        //driver.get("http://"+userName+":"+pass+ "@" + "the-internet.herokuapp.com/basic_auth");
        //Assert.assertEquals(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).getText(), "Congratulations! You must have the proper credentials.");

        // Cach 2: Tu page A, thao tac len element, qua page B (can thao tac voi Authen Alert truoc)
        driver.get("http://the-internet.herokuapp.com/");
        String authenLink = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
        //http://the-internet.herokuapp.com/basic_auth

        // Kh xai ham
        //String [] authenLinkAfterSplit = authenLink.split("//");
        //driver.get(authenLinkAfterSplit[0] + "//" +userName+ ":" +pass+ "@" + authenLinkAfterSplit[1]);

        // Viet ham
        driver.get(getURL(authenLink,userName,pass));

        Assert.assertEquals(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).getText(), "Congratulations! You must have the proper credentials.");

    }

    @Test
    public void TC_05_Authentication_Alert_AutoIT() throws IOException {
        // Cach 2: Chay tren Windows (AutoIT)
        // AutoIT nhuoc diem:
        //  - Kh xai dc cho MAC, Linux
        //  - Moi browser se can 1 doan script kha nhau
        // Thuc thi doan code AutoIT de  cho Alert xuat hien
        Runtime.getRuntime().exec(new String[]{projLocation + "\\autoIT\\authen_firefox.exe", "admin", "admin"});
        driver.get("http://the-internet.herokuapp.com/basic_auth");
        sleepInSecond(5);
        Assert.assertEquals(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).getText(), "Congratulations! You must have the proper credentials.");


    }

    @Test
    public void TC_06_Authentication_Alert_Selenium_4x() {

        //Topic 30 (Authentication Alert) https://www.youtube.com/watch?v=78jBc6fLhKg&list=PLj3qi53T2WeRwl6yqsJy0p-5hLsndpQE4&index=13

        // Cach 3:
        // Thu vien Alert cua selenium kh support
        // Chrome Devtool Protocol (CDP) - xai cho Chrome va Edge (Chromium)
        // Get DevTool object
        DevTools devTools = ((HasDevTools) driver).getDevTools();

        // Start new session
        devTools.createSession();

        // Enable the Network domain of devtools
        //devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        // Encode username/ password
        Map<String, Object> headers = new HashMap<String, Object>();
        String basicAuthen = "Basic " + new String(new Base64().encode(String.format("%s:%s", "admin", "admin").getBytes()));
        headers.put("Authorization", basicAuthen);

        // Set to Header
        //devTools.send(Network.setExtraHTTPHeaders(new Headers(headers)));

        driver.get("https://the-internet.herokuapp.com/basic_auth");

        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public String getURL (String authenLink, String userName, String pass) {
        String [] authenLinkAfterSplit = authenLink.split("//");
        return authenLinkAfterSplit[0] + "//" +userName+ ":" +pass+ "@" + authenLinkAfterSplit[1];
    }

    public void sleepInSecond(long timeSleeping) {
        try {
            Thread.sleep(timeSleeping * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}