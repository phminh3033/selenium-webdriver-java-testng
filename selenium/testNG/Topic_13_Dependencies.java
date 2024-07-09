package testNG;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class Topic_13_Dependencies {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
    }

    @Test
    public void TC_01_CreateNewUser() {
    }

    @Test(dependsOnMethods = "TC_01_CreateNewUser")
    public void TC_02_ViewAndSearchUser() {
    }

    @Test(dependsOnMethods = "TC_01_CreateNewUser")
    public void TC_03_UpdateExistingUser() {
        Assert.assertTrue(false);
    }

    @Test(dependsOnMethods = "TC_03_UpdateExistingUser")
    public void TC_04_MoveExistingUserToOTherRole() {
    }

    @Test(dependsOnMethods = "TC_04_MoveExistingUserToOTherRole")
    public void TC_05_DeleteExistingUser() {
    }

    @AfterClass
    public void afterClass() {
    }
}
