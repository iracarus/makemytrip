package com.mmt.tests;

import com.mmt.base.TestBase;
import com.mmt.pages.HomePage;
import com.mmt.pages.SearchPage;
import com.mmt.utils.BrowserUtils;
import com.mmt.utils.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;

public class SearchPageTests extends TestBase {
    private Logger logger4j= LogManager.getLogger(SearchPage.class);
    private HomePage homePage;
    private SearchPage searchPage;

    @BeforeMethod
    public void setup()
    {
        TestBase.initialize();
        homePage = new HomePage();
        searchPage = new SearchPage();
    }

    @Test(priority = 3)
    public void printFlightCounts()
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

    @Test
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

        searchPage.resetStopsFilter();
        BrowserUtils.ScrollDown();
        searchPage.selectDepartureFlight(5);
        System.out.println("here 1");

        searchPage.resetStopsFilter();
        BrowserUtils.ScrollDown();
        System.out.println("here 2");
        searchPage.selectDepartureFlight(2);

    }

    @AfterMethod
    public void end()
    {
        //tearDown();
    }
}
