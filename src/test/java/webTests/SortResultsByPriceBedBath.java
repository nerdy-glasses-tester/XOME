package webTests;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import base.TestBase;
import webPageObjects.Search;
import webPageObjects.SortResults_Page;

public class SortResultsByPriceBedBath extends TestBase {

    //****************************************//
    //***                                  ***//
    //*** Created by Angela Tong Apr 2018  ***//
    //***                                  ***//
    //****************************************//

    static SoftAssert softAssert = new SoftAssert();
    final static Logger log = LogManager.getLogger(SortResultsByPriceBedBath.class);

    // ---------------------- Test Methods ----------------------

    @Test(groups = { "smoke", "regression" }, dataProvider = "getData")
    public void sortResultsByMostRecent(String searchKeyword) throws IOException, InterruptedException {
        Search search = new Search();
        search.searchByCity(webdriver, searchKeyword);

        SortResults_Page sortResultsPg = new SortResults_Page();
        boolean sorted = sortResultsPg.sortResultsByMostRecent(webdriver).equalsIgnoreCase("yes");

        Assert.assertTrue(sorted, "Failed to sort results by Most Recent");
        log.info("Verified sorting by Most Recent.");
    }

    @Test(groups = { "smoke", "regression" }, dataProvider = "getData")
    public void sortResultsByPrice(String searchKeyword) throws IOException, InterruptedException {
        Search search = new Search();
        search.searchByCity(webdriver, searchKeyword);

        SortResults_Page sortResultsPg = new SortResults_Page();

        boolean sortedDesc = sortResultsPg.sortResultsByDescPrice(webdriver).equalsIgnoreCase("yes");
        Assert.assertTrue(sortedDesc, "Failed to sort price by descending order");
        log.info("Verified sorting price descending.");

        boolean sortedAsc = sortResultsPg.sortResultsByAscPrice(webdriver).equalsIgnoreCase("yes");
        Assert.assertTrue(sortedAsc, "Failed to sort price by ascending order");
        log.info("Verified sorting price ascending.");
    }

    @Test(groups = { "smoke", "regression" }, dataProvider = "getData")
    public void sortResultsByBeds(String searchKeyword) throws IOException, InterruptedException {
        Search search = new Search();
        search.searchByCity(webdriver, searchKeyword);

        SortResults_Page sortResultsPg = new SortResults_Page();

        boolean sortedDesc = sortResultsPg.sortResultsByDescBeds(webdriver).equalsIgnoreCase("yes");
        Assert.assertTrue(sortedDesc, "Failed to sort beds by descending order");
        log.info("Verified sorting beds descending.");

        boolean sortedAsc = sortResultsPg.sortResultsByAscBeds(webdriver).equalsIgnoreCase("yes");
        Assert.assertTrue(sortedAsc, "Failed to sort beds by ascending order");
        log.info("Verified sorting beds ascending.");
    }

    @Test(groups = { "smoke", "regression" }, dataProvider = "getData")
    public void sortResultsByBaths(String searchKeyword) throws IOException, InterruptedException {
        Search search = new Search();
        search.searchByCity(webdriver, searchKeyword);

        SortResults_Page sortResultsPg = new SortResults_Page();

        boolean sortedDesc = sortResultsPg.sortResultsByDescBaths(webdriver).equalsIgnoreCase("yes");
        Assert.assertTrue(sortedDesc, "Failed to sort baths by descending order");
        log.info("Verified sorting baths descending.");

        boolean sortedAsc = sortResultsPg.sortResultsByAscBaths(webdriver).equalsIgnoreCase("yes");
        Assert.assertTrue(sortedAsc, "Failed to sort baths by ascending order");
        log.info("Verified sorting baths ascending.");
    }
}
