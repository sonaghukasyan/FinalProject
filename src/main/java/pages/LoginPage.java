package pages;

import locators.LoginPageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.NoSuchElementException;

public class LoginPage extends BasePage{
    private final By emailField = By.id(LoginPageLocators.emailFieldId);;
    private final By passwordField= By.id(LoginPageLocators.pswFieldId);
    private final By loginButton = By.cssSelector(LoginPageLocators.loginButtonCSS);
    private final By errorMessage = By.cssSelector(LoginPageLocators.loginErrorCCS);

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void setEmail(String email) {
        WebElement mailEl = driver.findElement(emailField);
        mailEl.clear();
        mailEl.sendKeys(email);
    }

    public void setPassword(String password) {
        WebElement mailEl = driver.findElement(passwordField);
        mailEl.clear();
        mailEl.sendKeys(password);;
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void login(String email, String password){
        setEmail(email);
        setPassword(password);
        clickLoginButton();
    }

    public boolean isLoginPageDisplayed() {
        try {
            // If all elements are displayed, return true
            return driver.findElement(emailField).isDisplayed()
                    && driver.findElement(passwordField).isDisplayed()
                    && driver.findElement(loginButton).isDisplayed();
        } catch (NoSuchElementException e) {
            // If any element is not displayed, return false
            return false;
        }
    }

    public boolean isErrorMessageDisplayed() {
        try{
            WebElement element = driver.findElement(errorMessage);
            return element != null && element.isDisplayed();
        }
        catch (Exception ex){
            return false;
        }
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }
}
