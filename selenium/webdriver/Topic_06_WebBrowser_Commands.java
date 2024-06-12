package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Topic_06_WebBrowser_Commands {
    WebDriver driver;

    @BeforeClass //Pre- condition khởi tạo dữ liệu
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); // selenium 3
        driver.manage().window().maximize();

        driver = new FirefoxDriver();
        driver = new ChromeDriver();
        driver = new EdgeDriver();
        driver = new SafariDriver();
        driver = new InternetExplorerDriver();
        //driver = new OperaDriver(); //Selenium 4 strop support from 2022
        //driver = new HTMLUnit(); //Headless browser
        //Từ năm 2016: Chrome/Firefox có support chạy dạng headless
        //Headless: Crawl data (Data Analyst)/ Dev FE làm unit test
    }

    @Test
    public void TC_01_ID() throws MalformedURLException {
        driver.get("https://www.youtube.com");

        driver.close(); //Đóng tab/window đang active
        driver.quit(); //Đóng luôn browser (kh care bao nhiêu tab/window

        //2 hàm nãy sẽ bị ảnh hưởng timeout của implicitWait
        //findElement/ findElements

        //Nó sẽ đi tìm với loại By nào và trả về element nếu như được tìm thấy (WebElement)
        //Không được tìm thấy -> Fail tại step này - throw exception: NoSuchElementException
        //Trả về 1 element - nếu như tìm thấy nhiều hơn 1 thì cũng chỉ lấy 1 (thao tác với cái đầu tiên)
        WebElement emailAddTextbox = driver.findElement(By.id("email"));

        //Nó sẽ đi tìm với loại By nào trả về 1 danh sách element nếu như được tìm thấy (List WebElement)
        //Không được tìm thấy -> không bị fail - trả về 1 list rỗng (0 element)
        List<WebElement> checkboxs = driver.findElements(By.xpath("//input[@text='checkbox']"));

        //Dùng để lấy ra URL của màn hình/page hiện tại đang dùng
        String loginPageUrl = driver.getCurrentUrl();

        //Lấy ra page source HTML/CSS/JS của page hiện tại
        //Verify 1 cách tương đối - relative
        driver.getPageSource();
        Assert.assertTrue(driver.getPageSource().contains("The Apple and Google Play...."));


        driver.getTitle(); //Lấy ra title của page hiện tại

        // Lấy ra ID của cửa sổ/tab hiện tại
        // Sử dụng  khi handle các window browser/tab
        driver.getWindowHandle();
        driver.getWindowHandles();

        //Cookies - Framework
        driver.manage().getCookies();
//        driver.manage().addCookie();
//        driver.manage().getCookieNamed();
//        driver.manage().deleteCookie();
//        driver.manage().deleteAllCookies();
//        driver.manage().deleteCookieNamed();

        //Get ra những log ở Dev tool - Framework
        driver.manage().logs().get(LogType.DRIVER);

        //Apply cho tìm element
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); //selenium 4

        //Chờ cho page load xong
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        //Set trước khi dùng với thư viện JavascriptExecutor - JavascriptExecutor
        //Inject 1 đoạn code JS vào trong Browser/Element
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));

        //Selenium 4
        driver.manage().timeouts().getImplicitWaitTimeout();
        driver.manage().timeouts().getPageLoadTimeout();
        driver.manage().timeouts().getScriptTimeout();

        //Test GUI
        driver.manage().window().fullscreen(); //giống F11
        driver.manage().window().maximize(); // full màn hình
        driver.manage().window().minimize(); // hạ màn hình
        //Test Responsive (Resolution)
        driver.manage().window().setSize(new Dimension(1366, 768)); //set kích thước của browser
        driver.manage().window().setPosition(new Point(0,0)); //set vị trí của browser so với độ phân giải màn hình

        driver.manage().window().getSize();
        driver.manage().window().getPosition();


        //Điều hướng trang web
        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().refresh();

        //Thao tác với history của web page (back/forward)
        driver.navigate().to("https://www.youtube.com");
        driver.navigate().to(new URL("https://www.youtube.com"));

        //Alert / Window(tab) / Frame(iFrame)
        //Alert
        driver.switchTo().alert().accept();
        driver.switchTo().alert().dismiss();
        driver.switchTo().alert().getText();
        driver.switchTo().alert().sendKeys("");

        //Window - Handle window/Tab
        String homePageWindowID = driver.getWindowHandle();
        Set<String> allWindowIDs = driver.getWindowHandles();

        driver.switchTo().window(homePageWindowID); //switch qua tab bằng ID
        //driver.switchTo().newWindow();

        //Frame (iFrame)
        // Có 3 cách switch: Index / ID (name) / Element
        driver.switchTo().frame(0);
        driver.switchTo().frame("45653");
        driver.switchTo().frame(driver.findElement(By.id("")));

        //Switch về HTML chứa frame trước đó
        driver.switchTo().defaultContent();

        //Switch từ frame trong ra ngoài frame chưa nó
        driver.switchTo().parentFrame();

    }

    @AfterClass // Post-condition, xóa dữ liệu sau khi test
    public void afterClass() {
        driver.quit();
    }
}