package locators;

public class ProductDetailsLocators {
    public static final String dataProductXPath = "//div[@data-productid]";
    public static final String productIdAttributeName = "data-productid";
    public static final String reviewsCSS = "a[href='/productreviews/%s']";
    public static final String addToCartCSS = "button#add-to-cart-button-%s";
    public static final String notifBarXPath = "//body/div[@id='bar-notification']/div[1]";
    public static final String shoppingCartLinkTest = "shopping cart";
    public static final String productNameCSS = "div.product-name h1";
    public static final String productQuantityXPath = "//input[@id='product_enteredQuantity_%s']";
}
