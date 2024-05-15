package javaTester;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class Topic_01_Data_Type_T11 {

    double bNum = 154.145d;
    float fNum = 34.656f;
    char n = 'h';
    String name ="minh";

    //Kieu du lieu tham chieu (Reference type)
    //Class
    FirefoxDriver firefoxDriver = new FirefoxDriver();
    Select select = new Select(firefoxDriver.findElement(By.className("")));

    //Interface
    WebDriver webDriver;
    JavascriptExecutor jsExecutor;

    //Array
    int[] studentAge = {15, 45, 76};
    String[] studentName = {"A", "B"};

    //Collection : List / Set / Queue
    //List
    List<String> studentAdd = new ArrayList<String>();
    List<String> studentCity = new LinkedList<String>();
    List<String> studentPhone = new Vector<String>();

}
