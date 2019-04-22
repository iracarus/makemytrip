package tests;

import com.mmt.base.TestBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HomePageTests extends TestBase {
    Logger logger = LogManager.getLogger(HomePageTests.class);

    @BeforeClass
    public void setup()
    {
        TestBase.initialize();
    }

    @Test
    public void verifyTitle()
    {
        String title = TestBase.driver.getTitle();
        logger.info(title);
        Assert.assertTrue(title.contains("MakeMyTrip"));
    }

    @Test
    public void verifyFlightsActive()
    {
        driver.
    }

    @AfterClass
    public void end()
    {
        tearDown();
    }
}
