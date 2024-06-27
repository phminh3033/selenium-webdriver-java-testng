package webdriver;

import com.beust.ah.A;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_14_User_Interactions {
    WebDriver driver;
    Actions actions;
    String osName = System.getProperty("os.name");
    Keys keys;
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        actions = new Actions(driver);

        keys = osName.startsWith("Windows") ? Keys.CONTROL : Keys.COMMAND;
    }

    @Test
    public void TC_01_Hover() {
        driver.get("https://automationfc.github.io/jquery-tooltip/");
        WebElement textBoxHover = driver.findElement(By.cssSelector("input#age"));
        actions.moveToElement(textBoxHover).perform();
        sleepInSecond(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");

    }

    @Test
    public void TC_02_MynTra() {
        driver.get("https://www.myntra.com/");
        actions.moveToElement(driver.findElement(By.xpath("//a[@class='desktop-main' and text()='Kids']"))).perform();
        sleepInSecond(2);
        driver.findElement(By.xpath("//a[@class='desktop-categoryName' and text()='Home & Bath']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("span.breadcrumbs-crumb")).getText(), "Kids Home Bath");
    }

    @Test
    public void TC_03_Fahasa() {
        driver.get("https://www.fahasa.com/");
        sleepInSecond(9);
        actions.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
        actions.moveToElement(driver.findElement(By.xpath("//span[text()='FOREIGN BOOKS']"))).perform();
        driver.findElement(By.xpath("//div[@class='fhs_column_stretch']//a[text()='Finance & Accounting']")).click();
        //Assert.assertEquals(driver.findElement(By.xpath("//strong[text()='Finance & Accounting']")).getText(), "Finance & Accounting");
    }

    //Actions II

    @Test
    public void TC_01_Click_And_Hold_Block() {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> allBlock = driver.findElements(By.cssSelector("ol#selectable>li"));
        Assert.assertEquals(allBlock.size(), 20);
        actions.clickAndHold(allBlock.get(0)).moveToElement(allBlock.get(3)).release().perform();
        List<WebElement> selectedBlock = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
        Assert.assertEquals(selectedBlock.size(), 4);
    }

    @Test
    public void TC_02_Click_And_Hold_Random_Block() {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> allBlock = driver.findElements(By.cssSelector("ol#selectable>li"));
        Assert.assertEquals(allBlock.size(), 20);

        actions.keyDown(keys).perform();
        actions.click(allBlock.get(0))
                .click(allBlock.get(6))
                .click(allBlock.get(11))
                .pause(Duration.ofSeconds(3))
                .perform();

        actions.keyUp(keys);
        List<WebElement> selectedBlock = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
        Assert.assertEquals(selectedBlock.size(), 3);
    }

    @Test
    public void TC_03_Double_Click() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        WebElement doubleClickButton = driver.findElement(By.xpath("//button[text()='Double click me']"));

        if (driver.toString().contains("firefox")) {
            //scrollIntoView(true): kéo mép trên của element lên phía trên của viewport
            //scrollIntoView(false): kéo mép dưới của element xuống phía dưới cùng của viewport
            jsExecutor.executeScript("arguments[0].scrollIntoView(false)", doubleClickButton);
            sleepInSecond(2);
        } else {
            actions.scrollToElement(doubleClickButton).perform();
            sleepInSecond(2);
        }

        actions.doubleClick(doubleClickButton).perform();
        sleepInSecond(2);

        Assert.assertEquals(driver.findElement(By.id("demo")).getText(), "Hello Automation Guys!");
    }



    @Test
    public void TC_04_Right_Click() {
        By quitContextMenu = By.cssSelector("span.context-menu-one");
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");

        actions.contextClick(driver.findElement(By.cssSelector("span.context-menu-one"))).perform();
        sleepInSecond(2);

        Assert.assertTrue(driver.findElement(quitContextMenu).isDisplayed());
        actions.moveToElement(driver.findElement(quitContextMenu)).perform();
        sleepInSecond(2);

        Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit context-menu-visible context-menu-hover')]")).isDisplayed());
        sleepInSecond(2);
        actions.click(driver.findElement(quitContextMenu)).perform();
        sleepInSecond(2);
        driver.switchTo().alert().accept();
        sleepInSecond(2);
        Assert.assertFalse(driver.findElement(quitContextMenu).isDisplayed());


    }

    //Actions III



    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void sleepInSecond(long timeSleeping) {
        try {
            Thread.sleep(timeSleeping * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getContentFile(String filePath) throws IOException {
        Charset cs = Charset.forName("UTF-8");
        FileInputStream stream = new FileInputStream(filePath);
        try {
            Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[8192];
            int read;
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                builder.append(buffer, 0, read);
            }
            return builder.toString();
        } finally {
            stream.close();
        }
    }
}

