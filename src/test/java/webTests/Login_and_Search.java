package webTests;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import base.TestBase;
import webPageObjects.Login_Page;
import webPageObjects.Search;

public class Login_and_Search extends TestBase {

    //****************************************//
    //***                                  ***//
    //*** Created by Angela Tong Apr 2018  ***//
    //***                                  ***//
    //****************************************//

    static SoftAssert softAssert = new SoftAssert();
    final static Logger log = LogManager.getLogger(Login_and_Search.class);

    // ---------------------- Test Method ----------------------
    @Test(groups = { "smoke", "regression" }, dataProvider = "getData")
    public void loginSearch(String login, String password, String searchKeyword) throws IOException, InterruptedException {

        // ---------- Login ----------
        Login_Page loginpg = new Login_Page();
        loginpg.clickSignIn(webdriver);
        String signedInUsername = loginpg.login(webdriver, login, password);
        Thread.sleep(5000);

        try {
            Assert.assertEquals(signedInUsername, "Automation Tester");
            log.info("Verified signed in user: " + signedInUsername);
        } catch (AssertionError e) {
            log.error("Login verification failed. Expected 'Automation Tester', but got: " + signedInUsername, e);
            softAssert.fail();
        }

        // ---------- Search ----------
        Search search = new Search();
        boolean match = search.loginsearchfor6thhouseinresults(webdriver, searchKeyword);

        try {
            Assert.assertTrue(match, "Expected to find matching house in search results");
            log.info("Verified search results contain the expected house.");
        } catch (AssertionError e) {
            log.error("Search verification failed. Did not find the expected house.", e);
            softAssert.fail();
        }

        softAssert.assertAll();
    }
}
