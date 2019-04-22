package com.mmt.pages;

import com.mmt.base.TestBase;
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

public class HomePage extends TestBase {
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

    @FindBy(xpath = "//p[contains(text(),', India')]/parent::div/parent::div")
    WebElement toCitySuggestion;

    @FindAll(@FindBy(xpath="//ul[@class='react-autosuggest__suggestions-list']/li"))
    List<WebElement> toCitySuggestions;

    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    /**
     * <h1>Select a section on home page!</h1>
     * @author  Raj Chahal
     * @version 1.0
     * @since   2019-04-22
     * @param sectionName
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

    public void waitForSearchButton()
    {
        WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(props.getProperty("DEFAULT_EXPLICITWAIT_TIME")));
        searchButton = wait.until(ExpectedConditions.visibilityOf(searchButton));
    }

    public void selectRoundTripOption() {
        WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(props.getProperty("DEFAULT_EXPLICITWAIT_TIME")));
        roundTripOption = wait.until(ExpectedConditions.elementToBeClickable(roundTripOption));
        roundTripOption.click();
    }

    public void selectFromCity(String fromCity)
    {
        fromCityElement.click();
        WebElement editBox = driver.findElement(By.xpath("//input[@placeholder='From']"));
        editBox.sendKeys(fromCity);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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

    public void selectToCity(String toCity)
    {
        WebElement editBox = driver.findElement(By.xpath("//input[@placeholder='To']"));
        if(!editBox.isDisplayed())
        {
            WebDriverWait waitElement = new WebDriverWait(driver, Long.parseLong(props.getProperty("DEFAULT_EXPLICITWAIT_TIME")));
            waitElement.until(ExpectedConditions.elementToBeClickable(toCityElement));
            toCityElement.click();
        }

        editBox.sendKeys(toCity);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //List<WebElement> toCitySuggestions = driver.findElements(By.xpath("//ul[@class='react-autosuggest__suggestions-list']/li"));
        for (int i=0; i<toCitySuggestions.size(); i++)
        {
            WebElement currentSuggestion = toCitySuggestions.get(i).findElement(By.xpath(".//p"));
            if(currentSuggestion.getText().equalsIgnoreCase(toCity + ", India"))
            {
                toCitySuggestions.get(i).findElement(By.xpath("./div/div")).click();
                break;
            }
        }
    }

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

    public void clickSearchButton() {
        searchButton.click();
    }
}
