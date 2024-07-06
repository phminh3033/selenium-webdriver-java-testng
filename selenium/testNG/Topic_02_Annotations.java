package testNG;

import org.testng.annotations.*;

public class Topic_02_Annotations {
    @BeforeClass
    public void beforeClass() {
        System.out.println("Before Class");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("After Class");
    }

    @BeforeGroups
    public void beforeGroups() {
        System.out.println("Before Groups");
    }

    @AfterGroups
    public void afterGroups() {
        System.out.println("After Groups");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Before Method");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("After Method");
    }

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Before Suite");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("After Suite");
    }

    @Test
    public void TC_01() {
        System.out.println("TC01");
    }

    @Test
    public void TC_02() {
        System.out.println("TC02");
    }

    @Test
    public void TC_03() {
        System.out.println("TC03");
    }
}
