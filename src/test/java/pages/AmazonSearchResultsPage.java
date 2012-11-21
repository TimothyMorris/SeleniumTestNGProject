package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.Page;

/**
 * Handles interactions with the Amazon Search Results Page.
 * 
 * @author tmorris
 */
public class AmazonSearchResultsPage extends Page<AmazonSearchResultsPage> {
	
    @FindBy(css = "h1[id='breadCrumb']")
	private WebElement breadCrumb;
    @FindBy(css = "div[id='result_0'] span")
	private WebElement firstSearchResult;

	/**
	 * Default constructor.
	 * @param driver the WebDriver
	 */
	public AmazonSearchResultsPage(final WebDriver driver) {
		super(driver);
	}
	
	/**
	 * Gets the term that was searched for.
	 * 
	 * @return String term that was searched for
	 */
	public String getBreadCrumb() {
		return breadCrumb.getText();
	}

	/**
	 * Gets the title of the top search result.
	 * 
	 * @return String title of the first search result
	 */
	public String getFirstSearchResult() {
		return firstSearchResult.getText();
	}
	
}
