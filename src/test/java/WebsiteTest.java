import assertions.LoginPageTestAssertions;
import assertions.ProductDetailsTestAssertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WebsiteTest extends BaseTest{
    @Test
    public void testLoginButtonNavigation() {
        navigateToHomePage();
        homePage.navigateToLoginPage();
        // Assert if the current URL is equal to the expected URL
         assertEquals(driver.getCurrentUrl(),"https://demo.nopcommerce.com/login?returnUrl=%2F",
                      LoginPageTestAssertions.loginNavigationMessage );
        // Assert that the login page is displayed properly
        assertTrue(loginPage.isLoginPageDisplayed(), LoginPageTestAssertions.loginPageIsNotProperlyDisplayed);
    }

    @Test
    public void testValidLogin() {
        navigateToHomePage();
        homePage.navigateToLoginPage();
        loginPage.setEmail("ghukasyans033@gmail.com");
        loginPage.setPassword("288858");
        loginPage.clickLoginButton();
        // Assert that NO error message is displayed
        assertFalse(loginPage.isErrorMessageDisplayed(), LoginPageTestAssertions.validLoginFailed);
    }

    @Test
    public void testInvalidLogin() {
        navigateToHomePage();
        homePage.navigateToLoginPage();
        loginPage.setEmail("lll@mail.ru");
        loginPage.setPassword("aaaa");
        loginPage.clickLoginButton();

        // Assert that login fails and error message is displayed
        assertTrue(loginPage.isErrorMessageDisplayed(), LoginPageTestAssertions.invalidLoginMessageVisibility);
        assertEquals(loginPage.getErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found",
                LoginPageTestAssertions.invalidLoginMessageText);

    }

    @Test
    public void testValidAdditionToCart(){
        navigateToHomePage();
        homePage.navigateToProductDetails(4);
        productDetailsPage.setProductQuantity("2");
        productDetailsPage.addToCart();
        assertTrue(productDetailsPage.isAddedToCart());
    }

    @Test
    public void testInvalidAdditionToCart(){
        navigateToHomePage();
        homePage.navigateToProductDetails(4);
        productDetailsPage.setProductQuantity("0");
        productDetailsPage.addToCart();
        assertFalse(productDetailsPage.isAddedToCart(), ProductDetailsTestAssertions.addedToCartInvalidQuantity);
    }

    @Test
    public void testFullFunctionality() {
        navigateToHomePage();

        //login and then continue
        homePage.navigateToLoginPage();
        loginPage.login("sghukasyan033@gmail.com", "288858");

        //navigate back
        navigateToHomePage();
        homePage.scrollToProductSection();

        //view products
        List<WebElement> items = homePage.getProductsList();
        Actions actions = new Actions(driver);
        for (WebElement item : items) {
            // Hover on the element to expand the menu bar
            actions.moveToElement(item).perform();
        }

        homePage.navigateToProductDetails(4);
        assertEquals(driver.getCurrentUrl(),"https://demo.nopcommerce.com/apple-macbook-pro-13-inch",
                ProductDetailsTestAssertions.invalidProductUrl);

        productDetailsPage.initProductFields();
        productDetailsPage.scroll();

        assertEquals(productDetailsPage.getProductName(), "Apple MacBook Pro 13-inch");
    }
}
