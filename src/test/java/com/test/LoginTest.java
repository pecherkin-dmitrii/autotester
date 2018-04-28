package com.test;

import com.test.base.BaseClass;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseClass {

    @Test
    public void simpleTest() {
        openMainPage();
        logIn();
        String current = driver.findElement(By.className("info-nick")).getText();
        Assert.assertEquals(current, login);
        logOut();
    }
}
