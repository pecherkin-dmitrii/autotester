package com.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class LoginTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(LoginTest.class);

    private String login;
    private String password;
    private String url = "http://www.e-katalog.ru/";

    @BeforeTest
    private void setUp() {
        FileInputStream fis;
        Properties properties = new Properties();

        try {
            fis = new FileInputStream("app.properties");
            properties.load(fis);

            login = properties.getProperty("login");
            password = properties.getProperty("password");
        } catch (IOException e) {
            LOGGER.error("Cannot load properties file.");
        }
    }

    @Test(description = "Login test")
    public void simpleTest() {
        //System.setProperty("webdriver.chrome.driver", "/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.get(url);
        WebElement loginTab = driver.findElement(By.className("wu_entr"));
        loginTab.click();
        (new WebDriverWait(driver, 10))
            .until(ExpectedConditions.presenceOfElementLocated(By.id("mui_user_login_window_avt")));
        driver.findElement(By.name("l_")).sendKeys(login);
        driver.findElement(By.name("pw_")).sendKeys(password);
        driver.findElement(By.className("l-but")).click();

        String current = driver.findElement(By.className("info-nick")).getText();
        Assert.assertEquals(current, login);
        driver.findElement(By.className("help2")).click();
        driver.quit();
    }
}
