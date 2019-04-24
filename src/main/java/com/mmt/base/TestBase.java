package com.mmt.base;

import com.mmt.utils.DateUtils;
import com.mmt.utils.OtherUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;

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
        logger4j.info("*Constructor* Begins");
        FileInputStream inputFile;
        props = new Properties();
        try {
            inputFile = new FileInputStream(System.getProperty("user.dir") +
                    File.separator + "src" + File.separator + "main" + File.separator + "resources" +File.separator+ "environment.properties");

            props.load(inputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger4j.info("*Constructor* Ends");
    }

    /**
     * Initializes all the basic components like driver, properties file etc
     * Takes care of the operating system to load relevant drivers/files
     */
    public static void initialize()
    {
        logStatic.info("*initialize* Begins");
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
        logStatic.info("*initialize* Driver Created");
        driver.get(props.getProperty("url"));
        logStatic.info("*initialize* Ends");
    }

    /**
     * Quits the driver to close the browser
     */
    public void tearDown()
    {
        logger4j.info("*tearDown* Begins");
        driver.quit();
        logger4j.info("*tearDown* Ends");
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
