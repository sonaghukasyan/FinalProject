import assertions.AddToCartFunctionalityAssertions;
import dataProviders.dataprovider;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.ProductDetailsPage;
import pages.ShoppingCartPage;

public class AddToCartFunctionalityTest extends BaseTest {
    ProductDetailsPage productDetailsPage;
    ShoppingCartPage cart;

    @DataProvider
    public static Object[][] validQnt() {
        return dataprovider.VALID_QUANTITY_ADD_TO_CART_MAC;
    }

    @DataProvider
    public static Object[][] invalidQnt() {
        return dataprovider.INVALID_QUANTITY;
    }

    @BeforeMethod
    public void emptyTheShoppingCart(){
        navigateToHomePage();
        //redirect to shopping cart.
        cart = homePage.navigateToShoppingCart();
        cart.emptyTheCart();
        navigateToHomePage();
    }

    @Test(description = "add to cart test with valid quantities and check cart fields.", groups = {"addToCart"}, dataProvider = "validQnt")
    public void testValidAdditionToCart(int quantity){
        productDetailsPage = homePage.navigateToProductDetails(4);
        productDetailsPage.setProductQuantity(quantity);
        productDetailsPage.addToCart();
        String productName =  productDetailsPage.getProductName();
        double productPriceExpected = productDetailsPage.getProductPrice();

        //ensure that added to cart notification is displayed.
        Assert.assertTrue(productDetailsPage.isAddedToCart(), AddToCartFunctionalityAssertions.notAddedToCart);
        productDetailsPage.closeNotificationBar();

        //redirect to shopping cart.
        cart = homePage.navigateToShoppingCart();

        //ensure that item is added to cart
        Assert.assertEquals(cart.getNumberOfItemsInCart(), 1);

        //check that product name is right
        Assert.assertEquals(cart.getProductName(0), productName);

        //check that product price is right
        double productPriceAct = cart.getProductPrice(0);
        Assert.assertEquals(productPriceAct, productPriceExpected, "Wrong product price");

        //check that product quantity is right
        int actualQnt = cart.getProductQuantity(0);
        Assert.assertEquals(actualQnt, quantity, "Wrong product quantity.");

        //check that product subtotal price is right
        double subTotalPrice = productPriceExpected * quantity;
        Assert.assertEquals(cart.getSubTotalPrice(0), subTotalPrice, "Wrong subtotal price.");
    }

    @Test(description = "add to cart multiple items", groups = {"addToCart"})
    public void testValidMultipleAdditionToCart(){
        //add the first item
        productDetailsPage = homePage.navigateToProductDetails(4);
        productDetailsPage.setProductQuantity(2);
        productDetailsPage.addToCart();
        double priceOfFirstEl = productDetailsPage.getProductPrice()*2;
        String nameOfFirstEl = productDetailsPage.getProductName();

        //add the second item
        navigateToHomePage();
        productDetailsPage = homePage.navigateToProductDetails(5);
        productDetailsPage.setProductQuantity(2);
        productDetailsPage.addToCart();
        double priceOfSecondEl = productDetailsPage.getProductPrice() * 2;
        String nameOfSecEl = productDetailsPage.getProductName();

        //go to shopping cart
        productDetailsPage.closeNotificationBar();
        cart = homePage.navigateToShoppingCart();

        //ensure that 2 different items are added to cart
        Assert.assertEquals(cart.getNumberOfItemsInCart(), 2);
        //check the first item's name
        Assert.assertEquals(cart.getProductName(0), nameOfFirstEl);
        //check the second item's name
        Assert.assertEquals(cart.getProductName(1), nameOfSecEl);
        //check that total price amount in cart is correct
        Assert.assertEquals(cart.getTotalPrice(), priceOfFirstEl + priceOfSecondEl);
    }

    @Test(description = "add to cart test with invalid quantities", groups = {"addToCart"}, dataProvider = "invalidQnt")
    public void nonPositiveQuantityAddToCart(int quantity){
        productDetailsPage = homePage.navigateToProductDetails(4);
        productDetailsPage.setProductQuantity(quantity);
        productDetailsPage.addToCart();
        Assert.assertFalse(productDetailsPage.isAddedToCart(), "Added to cart with invalid qnt");

        //ensure that error notifications are displayed properly.
        if(quantity <= 0){
            Assert.assertEquals(productDetailsPage.getNotificationText(), "Quantity should be positive");
        }
        else{
            Assert.assertEquals(productDetailsPage.getNotificationText(), "The minimum quantity allowed for purchase is 2.");
        }

        //ensure that cart is empty
        productDetailsPage.closeNotificationBar();
        cart = homePage.navigateToShoppingCart();
        Assert.assertTrue(cart.isCartEmpty());
    }
}
