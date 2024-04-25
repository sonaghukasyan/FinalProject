package pages;

import locators.ProductDetailsLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductDetailsPage extends BasePage{
    private final By notificationBar = By.xpath(ProductDetailsLocators.notifBarXPath);
    private final By shoppingCart = By.linkText(ProductDetailsLocators.shoppingCartLinkTest);
    private final By productNameElement = By.cssSelector(ProductDetailsLocators.productNameCSS);

    private String productId;
    private By reviewSection;
    private By addToCartButton;
    private By productQuantityField;

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    public void initProductFields(){
        productId = driver.findElement(By.xpath(ProductDetailsLocators.dataProductXPath))
                .getAttribute(ProductDetailsLocators.productIdAttributeName);
        String reviewSectionLocator = String.format(ProductDetailsLocators.reviewsCSS, productId);
        this.reviewSection = By.cssSelector(reviewSectionLocator);
        String addToCartButtonLocator = String.format(ProductDetailsLocators.addToCartCSS, productId);
        this.addToCartButton = By.cssSelector(addToCartButtonLocator);
        String prdQuantityLocator = String.format(ProductDetailsLocators.productQuantityXPath, productId);
        this.productQuantityField = By.xpath(prdQuantityLocator);
    }

    public void seeReviews(){
        click(reviewSection);
    }

    public void addToCart(){
        click(addToCartButton);
    }

    public boolean isAddedToCart() {
        WebElement notification = waitForVisibilityOfElementLocated(notificationBar);
        String notificationClass = notification.getAttribute("class");

        if (notificationClass.contains("success")) {
            click(shoppingCart);
            return true;
        } else {
            return false;
        }
    }

    public void scroll(){
        scrollToElement(reviewSection);
    }

    public String getProductName(){
        return driver.findElement(productNameElement).getText();
    }

    public void setProductQuantity(String quantity){
        WebElement quantityEl =  driver.findElement(productQuantityField);
        quantityEl.clear();
        quantityEl.sendKeys(quantity);
    }
}
