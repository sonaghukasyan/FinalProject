import com.google.common.io.Files;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductDetailsPage;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BaseTest {
    protected static WebDriver driver;
    protected static HomePage homePage;
    protected static LoginPage loginPage;
    protected static ProductDetailsPage productDetailsPage;

    @BeforeClass
    public static void setUp() {
        System.setProperty("chromedriver.exe", "drivers/chromedriver-win32/");
        driver = new ChromeDriver();
        driver.get("https://demo.nopcommerce.com/");
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        productDetailsPage = new ProductDetailsPage(driver);
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

    @AfterClass
    public static void tearDown(){
        driver.quit();
    }

    protected void navigateToHomePage(){
        driver.navigate().to("https://demo.nopcommerce.com/");
    }
}