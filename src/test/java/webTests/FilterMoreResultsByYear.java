package webTests;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import base.TestBase;
import common.ScreenshotURL;
import webPageObjects.FilterMoreResults_Page;
import webPageObjects.Search;

public class FilterMoreResultsByYear extends TestBase {

    //****************************************//
    //***                                  ***//
    //*** Created by Angela Tong Apr 2018  ***//
    //***                                  ***//
    //****************************************//

    static SoftAssert softAssert = new SoftAssert();
    final static Logger log = LogManager.getLogger(FilterMoreResultsByYear.class);
    static String className = FilterMoreResultsByYear.class.getSimpleName();
    static Date date1 = new Date();
    static String originaltimestamp = new Timestamp(date1.getTime()).toString();
    static String timestamp = originaltimestamp.replace(':', 'x').substring(11);
    static String foldername = className + timestamp;
    static String errorname = "";


    @Test(groups = { "smoke", "regression" }, dataProvider = "getData")
    public void filterResultsByYear(String searchkeyword, String minYear, String maxYear)
            throws IOException, InterruptedException {

        // Perform search
        Search search = new Search();
        search.searchByCity(webdriver, searchkeyword);

        // Apply more filters
        FilterMoreResults_Page filterPage = new FilterMoreResults_Page();
        filterPage.clickOpenMoreFilters(webdriver);
        filterPage.filterByYear(webdriver, minYear, maxYear);
        filterPage.applyMoreFilters(webdriver);

        // Verify filtered results
        String result = filterPage.verifyFilterByYear(webdriver, minYear, maxYear);

        try {
            Assert.assertEquals(result, "yes", "Filter by year failed");
            log.info("Successfully filtered results by year: " + minYear + " - " + maxYear);
        } catch (AssertionError e) {
            log.error("Filtering by year failed", e);
            ScreenshotURL.capture(webdriver, foldername, errorname, "didntfilterbyyear");
            softAssert.fail();
        }

        softAssert.assertAll();
    }
}
