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
import mobilePageObjects.MSearch;
import mobilePageObjects.MSortResults_Page;


public class MSortResultsByBedBathPriceSqFtMostRecent extends TestBase{
	
    //****************************************//
	//***                                  ***//
	//*** Created by Angela Tong Apr 2018  ***//
	//***                                  ***//
	//****************************************//
	
	final static Logger log = LogManager.getLogger(MSortResultsByBedBathPriceSqFtMostRecent.class);
	
	
	static String className = MSortResultsByBedBathPriceSqFtMostRecent.class.getSimpleName();
 	static Date date1= new Date();
 	static String originaltimestamp = new Timestamp(date1.getTime()).toString();
 	static String timestamp = originaltimestamp.replace(':', 'x').substring(11);
	static String foldername = className+timestamp;
	static String errorname = "";

	
	@Test(priority=1, groups= {"smoke", "regression"}, dataProvider = "getMobileData") 
	public void mSortByBed (String searchkeyword) throws IOException, InterruptedException {
        // use driver created by TestBase
        AppiumDriver driver = mobileDriver;
        
		Thread.sleep(4000);
		if (driver instanceof TakesScreenshot) {
            TakesScreenshot screenshotDriver = (TakesScreenshot) driver;

			MSearch msearch = new MSearch(driver);
			msearch.searchHomes(searchkeyword);
			MFilterResults_Page mfilterresultspg = new MFilterResults_Page(driver);
			mfilterresultspg.firstOpenAppFilterBySingleFamilyTownHomeCondo();
			Thread.sleep(4000);
			MSortResults_Page msortresultspg = new MSortResults_Page(driver);
			String diditsort = msortresultspg.sortAndVerifyByBeds();

			try{
				Assert.assertEquals(diditsort, "yes");
			} 
			catch(AssertionError e)
			{ 
				log.error("Didn't sort by beds correctly.", e.getMessage());
				errorname = "didntsortybybedscorrectly";
				ScreenshotURL.capture(screenshotDriver, foldername, errorname, "Didn't sort by beds correctly");
				throw e;
			}


		   } else {
            log.error("Driver does not support screenshots.");
        }

	}



