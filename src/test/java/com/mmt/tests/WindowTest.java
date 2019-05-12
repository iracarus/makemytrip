package com.mmt.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class WindowTest {
    public static void gethotelname(WebDriver driver, List<WebElement> element, int timeout, String value) throws InterruptedException
    {
        new WebDriverWait(driver,timeout).
                until(ExpectedConditions.visibilityOfAllElements(element));
        List<WebElement> lst=element;
        int lstsize=lst.size();
//        if(nextpagearrow.isDisplayed())
//        {
//            for(int i=0;i<=lstsize;i++)
//            {
//                if(lst.get(i).getText().contains(value))
//                {
//                    lst.get(i).click();
//                    break;
//                }
//            }
//            nextpagearrow.click();
//        }

        if(true)
        {
            for(int i=0;i<=lstsize;i++)
            {
                if(lst.get(i).getText().contains(value))
                {
                    lst.get(i).click();
                    break;
                }
            }
            Thread.sleep(5000);
        }
    }
}
