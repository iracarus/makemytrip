package com.mmt.pages;

import com.mmt.base.TestBase;
import com.mmt.utils.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchPage extends TestBase {
    @FindBy(xpath = "//div[@id='fli_filter__stops']//span[text()='Stops']/following-sibling::a[text()='Reset'] | //div[@class='filter_subdivisions stops_info stops-dep']//a[text()='Reset'] | //div[@class='rows']//a[contains(text(), 'Reset All') and contains(@class, 'hidden')]/preceding-sibling::a | //div[@class='filter_subdivisions stops_info stops-dep']//a[text()='Reset'] | //div[@class='rows']//a[contains(text(), 'Reset All') and contains(@class, 'hidden')]/preceding-sibling::a")
    WebElement stopsReset;

    @FindBy(xpath = "//label[@for='filter_stop0']//span[@class='box']")
    WebElement nonStopCheckBox;

    @FindBy(xpath = "//a[@id='stops_0_dep']")
    WebElement nonStopCheckBoxNew;

    @FindBy(xpath = "//label[@for='filter_stop1']//span[@class='box']")
    WebElement singleStopCheckBox;

    @FindBy(xpath = "//a[@id='stops_1_dep']")
    WebElement singleStopCheckBoxNew;

    @FindBy(xpath = "//a[@id='stops_2_dep']")
    WebElement multiStopCheckBoxNew;

    @FindBy(xpath = "//div[@id='ow_domrt-jrny']")
    WebElement departureFlightsSection;

    @FindBy(xpath = "//div[@id='rt-domrt-jrny']")
    WebElement returnflightsSection;

    @FindAll(@FindBy(xpath="//div[@id='ow_domrt-jrny']//div[@class='fli-list splitVw-listing']"))
    List<WebElement> departureFlightOptions;

    @FindAll(@FindBy(xpath="//div[@class='row wrap-dep']/div[2]/div/div/div"))
    List<WebElement> departureFlightOptionsNew;

    @FindAll(@FindBy(xpath="//div[@id='rt-domrt-jrny']//div[@class='fli-list splitVw-listing']"))
    List<WebElement> returnFlightOptions;

    @FindAll(@FindBy(xpath="//div[@class='row wrap-ret']/div[2]/div/div/div"))
    List<WebElement> returnFlightOptionsNew;

    public SearchPage()
    {
        PageFactory.initElements(driver, this);
    }

    public void resetStopsFilter() {
        Boolean isPresent = driver.findElements(By.xpath("//a[@id='stops_0_dep']")).size() > 0;
        if(isPresent)
        {
            if(nonStopCheckBoxNew.getAttribute("class").contains("active"))
            {
                nonStopCheckBoxNew.click();
            }

            if(singleStopCheckBoxNew.getAttribute("class").contains("active"))
            {
                singleStopCheckBoxNew.click();
            }

            if(multiStopCheckBoxNew.getAttribute("class").contains("active"))
            {
                multiStopCheckBoxNew.click();
            }
        }
        else
        {
            BrowserUtils.jsClick(stopsReset);
        }

    }

    public void selectNonStop()
    {
        if(driver.findElements(By.xpath("//a[@id='stops_0_dep']")).size() > 0 )
        {
            BrowserUtils.waitAndClick(nonStopCheckBoxNew);
        }
        else
        {
            BrowserUtils.waitAndClick(nonStopCheckBox);
        }

    }

    public void selectSingleStop()
    {
        if(driver.findElements(By.xpath("//a[@id='stops_1_dep']")).size() > 0 )
        {
            BrowserUtils.waitAndClick(singleStopCheckBoxNew);
        }
        else
        {
            BrowserUtils.waitAndClick(singleStopCheckBox);
        }

    }

    public List<WebElement> getDepartureFlightsList() { return departureFlightOptions; }

    public List<WebElement> getReturnFlightsList() { return returnFlightOptions; }

    public int getDepartureFlightCounts() {
        if(departureFlightOptions.size() == 0)
        {
            return departureFlightOptionsNew.size();
        }
        else
        {
            return departureFlightOptions.size();
        }

    }

    public int getReturnFlightCounts() {
        if(returnFlightOptions.size() == 0 )
        {
            return returnFlightOptionsNew.size();
        }
        else
        {
            return returnFlightOptions.size();
        }

    }

    public boolean isAt() {
        return (departureFlightsSection.isDisplayed() && returnflightsSection.isDisplayed());
    }

    public int selectDepartureFlight(int searchResultIndex)
    {
        int flightsCount = departureFlightOptions.size();
        int resultIndex = -1;
        for(int i =0; i<flightsCount; i++)
        {
            if(i==searchResultIndex-1) {
                boolean radioStatus = departureFlightOptions.get(i).findElement(By.xpath("./input")).isSelected();
                //WebElement radioBtnElement = departureFlightOptions.get(i).findElement(By.xpath(".//span[contains(@class,'splitVw-inner')]"));
                if(searchResultIndex!=1)
                {
                    BrowserUtils.scrollToElement(departureFlightOptions.get(i-1));
                }
                BrowserUtils.jsClick(departureFlightOptions.get(i));
                resultIndex = i;
                break;
            }
        }
        return resultIndex;
    }
}
