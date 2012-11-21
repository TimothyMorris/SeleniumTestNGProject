package base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;

/**
 * Executes javascript on the active page.
 * 
 * @author tmorris
 * 
 */
public class JavascriptRenderer {
    private static final int THREE_SECONDS = 3000;
    private static final String STATUS_MESSAGE = "Start Test: %s";
    private static final String COLOR_SUCCESS = "background:green;color:black;";
    private static final String COLOR_FAILURE = "background:red;color:white;";
    private static final String BOTTOM_BAR_JS = "var newDiv = document.createElement('div');" + "newDiv.innerHTML=\"%s\";"
            + "newDiv.setAttribute(\"style\", \"position:fixed;%s;padding-left:20px;bottom:0px;"
            + "border-top:2px solid #000;width:100%%;height:30px;\");document.body.appendChild(newDiv);";

    private final transient WebDriver driver;

    /**
     * Gets a new renderer.
     * 
     * @param webDriver webdriver
     */
    public JavascriptRenderer(final WebDriver webDriver) {
        this.driver = webDriver;
    }

    /**
     * Set a status message at script startup.
     * 
     * @param context ITestContext instance
     */
    public void startMessage(final ITestContext context) {
        final String name = context.getName();
        jsStatusBar(String.format(STATUS_MESSAGE, name), "background-color:white");
    }

    /**
     * Generate the result box.
     * 
     * @param result object (autowired)
     */
    public void resultBar(final ITestResult result) {
        final String name = result.getName();

        String color;
        color = result.isSuccess() ? COLOR_SUCCESS : COLOR_FAILURE;
        jsStatusBar(String.format("%s", name), color);
        if (!result.isSuccess()) {
            try {
                Thread.sleep(THREE_SECONDS);
            } catch (InterruptedException e) {
                Assert.fail(String.format("Sleep was interrupted!"));
            }
        }
    }

    /**
     * Insert a javascript statusbar on the current browser frame.
     * 
     * @param statusMessage message to paste in the window.
     * @param color in "backgroundcolor: #value, etc" format.
     */
    public void jsStatusBar(final String statusMessage, final String color) {
        injectJavascript(String.format(BOTTOM_BAR_JS, statusMessage, color));
    }

    /**
     * Runs javascript in the browser.
     * 
     * @param script to run.
     */
    public void injectJavascript(final String script) {
        ((JavascriptExecutor) driver).executeScript(script);
    }

}
