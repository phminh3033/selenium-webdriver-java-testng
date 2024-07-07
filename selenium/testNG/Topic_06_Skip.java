package testNG;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Skip {
    @BeforeClass
    public void beforeClass() {
        System.out.println("Run before class");
    }

    @Test
    public void Priority_01_testSearchWithDate() {
    }

    @Test(enabled = false)
    public void Priority_02_testSearchWithBilling() {
    }

    @Test
    public void Priority_03_testSearchWithProduct() {
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        System.out.println("Run after class");
    }
}
