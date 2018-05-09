package com.test;

import com.test.base.BaseClass;
import io.qameta.allure.Description;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

@Test(groups = {"other"})
public class CameraFindingTest extends BaseClass {

    @Test
    @Description("Searching for Sony cameras with NFC, checking that Sony HDR-AZ1VW is in list, adding to bookmarks," +
        "checking that it appears in bookmarks and in history.")
    public void findingCamera() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        openMainPage();

        WebElement gadgetElement = driver.findElement(By.cssSelector("body > div.mainmenu > div > ul > li:nth-child(1) > a"));
        Actions actions = new Actions(driver);
        actions.moveToElement(gadgetElement).perform();

        WebElement actionCameraElement = driver.findElement(By.cssSelector("body > div.mainmenu > div > ul > li:nth-child(1) > div > div > a.mainmenu-subitem.mainmenu-icon695"));
        waitUntilVisibilityOf(10, actionCameraElement);
        actions.moveToElement(actionCameraElement).click().build().perform();

        WebElement sonyElement = driver.findElement(By.cssSelector("#li_br156 > label"));
        js.executeScript("arguments[0].scrollIntoView();", sonyElement);
        sonyElement.click();

        WebElement nfcElement = driver.findElement(By.cssSelector("#preset18920 > li:nth-child(8) > label"));
        js.executeScript("arguments[0].scrollIntoView();", nfcElement);
        nfcElement.click();

        for (int i = 0; i < 2; i++) {
            try {
                WebElement showResultsElement = driver.findElement(By.cssSelector("#tt-info > a"));
                showResultsElement.click();
                break;
            } catch (StaleElementReferenceException e) {
                //do nothing
            }
        }

        WebElement cameraElement = driver.findElement(By.linkText("Sony HDR-AZ1VW"));
        cameraElement.click();
        saveScreenshot();
        WebElement addToFavorite = driver.findElement(By.cssSelector("#m_625607 > div.big-star-off.off > em"));
        js.executeScript("arguments[0].scrollIntoView();", addToFavorite);
        waitUntilVisibilityOf(10, addToFavorite);
        addToFavorite.click();

        WebElement bookmarks = driver.findElement(By.id("name_bm_marked"));
        bookmarks.click();
        WebElement bookmark = driver.findElement(By.xpath("//*[@id='content_bm_marked']//*[text()='Sony HDR-AZ1VW']/.."));
        saveScreenshot();

        WebElement history = driver.findElement(By.id("name_bm_visited"));
        history.click();
        WebElement historyRecord = driver.findElement(By.xpath("//*[@id='content_bm_visited']//*[text()='Sony HDR-AZ1VW']/.."));
        saveScreenshot();
    }
}
