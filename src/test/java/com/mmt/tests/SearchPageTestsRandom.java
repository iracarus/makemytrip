package com.mmt.tests;

import com.mmt.base.ActionsClass;
import com.mmt.base.TestBase;
import com.mmt.pages.SearchPage;
import com.mmt.utils.BrowserUtils;
import com.mmt.utils.OtherUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.util.Date;

public class SearchPageTestsRandom extends TestBase {
    private Logger logger4j= LogManager.getLogger(OtherUtils.padLeft("[" + SearchPageTestsRandom.class + "]", Integer.parseInt(props.getProperty("LOGS_PADDING"))));
    private SearchPage searchPage;
    private ActionsClass actionsClass;

    @BeforeMethod
    public void setup()
    {
        TestBase.initialize();
        searchPage = new SearchPage();
        actionsClass = new ActionsClass();
    }

    @Test(priority = 4, dataProvider = "PerformSearchMulti")
    public void verifyTotalAmount(String stopType, String departureCity, String arrivalCity, Date departureDate, Date returnDate) throws InterruptedException {
        logger4j.debug("Test Starting ------- verifyTotalAmount -------");
        actionsClass.performSearch("Flights", departureCity, arrivalCity, departureDate, returnDate);
        searchPage.resetStopsFilter();
        logger4j.info("Test the total amount for Stops Filter Selected as : '" + stopType + "'");
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

        for(int i=1; i<=10 ; i++)
        {
            BrowserUtils.ScrollDown();
            searchPage.selectDepartureFlight(i);
            searchPage.selectReturnFlight(i);

            int expTotalCost = searchPage.getDepartureFlightCost() + searchPage.getReturnFlightCost();
            int actualTotalCost = searchPage.getTotalCost();

            Assert.assertEquals(actualTotalCost, expTotalCost);
            logger4j.info("Result "+i+", Stop Type : "+ stopType +", Expected : " + expTotalCost + ", Actual : " + actualTotalCost);
        }
        logger4j.debug("Test Ending ------- verifyTotalAmount -------");
        Reporter.log("Test Ending ------- verifyTotalAmount -------");
    }

    @AfterMethod
    public void end()
    {
        //tearDown();
    }
}