	@Test(priority=2, groups= {"smoke", "regression"}, dataProvider = "getMobileData") 
	public void mSortByBath (String searchkeyword) throws IOException, InterruptedException {

        // use driver created by TestBase
        AppiumDriver driver = mobileDriver;
		Thread.sleep(4000);

		if (driver instanceof TakesScreenshot) {
            TakesScreenshot screenshotDriver = (TakesScreenshot) driver;		
			MSearch msearch = new MSearch(driver);
			msearch.searchHomes(searchkeyword);
			MFilterResults_Page mfilterresultspg = new MFilterResults_Page(driver);
			mfilterresultspg.firstOpenAppFilterBySingleFamilyTownHomeCondo();
			Thread.sleep(4000);
			MSortResults_Page msortresultspg = new MSortResults_Page(driver);
			String diditsort = msortresultspg.sortAndVerifyByBaths();


			try{
				Assert.assertEquals(diditsort, "yes");
			} 
			catch(AssertionError e)
			{ 
				log.error("Didn't sort by baths correctly.", e.getMessage());
				errorname = "didntsortbybathscorrectly";
				ScreenshotURL.capture(screenshotDriver, foldername, errorname, "Didn't sort by baths correctly");
				throw e;
			}

		   } else {
            log.error("Driver does not support screenshots.");
        }

	}
	
	
	@Test(priority=3, groups= {"smoke", "regression"}, dataProvider = "getMobileData") 
	public void mSortByLowtoHighPrice (String searchkeyword) throws IOException, InterruptedException {

        // use driver created by TestBase
        AppiumDriver driver = mobileDriver;
		Thread.sleep(4000);

		if (driver instanceof TakesScreenshot) {
            TakesScreenshot screenshotDriver = (TakesScreenshot) driver;		
			MSearch msearch = new MSearch(driver);
			msearch.searchHomes(searchkeyword);
			MFilterResults_Page mfilterresultspg = new MFilterResults_Page(driver);
			mfilterresultspg.firstOpenAppFilterBySingleFamilyTownHomeCondo();
			Thread.sleep(4000);
			MSortResults_Page msortresultspg = new MSortResults_Page(driver);
			msortresultspg.sortByLowtoHighPrice();
			String diditsort = msortresultspg.verifySortByLowtoHighPrice();

			try{
				Assert.assertEquals(diditsort, "yes");
			} 
			catch(AssertionError e)
			{ 
				log.error("Didn't sort by low to high price correctly.", e.getMessage());
				errorname = "didntsortbylowtohighpricecorrectly";
				ScreenshotURL.capture(screenshotDriver, foldername, errorname, "Didn't sort by low to high price correctly");
				throw e;
			}


		   } else {
            log.error("Driver does not support screenshots.");
        }

	}


	
	@Test(priority=4, groups= {"smoke", "regression"}, dataProvider = "getMobileData") 
	public void mSortByHightoLowPrice (String searchkeyword) throws IOException, InterruptedException {

        // use driver created by TestBase
        AppiumDriver driver = mobileDriver;
		Thread.sleep(4000);

		if (driver instanceof TakesScreenshot) {
            TakesScreenshot screenshotDriver = (TakesScreenshot) driver;		
			MSearch msearch = new MSearch(driver);
			msearch.searchHomes(searchkeyword);
			MFilterResults_Page mfilterresultspg = new MFilterResults_Page(driver);
			mfilterresultspg.firstOpenAppFilterBySingleFamilyTownHomeCondo();
			Thread.sleep(4000);
			MSortResults_Page msortresultspg = new MSortResults_Page(driver);
			msortresultspg.sortByHightoLowPrice();
			String diditsort = msortresultspg.verifySortByHightoLowPrice();

			try{
				Assert.assertEquals(diditsort, "yes");
			} 
			catch(AssertionError e)
			{ 
				log.error("Didn't sort by high to low price correctly.", e.getMessage());
				errorname = "didntsortbyhightolowpricecorrectly";
				ScreenshotURL.capture(screenshotDriver, foldername, errorname, "Didn't sort by hight to low price correctly");
				throw e;
			}


		   } else {
            log.error("Driver does not support screenshots.");
        }

	}
	
	
	@Test(priority=5, groups= {"smoke", "regression"}, dataProvider = "getMobileData") 
	public void mSortBySqFt (String searchkeyword) throws IOException, InterruptedException {

        // use driver created by TestBase
        AppiumDriver driver = mobileDriver;
		Thread.sleep(4000);

		if (driver instanceof TakesScreenshot) {
            TakesScreenshot screenshotDriver = (TakesScreenshot) driver;
			MSearch msearch = new MSearch(driver);
			msearch.searchHomes(searchkeyword);
			MFilterResults_Page mfilterresultspg = new MFilterResults_Page(driver);
			mfilterresultspg.firstOpenAppFilterBySingleFamilyTownHomeCondo();
			Thread.sleep(4000);
			MSortResults_Page msortresultspg = new MSortResults_Page(driver);
			msortresultspg.sortBySqFt();
			String diditsort = msortresultspg.verifySortBySquareFeet();

			try{
				Assert.assertEquals(diditsort, "yes");
			} 
			catch(AssertionError e)
			{ 
				log.error("Didn't sort by square feet correctly.", e.getMessage());
				errorname = "didntsortbysqftcorrectly";
				ScreenshotURL.capture(screenshotDriver, foldername, errorname, "Didn't sort by square feet correctly");
				throw e;
			}

		   } else {
            log.error("Driver does not support screenshots.");
        }

	}
	

	
	@Test(priority=6, groups= {"smoke", "regression"}, dataProvider = "getMobileData") 
	public void mSortByMostRecent (String searchkeyword) throws IOException, InterruptedException {

        // use driver created by TestBase
        AppiumDriver driver = mobileDriver;
		Thread.sleep(4000);

		if (driver instanceof TakesScreenshot) {
            TakesScreenshot screenshotDriver = (TakesScreenshot) driver;
			MSearch msearch = new MSearch(driver);
			msearch.searchHomes(searchkeyword);
			MFilterResults_Page mfilterresultspg = new MFilterResults_Page(driver);
			mfilterresultspg.firstOpenAppFilterBySingleFamilyTownHomeCondo();
			Thread.sleep(4000);
			MSortResults_Page msortresultspg = new MSortResults_Page(driver);
			msortresultspg.sortByHightoLowPrice();
			Thread.sleep(4000);
			msortresultspg.sortByMostRecent();
			String diditsort = msortresultspg.verifySortByMostRecent();

			try{
				Assert.assertEquals(diditsort, "yes");
			} 
			catch(AssertionError e)
			{ 
				log.error("Didn't sort by most recent correctly.", e.getMessage());
				errorname = "didntsortbymostrecentcorrectly";
				ScreenshotURL.capture(screenshotDriver, foldername, errorname, "Didn't sort by. most recent correctly");
				throw e;
			}

		   } else {
            log.error("Driver does not support screenshots.");
        }

	}
	

}
