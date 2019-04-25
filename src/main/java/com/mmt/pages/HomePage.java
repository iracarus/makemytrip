package com.mmt.pages;

import com.mmt.base.TestBase;
import com.mmt.utils.BrowserUtils;
import com.mmt.utils.DateUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Date;
import java.util.List;

/**
 * Home Page Class
 */
public class HomePage extends TestBase {
    SearchPage searchPage;

    @FindBy(xpath="//nav/ul/li")
    WebElement headerOptions;

    @FindBy(xpath = "//a[text()='Search']")
    WebElement searchButton;

    @FindBy(xpath = "//text()[.='Round Trip']/preceding-sibling::span")
    WebElement roundTripOption;

    @FindBy(xpath = "//label[@for='fromCity']")
    WebElement fromCityElement;

    @FindBy(xpath = "//label[@for='toCity']")
    WebElement toCityElement;

    @FindAll(@FindBy(xpath="//ul[@class='react-autosuggest__suggestions-list']/li"))
    List<WebElement> toCitySuggestions;

    public HomePage() {
        PageFactory.initElements(driver, this);
        searchPage = new SearchPage();
    }

    /**
     * <h1>Select a section on home page!</h1>
     * @author  Raj Chahal
     * @version 1.0
     * @since   2019-04-22
     * @param sectionName The main section to navigate to ( currently covers only flights  section
     */
    public void selectSection(String sectionName)
    {
        List<WebElement> sectionList = driver.findElements(By.xpath("//nav/ul/li"));
        for(int i = 0; i<sectionList.size(); i++)
        {
            WebElement sectionElement = sectionList.get(i).findElement(By.xpath(".//span[contains(@class,'NavText')]"));
            if(sectionElement.getText().equalsIgnoreCase(sectionName))
            {
                sectionElement.click();
                break;
            }
        }

        waitForSearchButton();
    }

    /**
     * Wait for the search button to be available on the home page
     */
    public void waitForSearchButton()
    {
        WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(props.getProperty("DEFAULT_EXPLICITWAIT_TIME")));
        searchButton = wait.until(ExpectedConditions.visibilityOf(searchButton));
    }

    /**
     * Select the round trip option on home page
     */
    public void selectRoundTripOption() {
        WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(props.getProperty("DEFAULT_EXPLICITWAIT_TIME")));
        roundTripOption = wait.until(ExpectedConditions.elementToBeClickable(roundTripOption));
        roundTripOption.click();
    }

    /**
     * Select the Departure City
     * @param fromCity  Select city as the Departure City
     */
    public void selectFromCity(String fromCity) throws InterruptedException {
        fromCityElement.click();
        WebElement editBox = driver.findElement(By.xpath("//input[@placeholder='From']"));
        editBox.sendKeys(fromCity);
        Thread.sleep(5000);

        List<WebElement> fromCitySuggestions = driver.findElements(By.xpath("//ul[@class='react-autosuggest__suggestions-list']/li"));
        for (int i=0; i<fromCitySuggestions.size(); i++)
        {
            WebElement currentSuggestion = fromCitySuggestions.get(i).findElement(By.xpath(".//p"));
            if(currentSuggestion.getText().equalsIgnoreCase(fromCity+ ", India"))
            {
                fromCitySuggestions.get(i).findElement(By.xpath("./div/div")).click();
                break;
            }
        }
    }

    /**
     * Select the Arrival City
     * @param toCity this is the arrival city
     */
    public void selectToCity(String toCity) throws InterruptedException {
        WebElement editBox = driver.findElement(By.xpath("//input[@placeholder='To']"));
        if(!editBox.isDisplayed())
        {
            WebDriverWait waitElement = new WebDriverWait(driver, Long.parseLong(props.getProperty("DEFAULT_EXPLICITWAIT_TIME")));
            waitElement.until(ExpectedConditions.elementToBeClickable(toCityElement));
            toCityElement.click();
        }

        editBox.sendKeys(toCity);
        Thread.sleep(5000);

        for(int i=0; i<toCitySuggestions.size(); i++)
        {
            WebElement currentSuggestion = toCitySuggestions.get(i).findElement(By.xpath(".//p"));
            if(currentSuggestion.getText().equalsIgnoreCase(toCity + ", India"))
            {
                toCitySuggestions.get(i).findElement(By.xpath("./div/div")).click();
                break;
            }
        }
    }

    /**
     * Select the Departure Date
     * @param departureDate select the departure date
     */
    public void selectDepartureDate(Date departureDate)
    {
        List<WebElement> currentMonthWeeks = driver.findElements(By.xpath("//div[@class='DayPicker-Month'][1]/div[@class='DayPicker-Body']/div"));
        for(int i=0; i<currentMonthWeeks.size(); i++)
        {
            List<WebElement> currentWeekDays = currentMonthWeeks.get(i).findElements(By.xpath("./div[contains(@class, 'DayPicker-Day')]"));
            for(int j=0; j< currentWeekDays.size(); j++)
            {
                String currentDate = currentWeekDays.get(j).getAttribute("aria-label");
                if(currentDate.equalsIgnoreCase(DateUtils.convertDateToFormat(departureDate, "E MMM dd yyyy")))
                {
                    currentWeekDays.get(j).click();
                    break;
                }
            }
        }
    }


    /**
     * Select the return date
     * @param returnDate    select the return date
     */
    public void selectReturnDate(Date returnDate)
    {
        List<WebElement> currentMonthWeeks = driver.findElements(By.xpath("//div[@class='DayPicker-Month'][1]/div[@class='DayPicker-Body']/div"));
        for(int i=0; i<currentMonthWeeks.size(); i++)
        {
            List<WebElement> currentWeekDays = currentMonthWeeks.get(i).findElements(By.xpath("./div[contains(@class, 'DayPicker-Day')]"));

            for(int j=0; j< currentWeekDays.size(); j++)
            {
                String currentDate = currentWeekDays.get(j).getAttribute("aria-label");
                if(currentDate.equalsIgnoreCase(DateUtils.convertDateToFormat(returnDate, "E MMM dd yyyy")))
                {
                    currentWeekDays.get(j).click();
                    break;
                }
            }
        }
    }

    /**
     * Click the search button after filling in the flight details
     */
    public void clickSearchButton() {
        BrowserUtils.jsClick(searchButton);
    }
}
