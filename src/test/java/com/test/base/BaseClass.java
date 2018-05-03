package com.test.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class BaseClass {

    private final static String DRIVER_PROPERTY_PATH = System.getProperty("user.dir") + File.separator + "src" +
        File.separator + "test" + File.separator + "resources" + File.separator + "binaries" +
        File.separator + "chromedriver.exe";
    private final static String DRIVER_PROPERTY_NAME = "webdriver.chrome.driver";

    private String url = "http://www.e-katalog.ru/";
    private String password = "testPassword0987";
    protected String login = "testLogin111";
    protected WebDriver driver;

    @BeforeClass
    protected void setUp() {
        System.setProperty(DRIVER_PROPERTY_NAME, DRIVER_PROPERTY_PATH);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    protected void openMainPage() {
        driver.get(url);
    }

    protected void logIn() {
        WebElement loginTab = driver.findElement(By.className("wu_entr"));
        loginTab.click();
        (new WebDriverWait(driver, 10))
            .until(ExpectedConditions.presenceOfElementLocated(By.id("mui_user_login_window_avt")));
        driver.findElement(By.name("l_")).sendKeys(login);
        driver.findElement(By.name("pw_")).sendKeys(password);
        driver.findElement(By.className("l-but")).click();
    }

    protected void logOut() {
        driver.findElement(By.className("help2")).click();
    }

    protected void waitUntilVisibilityOf(int seconds, WebElement element) {
        new WebDriverWait(driver, seconds).until(ExpectedConditions.visibilityOf(element));
    }

    @AfterClass
    protected void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
