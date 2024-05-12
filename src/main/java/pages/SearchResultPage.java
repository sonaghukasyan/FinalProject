package pages;
import locators.SearchResultPageLocators;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class SearchResultPage extends BasePage{
    private By productTitles = By.cssSelector(SearchResultPageLocators.productsTitlesCSS);
    private By noResultMessage = By.xpath(SearchResultPageLocators.noResultXPath);
    private By invalidSearchWarningMessage = By.xpath(SearchResultPageLocators.searchWarningMessageXPath);
    private By productPrices = By.xpath(SearchResultPageLocators.productPriceXPath);
    private By sortDropdown = By.id(SearchResultPageLocators.sortDropdownId);

    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    public boolean isNoResultMessageDisplayed() {
        return isDisplayed(noResultMessage);
    }

    public boolean isInvalidSearchWarningMessageDisplayed() {
        return isDisplayed(invalidSearchWarningMessage);
    }

    public boolean isEmptySearchBarDisplayed() {
        try{
            Alert alert = driver.switchTo().alert();
            if(alert.getText().equals("Please enter some search keyword")){
                alert.dismiss();
                return true;
            }
            else{
                alert.dismiss();
                return false;
            }
        }
        catch (Exception ex){
            return false;
        }
    }

    public boolean compareSearchResultsAgainstKeyword(String keyword) {
        // Find all elements with the class name "product-title"
        List<WebElement> productTitlesElements = driver.findElements(productTitles);
        // Iterate through each element and extract the text
        for (WebElement title : productTitlesElements) {
            boolean contains = title.getText().toLowerCase().contains(keyword.toLowerCase());
            if(!contains){
                System.out.println("Wrong Caption =" + title.getText());
                return false;
            }
        }

        return true;
    }

    public void sortPrices(boolean ascending){
        // Get the dropdown element
        WebElement dropdown = waitForVisibilityOfElementLocated(sortDropdown);
        // Select the "Price: Low to High" option
        Select select = new Select(dropdown);
        if(ascending){
            select.selectByValue(SearchResultPageLocators.lowToHighValue);
        }
        else{
            select.selectByValue(SearchResultPageLocators.highToLowValue);
        }
    }

    public boolean pricesSorted(boolean ascendingOrder){
        // Find all prices
        List<WebElement> priceElements = driver.findElements(productPrices);
        // List to store prices as doubles
        List<Double> prices = new ArrayList<>();

        double pricePrevious = Double.parseDouble(priceElements.get(0).getText().replaceAll("[^0-9.]", ""));

        for (WebElement priceElement : priceElements) {
            // Remove non-numeric characters
            String priceText = priceElement.getText().replaceAll("[^0-9.]", "");
            double price = Double.parseDouble(priceText);
            if(ascendingOrder && price < pricePrevious){
                return false;
            }
            else if(!ascendingOrder && price > pricePrevious){
                return false;
            }
            pricePrevious = price;
            prices.add(price);
        }
        return true;
    }
}
