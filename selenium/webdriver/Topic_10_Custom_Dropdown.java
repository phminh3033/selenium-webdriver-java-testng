package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_10_Custom_Dropdown {
    WebDriver driver;

    //Tuong minh: Trang thai cu the cho element
    // Visible/ Invisible/ Presence (Co trong HTML nhung kh hien thi)/ Number(so luong element bang may?)/ Clickable)co the click hay kh?) / ....
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));

        //Ngam dinh: kh ro rang cho 1 trang thai cu the nao cua element
        // Ap dung cho viec tim element - findElement(s)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_JQuery() {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

        selectItemInDropdown("span#speed-button", "ul#speed-menu  div", "Medium");
        selectItemInDropdown("span#files-button", "ul#files-menu div", "ui.jQuery.js");
        selectItemInDropdown("span#number-button", "ul#number-menu div", "13");
        selectItemInDropdown("span#salutation-button", "ul#salutation-menu div", "Dr.");
    }

    @Test
    public void TC_02_Logo() {
    }

    //helo prv v
    @Test
    public void TC_03_Form() {
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

    public void selectItemInDropdown (String parentCss, String childItemCss, String itemTextExpected) {

        // 1 - Click vao the de xo het cac item ben trong dropdown
        driver.findElement(By.cssSelector(parentCss)).click(); //"span#number-button"
        //sleepInSecond(5);

        // 2.1 - No se xo ra chua het tat ca item
        // 2.2 - No se xo ra nhung chi chua 1 phan va dang load them
        // Cho cho xuat hien day du item tren HTML
        // (React/ Angular/..)

        // Vua wait + vua tim element
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childItemCss))); //"ul#number-menu div" //Co case item kh visible het tat ca nen phai dung presenceOfAllElementsLocatedBy thay vi la visibilityOfAllElementsLocatedBy

        // Chi tim element
        List<WebElement> allItems = driver.findElements(By.cssSelector(childItemCss)); //allItems dang luu tru 19 item

        for (WebElement item : allItems) {
            // Neu case element click chon xong roi, cac item con lai kh con trong HTML nua
            // -> Thi getText se FAILED
            String textOfItem = item.getText();

            // 3 - Ktr text cua tung item va thoa dieu kien thi click vao
            if (textOfItem.equals(itemTextExpected)){
                System.out.println("Text item selected = " + textOfItem);
                item.click();
                break;
            }
        }

        // 3.1 - Neu nhu item can chon no hien thi thi click vao
        // 3.2 - Neu Nhu item can chon nam ben duoi thi 1 so case can scroll xuong de hien thi len roi moi chon duoc (JavascriptExecutor)

        // 4 - Truoc khi click can kiem tra: Neu text cua item = item can chon thi click
    }
}