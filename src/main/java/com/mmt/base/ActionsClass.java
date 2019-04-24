package com.mmt.base;

import com.mmt.pages.HomePage;
import com.mmt.pages.SearchPage;
import com.mmt.utils.DateUtils;

import java.util.Date;

public class ActionsClass {
    HomePage homePage;
    SearchPage searchPage;
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

        return searchPage.isAt();
    }
}
