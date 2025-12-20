package mobileTests;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.TestBase;
import common.ScreenshotURL;
import io.appium.java_client.AppiumDriver;
import mobilePageObjects.MFilterResults_Page;
import mobilePageObjects.MSearch;

public class MFilterByBedBath extends TestBase {

    //****************************************//
    //***                                  ***//
    //*** Created by Angela Tong Apr 2018  ***//
    //*** Updated for unified driver      ***//
    //****************************************//

    final static Logger log = LogManager.getLogger(MFilterByBedBath.class);

    static String className = MFilterByBedBath.class.getSimpleName();
    static Date date1 = new Date();
    static String originalTimestamp = new Timestamp(date1.getTime()).toString();
    static String timestamp = originalTimestamp.replace(':', 'x').substring(11);
    static String folderName = className + timestamp;
    static String errorName = "";

    @Test(groups = {"smoke", "regression"}, dataProvider = "getMobileData")
    public void mFilterBed(String searchKeyword, String beds) throws IOException, InterruptedException {

    
        // use driver created by TestBase
        AppiumDriver driver = mobileDriver;
    	
        MSearch msearch = new MSearch(driver);
        msearch.searchHomes(searchKeyword);

        MFilterResults_Page mfilterresultspg = new MFilterResults_Page(driver);
        mfilterresultspg.clickFilterOption();
        mfilterresultspg.clickFilterByBed(beds);
        mfilterresultspg.clickApplyFilter();

        String didItFilter = mfilterresultspg.verifyFilterByBeds(beds);

        try {
            Assert.assertEquals(didItFilter, "yes");
        } catch (AssertionError e) {
            log.error("Didn't filter by beds correctly.", e);
            errorName = "didntfilterbybedscorrectly";
            ScreenshotURL.capture(mobileDriver, folderName, errorName, didItFilter);
            throw e;
        }

    }

    @Test(groups = {"smoke", "regression"}, dataProvider = "getMobileData")
    public void mFilterBath(String searchKeyword, String baths) throws IOException, InterruptedException {
        
        // use driver created by TestBase
        AppiumDriver driver = mobileDriver;

        MSearch msearch = new MSearch(driver);
        msearch.searchHomes(searchKeyword);

        MFilterResults_Page mfilterresultspg = new MFilterResults_Page(driver);
        mfilterresultspg.clickFilterOption();
        mfilterresultspg.clickFilterByBath(baths);
        mfilterresultspg.clickApplyFilter();

        String didItFilter = mfilterresultspg.verifyFilterByBaths(baths);

        try {
            Assert.assertEquals(didItFilter, "yes");
        } catch (AssertionError e) {
            log.error("Didn't filter by baths correctly.", e);
            errorName = "didntfilterbybathscorrectly";
            ScreenshotURL.capture(mobileDriver, folderName, errorName, "Didn't filter by baths");
            throw e;
        }

    }
}
