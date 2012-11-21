package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import base.Page;

/**
 * Handles interactions with the Amazon Home Page.
 * 
 * @author tmorris
 * 
 */
public class AmazonHomePage extends Page<AmazonHomePage> {
    
	@FindBy(css = "input[name='field-keywords']")
	private WebElement searchBox;
	@FindBy(css = "input[value='Go']")
	private WebElement searchButton;
	@FindBy(css = "select[id='searchDropdownBox']")
	private WebElement dropDownBox;

	/**
	 * Default constructor.
	 * @param driver the WebDriver
	 */
	public AmazonHomePage(final WebDriver driver) {
		super(driver);
	}
	
	/**
	 * Load the Amazon Home Page.
	 */
	public void load() {
		driver.get("http://www.amazon.ca/");
	}

	/**
	 * Enter a search term in to the Amazon search box.
	 * 
	 * @param searchTerm to enter in to the search box
	 */
	public void enterSearchTerm(String searchTerm) {
		searchBox.sendKeys(searchTerm);
	}

	/**
	 * Click the Amazon Go button.
	 */
	public void performSearch() {
		searchButton.submit();
	}
	
	/**
	 * Selects an option from the Drop Down Box.
	 * 
	 * @param selection to select from the Drop Down Box options
	 */
    public void selectDropDownBoxOption(String selection) {
        for (WebElement option : dropDownBox.findElements(By.tagName("option"))) {
            if (option.getText().equals(selection)) {
                option.click();
            }
        }
    }
}
