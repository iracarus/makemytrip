package com.mmt.utils;

import com.mmt.base.TestBase;
import org.openqa.selenium.JavascriptExecutor;

public class BrowserUtils extends TestBase {
    public static void scrollToTop() {
        JavascriptExecutor js = (JavascriptExecutor)driver;

        //This will scroll the web page till end.
        js.executeScript("window.scrollTo(0, 0);");
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
}
