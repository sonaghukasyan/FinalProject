import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import pages.HomePage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class BaseTest {
    protected static WebDriver driver;
    protected static HomePage homePage;

    @BeforeTest
    public static void setUp() throws MalformedURLException {
       // ChromeOptions options = new ChromeOptions();
        //EdgeOptions options = new EdgeOptions();
        //FirefoxOptions options = new FirefoxOptions();
       // String gridHubUrl = "http://localhost:4444/wd/hub";
       // driver = new RemoteWebDriver(new URL(gridHubUrl), options);
       // driver.get("https://demo.nopcommerce.com/");
        //homePage = new HomePage(driver);
        System.setProperty("chromedriver.exe", "drivers/chromedriver-win32/");
        driver = new ChromeDriver();
        driver.get("https://demo.nopcommerce.com/");
        homePage = new HomePage(driver);
    }

    @AfterMethod
    public void recordFailure(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            var camera = (TakesScreenshot)driver;
            File screenshot = camera.getScreenshotAs(OutputType.FILE);
            try {
                Files.move(screenshot, new File("resources/screenshots/" + result.getName() + ".png"));
            }
            catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    @AfterTest
    public void tearDown(){
        driver.close();
        driver.quit();
    }

    protected void navigateToHomePage(){
        driver.navigate().to("https://demo.nopcommerce.com/");
    }
}