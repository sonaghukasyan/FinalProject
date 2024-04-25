package pages;

import locators.HomePageLocators;
import locators.LoginPageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage extends BasePage{
    private By loginButton = By.className(HomePageLocators.loginButton);
    private By productSection = By.xpath(HomePageLocators.productSectionXPath);
    private By productItems = By.xpath(HomePageLocators.productItemsXPath);
    private final By logoutButton = By.xpath(HomePageLocators.logoutXPath);

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void navigateToLoginPage(){
        if(isLoggedIn()){
            logout();
        }
        click(loginButton);
    }

    public void scrollToProductSection(){
        scrollToElement(productSection);
    }

    public List<WebElement> getProductsList(){
        List<WebElement> itemsList = driver.findElements(productItems);
        return itemsList;
    }

    public void navigateToProductDetails(int productIndex){
        List<WebElement> itemsList = driver.findElements(productItems);
        WebElement productElement = itemsList.get(productIndex);
        click(productElement);
    }

    public boolean isLoggedIn(){
        try{
            driver.findElement(logoutButton);
            return true;
        }
        catch (Exception ex){
            return false;
        }
    }

    public void logout(){
        click(logoutButton);
    }
}
