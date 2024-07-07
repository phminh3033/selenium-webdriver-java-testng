package testNG;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Priority {
    @BeforeClass
    public void beforeClass() {
        System.out.println("Run before class");
    }

    @Test(priority = 1)
    public void Priority_01_testSearchWithDate() {
    }

    @Test(priority = 2)
    public void Priority_02_testSearchWithBilling() {
    }

    @Test(priority = 3)
    public void Priority_03_testSearchWithProduct() {
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        System.out.println("Run after class");
    }
}
