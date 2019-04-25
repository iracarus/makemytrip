package com.mmt.utils;

import com.mmt.base.TestBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowserUtils extends TestBase {
    private static final Logger logger4j= LogManager.getLogger(OtherUtils.padLeft("[" + BrowserUtils.class + "]", 40) );

    public static void scrollToTop() {
        try {
            JavascriptExecutor js = (JavascriptExecutor)driver;

            //This will scroll the web page till end.
            js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
            Thread.sleep(2000);
            js.executeScript("window.scrollTo(0, -250);");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void ScrollDown() {
        try {
            long lastHeight = (long) ((JavascriptExecutor)driver).executeScript("return document.body.scrollHeight");

            while (true) {
                ((JavascriptExecutor)driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
                Thread.sleep(2000);

                long newHeight = (long) ((JavascriptExecutor)driver).executeScript("return document.body.scrollHeight");
                if (newHeight == lastHeight) {
                    break;
                }
                lastHeight = newHeight;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void scrollToElement(WebElement element)
    {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void waitAndClick(WebElement element)
    {
        scrollToElement(element);
        WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(props.getProperty("DEFAULT_EXPLICITWAIT_TIME")));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public static void moveToAndClick(WebElement element)
    {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
    }

    public static void jsClick(WebElement element)
    {
        scrollToElement(element);
        WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(props.getProperty("DEFAULT_EXPLICITWAIT_TIME")));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
    }
}