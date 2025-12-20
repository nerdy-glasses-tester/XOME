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

public class MFilterBySqFt extends TestBase {

    //****************************************//
    //***                                  ***//
    //*** Created by Angela Tong Apr 2018  ***//
    //***                                  ***//
    //****************************************//

    final static Logger log = LogManager.getLogger(MFilterBySqFt.class);

    static String className = MFilterBySqFt.class.getSimpleName();
    static Date date1 = new Date();
    static String originaltimestamp = new Timestamp(date1.getTime()).toString();
    static String timestamp = originaltimestamp.replace(':', 'x').substring(11);
    static String foldername = className + timestamp;
    static String errorname = "";


    @Test(groups = {"smoke", "regression"}, dataProvider = "getMobileData") 
    public void mFilterSqFt(String searchkeyword, String minsqft, String maxsqft) throws IOException, InterruptedException {
        // use driver created by TestBase
        AppiumDriver driver = mobileDriver;
        
        MSearch msearch = new MSearch(driver);
        msearch.searchHomes(searchkeyword);

        MFilterResults_Page mfilterresultspg = new MFilterResults_Page(driver);
        mfilterresultspg.clickFilterOption();
        mfilterresultspg.clickFilterByMinSqFt(minsqft);
        mfilterresultspg.clickFilterByMaxSqFt(maxsqft);
        mfilterresultspg.clickApplyFilter();

        String diditfilter = mfilterresultspg.verifyFilterBySqFt(minsqft, maxsqft);

        try {
            Assert.assertEquals(diditfilter, "yes");
        } catch (AssertionError e) {
            log.error("Didn't filter by square feet correctly.", e.getMessage());
            errorname = "didntfilterybysqftcorrectly";
            ScreenshotURL.capture(driver, foldername, errorname, "Didn't filter by square feet");
            throw e;
        }


    }
}
