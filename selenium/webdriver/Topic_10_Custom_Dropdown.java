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

        selectItemInDropdown("span#speed-button", "ul#speed-menu div", "Medium");
        selectItemInDropdown("span#files-button", "ul#files-menu div", "ui.jQuery.js");
        selectItemInDropdown("span#number-button", "ul#number-menu div", "13");
        selectItemInDropdown("span#salutation-button", "ul#salutation-menu div", "Dr.");

        // Verify cai da chon
        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Medium");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#files-button>span.ui-selectmenu-text")).getText(), "ui.jQuery.js");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(), "13");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button>span.ui-selectmenu-text")).getText(), "Dr.");
    }

    @Test
    public void TC_02_React() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

        selectItemInDropdown("div.selection.dropdown", "div.item", "Elliot Fu");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Elliot Fu");
        sleepInSecond(2);

        selectItemInDropdown("div.selection.dropdown", "div.item", "Matt");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Matt");
        sleepInSecond(2);

        selectItemInDropdown("div.selection.dropdown", "div.item", "Christian");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Christian");
        sleepInSecond(2);
    }

    //helo prv v
    @Test
    public void TC_03_VueJS() {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");
        selectItemInDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "Second Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");
    }

    @Test
    public void TC_04_Editable() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
        selectItemInEditableDropdown("input.search", "div.item span", "Belgium");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Belgium");
        sleepInSecond(2);

        selectItemInEditableDropdown("input.search", "div.item span", "Australia");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Australia");
        sleepInSecond(2);

        selectItemInEditableDropdown("input.search", "div.item span", "Argentina");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Argentina");
        sleepInSecond(2);
    }

    @Test
    public void TC_05_Nopcommerce () {
        driver.get("https://demo.nopcommerce.com/register");
        selectItemInDropdown("select[name='DateOfBirthDay']", "select[name='DateOfBirthDay']>option", "23");
        selectItemInDropdown("select[name='DateOfBirthMonth']", "select[name='DateOfBirthMonth']>option", "June");
        selectItemInDropdown("select[name='DateOfBirthYear']", "select[name='DateOfBirthYear']>option", "2000");

        Assert.assertTrue(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']>option[value='23']")).isSelected());
        Assert.assertTrue(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']>option[value='6']")).isSelected());
        Assert.assertTrue(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']>option[value='2000']")).isSelected());
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

        /*

        // Vua wait + vua tim element
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childItemCss))); //"ul#number-menu div" //Co case item kh visible het tat ca nen phai dung presenceOfAllElementsLocatedBy thay vi la visibilityOfAllElementsLocatedBy

        // Chi tim element
        List<WebElement> allItems = driver.findElements(By.cssSelector(childItemCss)); //allItems dang luu tru 19 item

        */

        // Gop thanh nhu nay:
        List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childItemCss)));

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

    public void selectItemInEditableDropdown (String parentCss, String childItemCss, String itemTextExpected) {
        driver.findElement(By.cssSelector(parentCss)).clear();
        driver.findElement(By.cssSelector(parentCss)).sendKeys(itemTextExpected);
        List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childItemCss)));
        for (WebElement item : allItems) {
            String textOfItem = item.getText();
            if (textOfItem.equals(itemTextExpected)){
                System.out.println("Text item selected = " + textOfItem);
                item.click();
                break;
            }
        }
    }
}