package webTests;

import java.io.IOException;
import java.util.Date;
import java.sql.Timestamp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import base.TestBase;
import common.ScreenshotURL;
import webPageObjects.FilterMoreResults_Page;
import webPageObjects.Search;

public class FilterMoreResultsByKeyword extends TestBase {

    //****************************************//
    //***                                  ***//
    //*** Created by Angela Tong Apr 2018  ***//
    //***                                  ***//
    //****************************************//

    static final SoftAssert softAssert = new SoftAssert();
    static final Logger log = LogManager.getLogger(FilterMoreResultsByKeyword.class);
    static String className = FilterMoreResultsByKeyword.class.getSimpleName();
    static Date date1 = new Date();
    static String originaltimestamp = new Timestamp(date1.getTime()).toString();
    static String timestamp = originaltimestamp.replace(':', 'x').substring(11);
    static String foldername = className + timestamp;
    static String errorname = "";
    
    @Test(groups = { "smoke", "regression" }, dataProvider = "getData")
    public void filterResultsByKeyword(String searchKeyword, String keyword) throws IOException, InterruptedException {

        // Perform search
        Search search = new Search();
        search.searchByCity(webdriver, searchKeyword);

        // Apply keyword filter
        FilterMoreResults_Page filterPage = new FilterMoreResults_Page();
        filterPage.clickOpenMoreFilters(webdriver);
        filterPage.filterByKeyword(webdriver, keyword);
        filterPage.applyMoreFilters(webdriver);

        // Verify filtered results
        String result = filterPage.verifyFilterByKeyword(webdriver, keyword);

        try {
            Assert.assertEquals(result, "yes", "Filter by keyword failed");
            log.info("Successfully filtered results by keyword: {}", keyword);
        } catch (AssertionError e) {
            log.error("Filtering by keyword '{}' failed", keyword, e);
            ScreenshotURL.capture(webdriver, foldername, errorname,"didntfilterbykeyword");
            softAssert.fail();
        }

        softAssert.assertAll();
    }
}
