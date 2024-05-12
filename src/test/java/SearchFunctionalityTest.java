import assertions.SearchFunctionalityTestAssertions;
import dataProviders.dataprovider;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.SearchResultPage;

public class SearchFunctionalityTest extends BaseTest{
    SearchResultPage searchResultPage;

    @DataProvider
    public static Object[][] validKeywords() {
        return dataprovider.VALID_SEARCH_KEYWORDS;
    }

    @DataProvider
    public static Object[][] invalidKeywords() {
        return dataprovider.INVALID_SEARCH_KEYWORDS;
    }

    @Test(description = "search for invalid keywords, which have less than 3 characters", groups = {"search"}, dataProvider = "invalidKeywords")
    public void invalidSearchTest(String searchText) {
        searchResultPage = homePage.performSearch(searchText);
        Assert.assertTrue(searchResultPage.isInvalidSearchWarningMessageDisplayed(), SearchFunctionalityTestAssertions.searchTestFailAssertion);
    }

    @Test
    @Parameters("searchText")
    public void noResultSearchTest(String searchText) {
        searchResultPage = homePage.performSearch(searchText);
        Assert.assertTrue(searchResultPage.isNoResultMessageDisplayed(), SearchFunctionalityTestAssertions.searchTestFailAssertion);
    }

    @Test(description = "search with empty keyword", groups = {"search"})
    public void emptySearchTest() {
        searchResultPage = homePage.performSearch("");
        Assert.assertTrue(searchResultPage.isEmptySearchBarDisplayed(), SearchFunctionalityTestAssertions.searchTestFailAssertion);
    }

    @Test(description = "search for products that exist", groups = {"search"}, dataProvider = "validKeywords")
    public void searchForExistingProductsTest(String searchText) {
        searchResultPage = homePage.performSearch(searchText);
        Assert.assertTrue(searchResultPage.compareSearchResultsAgainstKeyword(searchText), SearchFunctionalityTestAssertions.searchTestFailAssertion);
    }
}
