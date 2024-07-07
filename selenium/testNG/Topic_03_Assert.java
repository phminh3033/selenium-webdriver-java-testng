package testNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_03_Assert {
    WebDriver driver;

    @Test
    public void test01() {
        //Equals = kiểm tra 2 dữ liệu bằng nhau (Actual - Expected)
        //String/ boolean/ Int/ Float...
        String fullName = "Automation FC";
        Assert.assertEquals(fullName, "Automation FC");

        //True-False
        //Điều kiện nhận vào là boolean (isDisplayed/ isEnbabled/ isSelected/...)

        //Mong đợi kết quả trả về là true
        Assert.assertTrue(isElementDisplayed(By.cssSelector("")));

        //Mong đợi kết quả trả về là false
        Assert.assertFalse(isElementDisplayed(By.cssSelector("")));

    }

    private boolean isElementDisplayed(By locator) {
        return driver.findElement(locator).isDisplayed();
    }
}
