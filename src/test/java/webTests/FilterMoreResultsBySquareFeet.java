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
import webPageObjects.FilterMoreResults_Page;
import webPageObjects.Search;

public class FilterMoreResultsBySquareFeet extends TestBase{
	
    //****************************************//
	//***                                  ***//
	//*** Created by Angela Tong Apr 2018  ***//
	//***                                  ***//
	//****************************************//
	
	static SoftAssert softAssert = new SoftAssert();
	final static Logger log = LogManager.getLogger(FilterMoreResultsBySquareFeet.class);
	
	
	static String className = FilterMoreResultsBySquareFeet.class.getSimpleName();
 	static Date date1= new Date();
 	static String originaltimestamp = new Timestamp(date1.getTime()).toString();
 	static String timestamp = originaltimestamp.replace(':', 'x').substring(11);
	static String foldername = className+timestamp;
	static String errorname = "";

	@Test(groups= {"smoke", "regression"}, dataProvider = "getData") 
	public void filterResultsBySqFeet (String searchkeyword, String minsqfeet, String maxsqfeet) throws IOException, InterruptedException
	{
		Search search = new Search();
		search.searchByCity(webdriver, searchkeyword);
		FilterMoreResults_Page filtermoreresultspg = new FilterMoreResults_Page();
		filtermoreresultspg.clickOpenMoreFilters(webdriver);
		filtermoreresultspg.filterBySqFeet(webdriver, minsqfeet, maxsqfeet);
		filtermoreresultspg.applyMoreFilters(webdriver);
		String diditfilter = filtermoreresultspg.verifyFilterBySqFeet(webdriver, minsqfeet, maxsqfeet);

		try{
			Assert.assertEquals(diditfilter, "yes");
		} 
		catch(AssertionError e)
		{ 
			log.error("Didn't filter by square feet.", e.getMessage());
			errorname = "didntfilterbysqft";
			ScreenshotURL.screenshotURL(webdriver, foldername, errorname);
			softAssert.fail();
		}

		   softAssert.assertAll();
		
	}
}
