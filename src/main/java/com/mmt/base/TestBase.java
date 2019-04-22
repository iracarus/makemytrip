import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separator + "BrowserDrivers" +File.separator+osPart+ File.separator + "chromedriver" + exePart);
        else
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + File.separator + "BrowserDrivers" +File.separator+osPart+ File.separator + "geckodriver" + exePart);

        String browserName = props.getProperty("browser");

        if(browserName.equalsIgnoreCase("chrome"))
        {
            driver = new ChromeDriver();
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
