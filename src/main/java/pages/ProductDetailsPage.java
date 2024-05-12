package pages;

import locators.ProductDetailsLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductDetailsPage extends BasePage{
    private final By notificationBar = By.xpath(ProductDetailsLocators.notifBarXPath);
    private final By notificationBarClose = By.cssSelector(ProductDetailsLocators.notifBarClose);
    private final By productNameElement = By.cssSelector(ProductDetailsLocators.productNameCSS);

    private String productId;
    private By addToCartButton;
    private By productQuantityField;
    private By productPriceField;

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
        initProductFields();
    }

    public void initProductFields(){
        productId = driver.findElement(By.xpath(ProductDetailsLocators.dataProductXPath))
                .getAttribute(ProductDetailsLocators.productIdAttributeName);
        String reviewSectionLocator = String.format(ProductDetailsLocators.reviewsCSS, productId);
        String addToCartButtonLocator = String.format(ProductDetailsLocators.addToCartCSS, productId);
        this.addToCartButton = By.cssSelector(addToCartButtonLocator);
        String prdQuantityLocator = String.format(ProductDetailsLocators.productQuantityXPath, productId);
        this.productQuantityField = By.xpath(prdQuantityLocator);
        String prdPriceLocator = String.format(ProductDetailsLocators.productPrice, productId, productId);
        this.productPriceField = By.xpath(prdPriceLocator);
    }

    public void addToCart(){
        click(addToCartButton);
    }

    public boolean isAddedToCart() {
        WebElement notification = waitForVisibilityOfElementLocated(notificationBar);
        String notificationClass = notification.getAttribute("class");

        if (notificationClass.contains("success")) {
            return true;
        } else {
            return false;
        }
    }

    public String getNotificationText(){
        WebElement notification = waitForVisibilityOfElementLocated(notificationBar);
        return notification.getText();
    }

    public void setProductQuantity(int quantity){
        WebElement quantityEl =  driver.findElement(productQuantityField);
        quantityEl.clear();
        quantityEl.sendKeys("" + quantity);
    }

    public void closeNotificationBar(){
        click(notificationBarClose);
    }

    public double getProductPrice(){
        WebElement priceElement = driver.findElement(productPriceField);
        String priceText = priceElement.getText().replaceAll("[^0-9.]", "");
        return Double.parseDouble(priceText);
    }

    public String getProductName(){
        return driver.findElement(productNameElement).getText();
    }

}
