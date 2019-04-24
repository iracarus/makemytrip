package com.mmt.tests;

import com.mmt.base.ActionsClass;
import com.mmt.base.TestBase;
import com.mmt.pages.SearchPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Date;

public class HomePageTests extends TestBase {
    private SearchPage searchPage;
    private ActionsClass actionsClass;
    public Logger logger4j= LogManager.getLogger("Automation Framework");

    @BeforeTest
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
        logger4j.debug("Test Starting ------- verifyTitle");
        String title = TestBase.driver.getTitle();
        logger4j.info("Title of the Home Page is : " + title);
        Assert.assertTrue(title.contains("MakeMyTrip"));
        logger4j.debug("End Test ---- VerifyTitle");
    }


    @Test(priority = 2, dataProvider = "PerformSearch")
    public void searchFlights(String stopType, String departureCity, String arrivalCity, Date departureDate, Date returnDate) throws InterruptedException {
        logger4j.debug("Startig Test --------- searchFlights");
        logger4j.debug("About to start Perform Search.");
        actionsClass.performSearch(stopType, departureCity, arrivalCity, departureDate, returnDate);
        logger4j.debug("ended the perform search action");
        Assert.assertTrue(searchPage.isAt());
        logger4j.info("Succefully searched for the flight.");
    }

    @AfterTest
    public void end() {
        logger4j.debug("About to end the test ---- home page tests class");
        tearDown();
        logger4j.debug("Ended the test for ----- home page tests class");
    }
}
