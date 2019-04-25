package com.mmt.tests;

import com.mmt.base.ActionsClass;
import com.mmt.base.TestBase;
import com.mmt.pages.HomePage;
import com.mmt.pages.SearchPage;
import com.mmt.utils.BrowserUtils;
import com.mmt.utils.DateUtils;
import com.mmt.utils.OtherUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import java.util.Date;
import java.util.List;

public class SearchPageTests extends TestBase {
    private Logger logger4j= LogManager.getLogger(OtherUtils.padLeft("[" + SearchPageTests.class + "]", Integer.parseInt(props.getProperty("LOGS_PADDING"))));

    private SearchPage searchPage;
    private ActionsClass actionsClass;

    @BeforeTest
    public void setup()
    {
        TestBase.initialize();
        searchPage = new SearchPage();
        actionsClass = new ActionsClass();
    }

    @Test(priority = 3, dataProvider = "PerformSearch")
    public void printFlightCounts(String stopType, String departureCity, String arrivalCity, Date departureDate, Date returnDate) throws InterruptedException {
        logger4j.debug("Test Starting ------- printFlightsCount -------");
        actionsClass.performSearch(stopType, departureCity, arrivalCity, departureDate, returnDate);

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

        logger4j.debug("Test Ending ------- printFlightsCount -------");
    }

    @AfterTest
    public void end()
    {
        tearDown();
    }
}
