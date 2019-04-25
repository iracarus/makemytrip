package com.mmt.base;

import com.mmt.pages.HomePage;
import com.mmt.pages.SearchPage;
import com.mmt.utils.DateUtils;
import com.mmt.utils.OtherUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;

public class ActionsClass {
    HomePage homePage;
    SearchPage searchPage;

    private Logger logger4j= LogManager.getLogger(OtherUtils.padLeft("[" + ActionsClass.class + "]", 40) );

    public ActionsClass()
    {
        homePage = new HomePage();
        searchPage = new SearchPage();
    }

    public boolean performSearch(String tripType, String departureCity, String arrivalCity, Date depDate, Date retDate) throws InterruptedException {
        Date currentDate = new Date();
        Date returnDate = DateUtils.addDays(currentDate, 7);
        homePage.selectSection("Flights");
        homePage.waitForSearchButton();
        homePage.selectRoundTripOption();
        homePage.selectFromCity(departureCity);
        homePage.selectToCity(arrivalCity);
        homePage.selectDepartureDate(DateUtils.addDays(currentDate, 1));
        homePage.selectReturnDate(returnDate);
        homePage.clickSearchButton();
        logger4j.info("*performSearch* Ends");
        return searchPage.isAt();
    }
}