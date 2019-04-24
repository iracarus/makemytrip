package com.mmt.pages;

import com.mmt.base.TestBase;
import com.mmt.utils.BrowserUtils;
import com.mmt.utils.OtherUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchPage extends TestBase {
    @FindBy(xpath = "//div[@id='fli_filter__stops']//span[text()='Stops']/following-sibling::a[text()='Reset']")
    WebElement stopsReset;

    @FindBy(xpath = "//div[@class='fli-intl-container clearfix']")
    WebElement filter;

    @FindBy(xpath = "//label[contains(@for,'filter_stop0')]//span[@class='box']/span")
    WebElement nonStopCheckBox;

    @FindBy(xpath = "//label[contains(@for, 'filter_stop1')]//span[@class='box']/span")
    WebElement singleStopCheckBox;

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

    @FindBy(xpath =  "//div[contains(@class,'splitVw-footer-left')]//p[@class='actual-price']")
    WebElement priceDeparture;

    @FindBy(xpath =  "//div[contains(@class,'splitVw-footer-right')]//p[@class='actual-price']")
    WebElement priceReturn;

    @FindBy(xpath = "//span[@class='splitVw-total-fare']/span")
    WebElement priceTotal;

    /**
     * Constructor for Search Page objects
     */
    public SearchPage()
    {
        PageFactory.initElements(driver, this);
    }

    /**
     * Resets the stop filter
     */
    public void resetStopsFilter() {
        BrowserUtils.waitAndClick(stopsReset);
    }

    /**
     * Select the Non Stop filter
     */
    public void selectNonStop()
    {
        BrowserUtils.moveToAndClick(nonStopCheckBox);
    }

    /**
     * Select the 1 Stop filter option
     */
    public void selectSingleStop()
    {
        BrowserUtils.moveToAndClick(singleStopCheckBox);
    }

    /**
     * Fetches the departure flights list as List<WebElement> ( currently it is not used )
     * @return List<WebElement>
     */
    public List<WebElement> getDepartureFlightsList() { return departureFlightOptions; }

    /**
     * Fetches the return flights list as List<WebElement> ( currently it is not used )
     * @return List<WebElement>
     */
    public List<WebElement> getReturnFlightsList() { return returnFlightOptions; }

    /**
     * Returns the count of flights available in Departure section after search
     * @return int
     */
    public int getDepartureFlightCounts() {
        return departureFlightOptions.size();
    }

    /**
     * Returns the count of flights available in Return section after search
     * @return int
     */
    public int getReturnFlightCounts() {
        return returnFlightOptions.size();
    }

    /**
     * Check if the currently active page is the Search Page or not
     * @return boolean
     */
    public boolean isAt() {
        return (departureFlightsSection.isDisplayed() && returnflightsSection.isDisplayed());
    }

    /**
     * Selects the departure flight as per the passed parameter
     * @param searchResultIndex
     * @return int returns the flight index in the search or -1 in case no flight is found
     */
    public int selectDepartureFlight(int searchResultIndex)
    {
        int flightsCount = departureFlightOptions.size();
        int resultIndex = -1;
        for(int i =0; i<flightsCount; i++)
        {
            if(i==searchResultIndex-1) {
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

    /**
     * Selects the return flight as per the passed parameter
     * @param searchResultIndex
     * @return int returns the flight index in the search or -1 in case no flight is found
     */
    public int selectReturnFlight(int searchResultIndex)
    {
        int flightsCount = returnFlightOptions.size();
        int resultIndex = -1;
        for(int i =0; i<flightsCount; i++)
        {
            if(i==searchResultIndex-1) {
                if(searchResultIndex!=1)
                    BrowserUtils.scrollToElement(returnFlightOptions.get(i-1));
                BrowserUtils.jsClick(returnFlightOptions.get(i));
                resultIndex = i;
                break;
            }
        }
        return resultIndex;
    }

    /**
     * Returns the Cost of the flight from the departure section on Search page
     * @return int Cost of the flight as an Int
     */
    public int getDepartureFlightCost()
    {
        return OtherUtils.getPrice(priceDeparture.getText());
    }

    /**
     * Returns the Cost of the flight from the return section on Search page
     * @return int Cost of the flight as an int
     */
    public int getReturnFlightCost()
    {
        return OtherUtils.getPrice(priceReturn.getText());
    }

    /**
     * Returns the Total Cost shown on the Search Page
     * @return int Total Flight Cost ( Departure + Return )
     */
    public int getTotalCost()
    {
        return OtherUtils.getPrice(priceTotal.getText());
    }
}