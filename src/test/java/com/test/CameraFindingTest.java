package com.test;

import com.test.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class CameraFindingTest extends BaseClass {

    @Test
    public void findingCamera() {
        openMainPage();
        WebElement gadjetElement = driver.findElement(By.className("wu_entr"));
        Actions actions = new Actions(driver);
        actions.moveToElement(gadjetElement).build().perform();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
