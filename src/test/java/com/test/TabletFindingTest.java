package com.test;

import com.test.base.BaseClass;
import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

@Test(groups = {"other"})
public class TabletFindingTest extends BaseClass {

    @Test
    @Description("Searching for tablets with a price lower than 'price' parameter and" +
        "asserting that the search result list is not empty and a 'price from' of all items in list is lower than" +
        " 'price'.")
    @Parameters({"price"})
    public void findingTablets(String priceString) {
        int priceIntTarget = Integer.parseInt(priceString);

        openMainPage();

        WebElement gadgetElement = driver.findElement(By.cssSelector("body > div.mainmenu > div > ul > li:nth-child(2) > a"));
        Actions actions = new Actions(driver);
        actions.moveToElement(gadgetElement).perform();

        WebElement actionCameraElement = driver.findElement(By.cssSelector("body > div.mainmenu > div > ul > li:nth-child(2) > div > div > a.mainmenu-subitem.mainmenu-icon30"));
        waitUntilVisibilityOf(10, actionCameraElement);
        actions.moveToElement(actionCameraElement).click().build().perform();

        WebElement maxPrice = driver.findElement(By.id("maxPrice_"));
        maxPrice.sendKeys(priceString);
        maxPrice.submit();

        boolean listIsEmpty = driver.findElements(By.id("msg_search_w")).size() < 1;
        if (!listIsEmpty) {
            saveScreenshot();
            throw new AssertionError("The list is empty");
        }

        while (true) {
            List<WebElement> prices = driver.findElements(By.cssSelector(".model-price-range > a > span[id]"));
            for (WebElement element : prices) {
                int priceIntCurrent = Integer.parseInt(element.getText().replaceAll(" ", ""));
                Assert.assertTrue(priceIntCurrent < priceIntTarget);
            }

            try {
                driver.findElement(By.cssSelector("div.list-more-div.h.blue-button > em")).click();
            } catch (NoSuchElementException e) {
                break;
            }
        }
    }
}
