package com.mmt.tests;

import com.mmt.base.ActionsClass;
import com.mmt.base.TestBase;
import com.mmt.pages.SearchPage;
import com.mmt.utils.BrowserUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Date;

public class SearchPageTestsRandom extends TestBase {
    private Logger logger4j= LogManager.getLogger(SearchPage.class);
    private SearchPage searchPage;
    private ActionsClass actionsClass;

    @BeforeTest
    public void setup()
    {
        TestBase.initialize();
        searchPage = new SearchPage();
        actionsClass = new ActionsClass();
    }

    @Test(dataProvider = "PerformSearch")
    public void verifyTotalAmount(String stopType, String departureCity, String arrivalCity, Date departureDate, Date returnDate) throws InterruptedException {
        actionsClass.performSearch(stopType, departureCity, arrivalCity, departureDate, returnDate);
        searchPage.resetStopsFilter();

        switch (stopType)
        {
            case "" :
                break;
            case "nonstop" :
                searchPage.selectNonStop();
                break;
            case "singlestop" :
                searchPage.selectSingleStop();
                break;
        }

        BrowserUtils.ScrollDown();
        searchPage.selectDepartureFlight(4);
        searchPage.selectReturnFlight(7);
        int expTotalCost = searchPage.getDepartureFlightCost() + searchPage.getReturnFlightCost();
        int actualTotalCost = searchPage.getTotalCost();

        Assert.assertEquals(actualTotalCost, expTotalCost);
        logger4j.info("Stop Type : "+ stopType +"Expected : " + expTotalCost + ", Actual : " + actualTotalCost);
    }

    @AfterTest
    public void end()
    {
        tearDown();
    }
}
