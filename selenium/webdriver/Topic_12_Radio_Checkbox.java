package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_12_Radio_Checkbox {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Telerik_Default_Checkbox() {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
        sleepInSecond(3);
        WebElement rearSideCheckbox = driver.findElement(By.xpath("//label[text()='Rear side airbags']/preceding-sibling::span/input"));
        WebElement dualZoneCheckbox = driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input"));

        // Case 1: Neu app mo, cai checkbox da dc chon roi
        checkToElement(rearSideCheckbox);

        // Case 2: Mo app checkbox chua dc chon
        checkToElement(dualZoneCheckbox);

        // Verify checkbox da dc chon thanh cong
        Assert.assertTrue(rearSideCheckbox.isSelected());
        Assert.assertTrue(dualZoneCheckbox.isSelected());

        unCheckToElement(rearSideCheckbox);
        unCheckToElement(dualZoneCheckbox);

        // Verify checkbox da bo chon thanh cong
        Assert.assertFalse(rearSideCheckbox.isSelected());
        Assert.assertFalse(dualZoneCheckbox.isSelected());
    }

    @Test
    public void TC_02_Telerik_Default_Radio() {
        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
        sleepInSecond(3);
        WebElement twoPetrol = driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::span/input"));
        WebElement twoDiesel = driver.findElement(By.xpath("//label[text()='2.0 Diesel, 103kW']/preceding-sibling::span/input"));

        checkToElement(twoPetrol);
        Assert.assertTrue(twoPetrol.isSelected());
        Assert.assertFalse(twoDiesel.isSelected());

        checkToElement(twoDiesel);
        Assert.assertFalse(twoPetrol.isSelected());
        Assert.assertTrue(twoDiesel.isSelected());

    }

    @Test
    public void TC_03_Default_Checkbox_Radio() {
        Assert.assertTrue(driver.findElement(By.xpath("//form[@data-testid='royal_login_form']")).isDisplayed());
    }

    @Test
    public void TC_04_Select_All_Checkbox() {
        driver.get("https://automationfc.github.io/multiple-fields/");
        List<WebElement> allCheckbox = driver.findElements(By.cssSelector("div.form-input-wide input[type='checkbox']"));
        for (WebElement checkbox : allCheckbox) {
            if (!checkbox.isSelected()) {
                checkbox.click();
                //sleepInSecond(1);
            }
        }

        // Verify all checkbox dc chon
        for (WebElement checkbox : allCheckbox) {
            Assert.assertTrue(checkbox.isSelected());
        }

        // Chon 1 checkbox/radio nao do trong tat ca
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        sleepInSecond(2);

        // Gan lai bien vi khi refresh thi bien bi xoa
        allCheckbox = driver.findElements(By.cssSelector("div.form-input-wide input[type='checkbox']"));
        for (WebElement checkbox : allCheckbox) {
            if (checkbox.getAttribute("value").equals("Hepatitis") && !checkbox.isSelected()) {
                checkbox.click();
            }
        }

        for (WebElement checkbox : allCheckbox) {
            if (checkbox.getAttribute("value").equals("Hepatitis")) {
                Assert.assertTrue(checkbox.isSelected());
            } else {
                Assert.assertFalse(checkbox.isSelected());
            }
        }
    }

    @Test
    public void TC_05_Custom_Radio () {
        driver.get("tiemchungcovid19");

        // Case 1:
        // Dung the input de click => The input bi che boi 1 element khac => FAILED
        // WebElement click():
        // isSelected: only applies to input elements

        // Case 2:
        // Dung the div ben ngoai de click => PASSED
        // Dung the div ben ngoai de verify => FAILED
        driver.findElement(By.xpath("//div[text()='']/preceding-sibling::div/div[@class='mat-radio-outer-circle']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[text()='']/preceding-sibling::div/div[@class='mat-radio-outer-circle']")).isSelected());

        // Case 3:
        // Dung the div ben ngoai de click => PASSED
        // Dung the input de verify => PASSED
        // 1 element ma can define toi 2 locator thi sau nay Maintain mat nhieu time
        driver.findElement(By.xpath("//div[text()='']/preceding-sibling::div/div[@class='mat-radio-outer-circle']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[text()='']/preceding-sibling::div/input']")).isSelected());

        // Case 4:
        // Dung the input de click => JavaExecutor (JS)
        // Dung the input de verify => isSelected: only applies to input elements
        WebElement registerRadio = driver.findElement(By.xpath("//div[text()='']/preceding-sibling::div/input']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", registerRadio);
        Assert.assertTrue(registerRadio.isSelected());
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

    public void checkToElement(WebElement checkElement) {
        if (!checkElement.isSelected()) {
            checkElement.click();
            sleepInSecond(3);
        }
    }

    public void unCheckToElement(WebElement checkElement) {
        if (checkElement.isSelected()) {
            checkElement.click();
            sleepInSecond(3);
        }
    }
}