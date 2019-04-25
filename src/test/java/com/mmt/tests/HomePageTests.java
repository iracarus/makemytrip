package com.mmt.tests;

import com.mmt.base.ActionsClass;
import com.mmt.base.TestBase;
import com.mmt.pages.SearchPage;
import com.mmt.utils.OtherUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Date;

public class HomePageTests extends TestBase {
    private SearchPage searchPage;
    private ActionsClass actionsClass;
    private Logger logger4j= LogManager.getLogger(OtherUtils.padLeft("[" + HomePageTests.class + "]", 40));

    @BeforeMethod
    public void setup()
    {
        logger4j.debug("About to setup - HomePageTests");
        TestBase.initialize();
        actionsClass = new ActionsClass();
        searchPage = new SearchPage();
        logger4j.debug("About to setup - HomePageTests");
    }

    @Test(priority = 1)
    public void verifyTitle()
    {
        logger4j.debug("Test Starting ------- verifyTitle -------");
        String title = TestBase.driver.getTitle();
        Assert.assertTrue(title.contains("MakeMyTrip"));
        logger4j.info("Title of the Home Page is : " + title);
        logger4j.debug("Test Ending ------- verifyTitle -------");
    }


    @Test(priority = 2, dataProvider = "PerformSearch", enabled = false)
    public void searchFlights(String stopType, String departureCity, String arrivalCity, Date departureDate, Date returnDate) throws InterruptedException {
        logger4j.debug("Test Starting ------- searchFlights -------");
        actionsClass.performSearch(stopType, departureCity, arrivalCity, departureDate, returnDate);
        Assert.assertTrue(searchPage.isAt());
        logger4j.info("Succefully searched for the flight.");
        logger4j.debug("Test Ending ------- searchFlights -------");
    }

    @AfterMethod
    public void end() {
        tearDown();
    }

}
