package com.mmt.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.mmt.pages.SearchPage;
import com.mmt.utils.DateUtils;
import com.mmt.utils.OtherUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {
    public static Properties props;
    public static WebDriver driver;

    private static String OS = System.getProperty("os.name").toLowerCase();

    private static final Logger logger4j= LogManager.getLogger(OtherUtils.padLeft("[" + TestBase.class + "]", 40) );
    private static Logger logStatic = LogManager.getLogger(OtherUtils.padLeft("[" + TestBase.class + "]", 40) );
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

    /**
     * Initializes all the basic components like driver, properties file etc
     * Takes care of the operating system to load relevant drivers/files
     */
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

    /**
     * Quits the driver to close the browser
     */
    public void tearDown()
    {
        driver.quit();
    }



    /**
     * This is data provider that drives the tests
     * @return Returns a Object[][] object
     */
    @DataProvider(name="PerformSearch")
    public static Object[][] searchOptions() {
        return new Object[][]  {{"RoundTrip", "Delhi", "Bangalore", new Date(), DateUtils.addDays(new Date(), 7)}};
    }
}
