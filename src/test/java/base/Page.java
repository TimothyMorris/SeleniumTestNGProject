package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Page is a structural class for setting up page interactions on a web site.
 * 
 * @param <T> the type of the object extending Page
 * @author tmorris
 * 
 */
public abstract class Page<T extends Page<T>> {

    /** The driver. */
	protected WebDriver driver;

	/**
	 * Default constructor.
	 * @param driver the WebDriver
	 */
	public Page(final WebDriver driver) {
		super();
		setDriver(driver);
		PageFactory.initElements(driver, this);
	}
	
	/**
     * Set the webdriver object.
     * 
     * @param driver webdriver object
     */
    protected final void setDriver(final WebDriver driver) {
        this.driver = driver;
    }

}
