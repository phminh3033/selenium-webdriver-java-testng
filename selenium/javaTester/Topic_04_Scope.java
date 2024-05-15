package javaTester;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class Topic_04_Scope {
    //Các biến được khai báo ở bên ngoài hàm -> phạm vi là Class
    //Biến global (toàn cục)
    //Có thể dùng cho tất cả các hàm ở trong 1 Class nào đó
    WebDriver driver;
    String homePageUrl; //Khai báo: Declare
    String fullName = "Automation test"; //Khai báo + khởi tạo (Initial)

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
    }

    @Test
    public void TC_01(){
        //Các biến được khai báo ở trong 1 hàm/block code -> phạm vi local (cục bộ)
        //Dùng trong cái hàm nó được khai báo/block code được sinh ra
        String homePageUrl = "https://facebook.com/";

        //Trong 1 hàm nếu như có 2 biến cùng tên (global/local) thì nó sẽ ưu tiên lấy biến local dùng
        //1 biến local nếu như gọi tới dùng mà chưa được khởi tạo thì sẽ bị lỗi
        //Biến local chưa khởi tạo mà gọi ra dùng nó sẽ báo lỗi ngay (complie code)
        driver.get(homePageUrl);

        //Nếu trong 1 hàm có 2 biến cùng tên (global/local) mà mình muốn lấy biến global ra dùng
        //Từ khoá this
        //Biến global chưa khởi tạo mà gọi ra dùng thì không báo lỗi ở level (complie code)
        //Level runtime sẽ lỗi
        driver.get(this.homePageUrl);
    }

    @AfterClass
    public void afterClass() {
//        driver.quit();
    }
}
