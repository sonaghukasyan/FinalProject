package pages;

import locators.SearchResultPageLocators;
import locators.ShoppingCartLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ShoppingCartPage extends BasePage{
    private By emptyCartMessage = By.cssSelector(ShoppingCartLocators.cartEmptyCSS);
    private By productNameLocator = By.xpath(ShoppingCartLocators.productNameXPath);
    private By productPriceLocator = By.cssSelector(ShoppingCartLocators.productPriceCSS);
    private By productQuantity = By.cssSelector(ShoppingCartLocators.productQuantityCSS);
    private By subtotalPrice = By.xpath(ShoppingCartLocators.subtotalPriceXPath);
    private By totalPrice = By.xpath(ShoppingCartLocators.totalPriceXPath);
    private By removeFromCartButton = By.xpath(ShoppingCartLocators.removeItemFromCartXPath);

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    public void emptyTheCart(){
        if(!isCartEmpty()){
            List<WebElement> itemsList = driver.findElements(removeFromCartButton);
            for (WebElement item : itemsList) {
                click(item);
            }
        }
    }

    public boolean isCartEmpty(){
        return isDisplayed(emptyCartMessage);
    }

    public int getNumberOfItemsInCart(){
        List<WebElement> itemsList = driver.findElements(productNameLocator);
        return  itemsList.size();
    }

    public String getProductName(int productIndex){
        List<WebElement> itemsList = driver.findElements(productNameLocator);
        WebElement productElement = itemsList.get(productIndex);
        return productElement.getText();
    }

    public double getProductPrice(int productIndex){
        List<WebElement> itemsList = driver.findElements(productPriceLocator);
        WebElement priceElement = itemsList.get(productIndex);
        String priceText = priceElement.getText().replaceAll("[^0-9.]", "");
        return Double.parseDouble(priceText);
    }

    public int getProductQuantity(int productIndex){
        // Locate the input element by its class and within the parent div
        List<WebElement> itemsList = driver.findElements(productQuantity);
        WebElement quantityInput = itemsList.get(productIndex);
        return Integer.parseInt(quantityInput.getAttribute("value"));
    }

    public double getSubTotalPrice(int productIndex){
        // Locate the input element by its class and within the parent div
        List<WebElement> itemsList = driver.findElements(subtotalPrice);
        WebElement subtotalPriceElement = itemsList.get(productIndex);
        // Remove non-numeric characters
        String priceText = subtotalPriceElement.getText().replaceAll("[^0-9.]", "");
        return Double.parseDouble(priceText);
    }

    public double getTotalPrice(){
        // Locate the input element by its class and within the parent div
        WebElement totalPriceElement = driver.findElement(totalPrice);
        // Remove non-numeric characters
        String priceText = totalPriceElement.getText().replaceAll("[^0-9.]", "");
        return Double.parseDouble(priceText);
    }
}
