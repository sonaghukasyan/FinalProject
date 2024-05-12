package locators;

public class ShoppingCartLocators {
    public static final String cartEmptyCSS = "div.no-data";
    public static final String productNameXPath = "//a[@class='product-name']";
    public static final String productPriceCSS = "span.product-unit-price";
    public static final String productQuantityCSS = "div.product-quantity input.qty-input";
    public static final String subtotalPriceXPath = "//span[@class='product-subtotal']";
    public static final String totalPriceXPath = "//td[@class='cart-total-right']/span[@class='value-summary']/strong";
    public static final String removeItemFromCartXPath = "//td[@class='remove-from-cart']";
}
