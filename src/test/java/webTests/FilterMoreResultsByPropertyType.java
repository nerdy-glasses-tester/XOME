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

public class FilterMoreResultsByPropertyType extends TestBase {

    //****************************************//
    //***                                  ***//
    //*** Created by Angela Tong Apr 2018  ***//
    //***                                  ***//
    //****************************************//

    static SoftAssert softAssert = new SoftAssert();
    final static Logger log = LogManager.getLogger(FilterMoreResultsByPropertyType.class);
    static String className = FilterMoreResultsByPropertyType.class.getSimpleName();
    static Date date1 = new Date();
    static String originaltimestamp = new Timestamp(date1.getTime()).toString();
    static String timestamp = originaltimestamp.replace(':', 'x').substring(11);
    static String foldername = className + timestamp;
    static String errorname = "";


    @Test(groups = { "smoke", "regression" }, dataProvider = "getData")
    public void filterResultsByPropertyTypeLand(String searchKeyword) throws IOException, InterruptedException {

        // Perform search
        Search search = new Search();
        search.searchByCity(webdriver, searchKeyword);

        // Apply property type filter
        FilterMoreResults_Page filterPage = new FilterMoreResults_Page();
        filterPage.clickOpenMoreFilters(webdriver);
        filterPage.uncheckAllPropertyType(webdriver);
        filterPage.filterByPropertyTypeLand(webdriver);
        filterPage.applyMoreFilters(webdriver);

        // Verify filtered results
        String result = filterPage.verifyFilterByPropertyTypeLand(webdriver);

        try {
            Assert.assertEquals(result, "yes", "Filter by property type 'Land' failed");
            log.info("Successfully filtered results by property type 'Land'");
        } catch (AssertionError e) {
            log.error("Filtering by property type 'Land' failed", e);
            ScreenshotURL.capture(webdriver, foldername, errorname, "didntfilterbypropertytypeland");
            softAssert.fail();
        }

        softAssert.assertAll();
    }
}
