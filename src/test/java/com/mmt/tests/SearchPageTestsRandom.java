package com.mmt.tests;

import com.mmt.base.ActionsClass;
import com.mmt.base.TestBase;
import com.mmt.pages.HomePage;
import com.mmt.pages.SearchPage;
import com.mmt.utils.BrowserUtils;
import com.mmt.utils.OtherUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

import java.sql.SQLOutput;
import java.util.Date;

public class SearchPageTestsRandom extends TestBase {
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

    @Test
    public void verifyTotalAmount() {
        String stopType = "";
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
        searchPage.selectDepartureFlight(4);
        searchPage.selectReturnFlight(7);
        int expTotalCost = searchPage.getDepartureFlightCost() + searchPage.getReturnFlightCost();
        int actualTotalCost = searchPage.getTotalCost();

        logger4j.info("Expected : " + expTotalCost + ", Actual : " + actualTotalCost);
        Assert.assertEquals(actualTotalCost, expTotalCost);
    }

    @Test
    public void test(){
        String str1 = "Rs 8,824";
        String str2 = "Rs 7,326";

        String str3 = str1.replace("Rs ", "");
        String str4 = str3.replace(",", "");

        String str5 = str2.replace("Rs ", "");
        String str6 = str5.replace(",", "");
        int j = Integer.parseInt(str6);
        int i = Integer.parseInt(str4);

        System.out.println(i+j);
    }

    @AfterTest
    public void end()
    {
        tearDown();
    }
}
