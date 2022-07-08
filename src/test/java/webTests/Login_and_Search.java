package webTests;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import base.TestBase;
import common.ScreenshotURL;
import webPageObjects.Login_Page;
import webPageObjects.Search;

public class Login_and_Search extends TestBase{
	
    //****************************************//
	//***                                  ***//
	//*** Created by Angela Tong Apr 2018  ***//
	//***                                  ***//
	//****************************************//
	
	//Login and search by city, browse to the 12th house and click on pic, then verify address on details page and scroll through pics in details page

	static SoftAssert softAssert = new SoftAssert();
	final static Logger log = LogManager.getLogger(Logger.class.getName());
	
	
	static String className = Login_and_Search.class.getSimpleName();
 	static Date date1= new Date();
 	static String originaltimestamp = new Timestamp(date1.getTime()).toString();
 	static String timestamp = originaltimestamp.replace(':', 'x').substring(11);
	static String foldername = className+timestamp;
	static String errorname = "";

		
	@Test(groups= {"smoke", "regression"}, dataProvider = "getData") 
	public void loginSearch (String login, String password, String searchkeyword) throws IOException, InterruptedException
	{
		Login_Page loginpg = new Login_Page();
		loginpg.clickSignIn(webdriver);
		String signedinusername = loginpg.login(webdriver, login, password);
		Thread.sleep(5000);
		
		try{
			Assert.assertEquals(signedinusername, "Automation Tester");
			log.info("Verified sign in user.");
		} 
		catch(AssertionError e)
		{ 
			log.error("Didn't login as Automation Tester.", e.getMessage());
			errorname = "didntloginasAutomationTester";
			ScreenshotURL.screenshotURL(webdriver, foldername, errorname);
			softAssert.fail();
		}
		

		
		Search search = new Search();
		boolean match = search.loginsearchfor6thhouseinresults(webdriver, searchkeyword);
		
		try{
			Assert.assertEquals(match,  true);
			log.info("Verified search results.");
		} 
		catch(AssertionError e)
		{ 
			log.error("Didn't scroll down the list of search results.", e.getMessage());
			errorname = "didntscrollsearchresults";
			ScreenshotURL.screenshotURL(webdriver, foldername, errorname);
			softAssert.fail();
		}

		
		   softAssert.assertAll();
	}

}
