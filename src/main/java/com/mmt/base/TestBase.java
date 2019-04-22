package com.mmt.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {
    public static Properties props;
    public static WebDriver driver;
    private static String OS = System.getProperty("os.name").toLowerCase();


    public TestBase() {
        FileInputStream inputFile;
        props = new Properties();
        try {
            inputFile = new FileInputStream(System.getProperty("user.dir") +
                    File.separator + "src" + File.separator + "main" + File.separator + "resources" +File.separator+ "environment.properties");

            props.load(inputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void initialize()
    {
        ChromeOptions options = new ChromeOptions();

        String osPart, exePart;
        if(OS.contains("mac")) {
            osPart = "MAC";
            exePart = "";
        }
        else
        {
            osPart = "WIN";
            exePart = ".exe";
        }


        if(props.getProperty("browser").equalsIgnoreCase("chrome"))
        {
            options.addArguments("--disable-notifications");
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separator + "BrowserDrivers" +File.separator+osPart+ File.separator + "chromedriver" + exePart);
        }
        else
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + File.separator + "BrowserDrivers" +File.separator+osPart+ File.separator + "geckodriver" + exePart);

        String browserName = props.getProperty("browser");

        if(browserName.equalsIgnoreCase("chrome"))
        {
            driver = new ChromeDriver(options);
        }
        else if(browserName.equalsIgnoreCase("firefox"))
        {
            driver = new FirefoxDriver();
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(Long.parseLong(props.getProperty("PAGE_LOAD_TIMEOUT")), TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(Long.parseLong(props.getProperty("IMPLICITLY_WAIT")), TimeUnit.SECONDS);

        driver.get(props.getProperty("url"));
    }

    public void tearDown()
    {
        driver.quit();
    }
}
