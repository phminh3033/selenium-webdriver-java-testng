package webdriver;

import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;

import java.io.File;


public class Topic_01_System_info {
    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir");
        String osName = System.getProperty("os.name");
        Keys keys;

        if (osName.startsWith("Window")) {
            keys = Keys.CONTROL;
        } else {
            keys = Keys.COMMAND;
        }

        Keys cmdCtrl = Platform.getCurrent().is(Platform.WINDOWS) ? Keys.CONTROL : Keys.COMMAND;

        String benz = "benz.jpg";
        String car = "car.jpg";
        String monitor = "monitor.jpg";

        String characterSplash = Platform.getCurrent().is(Platform.WINDOWS) ? "\\" : "/";
        String character = File.separator;

        String benzPath = projectPath + character + "uploadFiles" + character + benz;
        String carPath = projectPath + character + "uploadFiles" + character + car;
        String monitorPath = projectPath + character + "uploadFiles" + character + monitor;

        System.out.println(benzPath);
        System.out.println(carPath);
        System.out.println(monitorPath);
    }
}

