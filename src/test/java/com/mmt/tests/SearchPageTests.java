package com.mmt.tests;

import com.mmt.base.TestBase;
import com.mmt.pages.HomePage;
import com.mmt.pages.SearchPage;
import com.mmt.utils.BrowserUtils;
import com.mmt.utils.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Date;

public class SearchPageTests extends TestBase {
    private Logger logger4j= LogManager.getLogger(HomePageTests.class);
    private HomePage homePage;
    private SearchPage searchPage;

    @BeforeMethod
    public void setup()
    {
        TestBase.initialize();
        homePage = new HomePage();
        searchPage = new SearchPage();
    }

    @Test(priority = 1)
    public void printFlightCounts()
    {
        Date currentDate = new Date();
        Date returnDate = DateUtils.addDays(currentDate, 7);
        homePage.selectSection("Flights");
        homePage.waitForSearchButton();
        homePage.selectRoundTripOption();
        homePage.selectFromCity("Delhi");
        homePage.selectToCity("Bangalore");
        homePage.selectDepartureDate(DateUtils.addDays(currentDate, 1));
        homePage.selectReturnDate(returnDate);
        homePage.clickSearchButton();

        searchPage.resetStopsFilter();
        BrowserUtils.ScrollDown();
        System.out.println("Without Filter - departure flights options : " + searchPage.getDepartureFlightCounts());
        System.out.println("Without Filter - return flights options : " + searchPage.getReturnFlightCounts());
        BrowserUtils.scrollToTop();
        searchPage.resetStopsFilter();

        searchPage.resetStopsFilter();
        searchPage.selectNonStop();
        BrowserUtils.ScrollDown();
        System.out.println("Non Stop Filter - departure flights options : " + searchPage.getDepartureFlightCounts());
        System.out.println("Non Stop Filter - return flights options : " + searchPage.getReturnFlightCounts());
        BrowserUtils.scrollToTop();
        searchPage.resetStopsFilter();



        searchPage.resetStopsFilter();
        searchPage.selectSingleStop();
        BrowserUtils.ScrollDown();
        System.out.println("1 Stop Filter - departure flights options : " + searchPage.getDepartureFlightCounts());
        System.out.println("1 Stop Filter - return flights options : " + searchPage.getReturnFlightCounts());
        BrowserUtils.scrollToTop();
        searchPage.resetStopsFilter();
    }
    @AfterMethod
    public void end() {
        tearDown();
    }
}
