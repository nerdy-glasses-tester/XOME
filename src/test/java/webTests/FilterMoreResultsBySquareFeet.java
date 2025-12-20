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

public class FilterMoreResultsBySquareFeet extends TestBase {

    //****************************************//
    //***                                  ***//
    //*** Created by Angela Tong Apr 2018  ***//
    //***                                  ***//
    //****************************************//

    static SoftAssert softAssert = new SoftAssert();
    final static Logger log = LogManager.getLogger(FilterMoreResultsBySquareFeet.class);
    static String className = FilterMoreResultsBySquareFeet.class.getSimpleName();
    static Date date1 = new Date();
    static String originaltimestamp = new Timestamp(date1.getTime()).toString();
    static String timestamp = originaltimestamp.replace(':', 'x').substring(11);
    static String foldername = className + timestamp;
    static String errorname = "";


    @Test(groups = { "smoke", "regression" }, dataProvider = "getData")
    public void filterResultsBySqFeet(String searchKeyword, String minSqFeet, String maxSqFeet)
            throws IOException, InterruptedException {

        // Perform search
        Search search = new Search();
        search.searchByCity(webdriver, searchKeyword);

        // Apply more filters
        FilterMoreResults_Page filterPage = new FilterMoreResults_Page();
        filterPage.clickOpenMoreFilters(webdriver);
        filterPage.filterBySqFeet(webdriver, minSqFeet, maxSqFeet);
        filterPage.applyMoreFilters(webdriver);

        // Verify filtered results
        String result = filterPage.verifyFilterBySqFeet(webdriver, minSqFeet, maxSqFeet);

        try {
            Assert.assertEquals(result, "yes", "Filter by square feet failed");
            log.info("Successfully filtered results by square feet: " + minSqFeet + " - " + maxSqFeet);
        } catch (AssertionError e) {
            log.error("Filtering by square feet failed", e);
            ScreenshotURL.capture(webdriver, foldername, errorname, "didntfilterbysqft");
            softAssert.fail();
        }

        softAssert.assertAll();
    }
}
