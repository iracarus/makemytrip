import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HomePageTests extends TestBase {

    @BeforeClass
    public void setup()
    {
        initialize();
    }

    @Test
    public void verifyTitle()
    {
        String title = driver.getTitle();
        System.out.println(title);
        Assert.assertTrue(title.contains("MakeMyTrip"));
    }

    @AfterClass
    public void end()
    {
        tearDown();
    }
}
