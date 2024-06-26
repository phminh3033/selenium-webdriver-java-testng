package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.*;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.List;

public class Topic_14_User_Interactions {
    WebDriver driver;
    Actions actions;
    String osName = System.getProperty("os.name");
    Keys keys;
    JavascriptExecutor jsExecutor;
    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        actions = new Actions(driver);
        jsExecutor = (JavascriptExecutor) driver;
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
        Assert.assertEquals(driver.findElement(By.xpath("//strong[text()='Finance & Accounting']")).getText(), "FINANCE & ACCOUNTING");
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
        By quitContextMenu = By.xpath("//li[contains(@class,'context-menu-icon-quit')]");
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");

        actions.contextClick(driver.findElement(By.cssSelector("span.context-menu-one"))).perform();
        sleepInSecond(2);

        Assert.assertTrue(driver.findElement(quitContextMenu).isDisplayed());
        actions.moveToElement(driver.findElement(quitContextMenu)).perform();
        sleepInSecond(2);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-visible.context-menu-hover")).isDisplayed());
        //Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit context-menu-visible context-menu-hover')]")).isDisplayed());
        sleepInSecond(2);
        actions.click(driver.findElement(quitContextMenu)).perform();
        sleepInSecond(2);
        driver.switchTo().alert().accept();
        sleepInSecond(2);
        Assert.assertFalse(driver.findElement(quitContextMenu).isDisplayed());


    }

    //Actions III

    @Test
    public void TC_01_Drag_And_Drop_HTML4() {
        driver.get("https://automationfc.github.io/kendo-drag-drop/");

        WebElement smallCircle = driver.findElement(By.cssSelector("div#draggable"));
        WebElement bigCircle = driver.findElement(By.cssSelector("div#droptarget"));
        actions.dragAndDrop(smallCircle,bigCircle).perform();
        Assert.assertEquals(bigCircle.getText(), "You did great!");
        Assert.assertEquals(Color.fromString(bigCircle.getCssValue("background-color")).asHex().toLowerCase(), "#03a9f4");

    }

    @Test
    public void TC_02_Drag_And_Drop_HTML5_JQuery() throws IOException {
        driver.get("https://automationfc.github.io/drag-drop-html5/");

        //Site kh support JQuery
        String jqueryLibraries = getContentFile(projectPath + "\\dragDrop\\jQueryLib.js");
        jsExecutor.executeScript(jqueryLibraries);

        //Run doan drag drop
        String jqueryDragDropContent = getContentFile(projectPath + "\\dragDrop\\dragAndDrop.js");

        // Drag A to B
        jsExecutor.executeScript(jqueryDragDropContent);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(), "B");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(), "A");
        sleepInSecond(3);
    }

    @Test
    public void TC_03_Drag_And_Drop_HTML5_Java_Robot() throws AWTException {
        driver.get("https://automationfc.github.io/drag-drop-html5/");
        dragAndDropHTML5ByXpath("div#column-a","div#column-b");
        sleepInSecond(3);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(), "B");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(), "A");
        sleepInSecond(3);
    }

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

    public void dragAndDropHTML5ByXpath(String sourceLocator, String targetLocator) throws AWTException {

        WebElement source = driver.findElement(By.cssSelector(sourceLocator));
        WebElement target = driver.findElement(By.cssSelector(targetLocator));

        // Setup robot
        Robot robot = new Robot();
        robot.setAutoDelay(500);

        // Get size of elements
        Dimension sourceSize = source.getSize();
        Dimension targetSize = target.getSize();

        // Get center distance
        int xCentreSource = sourceSize.width / 2;
        int yCentreSource = sourceSize.height / 2;
        int xCentreTarget = targetSize.width / 2;
        int yCentreTarget = targetSize.height / 2;

        Point sourceLocation = source.getLocation();
        Point targetLocation = target.getLocation();

        // Make Mouse coordinate center of element
        sourceLocation.x += 20 + xCentreSource;
        sourceLocation.y += 110 + yCentreSource;
        targetLocation.x += 20 + xCentreTarget;
        targetLocation.y += 110 + yCentreTarget;

        // Move mouse to drag from location
        robot.mouseMove(sourceLocation.x, sourceLocation.y);

        // Click and drag
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

        // Move to final position
        robot.mouseMove(targetLocation.x, targetLocation.y);

        // Drop
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }
}

