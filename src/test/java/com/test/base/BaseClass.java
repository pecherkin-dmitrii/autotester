package com.test.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class BaseClass {

    private final static String DRIVER_PROPERTY = System.getProperty("user.dir") + File.separator + "src" +
        File.separator + "test" + File.separator + "resources" + File.separator + "binaries" +
        File.separator + "chromedriver.exe";

    protected WebDriver driver;

    public BaseClass() {
        System.setProperty("webdriver.chrome.driver", DRIVER_PROPERTY);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
}
