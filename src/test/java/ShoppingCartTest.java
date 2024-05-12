import assertions.AddToCartFunctionalityAssertions;
import dataProviders.dataprovider;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.ProductDetailsPage;
import pages.ShoppingCartPage;

public class ShoppingCartTest extends BaseTest{
    ProductDetailsPage productDetailsPage;
    ShoppingCartPage cart;

    @BeforeMethod
    public void addItemsToCart(){
        //add the first item
        navigateToHomePage();
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
        productDetailsPage.closeNotificationBar();
    }

    @AfterMethod
    public void emptyCart(){
        if(cart.getNumberOfItemsInCart() > 0){
            cart.emptyTheCart();
        }
    }

    @Test(description = "remove items from cart.", groups = {"cartTest"})
    public void testRemoveItemsFromCart(){
        navigateToHomePage();
        cart = homePage.navigateToShoppingCart();
        int itemCount = cart.getNumberOfItemsInCart();
        int iterations = itemCount;

        if(itemCount > 0){
            //remove each item and check removal operation
            for(int i = 0; i < iterations; i++){
                double totalPriceOld = cart.getTotalPrice();
                double subTotalPriceOfItem = cart.getSubTotalPrice(0);
                cart.removeProductWithIndex(0);
                int newItemCount = cart.getNumberOfItemsInCart();
                itemCount--;

                //ensure that item count is decreased by 1
                Assert.assertEquals(newItemCount, itemCount);

                if(newItemCount != 0){
                    //ensure that total price is decreased by removed item's price
                    Assert.assertEquals(cart.getTotalPrice(), totalPriceOld - subTotalPriceOfItem);
                }
                else{
                    //ensure that cart is empty
                    Assert.assertTrue(cart.isCartEmpty());
                }
            }
        }
    }

    @Test(description = "change items quantities with valid values", groups = {"cartTest"})
    public void testChangeQuantitesWithValidValues(){
        navigateToHomePage();
        cart = homePage.navigateToShoppingCart();

        //increase quantity of first item by one and ensure that prices are increased
        double totalPriceOld = cart.getTotalPrice();
        double subTotalPriceOld = cart.getSubTotalPrice(0);
        cart.addQuantity(0);
        //ensure that subtotal price increases accordingly
        Assert.assertEquals(cart.getSubTotalPrice(0), subTotalPriceOld + cart.getProductPrice(0));
        //ensure that total price increases accordingly
        Assert.assertEquals(cart.getTotalPrice(), totalPriceOld + cart.getProductPrice(0));

        //decrease quantity of second item by one and ensure that prices are decreased
        totalPriceOld = cart.getTotalPrice();
        subTotalPriceOld = cart.getSubTotalPrice(1);
        cart.subtractQuantity(1);
        //ensure that subtotal price decreases accordingly
        Assert.assertEquals(cart.getSubTotalPrice(1), subTotalPriceOld - cart.getProductPrice(1));
        //ensure that total price decreases accordingly
        Assert.assertEquals(cart.getTotalPrice(), totalPriceOld - cart.getProductPrice(1));
    }

    @Test(description = "decrease quantity to 0 to remove item from cart", groups = {"cartTest"})
    public void testRemoveFromCartByChangingQuantity(){
        navigateToHomePage();
        cart = homePage.navigateToShoppingCart();
        //remove one element
        cart.removeProductWithIndex(0);
        //subtract quantity to set it 0
        int quantityOld = cart.getProductQuantity(0);
        for(int i = 0; i < quantityOld; i++){
            cart.subtractQuantity(0);
        }
        //ensure that cart is empty.
        Assert.assertTrue(cart.isCartEmpty());
    }
}
