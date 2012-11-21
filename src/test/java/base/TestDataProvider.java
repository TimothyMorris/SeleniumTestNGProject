package base;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.testng.ITestContext;
import com.google.common.collect.ImmutableList;

/**
 * Provides test data from iTestContext.
 * 
 * @author tmorris
 * 
 */
public class TestDataProvider {
    private ITestContext context;

    /**
     * Sets the test context.
     * 
     * @param context the context to set
     */
    public void setContext(final ITestContext context) {
        this.context = context;
    }

    /**
     * Filters the parameters as a map.
     * 
     * @param filter string such as "f:"
     * @return map of only parameters matching the string.
     */
    public Map<String, String> getFilteredXmlParameterMap(String filter) {
        Map<String, String> parameters = getXmlParameterMap();
        Map<String, String> result = new HashMap<String, String>();
        for (String key : parameters.keySet()) {
            if (key.startsWith(filter)) {
                result.put(key.substring(filter.length()), parameters.get(key));
            }
        }
        return result;
    }

    /**
     * Provides a raw parameter map from the iTestContext.
     * 
     * @return Map of xml test parameters.
     */
    public Map<String, String> getXmlParameterMap() {
        return context.getCurrentXmlTest().getParameters();
    }

    /**
     * provides an un-filtered iterator of all xml parameters.
     * 
     * @return Iterator of object arrays, consumable by TestNG.
     */
    public Iterator<Object[]> paramaterProvider() {
        return convertMapToIterator(getXmlParameterMap());
    }

    /**
     * provides a filtered iterator containing a subset of xml parameters.
     * 
     * @param filter a string such as "f:" to prefix xml paramaters, separating them into groups.
     * @return Iterator of object arrays, filtered to the string, consumable by TestNG.
     */
    public Iterator<Object[]> filteredParameterProvider(final String filter) {
        return convertMapToIterator(getFilteredXmlParameterMap(filter));
    }

    private Iterator<Object[]> convertMapToIterator(Map<String, String> parameters) {
        final ImmutableList.Builder<Object[]> result = ImmutableList.builder();
        Object[] row = new Object[2];
        for (String key : parameters.keySet()) {
            row[0] = key;
            row[1] = parameters.get(key);

            result.add(row.clone());
        }
        return result.build().iterator();
    }

    /**
     * Convenience function that provides field names and expected values.
     * 
     * @return values of all test fields prefixed by f:
     */
    public Iterator<Object[]> fieldValueProvider() {
        return filteredParameterProvider("f:");
    }
}
