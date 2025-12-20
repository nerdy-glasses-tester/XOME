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
import webPageObjects.FilterResults_Page;
import webPageObjects.Search;

public class FilterResultsByPriceBedBath extends TestBase {

    //****************************************//
    //***                                  ***//
    //*** Created by Angela Tong Apr 2018  ***//
    //***                                  ***//
    //****************************************//

    static SoftAssert softAssert = new SoftAssert();
    final static Logger log = LogManager.getLogger(FilterResultsByPriceBedBath.class);
    static String className = FilterResultsByPriceBedBath.class.getSimpleName();
    static Date date1 = new Date();
    static String originaltimestamp = new Timestamp(date1.getTime()).toString();
    static String timestamp = originaltimestamp.replace(':', 'x').substring(11);
    static String foldername = className + timestamp;
    static String errorname = "";


    @Test(groups = { "smoke", "regression" }, dataProvider = "getData")
    public void filterResultsByPrice(String searchkeyword, String price1, String price2) throws IOException, InterruptedException {
        Search search = new Search();
        search.searchByCity(webdriver, searchkeyword);

        FilterResults_Page filterResultsPage = new FilterResults_Page();
        filterResultsPage.filterByPrice(webdriver, price1, price2);

        String result = filterResultsPage.verifyFilterByPrice(webdriver, price1, price2);

        try {
            Assert.assertEquals(result, "yes", "Filter by price failed");
            log.info("Successfully filtered results by price: " + price1 + " - " + price2);
        } catch (AssertionError e) {
            log.error("Filtering by price failed", e);
            ScreenshotURL.capture(webdriver, foldername, errorname, "didntfilterbyprice");
            softAssert.fail();
        }

        softAssert.assertAll();
    }

    @Test(groups = { "smoke", "regression" }, dataProvider = "getData")
    public void filterResultsByBeds(String searchkeyword, String bed) throws IOException, InterruptedException {
        Search search = new Search();
        search.searchByCity(webdriver, searchkeyword);

        FilterResults_Page filterResultsPage = new FilterResults_Page();
        filterResultsPage.filterByBeds(webdriver, bed);

        String result = filterResultsPage.verifyFilterByBeds(webdriver, bed);

        try {
            Assert.assertEquals(result, "yes", "Filter by beds failed");
            log.info("Successfully filtered results by beds: " + bed);
        } catch (AssertionError e) {
            log.error("Filtering by beds failed", e);
            ScreenshotURL.capture(webdriver, foldername, errorname, "didntfilterbybed");
            softAssert.fail();
        }

        softAssert.assertAll();
    }

    @Test(groups = { "smoke", "regression" }, dataProvider = "getData")
    public void filterResultsByBaths(String searchkeyword, String bath) throws IOException, InterruptedException {
        Search search = new Search();
        search.searchByCity(webdriver, searchkeyword);

        FilterResults_Page filterResultsPage = new FilterResults_Page();
        filterResultsPage.filterByBaths(webdriver, bath);

        String result = filterResultsPage.verifyFilterByBaths(webdriver, bath);

        try {
            Assert.assertEquals(result, "yes", "Filter by baths failed");
            log.info("Successfully filtered results by baths: " + bath);
        } catch (AssertionError e) {
            log.error("Filtering by baths failed", e);
            ScreenshotURL.capture(webdriver, foldername, errorname, "didntfilterbybath");
            softAssert.fail();
        }

        softAssert.assertAll();
    }
}
