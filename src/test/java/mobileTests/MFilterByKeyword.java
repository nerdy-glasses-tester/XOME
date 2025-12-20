package mobileTests;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.TestBase;
import common.ScreenshotURL;
import io.appium.java_client.AppiumDriver;
import mobilePageObjects.MFilterResults_Page;
//import mobilePageObjects.MLogin_Page;
import mobilePageObjects.MSearch;

public class MFilterByKeyword extends TestBase {

    //****************************************//
    //***                                  ***//
    //*** Created by Angela Tong Apr 2018  ***//
    //***                                  ***//
    //****************************************//

    final static Logger log = LogManager.getLogger(MFilterByKeyword.class);

    static String className = MFilterByKeyword.class.getSimpleName();
    static Date date1 = new Date();
    static String originaltimestamp = new Timestamp(date1.getTime()).toString();
    static String timestamp = originaltimestamp.replace(':', 'x').substring(11);
    static String foldername = className + timestamp;
    static String errorname = "";

    @Test(groups = {"smoke", "regression"}, dataProvider = "getMobileData")
    public void mFilterKeyword(String searchkeyword, String keyword) throws IOException, InterruptedException {

        // use driver created by TestBase
        AppiumDriver driver = mobileDriver;

        if (driver instanceof TakesScreenshot) {
            TakesScreenshot screenshotDriver = (TakesScreenshot) driver;

            MSearch msearch = new MSearch(driver);
            msearch.searchHomes(searchkeyword);

            MFilterResults_Page mfilterresultspg = new MFilterResults_Page(driver);
            mfilterresultspg.clickFilterOption();
            mfilterresultspg.clickFilterByKeyword(keyword);
            mfilterresultspg.clickApplyFilter();
            Thread.sleep(4000);

            String diditfilter = mfilterresultspg.verifyFilterByKeyword(keyword);

            try {
                Assert.assertEquals(diditfilter, "yes");
            } catch (AssertionError e) {
                log.error("Didn't filter by keyword correctly.", e.getMessage());
                errorname = "didntfilterbykeywordcorrectly";

                // Correctly call ScreenshotURL.capture()
                ScreenshotURL.capture(screenshotDriver, foldername, errorname, "Didn't filter by keyword");
                throw e;

            }

        } else {
            log.error("Driver does not support screenshots.");
        }
    }
}

