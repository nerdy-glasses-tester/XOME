package mobileTests;

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
import mobilePageObjects.MFilterResults_Page;
import mobilePageObjects.MLogin_Page;
import mobilePageObjects.MSearch;


public class MFilterByPendingStatus extends TestBase{
	
    //****************************************//
	//***                                  ***//
	//*** Created by Angela Tong Apr 2018  ***//
	//***                                  ***//
	//****************************************//
	
	static SoftAssert softAssert = new SoftAssert();
	final static Logger log = LogManager.getLogger(MFilterByPendingStatus.class);
	
	
	static String className = MFilterByPendingStatus.class.getSimpleName();
 	static Date date1= new Date();
 	static String originaltimestamp = new Timestamp(date1.getTime()).toString();
 	static String timestamp = originaltimestamp.replace(':', 'x').substring(11);
	static String foldername = className+timestamp;
	static String errorname = "";



	
	@Test(groups= {"smoke", "regression"}, dataProvider = "getMobileData") 
	public void mFilterPending (String searchkeyword) throws IOException, InterruptedException
	{
		MLogin_Page mloginpg = new MLogin_Page();
		mloginpg.allowDeviceLocationAccess(driver);
		MSearch msearch = new MSearch();
		msearch.searchhomes(driver, searchkeyword);
	    MFilterResults_Page mfilterresultspg = new MFilterResults_Page();
	    mfilterresultspg.clickFilterOption(driver);
	    mfilterresultspg.uncheckAllStatus(driver);
	    mfilterresultspg.checkPendingStatus(driver);
	    mfilterresultspg.clickApplyFilter(driver);
		String diditfilter = mfilterresultspg.verifyPendingStatus(driver);

		try{
			Assert.assertEquals(diditfilter, "yes");
		} 
		catch(AssertionError e)
		{ 
			log.error("Didn't filter by pending sales correctly.", e.getMessage());
			errorname = "didntfilterybypendingcorrectly";
			ScreenshotURL.screenshotURL(driver, foldername, errorname);
			softAssert.fail();
		}


		   softAssert.assertAll();
	}

	
}
