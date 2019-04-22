package com.mmt.tests;

import com.mmt.base.TestBase;
import com.mmt.pages.HomePage;
import com.mmt.pages.SearchPage;
import com.mmt.utils.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Date;

public class HomePageTests extends TestBase {
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

    @Test
    public void verifyTitle()
    {
        //logger = extent.createTest("To verify title");
        //logger = extent.createTest("To verify Google Title");
        String title = TestBase.driver.getTitle();
        logger4j.info(title);
        Assert.assertTrue(title.contains("MakeMyTip"));
    }

    @Test
    public void verifyFlightsActive()
    {

    }

    @Test
    public void selectSectionAsFlight() {
        Date currentDate = new Date();
        Date returnDate = DateUtils.addDays(currentDate, 7);
        homePage.selectSection("Flights");
        homePage.waitForSearchButton();
        homePage.selectRoundTripOption();
        homePage.selectFromCity("Delhi");
        homePage.selectToCity("Bangalore");
        homePage.selectDepartureDate(currentDate);
        homePage.selectReturnDate(returnDate);
        homePage.clickSearchButton();

        searchPage.resetStopsFilter();

        searchPage.selectNonStop();
        searchPage.resetStopsFilter();
        searchPage.selectSingleStop();

    }


    @AfterMethod
    public void end() {
        tearDown();
    }
}
