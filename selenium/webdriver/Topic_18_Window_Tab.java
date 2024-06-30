package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class Topic_18_Window_Tab {
    WebDriver driver;
    WebDriver cellPhoneDriver;
    WebDriver hoangHaDriver;
    JavascriptExecutor js;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        cellPhoneDriver = new FirefoxDriver();
        cellPhoneDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        hoangHaDriver = new ChromeDriver();
        hoangHaDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.manage().window().maximize();
        js = (JavascriptExecutor) driver;
    }

    @Test
    public void TC_01_Basic_Form_Tab() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        sleepInSecond(3);

        switchToTabByTitle("Google");
        driver.findElement(By.cssSelector("textarea[name='q']")).sendKeys("Selenium");
        sleepInSecond(3);

        switchToTabByTitle("Selenium WebDriver");
        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
        sleepInSecond(3);

        switchToTabByTitle("Facebook – log in or sign up");
        driver.findElement(By.cssSelector("input#email")).sendKeys("aloooooooooooooooooooooooooo");
        sleepInSecond(3);

        switchToTabByTitle("Selenium WebDriver");
        sleepInSecond(5);

    }

    @Test
    public void TC_02_TechPanda_Window() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//a[text()='Mobile']")).click();

        driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
        sleepInSecond(2);
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The product Samsung Galaxy has been added to comparison list.");

        driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
        sleepInSecond(2);
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The product Sony Xperia has been added to comparison list.");

        driver.findElement(By.xpath("//a[text()='IPhone']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
        sleepInSecond(2);
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The product IPhone has been added to comparison list.");

        driver.findElement(By.cssSelector("button[title='Compare']")).click();
        sleepInSecond(2);

        switchToTabByTitle("Products Comparison List - Magento Commerce");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.title-buttons>h1")).getText(), "COMPARE PRODUCTS");

        switchToTabByTitle("Mobile");
        sleepInSecond(2);

        driver.findElement(By.cssSelector("input#search")).sendKeys("Aloooooooooooooo");

    }

    @Test
    public void TC_03_CellphoneS_Tab() {
        driver.get("https://cellphones.com.vn/");
        String parentID = driver.getWindowHandle();

        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");

        driver.findElement(By.cssSelector("div.box-tow__social img[alt='CellphoneS Youtube Chanel']")).click();
        sleepInSecond(2);
        switchToTabByTitle("CellphoneS - YouTube");
        driver.findElement(By.cssSelector("div#tabsContainer button[aria-label='Search']")).click();
        sleepInSecond(2);
        driver.findElement(By.cssSelector("form#form input")).sendKeys("Alooooooooooo");

        switchToTabByTitle("CellphoneS - Điện thoại, laptop, tablet, phụ kiện chính hãng");

        driver.findElement(By.cssSelector("div.box-tow__social img[alt='CellphoneS Fanpage']")).click();
        sleepInSecond(2);
        switchToTabByTitle("CellphoneS - Hệ thống bán lẻ di động toàn quốc | Facebook");
        driver.findElement(By.cssSelector("form#login_popup_cta_form input[name='email']")).sendKeys("Heloooooooo");

        closeAllWithoutParent(parentID);
        sleepInSecond(2);
    }

    @Test
    public void TC_04_Window_Tab_Selenium_4() {
        driver.get("https://cellphones.com.vn/");
        System.out.println("Current driver ID: " + driver.toString());
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());

        WebDriver youtubeWindow = driver.switchTo().newWindow(WindowType.WINDOW);
        youtubeWindow.get("https://www.youtube.com/@CellphoneSOfficial");
        sleepInSecond(2);
        System.out.println("Current driver ID: " + youtubeWindow.toString());
        System.out.println(youtubeWindow.getTitle());
        System.out.println(youtubeWindow.getCurrentUrl());
        youtubeWindow.findElement(By.cssSelector("input#search")).sendKeys("Den vau");
        sleepInSecond(3);

        WebDriver fbTab = driver.switchTo().newWindow(WindowType.TAB);
        fbTab.get("https://www.facebook.com/CellphoneSVietnam");
        sleepInSecond(2);
        System.out.println("Current driver ID: " + fbTab.toString());
        System.out.println(fbTab.getTitle());
        System.out.println(fbTab.getCurrentUrl());
        fbTab.findElement(By.cssSelector("form#login_popup_cta_form input[name='email']")).sendKeys("alooooo");
        sleepInSecond(3);

        switchToTabByTitle("CellphoneS - Điện thoại, laptop, tablet, phụ kiện chính hãng");
        driver.findElement(By.cssSelector("input.cps-input")).sendKeys("iphone");
    }

    @Test
    public void TC_05_2_Window_Tab_Selenium_4() {
        cellPhoneDriver.get("https://cellphones.com.vn/");
        System.out.println("Cell phone driver: " + cellPhoneDriver.toString());
        System.out.println(cellPhoneDriver.getTitle());
        System.out.println(cellPhoneDriver.getCurrentUrl());
        cellPhoneDriver.findElement(By.cssSelector("input.cps-input")).sendKeys("iphone");


        hoangHaDriver.get("https://hoanghamobile.com/");
        System.out.println("Hoang Ha driver: " + hoangHaDriver.toString());
        System.out.println(hoangHaDriver.getTitle());
        System.out.println(hoangHaDriver.getCurrentUrl());
        hoangHaDriver.findElement(By.cssSelector("a.close-modal")).click();

        //cellPhoneDriver.switchTo().newWindow(WindowType.TAB).get("https://www.youtube.com/@CellphoneSOfficial");
        cellPhoneDriver.findElement(By.cssSelector("input.cps-input")).clear();
        cellPhoneDriver.findElement(By.cssSelector("input.cps-input")).sendKeys("samsungggggggggggggg");
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

    //Doi voi chi mo 2 tab/window
    public void switchToTabByID (String expectedID) {
        Set<String> allIDs = driver.getWindowHandles();
        for (String id : allIDs) {
            if(!id.equals(expectedID)){
                driver.switchTo().window(id);
                break;
            }
        }
    }

    //Mo bao nhieu cung dc
    public void switchToTabByTitle (String expectedTitle) {
        // Get all id cac tab hien tai dang mo
        Set<String> allIDs = driver.getWindowHandles();
        for (String id : allIDs) {
            // Cho switch vao tung ID
            driver.switchTo().window(id);

            // Lay title tab hien tai
            String actualTitle = driver.getTitle();
            if (actualTitle.equals(expectedTitle)) {
                break;
            }
        }
    }

    public void closeAllWithoutParent (String parentID) {
        Set<String> allIDs = driver.getWindowHandles();
        for (String id : allIDs) {
            if (!id.equals(parentID)) {
                driver.switchTo().window(id);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
    }
}

