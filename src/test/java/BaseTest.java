import com.google.common.io.Files;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import pages.HomePage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {
    protected static WebDriver driver;
    protected static HomePage homePage;

    @BeforeClass
    public static void setUp() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        //EdgeOptions options = new EdgeOptions();
        //FirefoxOptions options = new FirefoxOptions();
        String gridHubUrl = "http://localhost:4444/wd/hub";
        driver = new RemoteWebDriver(new URL(gridHubUrl), options);
        driver.get("https://demo.nopcommerce.com/");
        homePage = new HomePage(driver);
    }

    @After
    public void recordFailure() {
        Description description = Description.createTestDescription(BaseTest.class, "recordFailure");

        var result = driver instanceof TakesScreenshot ? (TakesScreenshot) driver : null;
        if (result != null) {
            File screenshot = result.getScreenshotAs(OutputType.FILE);
            try {
                Files.move(screenshot, new File("resources/screenshots/" + description.getMethodName() + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected void navigateToHomePage(){
        driver.navigate().to("https://demo.nopcommerce.com/");
    }
}