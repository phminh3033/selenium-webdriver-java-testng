package javaTester;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_05_Assert {
    WebDriver driver;

    @Test
    public void verifyTestNG () {
        driver = new FirefoxDriver();
        driver.get("https://facebook.com/");
        //Trg Java có nhiều thư viện để verify data
        // Testing Framework (Uint/Intergration/UI Automation Test)
        // JUint 4/ TestNG(cả 3)/ JUint 5 (Intergration)/ Hamcrest/ AssertJ/..

        //UI test
        //Kiểu dữ liệu nhận vào là boolean (true/false)
        //Khi mong muốn điều kiện trả về là đúng thì dùng assertTrue để verify
        Assert.assertTrue(driver.getPageSource().contains("Facebook helps you connect and share with the people in your life."));

        //Mong muốn điều kiện trả về là sai thì dùng assertFalse
        Assert.assertFalse(driver.getPageSource().contains("Create a new account"));

        // Các hàm trả về kiểu dữ liệu boolean
        //Quy tắc: bắt đầu với tiền tố là is....
        driver.findElement(By.id("")).isDisplayed();
        driver.findElement(By.id("")).isEnabled();
        driver.findElement(By.id("")).isSelected();
        new Select(driver.findElement(By.id(""))).isMultiple();

        //Actual = Expected (Tuyệt đối - absolute
        Assert.assertEquals(driver.getCurrentUrl(), "https://facebook.com/");
        Assert.assertEquals(driver.findElement(By.id("")).getText(), "Create a new account");

        //Unit test
        Object name = null;
        Object name1 = "Selenium";
        Assert.assertNull(name);
        Assert.assertNotNull(name1);
    }
}
