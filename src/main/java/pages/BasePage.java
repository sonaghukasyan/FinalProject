package pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {
    protected final WebDriver driver;
    private WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));
    }

    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void click(WebElement w) {
        wait.until(ExpectedConditions.elementToBeClickable(w)).click();
    }

    protected void scrollToElement(By locator) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(locator));
    }

    public WebElement waitForVisibilityOfElementLocated(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            throw new RuntimeException("Element located by '" + locator + "' was not visible.");
        }
    }

    public boolean isDisplayed(By locator){
        try{
            WebElement element = waitForVisibilityOfElementLocated(locator);
            return element != null && element.isDisplayed();
        }
        catch (Exception ex){
            return false;
        }
    }
}
