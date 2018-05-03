package com.test;

import com.test.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class CameraFindingTest extends BaseClass {

    @Test
    public void findingCamera() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        openMainPage();

        WebElement gadjetElement = driver.findElement(By.cssSelector("body > div.mainmenu > div > ul > li:nth-child(1) > a"));

        Actions actions = new Actions(driver);
        actions.moveToElement(gadjetElement).perform();

        WebElement actionCameraElement = driver.findElement(By.cssSelector("body > div.mainmenu > div > ul > li:nth-child(1) > div > div > a.mainmenu-subitem.mainmenu-icon695"));
        waitUntilVisibilityOf(10, actionCameraElement);
        actions.moveToElement(actionCameraElement).click().build().perform();

        WebElement nfcElement = driver.findElement(By.cssSelector("#preset18920 > li:nth-child(8) > label"));
        js.executeScript("arguments[0].scrollIntoView();", nfcElement);
        nfcElement.click();

        WebElement showResultsElement = driver.findElement(By.cssSelector("#tt-info > a"));
        showResultsElement.click();

        WebElement cameraElement = driver.findElement(By.linkText("Sony HDR-AZ1VW"));

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
