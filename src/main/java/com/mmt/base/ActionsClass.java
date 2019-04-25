package com.mmt.base;

import com.mmt.pages.HomePage;
import com.mmt.pages.SearchPage;
import com.mmt.utils.DateUtils;
import com.mmt.utils.OtherUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;

/**
 * This is class is the wrapper class for all the main actions ( only perform search is the main action covered for now )
 */
public class ActionsClass {
    HomePage homePage;
    SearchPage searchPage;

    public ActionsClass()
    {
        homePage = new HomePage();
        searchPage = new SearchPage();
    }

    /**
     * This is a wrapper method to contain all the steps related to perform search at one place
     * @param tripType  Trip Type i.e. Round Trip or one way ( currently only Round Trip is supported )
     * @param departureCity Departure City
     * @param arrivalCity   Arrival City
     * @param depDate   Departure Date
     * @param retDate   Return Date
     * @return  Boolean returns if the search was performed successfully of not
     * @throws InterruptedException
     */
    public boolean performSearch(String tripType, String departureCity, String arrivalCity, Date depDate, Date retDate) throws InterruptedException {
        homePage.selectSection(tripType);
        homePage.waitForSearchButton();
        homePage.selectRoundTripOption();
        homePage.selectFromCity(departureCity);
        homePage.selectToCity(arrivalCity);
        homePage.selectDepartureDate(DateUtils.addDays(depDate, 1));
        homePage.selectReturnDate(retDate);
        homePage.clickSearchButton();
        return searchPage.isAt();
    }
}