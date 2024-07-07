package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.regex.Pattern;

public class Topic_25_Wait_05_Explicit_1 {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();

        // Khoi tao explicitWait co tong thoi gian = 10s, polling la 0.5s (default)
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Method() {
        // Cho cho 1 Alert presence trong HTML/DOM truoc khi thao tac len
        explicitWait.until(ExpectedConditions.alertIsPresent());

        // Cho cho 1 element KHONG con o trong DOM
        explicitWait.until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector(""))));

        // Cho cho 1 element CO trong DOM (KHONG quan tam co tren UI hay kh)
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("form#search-form inout#live-search-bar")));

        // Cho cho 1 LIST element CO trong DOM
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("")));

        // Cho cho element co thang cha la A co trong DOM (giong presenceOfElementLocated, dung presenceOfElementLocated cho gon)
        explicitWait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(By.cssSelector("form#search-form"), By.cssSelector("inout#live-search-bar")));

        // Cho cho 1-n element duoc hien thi tren UI (co ham su dung REST parameter lam tham so)
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("")));
        explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(""))));
        explicitWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.cssSelector("")),driver.findElement(By.cssSelector("")),driver.findElement(By.cssSelector(""))));

        // Cho cho element duoc phep click: link/button/checkbox/radio...
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("")));

        //Cho cho page hien tai co title nhu mong doi
        explicitWait.until(ExpectedConditions.titleIs("Facebook"));
        driver.getTitle();

        //Ket hop nhieu dieu kien - cac dieu kien phai dung
        explicitWait.until(ExpectedConditions.and(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("")),
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(""))));

        //Ket hop nhieu dieu kien - chi can 1 dieu kien dung la duoc
        explicitWait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("")),
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(""))));

        // Cho cho 1 element co attribute CHUA gia tri mong doi (tuong doi)
        explicitWait.until(ExpectedConditions.attributeContains(By.cssSelector("input#search"), "placehoder", "Search entire store here..."));
        explicitWait.until(ExpectedConditions.attributeContains(By.cssSelector("input#search"), "placehoder", "store here..."));

        // Cho cho 1 element co attribute CO gia tri mong doi (tuyet doi)
        explicitWait.until(ExpectedConditions.attributeToBe(By.cssSelector("input#search"), "placehoder", "Search entire store here..."));

        // Cho cho 1 element cos attribute khac null
        explicitWait.until(ExpectedConditions.attributeToBeNotEmpty(driver.findElement(By.cssSelector("input#search")), "placehoder"));

        explicitWait.until(ExpectedConditions.domAttributeToBe(driver.findElement(By.cssSelector("input#search")), "namespaceURL", "http://www.w3.org/1999/xhtml"));
        explicitWait.until(ExpectedConditions.domPropertyToBe(driver.findElement(By.cssSelector("input#search")), "namespaceURL", "http://www.w3.org/1999/xhtml"));

        // Cho cho 1 element duoc selected thanh cong
        // Checkbox/Radio/Dropdown Item (Default)
        explicitWait.until(ExpectedConditions.elementToBeSelected(By.cssSelector("")));

        // Cho cho 1 element da selected/unselected roi
        explicitWait.until(ExpectedConditions.elementSelectionStateToBe(By.cssSelector(""), true));
        explicitWait.until(ExpectedConditions.elementSelectionStateToBe(By.cssSelector(""), false));

        // Cho cho 1 frame/iFrame duoc avaiable va swwitch qua
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("nameOrId")); //name or id
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(4234324)); //index
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector(""))); //element
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.cssSelector("")))); //element

        // Cho cho 1 element bien mat (KHONG hien thi tren UI)
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("")));

        // Cho cho 1 doan code JS can tra ve gia tri
        explicitWait.until(ExpectedConditions.jsReturnsValue("document.documentElement.innerText;"));

        /* Cho cho 1 doan code JS duoc thuc thi KHONG nem ra ngoai le nao het
            + KHONG nem ra: true
            + CO ngoai le: false
         */
        explicitWait.until(ExpectedConditions.javaScriptThrowsNoExceptions("document.documentElement.innerText;"));
        Assert.assertTrue(explicitWait.until(ExpectedConditions.javaScriptThrowsNoExceptions("document.documentElement.innerText;")));

        // Cho so luong element bang 1 con so mong muon
        explicitWait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("select[title='Sort By']>option"), 6));
        explicitWait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.cssSelector("select[title='Sort By']>option"), 6));
        explicitWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("select[title='Sort By']>option"), 6));

        // Cho cho open ra dc so window/tab la bao nhieu (KHONG quan tam n load xong chua)
        explicitWait.until(ExpectedConditions.numberOfWindowsToBe(6));


        explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("div.category-title>h1"), "Mobile"));

        Pattern pattern = Pattern.compile("This is root of mobile", Pattern.CASE_INSENSITIVE);
        explicitWait.until(ExpectedConditions.textMatches(By.cssSelector(""), pattern));

        // Bat buoc text nay phai co trong DOM/HTML
        explicitWait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("div.category-title>h1"),"This is root of mobile"));

        explicitWait.until(ExpectedConditions.urlToBe("https://automationfc.github.io/dynamic-loading/")); // Tuyet doi
        explicitWait.until(ExpectedConditions.urlContains("github.io/dynamic-loading/")); // Tuong doi
        explicitWait.until(ExpectedConditions.urlMatches("[^abc]"));
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}