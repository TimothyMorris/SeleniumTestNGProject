package tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import base.TestBase;

/**
 * Test that performs a search on Amazon and verifies a result.
 * 
 * @author tmorris
 */
public class AmazonTest extends TestBase {
	
	/**
     * Loads the Amazon home page.
     */
    @Test
    public void loadAmazon() {
        getDriver().get("http://www.amazon.ca");
    }
    
    /**
     * Enters a search term.
     */
    @Test
    @Parameters("searchTerm")
    public void enterSearchTerm(String searchTerm) {
        WebElement searchBox = getDriver().findElement(By.cssSelector("input[name='field-keywords']"));
        searchBox.sendKeys(searchTerm);
    }
    
    /**
     * Clicks on the search button.
     */
    @Test
    public void performQuery() {
        WebElement searchButton = getDriver().findElement(By.cssSelector("input[value='Go']"));
        searchButton.click();
    }
    
    /**
     * Verifies that the desired product is returned in the search results.
     */
    @Test
    @Parameters("result")
    public void verifySearchResult(String result) {
        WebElement searchResult = getDriver().findElement(By.linkText(result));
        Assert.assertTrue(searchResult.isDisplayed());
        //The above method of testing isn't very robust.  An exception will occur if the WebElement isn't found.
        //The below method is robust.  If the WebElement isn't found, the assertion will fail and provide a detailed error message.
        List<WebElement> expectedSearchResult = getDriver().findElements(By.linkText(result));
        Assert.assertTrue(expectedSearchResult.size() > 0, String.format(
    			"The expected search result was not present on the page.  Expected Search Result: '%s'", result));
    }
}
