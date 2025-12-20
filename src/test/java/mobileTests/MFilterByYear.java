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

public class MFilterByYear extends TestBase {
    
    //****************************************//
    //***                                  ***//
    //*** Created by Angela Tong Apr 2018  ***//
    //***                                  ***//
    //****************************************//
    
    private static final Logger log =
            LogManager.getLogger(MFilterByYear.class);
    
    static String className = MFilterByYear.class.getSimpleName();
    static Date date1 = new Date();
    static String originaltimestamp = new Timestamp(date1.getTime()).toString();
    static String timestamp = originaltimestamp.replace(':', 'x').substring(11);
    static String foldername = className + timestamp;
    static String errorname = "";



    @Test(groups = {"smoke", "regression"}, dataProvider = "getMobileData") 
    public void mFilterYear(String searchkeyword, String minyear, String maxyear) throws IOException, InterruptedException {

       // use driver created by TestBase
       AppiumDriver driver = mobileDriver;

        MSearch searchPage = new MSearch(driver);
        searchPage.searchHomes(searchkeyword);

        MFilterResults_Page mfilterresultspg = new MFilterResults_Page(driver);
        mfilterresultspg.clickFilterOption();
        mfilterresultspg.clickFilterByMinYear(minyear);
        mfilterresultspg.clickFilterByMaxYear(maxyear);
        mfilterresultspg.clickApplyFilter();

        String diditfilter = mfilterresultspg.verifyFilterByYear(minyear, maxyear);

        try {
            Assert.assertEquals(diditfilter, "yes");
        } catch (AssertionError e1) { 
            log.error("Didn't filter by year correctly.", e1.getMessage());
            errorname = "didntfilterybyyearcorrectly";
            ScreenshotURL.capture(driver, foldername, errorname, "Didn't filter by year");

        }


    }
}
