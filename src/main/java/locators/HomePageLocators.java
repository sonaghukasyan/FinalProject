package locators;

public class HomePageLocators {
    public static final String searchFieldCSS = "#small-searchterms";
    public static final String searchButtonXPath = "//button[contains(text(),'Search')]";
    public static final String productSectionXPath = "/html[1]/body[1]/div[6]/div[3]/div[1]/div[1]/div[1]/div[1]/div[4]/div[2]";
    public static final String productItemsXPath="//div[@class='item-box']";
    public static final String shoppingCartCSS = "a.ico-cart > span.cart-label";
    public static final String productTitle = "div.item-box > .product-item > .details > h2";
}
