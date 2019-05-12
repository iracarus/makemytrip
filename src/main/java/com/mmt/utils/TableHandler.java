package com.mmt.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class TableHandler {
    WebDriver driver;

    @BeforeMethod
    public void setup()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }
    @Test
    public void TableHandlerTest()
    {
        printValues(driver);
    }

    @Test
    public void TablerHanlderTestColumn()
    {
        printValues(driver, 1);
    }

    @Test
    public void TableHandlerTestColumnName()
    {
        printValues(driver, "Table heading 3");
    }

    public void printValues(WebDriver driver)
    {
        driver.get("https://www.seleniumeasy.com/test/table-pagination-demo.html");

        // webtable : //table[@class='table table-hover']
        // rows :
        // //table[@class='table table-hover']/tbody/tr[@style='display: table-row;'][1]/td[1]
        // //table[@class='table table-hover']/tbody/tr[@style='display: table-row;'][1]/td[2]
        // //table[@class='table table-hover']/tbody/tr[@style='display: table-row;'][1]/td[3]
        // //table[@class='table table-hover']/tbody/tr[@style='display: table-row;'][1]/td[4]

        List<WebElement> previousLink = driver.findElements(By.xpath("//a[@class='prev_link']"));
        if(previousLink.size() > 0)
            driver.findElement(By.xpath("//a[text()='1']")).click();

        boolean tableEnded = false;
        while(!tableEnded)
        {
            List<WebElement> nextLink = driver.findElements(By.xpath("//a[@class='next_link']"));


            WebElement table = driver.findElement(By.xpath("//table[@class='table table-hover']"));
            List<WebElement> rows = driver.findElements(By.xpath("//table[@class='table table-hover']/tbody/tr[@style='display: table-row;']"));
            List<WebElement> colomns = driver.findElements(By.xpath("//table[@class='table table-hover']/thead/tr/th"));

            for(int i = 1; i <= rows.size(); i++)
            {
                for (int j = 1; j <= colomns.size(); j++) {
                    String value = driver.findElement(By.xpath("//table[@class='table table-hover']/tbody/tr[@style='display: table-row;'][" + i + "]/td[" + j + "]")).getText();
                    System.out.print(value);
                    System.out.print(" ");
                }
                System.out.println();
            }

            try
            {
                if(driver.findElement(By.xpath("//a[@class='next_link']")).isDisplayed())
                {
                    driver.findElement(By.xpath("//a[@class='next_link']")).click();
                }
                else
                {
                    tableEnded = true;
                }
            }
            catch (NoSuchElementException ex)
            {
                tableEnded = true;
            }
        }
    }


    public void printValues(WebDriver driver, int columnIndex)
    {
        driver.get("https://www.seleniumeasy.com/test/table-pagination-demo.html");

        // webtable : //table[@class='table table-hover']
        // rows :
        // //table[@class='table table-hover']/tbody/tr[@style='display: table-row;'][1]/td[1]
        // //table[@class='table table-hover']/tbody/tr[@style='display: table-row;'][1]/td[2]
        // //table[@class='table table-hover']/tbody/tr[@style='display: table-row;'][1]/td[3]
        // //table[@class='table table-hover']/tbody/tr[@style='display: table-row;'][1]/td[4]

        List<WebElement> previousLink = driver.findElements(By.xpath("//a[@class='prev_link']"));
        if(previousLink.size() > 0)
            driver.findElement(By.xpath("//a[text()='1']")).click();

        boolean tableEnded = false;
        while(!tableEnded)
        {
            List<WebElement> nextLink = driver.findElements(By.xpath("//a[@class='next_link']"));


            WebElement table = driver.findElement(By.xpath("//table[@class='table table-hover']"));
            List<WebElement> rows = driver.findElements(By.xpath("//table[@class='table table-hover']/tbody/tr[@style='display: table-row;']"));
            List<WebElement> colomns = driver.findElements(By.xpath("//table[@class='table table-hover']/thead/tr/th"));

            for(int i = 1; i <= rows.size(); i++)
            {
                for (int j = 1; j <= colomns.size(); j++) {
                    if(j == columnIndex)
                    {
                        String value = driver.findElement(By.xpath("//table[@class='table table-hover']/tbody/tr[@style='display: table-row;'][" + i + "]/td[" + j + "]")).getText();
                        System.out.print(value);
                        System.out.print(" ");
                    }

                }
                System.out.println();
            }

            try
            {
                if(driver.findElement(By.xpath("//a[@class='next_link']")).isDisplayed())
                {
                    driver.findElement(By.xpath("//a[@class='next_link']")).click();
                }
                else
                {
                    tableEnded = true;
                }
            }
            catch (NoSuchElementException ex)
            {
                tableEnded = true;
            }
        }
    }


    public void printValues(WebDriver driver, String columnName)
    {
        driver.get("https://www.seleniumeasy.com/test/table-pagination-demo.html");

        // webtable : //table[@class='table table-hover']
        // rows :
        // //table[@class='table table-hover']/tbody/tr[@style='display: table-row;'][1]/td[1]
        // //table[@class='table table-hover']/tbody/tr[@style='display: table-row;'][1]/td[2]
        // //table[@class='table table-hover']/tbody/tr[@style='display: table-row;'][1]/td[3]
        // //table[@class='table table-hover']/tbody/tr[@style='display: table-row;'][1]/td[4]

        List<WebElement> previousLink = driver.findElements(By.xpath("//a[@class='prev_link']"));
        if(previousLink.size() > 0)
            driver.findElement(By.xpath("//a[text()='1']")).click();

        boolean tableEnded = false;
        while(!tableEnded)
        {
            List<WebElement> nextLink = driver.findElements(By.xpath("//a[@class='next_link']"));


            WebElement table = driver.findElement(By.xpath("//table[@class='table table-hover']"));
            List<WebElement> rows = driver.findElements(By.xpath("//table[@class='table table-hover']/tbody/tr[@style='display: table-row;']"));
            List<WebElement> colomns = driver.findElements(By.xpath("//table[@class='table table-hover']/thead/tr/th"));
            int columnIndex = getColumnIndex(colomns, columnName);
            System.out.println(columnIndex);
            for(int i = 1; i <= rows.size(); i++)
            {
                for (int j = 1; j <= colomns.size(); j++) {
                    if(j == columnIndex)
                    {
                        String value = driver.findElement(By.xpath("//table[@class='table table-hover']/tbody/tr[@style='display: table-row;'][" + i + "]/td[" + j + "]")).getText();
                        System.out.print(value);
                        System.out.print(" ");
                    }

                }
                System.out.println();
            }

            try
            {
                if(driver.findElement(By.xpath("//a[@class='next_link']")).isDisplayed())
                {
                    driver.findElement(By.xpath("//a[@class='next_link']")).click();
                }
                else
                {
                    tableEnded = true;
                }
            }
            catch (NoSuchElementException ex)
            {
                tableEnded = true;
            }
        }
    }

    public int getColumnIndex(List<WebElement> columns, String columnName)
    {
        int columnIndexFound = -1;
        for(int i =0; i<columns.size(); i++)
        {
            String currentColumnHeading = columns.get(i).getText();
            if(currentColumnHeading.equalsIgnoreCase(columnName))
            {
                columnIndexFound = i;
                break;
            }

        }

        return columnIndexFound;
    }
    @AfterMethod
    public void tearDown()
    {
        driver.quit();
    }
}
