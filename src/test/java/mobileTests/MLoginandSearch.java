package mobileTests;

import java.sql.Timestamp;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.TestBase;
import common.ScreenshotURL;
import io.appium.java_client.AppiumDriver;
import mobilePageObjects.MLogin_Page;
import mobilePageObjects.MSearch;


public class MLoginandSearch extends TestBase {

   final static Logger log = LogManager.getLogger(MLoginandSearch.class);

    static String className = MLoginandSearch.class.getSimpleName();
    static Date date1 = new Date();
    static String originaltimestamp = new Timestamp(date1.getTime()).toString();
    static String timestamp = originaltimestamp.replace(':', 'x').substring(11);
    static String foldername = className + timestamp;
    static String errorname = "";



    @Test(groups = {"smoke", "regression"}, dataProvider = "getMobileData")
    public void mobileLoginAndSearch(
            String login,
            String password,
            String searchKeyword,
            String name) throws InterruptedException {
    	    

        // use driver created by TestBase
        AppiumDriver driver = mobileDriver;

        MLogin_Page loginPage = new MLogin_Page(driver);
        MSearch searchPage = new MSearch(driver);

        log.info("Attempting login for {}", login);

        String didLogin =
                loginPage.mobileLogin(login, password, searchKeyword, name);
        try{
            Assert.assertEquals(didLogin, "yes", "Login failed");
        } catch (AssertionError e) {
            log.error("Didn't login correctly.", e.getMessage());
            errorname = "didntlogincorrectly";

            // Correctly call ScreenshotURL.capture()
            ScreenshotURL.capture(driver, foldername, errorname, "Didn't login correctly");
            throw e;

        }


        searchPage.clickSearchToDismissLeftMenu();
        searchPage.searchHomes(searchKeyword);

        String didItFilter =
                searchPage.verifySearchResults(searchKeyword);
        try{
            Assert.assertEquals(
                didItFilter, "yes", "Search verification failed");
        } catch (AssertionError e) {
            log.error("Search verification failed.", e.getMessage());
            errorname = "searchverificationfailed";

            // Correctly call ScreenshotURL.capture()
            ScreenshotURL.capture(driver, foldername, errorname, "Search verification failed.");
            throw e;

        }





    }
}

