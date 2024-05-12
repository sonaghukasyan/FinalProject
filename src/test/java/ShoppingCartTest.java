import assertions.AddToCartFunctionalityAssertions;
import dataProviders.dataprovider;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.ProductDetailsPage;
import pages.ShoppingCartPage;

public class ShoppingCartTest extends BaseTest{
    ProductDetailsPage productDetailsPage;
    ShoppingCartPage cart;


    @Test(description = "add to cart test with valid quantities", groups = {"addToCart"}, dataProvider = "validQnt")
    public void testValidAdditionToCart(int quantity){
        ProductDetailsPage productDetailsPage = homePage.navigateToProductDetailsWithUrl("https://demo.nopcommerce.com/apple-macbook-pro-13-inch");
        productDetailsPage.setProductQuantity(quantity);
        productDetailsPage.addToCart();
        Assert.assertTrue(productDetailsPage.isAddedToCart(), AddToCartFunctionalityAssertions.notAddedToCart);
        productDetailsPage.closeNotificationBar();

        cart = homePage.navigateToShoppingCart();
        Assert.assertEquals(cart.getProductName(0), "Apple MacBook Pro 13-inch");

        double productPrice = cart.getProductPrice(0);
        Assert.assertEquals(productPrice, (Double) 1800.00, "Wrong product price");

        int actualQnt = cart.getProductQuantity(0);
        Assert.assertEquals(actualQnt, quantity, "Wrong product quantity.");

        double subTotalPrice = (Double) 1800.00 * quantity;

        Assert.assertEquals(cart.getSubTotalPrice(0), subTotalPrice, "Wrong subtotal price.");
    }
}
