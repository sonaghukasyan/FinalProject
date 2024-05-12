package pages;

import locators.HomePageLocators;
import locators.ProductDetailsLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class HomePage extends BasePage{
    private By productSection = By.xpath(HomePageLocators.productSectionXPath);
    private By productItems = By.xpath(HomePageLocators.productItemsXPath);
    private By searchTextField = By.cssSelector(HomePageLocators.searchFieldCSS);
    private By searchButton = By.xpath(HomePageLocators.searchButtonXPath);
    private By cartButton = By.cssSelector(HomePageLocators.shoppingCartCSS);
    private By productTitle = By.cssSelector(HomePageLocators.productTitle);

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void scrollToProductSection(){
        scrollToElement(productSection);
    }

    public List<WebElement> getProductsList(){
        List<WebElement> itemsList = driver.findElements(productItems);
        return itemsList;
    }

    public ProductDetailsPage navigateToProductDetails(int productIndex){
        List<WebElement> itemsList = driver.findElements(productItems);
        WebElement productElement = itemsList.get(productIndex);
        click(productElement);
        return new ProductDetailsPage(driver);
    }

    public void setSearchText(String searchText) {
        WebElement searchEl = driver.findElement(searchTextField);
        searchEl.clear();
        searchEl.sendKeys(searchText);
    }

    public void clickSearchButton(){
        click(searchButton);
    }

    public SearchResultPage performSearch(String keyword) {
        setSearchText(keyword);
        clickSearchButton();
        return new SearchResultPage(driver);
    }

    public ProductDetailsPage navigateToProductDetailsWithUrl(String url){
        driver.navigate().to(url);
        return new ProductDetailsPage(driver);
    }

    public ShoppingCartPage navigateToShoppingCart(){
        waitForVisibilityOfElementLocated(cartButton);
        click(cartButton);
        return new ShoppingCartPage(driver);
    }
}
