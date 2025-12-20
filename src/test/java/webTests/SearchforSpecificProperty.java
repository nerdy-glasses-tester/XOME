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
import webPageObjects.Search;

public class SearchforSpecificProperty extends TestBase {

    //****************************************//
    //***                                  ***//
    //*** Created by Angela Tong Apr 2018  ***//
    //***                                  ***//
    //****************************************//

    static SoftAssert softAssert = new SoftAssert();
    final static Logger log = LogManager.getLogger(SearchforSpecificProperty.class);
    static String className = SearchforSpecificProperty.class.getSimpleName();
    static Date date1 = new Date();
    static String originaltimestamp = new Timestamp(date1.getTime()).toString();
    static String timestamp = originaltimestamp.replace(':', 'x').substring(11);
    static String foldername = className + timestamp;
    static String errorname = "";


    @Test(groups = { "smoke", "regression" }, dataProvider = "getData")
    public void searchSpecificProperty(String address) throws IOException, InterruptedException {

        Search search = new Search();
        String displayedAddress = search.searchandVerifyAddressSearched(webdriver, address);

        try {
            Assert.assertEquals(displayedAddress, address, "Details page doesn't show the searched address");
            log.info("Verified details page shows the searched address: " + displayedAddress);
        } catch (AssertionError e) {
            log.error("Details page did not show search-specific address", e);
            ScreenshotURL.capture(webdriver, foldername, errorname, "detailspgdidntshowsearchspecificaddress");
            softAssert.fail();
        }

        softAssert.assertAll();
    }
}
