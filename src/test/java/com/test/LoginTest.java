package com.test;

import com.test.base.BaseClass;
import io.qameta.allure.Attachment;
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
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class LoginTest extends BaseClass {

    private final static Logger LOGGER = LoggerFactory.getLogger(LoginTest.class);

    private String login;
    private String password;
    private String url;

    @Parameters({"login", "password", "url"})
    @BeforeTest
    private void setUp(String login, String password, String url) {
        this.login = login;
        this.password = password;
        this.url = url;
    }

    @Test(description = "Login test")
    public void simpleTest() {
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

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshot(byte[] screenShot) {
        return screenShot;
    }
}
