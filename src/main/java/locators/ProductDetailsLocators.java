package locators;

public class ProductDetailsLocators {
    public static final String dataProductXPath = "//div[@data-productid]";
    public static final String productIdAttributeName = "data-productid";
    public static final String reviewsCSS = "a[href='/productreviews/%s']";
    public static final String addToCartCSS = "button#add-to-cart-button-%s";
    public static final String notifBarXPath = "//body/div[@id='bar-notification']/div[1]";
    public static final String productQuantityXPath = "//input[@id='product_enteredQuantity_%s']";
    public static final String notifBarClose = "span.close[title='Close']";
    public static final String productPrice = "//span[@id='price-value-%s' and contains(@class, 'price-value-%s')]";
    public static final String productNameCSS = "div.product-name h1";
}
