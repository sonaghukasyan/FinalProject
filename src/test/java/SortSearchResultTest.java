import assertions.SortSearchResultTestAssertions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.SearchResultPage;

public class SortSearchResultTest extends  BaseTest{
    SearchResultPage searchResultPage;

    @BeforeMethod
    public void performValidSearch(){
        navigateToHomePage();
        searchResultPage = homePage.performSearch("card");
    }

    @Test(description = "sort search with low to high prices test", groups = {"sort search"})
    public void searchSortPriceLowToHighTest() throws InterruptedException {
        searchResultPage.sortPrices(true);
        Thread.sleep( 1500);
        Assert.assertTrue(searchResultPage.pricesSorted(true), SortSearchResultTestAssertions.sortedHighToLowAssertion);
    }

    @Test(description = "sort search with high to low prices test", groups = {"sort search"})
    public void searchSortPriceHighToLowTest() throws InterruptedException {
        searchResultPage.sortPrices(false);
        //view products
        Thread.sleep( 1500);
        Assert.assertTrue(searchResultPage.pricesSorted(false), SortSearchResultTestAssertions.sortedLowToHighAssertion);
    }

}
