package com.mmt.pages;

import com.mmt.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchPage extends TestBase {
    @FindBy(xpath = "//div[@id='fli_filter__stops']//span[text()='Stops']/following-sibling::a[text()='Reset']")
    WebElement stopsReset;

    @FindBy(xpath = "//label[@for='filter_stop0']//span[@class='box']")
    WebElement nonStopCheckBox;

    @FindBy(xpath = "//label[@for='filter_stop1']//span[@class='box']")
    WebElement singleStop;

    @FindBy(xpath = "//div[@id='ow_domrt-jrny']")
    WebElement departureFlightsSection;

    @FindBy(xpath = "//div[@id='rt-domrt-jrny']")
    WebElement returnflightsSection;

    @FindAll(@FindBy(xpath="//div[@id='ow_domrt-jrny']//div[@class='fli-list splitVw-listing']"))
    List<WebElement> departureFlightOptions;

    @FindAll(@FindBy(xpath="//div[@id='rt-domrt-jrny']//div[@class='fli-list splitVw-listing']"))
    List<WebElement> returnFlightOptions;

    public SearchPage()
    {
        PageFactory.initElements(driver, this);
    }
    public void resetStopsFilter() {
        WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(props.getProperty("DEFAULT_EXPLICITWAIT_TIME")));
        wait.until(ExpectedConditions.elementToBeClickable(stopsReset));
        stopsReset.click();
    }

    public void selectNonStop()
    {
        WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(props.getProperty("DEFAULT_EXPLICITWAIT_TIME")));
        wait.until(ExpectedConditions.visibilityOf(nonStopCheckBox));
        nonStopCheckBox.click();
    }

    public void selectSingleStop()
    {
        WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(props.getProperty("DEFAULT_EXPLICITWAIT_TIME")));
        wait.until(ExpectedConditions.visibilityOf(singleStop));
        singleStop.click();
    }

    public int getDepartureFlightCounts() {
        return departureFlightOptions.size();
    }

    public int getReturnFlightCounts() {
        return returnFlightOptions.size();
    }

    public boolean isAt() {
        return (departureFlightsSection.isDisplayed() && returnflightsSection.isDisplayed());
    }
}
