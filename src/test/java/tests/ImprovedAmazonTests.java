package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.AmazonHomePage;
import pages.AmazonSearchResultsPage;
import base.TestBase;

/**
 * Performs searches on Amazon and verifies the results.
 * 
 * @author tmorris
 * 
 */
@Test
public class ImprovedAmazonTests extends TestBase {
	
    private AmazonHomePage amazonHomePage;
    private AmazonSearchResultsPage amazonSearchResultsPage;

    /**
	 * Initialises the pages needed for the test.
	 */
	@BeforeTest
	public void setUp() {
		amazonHomePage = new AmazonHomePage(getDriver());
		amazonSearchResultsPage = new AmazonSearchResultsPage(getDriver());
	}
	
	/**
     * Loads the Amazon Home Page.
     */
    @Test
    public void loadAmazonHomePage() {
    	amazonHomePage.load();
    }
    
    /**
     * Selects a filter for the search box
     * 
     * @param dropDownSelection the Drop Down Box option to select
     */
    @Test
    @Parameters("dropDownSelection")
    public void selectDropDownOption(String dropDownSelection) {
    	amazonHomePage.selectDropDownBoxOption(dropDownSelection);
    }
    
    /**
     * Performs a search.
     * 
     * @param searchTerm used to perform a search
     */
    @Test
    @Parameters("searchTerm")
    public void search(String searchTerm) {
    	amazonHomePage.enterSearchTerm(searchTerm);
    	amazonHomePage.performSearch();
    }
    
    /**
     * Verifies bread crumb is displayed correctly on the search results page.
     * 
     * @param expectedBreadCrumb the expected bread crumb string
     */
    @Test
    @Parameters("expectedBreadCrumb")
    public void verifyBreadCrumb(String expectedBreadCrumb) {
    	String actualBreadCrumb = amazonSearchResultsPage.getBreadCrumb();
    	Assert.assertEquals(actualBreadCrumb, expectedBreadCrumb, String.format(
    			"The actual bread crumb value did not match the expected.  Actual: '%s'. Expected: '%s'", actualBreadCrumb, expectedBreadCrumb)); 
    }
    
    /**
     * Verifies the first search result on the search results page.
     * 
     * @param expectedFirstSearchResult the expected top product in the search results
     */
    @Test
    @Parameters("expectedFirstSearchResult")
    public void verifyFirstSearchResult(String expectedFirstSearchResult) {
    	String actualFirstSearchResult = amazonSearchResultsPage.getFirstSearchResult();
    	Assert.assertEquals(actualFirstSearchResult, expectedFirstSearchResult, String.format(
    			"The actual first search result did not match the expected.  Actual: '%s'. Expected: '%s'", actualFirstSearchResult, expectedFirstSearchResult)); 
    }

}
