import com.google.common.io.Files;
import org.junit.AfterClass;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductDetailsPage;

import java.io.File;
import java.io.IOException;
import org.testng.ITestResult;

public class BaseTest {
    protected static WebDriver driver;
    protected static HomePage homePage;
    protected static LoginPage loginPage;
    protected static ProductDetailsPage productDetailsPage;

    @BeforeAll
    public static void setUp() {
        System.setProperty("chromedriver.exe", "drivers/chromedriver-win32/");
        driver = new ChromeDriver();
        driver.get("https://demo.nopcommerce.com/");
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        productDetailsPage = new ProductDetailsPage(driver);
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

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    protected void navigateToHomePage(){
        driver.navigate().to("https://demo.nopcommerce.com/");
    }
}