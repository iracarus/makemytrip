package com.mmt.base;

import com.mmt.pages.SearchPage;
import com.mmt.utils.DateUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * This is the base class for all the Tests to extend from
 */
public class TestBase {
    public static Properties props;
    public static WebDriver driver;

    public  SearchPage searchPage;
    public ActionsClass actionsClass;

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


    @BeforeMethod
    public void setup()
    {
        TestBase.initialize();
        searchPage = new SearchPage();
        actionsClass = new ActionsClass();
    }

    @AfterMethod
    public void end() {
        tearDown();
    }
    /**
     * Initializes all the basic components like driver, properties file etc
     * Takes care of the operating system to load relevant drivers/files
     */
    public static void initialize()
    {
        ChromeOptions options = new ChromeOptions();


        if(props.getProperty("browser").equalsIgnoreCase("chrome"))
        {
            options.addArguments("--disable-notifications");
            options.addArguments("--incognito");

            //DesiredCapabilities capabs = DesiredCapabilities.chrome();
            //capabs.setCapability(ChromeOptions.CAPABILITY, options);

            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);
        }
        else
        {
            WebDriverManager.firefoxdriver().setup();
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
        return new Object[][]  {{"", "Delhi", "Bangalore", new Date(), DateUtils.addDays(new Date(), 7)}};
    }

    /**
     * Had to create a separate data provided in order to perform multiple searches for different stop filters
     * @return  Object[][]
     */
    @DataProvider(name="PerformSearchMulti")
    public static Object[][] searchOptionsMulti() {
        return new Object[][]  {{"", "Delhi", "Bangalore", new Date(), DateUtils.addDays(new Date(), 7)},
                                {"nonstop", "Delhi", "Bangalore", new Date(), DateUtils.addDays(new Date(), 7)},
                                {"singlestop", "Delhi", "Bangalore", new Date(), DateUtils.addDays(new Date(), 7)}
        };
    }
}
