package base;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;

import com.google.common.base.Predicates;
import com.google.common.collect.Maps;

/**
 * The basics for all tests.
 * 
 * @author tmorris
 * 
 */
public class TestBase {
	
	private static WebDriverContainer wdc = new WebDriverContainer();
	private final PropertyManager pm = new PropertyManager();
	private final TestDataProvider tdp = new TestDataProvider();
    private ITestContext testContext;
    private JavascriptRenderer jsRenderer;
	
	/**
     * @return the PM
     */
    public PropertyManager getPM() {
        return pm;
    }
	
	/**
     * @return the PM
     */
    public WebDriver getDriver() {
        return wdc.getDriver();
    }
	
    /**
     * Public getter for test context.
     * 
     * @return ITestContext
     */
    public ITestContext getTestContext() {
        return testContext;
    }

    /**
     * Public setter for test context.
     * 
     * @param testContext to set.
     */
    public void setTestContext(ITestContext testContext) {
        this.testContext = testContext;
    }

    /**
     * Gets the TestNG xml parameters from the test context.
     * 
     * @return Map<String,String> of test parameters.
     */
    public Map<String, String> getTestParameters() {
        return testContext.getCurrentXmlTest().getParameters();
    }

    /**
     * Set a status message at script startup.
     * 
     * @param context ITestContext instance
     */
    @BeforeTest
    public void beforeTest(final ITestContext context) {
        testContext = context;
        tdp.setContext(testContext);
        jsRenderer = new JavascriptRenderer(wdc.getDriver());
        jsRenderer.startMessage(context);
    }
    
    /**
     * Generate the result box.
     * 
     * @param result object (autowired)
     */
    @AfterMethod
    public void afterMethod(final ITestResult result) {
        jsRenderer.resultBar(result);
    }

    /**
     * Provides the test data provider.
     * 
     * @return TestDataProvider
     */
    public TestDataProvider getTestDataProvider() {
        return tdp;
    }

    /**
     * provides explicit casts of key, value in a map to result in a proper Map<String, String> without implicit cast bonks.
     * 
     * @param map to cast
     * @return cast map.
     */
    public Map<String, String> stringifyMap(Map<Object, Object> map) {
        if (map.isEmpty()) {
            return new HashMap<String, String>();
        }

        Map<Object, Object> filtered = Maps.filterValues(map, Predicates.isNull());
        Map<String, String> result = new HashMap<String, String>();

        for (Entry<Object, Object> entry : filtered.entrySet()) {
            result.put(entry.getKey().toString(), entry.getValue().toString());
        }

        return result;
    }
    
    /**
     * Runs after the suite to close the browser.
     */
    @AfterSuite
    public void afterSuite() {
    	wdc.getDriver().quit();
		wdc.setDriverToNull();
    }
}
