package com.mmt.tests;

import com.mmt.base.ActionsClass;
import com.mmt.base.TestBase;
import com.mmt.pages.HomePage;
import com.mmt.pages.SearchPage;
import com.mmt.utils.BrowserUtils;
import com.mmt.utils.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import java.util.Date;
import java.util.List;

public class SearchPageTests extends TestBase {
    private Logger logger4j= LogManager.getLogger(SearchPage.class);
    private HomePage homePage;
    private SearchPage searchPage;
    private ActionsClass actionsClass;

    @BeforeTest
    public void setup()
    {
        TestBase.initialize();
        homePage = new HomePage();
        searchPage = new SearchPage();
        actionsClass = new ActionsClass();
    }

    @Test(priority = 3, enabled = false)
    @Parameters("stopType")
    public void printFlightCounts(String stopType) {
        actionsClass.performSearch("RoundTrip", "Delhi", "Bangalore", new Date(), new Date());
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
        logger4j.info(stopType + " - Filter - departure flights options : " + searchPage.getDepartureFlightCounts());
        logger4j.info(stopType + " - Filter - return flights options : " + searchPage.getReturnFlightCounts());

    }


    @Test(priority = 5, enabled = false)
    public void printFlightCountsNew()
    {
        Date currentDate = new Date();
        Date returnDate = DateUtils.addDays(currentDate, 7);
        homePage.selectSection("Flights");
        homePage.waitForSearchButton();
        homePage.selectRoundTripOption();
        homePage.selectFromCity("Mumbai");
        homePage.selectToCity("Patna");
        homePage.selectDepartureDate(DateUtils.addDays(currentDate, 1));
        homePage.selectReturnDate(returnDate);
        homePage.clickSearchButton();

        searchPage.resetStopsFilter();
        BrowserUtils.ScrollDown();
        logger4j.info("Without Filter - departure flights options : " + searchPage.getDepartureFlightCounts());
        logger4j.info("Without Filter - return flights options : " + searchPage.getReturnFlightCounts());
        BrowserUtils.scrollToTop();
        searchPage.resetStopsFilter();

        searchPage.resetStopsFilter();
        searchPage.selectNonStop();
        BrowserUtils.ScrollDown();
        logger4j.info("Non Stop Filter - departure flights options : " + searchPage.getDepartureFlightCounts());
        logger4j.info("Non Stop Filter - return flights options : " + searchPage.getReturnFlightCounts());
        BrowserUtils.scrollToTop();
        searchPage.resetStopsFilter();

        searchPage.resetStopsFilter();
        searchPage.selectSingleStop();
        BrowserUtils.ScrollDown();
        logger4j.info("1 Stop Filter - departure flights options : " + searchPage.getDepartureFlightCounts());
        logger4j.info("1 Stop Filter - return flights options : " + searchPage.getReturnFlightCounts());
        BrowserUtils.scrollToTop();
        searchPage.resetStopsFilter();
    }

    @Test(priority = 4, enabled = false)
    public void verifyTotalAmount()
    {
        Date currentDate = new Date();
        Date returnDate = DateUtils.addDays(currentDate, 7);
        homePage.selectSection("Flights");
        homePage.waitForSearchButton();
        homePage.selectRoundTripOption();
        homePage.selectFromCity("Delhi");
        homePage.selectToCity("Mumbai");
        homePage.selectDepartureDate(DateUtils.addDays(currentDate, 1));
        homePage.selectReturnDate(returnDate);
        homePage.clickSearchButton();

        //searchPage.resetStopsFilter();
        BrowserUtils.ScrollDown();
        searchPage.selectDepartureFlight(5);
        System.out.println("here 1");

        //searchPage.resetStopsFilter();
        BrowserUtils.ScrollDown();
        System.out.println("here 2");
        searchPage.selectDepartureFlight(2);

    }

    @AfterTest
    public void end()
    {
        tearDown();
    }
}
