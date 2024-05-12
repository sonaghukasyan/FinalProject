import assertions.SortSearchResultTestAssertions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.SearchResultPage;

public class SortSearchResultTest extends  BaseTest{
    SearchResultPage searchResultPage;

    @BeforeMethod
    public void performValidSearch(){
        searchResultPage = homePage.performSearch("card");
    }

    @Test(description = "sort search with low to high prices test", groups = {"sort search"})
    public void searchSortPriceLowToHighTest() {
        searchResultPage.sortPrices(true);
        Assert.assertTrue(searchResultPage.pricesSorted(true), SortSearchResultTestAssertions.sortedHighToLowAssertion);
    }

    @Test(description = "sort search with high to low prices test", groups = {"sort search"})
    public void searchSortPriceHighToLowTest() {
        searchResultPage.sortPrices(false);
        Assert.assertTrue(searchResultPage.pricesSorted(false), SortSearchResultTestAssertions.sortedLowToHighAssertion);
    }
}
