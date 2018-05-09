package com.test;

import com.test.base.BaseClass;
import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Test(groups = {"init"})
public class LoginTest extends BaseClass {

    @Test
    @Description("Entering login and password and checking that logged in successful.")
    @Parameters({"login", "password"})
    public void simpleTest(String login, String password) {
        openMainPage();
        logIn(login, password);
        String current = driver.findElement(By.className("info-nick")).getText();
        saveScreenshot();
        Assert.assertEquals(current, login);
        logOut();
    }
}
