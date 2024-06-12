package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_07_WebElement_Commands {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Element() {
        // HTML Element
        //Tìm element
        driver.findElement(By.id(""));

        //Tìm và tương tác
        driver.findElement(By.id("")).click();
        driver.findElement(By.id("")).sendKeys();

        //Tìm và lưu vào 1 biến WebElement
        //Khi sử dụng biến này >2 lần
        WebElement fullNameTextbox = driver.findElement(By.id(""));
        fullNameTextbox.clear();
        fullNameTextbox.sendKeys("?");
        fullNameTextbox.getAttribute("");

        //--------------

        //Dung de xoa data trong 1 field editable
        //Textbox/TextArea/Dropdown (editable)
        //Thuong dung truoc ham sendKeys
        driver.findElement(By.id("")).clear();

        //Dung de nhap lieu
        driver.findElement(By.id("")).sendKeys("");

        //Dung de click
        driver.findElement(By.id("")).click();

        //Tim tu node cha vao node con
        driver.findElement(By.id("form-validate")).findElement(By.id("firstname"));
        driver.findElement(By.cssSelector("form#form-validate input#firstname")); //dung nay cho ngan gon

        //Tra ve 1 element (khop voi dieu kien)
        WebElement nameTextbox = driver.findElement(By.id(""));

        //Tra ve nhieu element (khop voi dieu kien)
        List<WebElement> textBoxs = driver.findElements(By.id(""));

        //Java non Generic
        ArrayList name = new ArrayList();
        name.add("Auto");
        name.add(12);
        name.add('M');
        name.add(true);

        //Java Generic
        ArrayList<String> address = new ArrayList<String>();
        address.add("Auto");


        //Dung de verify 1 checkbox/radio/dropdown (default) da dc chon hay chua
        driver.findElement(By.id("")).isSelected();
        Assert.assertTrue(driver.findElement(By.id("")).isSelected());
        Assert.assertFalse(driver.findElement(By.id("")).isSelected());

        Select select = new Select(driver.findElement(By.id("")));

        //Dung de verify 1 element bat ki co hien thi hay kh
        driver.findElement(By.id("")).isDisplayed();
        Assert.assertTrue(driver.findElement(By.id("")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.id("")).isDisplayed());

        //Dung de verify 1 element co duoc thao tac hay kh (kh phai read-only)
        driver.findElement(By.id("")).isEnabled();
        Assert.assertTrue(driver.findElement(By.id("")).isEnabled());
        Assert.assertFalse(driver.findElement(By.id("")).isEnabled());

        // Dung de get value cua attribute
        //<input type="text" id="firstname" name="firstname" value=""
        // title="First Name" maxlength="255" class="input-text required-entry">
        driver.findElement(By.id("")).getAttribute("title");     //==> First Name
        driver.findElement(By.id("")).getAttribute("id");        //==> firstname
        driver.findElement(By.id("")).getAttribute("maxlength"); //==> 255

        //Tab Accesibility trong Elements
        driver.findElement(By.id("")).getAccessibleName();
        //Tab Properties trong Elements
        driver.findElement(By.id("")).getDomAttribute("checked");
        driver.findElement(By.id("")).getDomProperty("innerHTML");

        //Tab Styles trong Elements (test GUI)
        //Font/ Size/ Color/ Backgroud/...
        driver.findElement(By.id("")).getCssValue("padding-top");
        driver.findElement(By.id("")).getCssValue("font-family");

        //Location: Vi tri cua element so voi do phan giai man hinh
        Point nameTextboxLocaion = driver.findElement(By.id("")).getLocation();
        nameTextboxLocaion.getX();
        nameTextboxLocaion.getY();

        //Size: chieu cao + chieu rong
        Dimension ageSize = driver.findElement(By.id("")).getSize();
        ageSize.getWidth();
        ageSize.getHeight();

        //Location + Size
        Rectangle nameTextboxRect = driver.findElement(By.id("")).getRect();

        Point namePoint = nameTextboxRect.getPoint(); //Location
        namePoint.getX();
        namePoint.getY();

        Dimension nameSize = nameTextboxRect.getDimension(); //Size
        nameSize.getHeight();
        nameSize.getWidth();

        //Shadow Element (JavascriptExecutor)
        driver.findElement(By.id("")).getShadowRoot();

        //Truy nguoc lai tagname HTML tu id/class/name/css/xpath
        driver.findElement(By.cssSelector("#firstname")).getTagName(); //input
        driver.findElement(By.id("firstname")).getTagName(); //input
        driver.findElement(By.className("form-insructions")).getTagName(); //p

        //Element A-> dau ra cua n la tagname cua element A
        String formListTag = driver.findElement(By.xpath("//*[@class='form-list']")).getTagName();

        //Dau vao cua Element B se nhan tagname cua A lam tham so
        driver.findElement(By.xpath("//div[@class='remember-me-popup']/preceding-sibling::" + formListTag));


        //Lay text cua 1 element
        driver.findElement(By.cssSelector("address.copyright")).getText();
        //==> © 2015 Magento Demo Store. All Rights Reserved.


        //Chup hinh bi loi va luu duoi dang nao
        //BYTES
        //FILE (Luu thanh 1 hinh co kich thuoc o trong o cung
        //BASE64 (Hinh dang text)
        driver.findElement(By.id("")).getScreenshotAs(OutputType.BASE64);
        driver.findElement(By.id("")).getScreenshotAs(OutputType.FILE);
        driver.findElement(By.id("")).getScreenshotAs(OutputType.BYTES);

        //Form (element nao la the form or nam trong the form)
        //Hanh vi giong phim enter
        //Register/Login/search/...
        driver.findElement(By.id("")).submit();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}