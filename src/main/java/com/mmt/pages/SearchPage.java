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
    @FindBy(xpath = "//div[@id='fli_filter__stops']//span[text()='Stops']/following-sibling::a[text()='Reset']")
    WebElement stopsReset;

    @FindBy(xpath = "//label[@for='filter_stop0']//span[@class='box']")
    WebElement nonStopCheckBox;

    @FindBy(xpath = "//label[@for='filter_stop1']//span[@class='box']")
    WebElement singleStopCheckBox;

    @FindBy(xpath = "//div[@id='ow_domrt-jrny']")
    WebElement departureFlightsSection;

    @FindBy(xpath = "//div[@id='rt-domrt-jrny']")
    WebElement returnflightsSection;

    @FindAll(@FindBy(xpath="//div[@id='ow_domrt-jrny']//div[@class='fli-list splitVw-listing']"))
    List<WebElement> departureFlightOptions;

    @FindAll(@FindBy(xpath="//div[@id='rt-domrt-jrny']//div[@class='fli-list splitVw-listing']"))
    List<WebElement> returnFlightOptions;

    public SearchPage()
    {
        PageFactory.initElements(driver, this);
    }

    public void resetStopsFilter() {
        BrowserUtils.waitAndClick(stopsReset);
    }

    public void selectNonStop()
    {
        BrowserUtils.waitAndClick(nonStopCheckBox);
    }

    public void selectSingleStop()
    {
        BrowserUtils.waitAndClick(singleStopCheckBox);
    }

    public List<WebElement> getDepartureFlightsList() { return departureFlightOptions; }

    public List<WebElement> getReturnFlightsList() { return returnFlightOptions; }

    public int getDepartureFlightCounts() {
        return departureFlightOptions.size();
    }

    public int getReturnFlightCounts() {
        return returnFlightOptions.size();
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
