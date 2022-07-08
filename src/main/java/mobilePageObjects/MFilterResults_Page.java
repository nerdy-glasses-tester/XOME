package mobilePageObjects;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.ImmutableMap;

import common.MiscMethods;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;

public class MFilterResults_Page {
	    //****************************************//
		//***                                  ***//
		//*** Created by Angela Tong Apr 2018  ***//
		//***                                  ***//
		//****************************************//
		final static Logger log = LogManager.getLogger(MFilterResults_Page.class);

		private By viewByList = By.id("com.xome.android:id/search_view_type_text");

		private By filterbtn = By.id("com.xome.android:id/filters_btn_label");
		private By forsalestatus = By.id("com.xome.android:id/for_sale_listing_status_switch");
		private By contingentstatus = By.id("com.xome.android:id/contingent_listing_status_switch");
		private By pendingstatus = By.id("com.xome.android:id/pending_listing_status_switch");
		private By soldstatus = By.id("com.xome.android:id/sold_listing_status_switch");
		private By bedsdrpdown = By.id("com.xome.android:id/bed_drop_down");
		private By bedselection = By.id("com.xome.android:id/filter_option_text");
		private By bathsdrpdown = By.id("com.xome.android:id/bath_drop_down");
		private By bathselection = By.id("com.xome.android:id/filter_option_text");
		private By sqftmin = By.id("com.xome.android:id/min_square_feet_drop_down");
		private By sqftmax = By.id("com.xome.android:id/max_square_feet_drop_down");
		private By sqftselection = By.id("com.xome.android:id/filter_option_text");
		private By yrmin = By.id("com.xome.android:id/min_year_built_drop_down");
		private By yrmax = By.id("com.xome.android:id/max_year_built_drop_down");
		private By yrselection = By.id("com.xome.android:id/filter_option_text");
		private By listingDetails = By.id("com.xome.android:id/ldp_listing_details_view");
		private By yearBuilt = By.id("com.xome.android:id/record_value");
		private By propertyhouse = By.id("com.xome.android:id/property_type_house");
		private By propertytownhome = By.id("com.xome.android:id/property_type_townhome");
		private By propertycommercial = By.id("com.xome.android:id/listing_id_type_commercial");
		private By propertyvacantland = By.id("com.xome.android:id/property_type_vacant_land");
		private By propertycondo = By.id("com.xome.android:id/property_type_condo");
		private By propertymultifamily = By.id("com.xome.android:id/property_type_multi_family");
		private By propertyfarm = By.id("com.xome.android:id/property_type_farm_ranch");
		private By propertymobile = By.id("com.xome.android:id/property_type_mfd_mobile");
		private By propertyother = By.id("com.xome.android:id/property_type_by_other");
		private By keywordfield = By.id("com.xome.android:id/remarks_input");
		private By applybtn = By.id("com.xome.android:id/view_listings_button");
		
		private By pendingtag = By.id("com.xome.android:id/listing_card_listing_status_label");
		private By    soldtag = By.id("com.xome.android:id/listing_card_listing_status_label");
		private By contingenttag = By.id("com.xome.android:id/listing_card_listing_status_label");
		private By forsaletag = By.id("com.xome.android:id/listing_card_listing_status_label");
		private By propertybed = By.id("com.xome.android:id/listing_card_beds_value");
		private By propertybath = By.id("com.xome.android:id/listing_card_baths_value");
		private By propertysqft = By.id("com.xome.android:id/listing_card_sq_ft_value");
		private By propertypic = By.id("com.xome.android:id/listing_card_image_pager");
		private By propertydescription = By.id("com.xome.android:id/ldp_desc_value");
		private By propertydescriptionshowmorebutton = By.id("com.xome.android:id/ldp_desc_show_more_or_less_btn");
		private By propertytype = By.id("com.xome.android:id/ldp_prop_type_value");

		private String diditfilter = "";
		
		public void clickFilterOption (AppiumDriver driver) throws InterruptedException 
		{
			WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
			WebElement filterbtnelement = wait.until(ExpectedConditions.elementToBeClickable(filterbtn));
			filterbtnelement.click();
			Thread.sleep(4000); //Must wait for this time to load
		}
		
		public void uncheckAllStatus (AppiumDriver driver) throws InterruptedException
		{
			
			WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
			WebElement forsalestatuselement = wait.until(ExpectedConditions.elementToBeClickable(forsalestatus));
			WebElement contingentstatuselement = wait.until(ExpectedConditions.elementToBeClickable(contingentstatus));
			WebElement pendingstatuselement = wait.until(ExpectedConditions.elementToBeClickable(pendingstatus));
			WebElement soldstatuselement = wait.until(ExpectedConditions.elementToBeClickable(soldstatus));
			
			String forsaleselected = forsalestatuselement.getAttribute("checked");
			String contingentselected = contingentstatuselement.getAttribute("checked");
			String pendingselected = pendingstatuselement.getAttribute("checked");
			String soldselected = soldstatuselement.getAttribute("checked");
			
			if (forsaleselected.equals("true"))
			{
				forsalestatuselement.click();
				Thread.sleep(2000);
				log.info("For sale status is unchecked.");
			}
			else
			{
				log.info("For sale status is unchecked.");
			}
			
			if (contingentselected.equals("true"))
			{
				contingentstatuselement.click();
				Thread.sleep(2000);
				log.info("Contingent status is unchecked.");
			}
			else
			{
				log.info("Contingent status is unchecked.");
			}
			
			if (pendingselected.equals("true"))
			{
				pendingstatuselement.click();
				log.info("Pending status is unchecked.");
			}
			else 
			{
				log.info("Pending status is unchecked.");
			}
			
			if (soldselected.equals("true"))
			{
				soldstatuselement.click();
				log.info("Sold status is unchecked.");
			}
			else
			{
				log.info("Sold status is unchecked.");
			}
			
			Thread.sleep(4000);
		}
		
		public void checkPendingStatus (AppiumDriver driver) throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
			WebElement pendingstatuselement = wait.until(ExpectedConditions.elementToBeClickable(pendingstatus));
			pendingstatuselement.click();
			log.info("Pending status is checked.");
			Thread.sleep(4000);
		}
		
		public void checkSoldStatus (AppiumDriver driver) throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
			WebElement soldstatuselement = wait.until(ExpectedConditions.elementToBeClickable(soldstatus));
			soldstatuselement.click();
			log.info("Sold status is checked.");
			Thread.sleep(4000);
		}
		
		public void checkContingentStatus (AppiumDriver driver) throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
			WebElement contingentstatuselement = wait.until(ExpectedConditions.elementToBeClickable(contingentstatus));
			contingentstatuselement.click();
			log.info("Contingent status is checked.");
			Thread.sleep(4000);
		}
		
		public void checkForSaleStatus (AppiumDriver driver) throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
			WebElement forsalestatuselement = wait.until(ExpectedConditions.elementToBeClickable(forsalestatus));
			forsalestatuselement.click();
			log.info("For sale status is checked.");
			Thread.sleep(4000);
		}
		
		public void clickFilterByBed (AppiumDriver driver, String beds) throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
			WebElement bedsdrpdownelement = wait.until(ExpectedConditions.elementToBeClickable(bedsdrpdown));
			bedsdrpdownelement.click();
			Thread.sleep(4000);
			
			int numofbeds = Integer.parseInt(beds);
			List <WebElement> bedslist = (List<WebElement>) driver.findElements(bedselection);
			WebElement numofbedsselection = bedslist.get(numofbeds);
			numofbedsselection.click();
			Thread.sleep(4000);
		}
		
		public void clickFilterByBath (AppiumDriver driver, String baths) throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
			WebElement bathsdrpdownelement = wait.until(ExpectedConditions.elementToBeClickable(bathsdrpdown));
			bathsdrpdownelement.click();
			Thread.sleep(4000);
			
			int numofbaths = Integer.parseInt(baths);
			List <WebElement> bathslist = (List<WebElement>) driver.findElements(bathselection);
			WebElement numofbathsselection = bathslist.get(numofbaths);
			numofbathsselection.click();
			Thread.sleep(4000);
		}
		
		public void clickFilterByMinSqFt (AppiumDriver driver, String minsqft) throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
			WebElement sqftminelement = wait.until(ExpectedConditions.elementToBeClickable(sqftmin));
			sqftminelement.click();
			Thread.sleep(4000);
			
			List <WebElement> sqftselectionlist = (List<WebElement>) driver.findElements(sqftselection);
			
			
			switch (minsqft)
			{
				case "500": 
					WebElement sqft1 = sqftselectionlist.get(1);
					sqft1.click();
					break;
				case "750":
					WebElement sqft2 = sqftselectionlist.get(2);
					sqft2.click();
					break;
				case "1000":
					WebElement sqft3 = sqftselectionlist.get(3);
					sqft3.click();
					break;
				case "1250":
					WebElement sqft4 = sqftselectionlist.get(4);
					sqft4.click();
					break;
				case "1500":
					WebElement sqft5 = sqftselectionlist.get(5);
					sqft5.click();
					break;
				case "1750":
					WebElement sqft6 = sqftselectionlist.get(6);
					sqft6.click();
					break;
			}
			
			Thread.sleep(4000);	

		}
		
		
		public void clickFilterByMaxSqFt (AppiumDriver driver, String maxsqft) throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
			WebElement sqftmaxelement = wait.until(ExpectedConditions.elementToBeClickable(sqftmax));
			sqftmaxelement.click();
			Thread.sleep(4000);	
			
			List <WebElement> sqftselectionlist = (List<WebElement>) driver.findElements(sqftselection);
			
			
			switch (maxsqft)
			{
				case "7500": 
					WebElement sqft1 = sqftselectionlist.get(1);
					sqft1.click();
					break;
				case "5000":
					WebElement sqft2 = sqftselectionlist.get(2);
					sqft2.click();
					break;
				case "4000":
					WebElement sqft3 = sqftselectionlist.get(3);
					sqft3.click();
					break;
				case "3500":
					WebElement sqft4 = sqftselectionlist.get(4);
					sqft4.click();
					break;
				case "3000":
					WebElement sqft5 = sqftselectionlist.get(5);
					sqft5.click();
					break;
				case "2750":
					WebElement sqft6 = sqftselectionlist.get(6);
					sqft6.click();
					break;
			}
			
			Thread.sleep(4000);	

		}
		
		public void clickFilterByKeyword (AppiumDriver driver, String keyword) throws InterruptedException, IOException
		{
			WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
			MiscMethods.swipeUp75(driver);
			WebElement keywordelement = wait.until(ExpectedConditions.elementToBeClickable(keywordfield));
			keywordelement.click();
			keywordelement.clear();
			keywordelement.sendKeys(keyword);
			Thread.sleep(4000);		
			
		
		}	
		
		public void clickFilterByMinYear (AppiumDriver driver, String minyear) throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
			WebElement yrminelement = wait.until(ExpectedConditions.elementToBeClickable(yrmin));
			yrminelement.click();
			Thread.sleep(4000);
			
			List <WebElement> yrselectionlist = (List<WebElement>) driver.findElements(yrselection);
			
			switch (minyear)
			{
				case "1900":
					WebElement yrselectionelement1 = yrselectionlist.get(1);
					yrselectionelement1.click();
					break;
				case "1920":
					WebElement yrselectionelement2 = yrselectionlist.get(2);
					yrselectionelement2.click();
					break;
				case "1940":
					WebElement yrselectionelement3 = yrselectionlist.get(3);
					yrselectionelement3.click();
					break;
				case "1950":
					WebElement yrselectionelement4 = yrselectionlist.get(4);
					yrselectionelement4.click();
					break;
				case "1960":
					WebElement yrselectionelement5 = yrselectionlist.get(5);
					yrselectionelement5.click();
					break;
				case "1970":
					WebElement yrselectionelement6 = yrselectionlist.get(6);
					yrselectionelement6.click();
					break;
			}
			
			Thread.sleep(4000);
			
		}
		
		
		public void clickFilterByMaxYear (AppiumDriver driver, String maxyear) throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
			WebElement yrmaxelement = wait.until(ExpectedConditions.elementToBeClickable(yrmax));
			yrmaxelement.click();
			Thread.sleep(4000);
			
			List <WebElement> yrselectionlist = (List<WebElement>) driver.findElements(yrselection);
			
			switch (maxyear)
			{
				case "2020":
					WebElement yrselectionelement1 = yrselectionlist.get(1);
					yrselectionelement1.click();
					break;
				case "2019":
					WebElement yrselectionelement2 = yrselectionlist.get(2);
					yrselectionelement2.click();
					break;
				case "2018":
					WebElement yrselectionelement3 = yrselectionlist.get(3);
					yrselectionelement3.click();
					break;
				case "2017":
					WebElement yrselectionelement4 = yrselectionlist.get(4);
					yrselectionelement4.click();
					break;
				case "2016":
					WebElement yrselectionelement5 = yrselectionlist.get(5);
					yrselectionelement5.click();
					break;
				case "2015":
					WebElement yrselectionelement6 = yrselectionlist.get(6);
					yrselectionelement6.click();
					break;
			}
			
			Thread.sleep(4000);
			
		}
		
		public String verifyFilterByYear (AppiumDriver driver, String minyear, String maxyear) throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
			WebElement viewbylistelement = wait.until(ExpectedConditions.elementToBeClickable(viewByList));
			viewbylistelement.click();
			Thread.sleep(4000);
			
			int min_year = Integer.parseInt(minyear);
			int max_year = Integer.parseInt(maxyear);
			
			
			List <WebElement> propertypiclist = (List<WebElement>) driver.findElements(propertypic);
			WebElement property1pic = propertypiclist.get(0);
			property1pic.click();
			Thread.sleep(4000);

			MiscMethods.scrollUp(driver);
	        Thread.sleep(4000);
	        
	        MiscMethods.scrollDown(driver);
	        Thread.sleep(4000);

	        
	        WebElement listingDetailsElement = wait.until(ExpectedConditions.elementToBeClickable(listingDetails));
	        listingDetailsElement.click();
	        
	        
	        MiscMethods.scrollDown1(driver);
	        Thread.sleep(4000);
	        
	        //Year is the 7th record value
	        List <WebElement> yearBuiltElement = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(yearBuilt));
	        String yearofproperty1 = yearBuiltElement.get(6).getText();
			
	        System.out.println("Yearofproperty"+yearofproperty1);
	        
			int yearofproperty = Integer.parseInt(yearofproperty1);
				
		    if(yearofproperty >= min_year && yearofproperty <= max_year)
			{
				
				diditfilter="yes";
				log.info("It filter by min year "+minyear+" and max year "+maxyear+" successfully.");
			}
			else
			{
				diditfilter="no";
				log.error("It filter by min year "+minyear+" and max year "+maxyear+" unsuccessfully.");
			}
			
			return diditfilter;
			
		}
			
		public String verifyFilterByKeyword (AppiumDriver driver, String keyword) throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
			WebElement viewbylistelement = wait.until(ExpectedConditions.elementToBeClickable(viewByList));
			viewbylistelement.click();
			Thread.sleep(4000);
			
			
			List <WebElement> propertypiclist = (List<WebElement>) driver.findElements(propertypic);
			WebElement property1pic = propertypiclist.get(0);
			property1pic.click();
			Thread.sleep(4000);
			
			MiscMethods.scrollUp(driver);
	        Thread.sleep(4000);
	        
	        MiscMethods.scrollDown(driver);
	        Thread.sleep(4000);
			
			WebElement showmorelink = wait.until(ExpectedConditions.elementToBeClickable(propertydescriptionshowmorebutton));
			showmorelink.click();
			
				
			WebElement propdescriptionelement1 = wait.until(ExpectedConditions.elementToBeClickable(propertydescription));
			String propdescriptiontext1 = propdescriptionelement1.getText();
			Boolean propkeywordmatch1 = Pattern.compile(Pattern.quote(keyword), Pattern.CASE_INSENSITIVE).matcher(propdescriptiontext1).find();
			
			
			if (propkeywordmatch1.equals(true))
			{
				diditfilter="yes";
				log.info("It filter by keyword "+keyword+" successfully.");
			}
			else
			{
				diditfilter="no";
				log.error("It filter by keyword "+keyword+" unsuccessfully.");
			}
			
			return diditfilter;
		}
		
		public String verifyFilterBySqFt (AppiumDriver driver, String minsqft, String maxsqft) throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
			WebElement viewbylistelement = wait.until(ExpectedConditions.elementToBeClickable(viewByList));
			viewbylistelement.click();
			Thread.sleep(4000);
			
		    List <WebElement> sqftlist = (List<WebElement>) driver.findElements(propertysqft);
		    WebElement prop1sqftelement = sqftlist.get(0);
		    String prop1sqfttext = prop1sqftelement.getText();
		    prop1sqfttext = prop1sqfttext.replaceAll(",", "");
		    int prop1sqft = Integer.parseInt(prop1sqfttext);
		    
		    WebElement prop2sqftelement = sqftlist.get(0);
		    String prop2sqfttext = prop2sqftelement.getText();
		    prop2sqfttext = prop2sqfttext.replaceAll(",", "");
		    int prop2sqft = Integer.parseInt(prop2sqfttext);
		    
		    WebElement prop3sqftelement = sqftlist.get(0);
		    String prop3sqfttext = prop3sqftelement.getText();
		    prop3sqfttext = prop3sqfttext.replaceAll(",", "");
		    int prop3sqft = Integer.parseInt(prop3sqfttext);
			
		    int min_sqft = Integer.parseInt(minsqft);
		    int max_sqft = Integer.parseInt(maxsqft);
		    
		    if ((prop1sqft >= min_sqft && prop1sqft <= max_sqft) && (prop2sqft >= min_sqft && prop2sqft <= max_sqft)  && (prop3sqft >= min_sqft && prop3sqft <= max_sqft) )
		    {
		    		diditfilter="yes";
		    		log.info("It filter by min square feet  "+minsqft+" and max square feet "+maxsqft+" successfully.");
		    }
		    else
		    {
	    			diditfilter="no";
		    		log.error("It filter by min square feet "+minsqft+" and max square feet "+maxsqft+" unsuccessfully.");
		    }
		    
			return diditfilter;
		}
		
		public String verifyFilterByBeds (AppiumDriver driver, String beds) throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
			WebElement viewbylistelement = wait.until(ExpectedConditions.elementToBeClickable(viewByList));
			viewbylistelement.click();
			Thread.sleep(4000);
			
			int numofbeds = Integer.parseInt(beds);
			
			List <WebElement> bedlist = (List<WebElement>) driver.findElements(propertybed);
			WebElement prop1bedelement = bedlist.get(0);
			String prop1bedtext = prop1bedelement.getText();
			int prop1bed = Integer.parseInt(prop1bedtext);
			
			WebElement prop2bedelement = bedlist.get(1);
			String prop2bedtext = prop2bedelement.getText();
			int prop2bed = Integer.parseInt(prop2bedtext);
		
			WebElement prop3bedelement = bedlist.get(2);
			String prop3bedtext = prop3bedelement.getText();
			int prop3bed = Integer.parseInt(prop3bedtext);
			
			if(prop1bed>=numofbeds && prop2bed>=numofbeds && prop3bed>=numofbeds)
			{
				diditfilter="yes";
				log.info("It filtered by "+numofbeds+" beds successfully.");
			}
			else
			{
				diditfilter="no";
				log.error("It filtered by "+numofbeds+" beds unsuccessfully.");
			}
			
			return diditfilter;
		}
		
		public String verifyFilterByBaths (AppiumDriver driver, String baths) throws InterruptedException
		{
			
			WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
			WebElement viewbylistelement = wait.until(ExpectedConditions.elementToBeClickable(viewByList));
			viewbylistelement.click();
			Thread.sleep(4000);
			
			int numofbaths = Integer.parseInt(baths);
			
			List <WebElement> bathlist = (List<WebElement>) driver.findElements(propertybath);
			WebElement prop1bathelement = bathlist.get(0);
			String prop1bathtext = prop1bathelement.getText();
			int prop1bath = Integer.parseInt(prop1bathtext);
			
			WebElement prop2bathelement = bathlist.get(1);
			String prop2bathtext = prop2bathelement.getText();
			int prop2bath = Integer.parseInt(prop2bathtext);
		
			WebElement prop3bathelement = bathlist.get(2);
			String prop3bathtext = prop3bathelement.getText();
			int prop3bath = Integer.parseInt(prop3bathtext);
			
			if(prop1bath>=numofbaths && prop2bath>=numofbaths && prop3bath>=numofbaths)
			{
				diditfilter="yes";
				log.info("It filtered by "+numofbaths+" baths successfully.");
			}
			else
			{
				diditfilter="no";
				log.error("It filtered by "+numofbaths+" baths unsuccessfully.");
			}
			
			return diditfilter;
		}
		

		public String verifyPendingStatus (AppiumDriver driver) throws InterruptedException
		{
			Thread.sleep(3000);
			WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
			WebElement viewbylistelement = wait.until(ExpectedConditions.elementToBeClickable(viewByList));
			viewbylistelement.click();
			Thread.sleep(4000);

			List <WebElement> pendinglist = (List<WebElement>) driver.findElements(pendingtag);
			
			WebElement prop1pendingelement = pendinglist.get(0);
			String prop1pending = prop1pendingelement.getText();
			
			WebElement prop2pendingelement = pendinglist.get(0);
			String prop2pending = prop2pendingelement.getText();
			
			WebElement prop3pendingelement = pendinglist.get(0);
			String prop3pending = prop3pendingelement.getText();
			
			if (prop1pending.equalsIgnoreCase("PENDING") && prop2pending.equalsIgnoreCase("PENDING") && prop3pending.equalsIgnoreCase("PENDING"))
			{
				diditfilter="yes";
				log.info("It filtered by pending status successfully.");
			}
			else
			{
				diditfilter="no";
				log.error("It filtered by pending status unsuccessfully.");
			}
			
			return diditfilter;
		}
		
		public String verifySoldStatus (AppiumDriver driver) throws InterruptedException
		{
			Thread.sleep(3000);
			WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
			WebElement viewbylistelement = wait.until(ExpectedConditions.elementToBeClickable(viewByList));
			viewbylistelement.click();
			Thread.sleep(4000);
			;
			List <WebElement> soldlist = (List<WebElement>) driver.findElements(soldtag);
			
			WebElement prop1soldelement = soldlist.get(0);
			String prop1sold = prop1soldelement.getText();
			
			WebElement prop2soldelement = soldlist.get(0);
			String prop2sold = prop2soldelement.getText();
			
			WebElement prop3soldelement = soldlist.get(0);
			String prop3sold = prop3soldelement.getText();
			
			if (prop1sold.contains("SOLD") && prop2sold.contains("SOLD") && prop3sold.contains("SOLD"))
			{
				diditfilter="yes";
				log.info("It filtered by sold status successfully.");
			}
			else
			{
				diditfilter="no";
				log.error("It filtered by sold status unsuccessfully.");
			}
			
			return diditfilter;
		}
		
		public String verifyForSaleStatus (AppiumDriver driver) throws InterruptedException
		{
			Thread.sleep(3000);
			WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
			WebElement viewbylistelement = wait.until(ExpectedConditions.elementToBeClickable(viewByList));
			viewbylistelement.click();
			Thread.sleep(4000);
			
			List <WebElement> activelist = (List<WebElement>) driver.findElements(forsaletag);
			
			WebElement prop1activeelement = activelist.get(0);
			String prop1active = prop1activeelement.getText();
			
			WebElement prop2activeelement = activelist.get(0);
			String prop2active = prop2activeelement.getText();
			
			WebElement prop3activeelement = activelist.get(0);
			String prop3active = prop3activeelement.getText();
			
			if (prop1active.equalsIgnoreCase("FOR SALE") && prop2active.equalsIgnoreCase("FOR SALE") && prop3active.equalsIgnoreCase("FOR SALE"))
			{
				diditfilter="yes";
				log.info("It filtered by for sale active status successfully.");
			}
			else
			{
				diditfilter="no";
				log.error("It filtered by for sale active status unsuccessfully.");
			}
			
			return diditfilter;
		}
		
		
		public void uncheckAllProperty (AppiumDriver driver) throws InterruptedException
		{
			MiscMethods.swipeUp75(driver);
			Thread.sleep(4000);
			WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
			WebElement propertyhouseelement = wait.until(ExpectedConditions.elementToBeClickable(propertyhouse));
			WebElement propertytownhomeelement = wait.until(ExpectedConditions.elementToBeClickable(propertytownhome));
			WebElement propertycommercialelement = wait.until(ExpectedConditions.elementToBeClickable(propertycommercial));
			WebElement propertyvacantlandelement = wait.until(ExpectedConditions.elementToBeClickable(propertyvacantland));
			WebElement propertycondoelement = wait.until(ExpectedConditions.elementToBeClickable(propertycondo));
			WebElement propertymultifamilyelement = wait.until(ExpectedConditions.elementToBeClickable(propertymultifamily));
			WebElement propertyfarmelement = wait.until(ExpectedConditions.elementToBeClickable(propertyfarm));
			WebElement propertymobileelement = wait.until(ExpectedConditions.elementToBeClickable(propertymobile));
			WebElement propertyotherelement = wait.until(ExpectedConditions.elementToBeClickable(propertyother));
			
			
			propertyhouseelement.click();
			Thread.sleep(2000);
			propertytownhomeelement.click();
			Thread.sleep(2000);
			propertyvacantlandelement.click();
			Thread.sleep(2000);
			propertycondoelement.click();
			Thread.sleep(2000);
			propertymultifamilyelement.click();
			Thread.sleep(2000);
			
		}
		
		
		public void clickSingleFamily (AppiumDriver driver) throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
			WebElement propertysinglefamilyelement = wait.until(ExpectedConditions.elementToBeClickable(propertyhouse));
			propertysinglefamilyelement.click();
		    Thread.sleep(4000);
		}
		
		public void clickTownHome (AppiumDriver driver) throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
			WebElement propertytownhomeelement = wait.until(ExpectedConditions.elementToBeClickable(propertytownhome));
			propertytownhomeelement.click();
		    Thread.sleep(4000);
		}
		
		public void clickCondo (AppiumDriver driver) throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
			WebElement propertycondoelement = wait.until(ExpectedConditions.elementToBeClickable(propertycondo));
			propertycondoelement.click();
		    Thread.sleep(4000);
		}
		
		public String verifyFilterBySingleFamily (AppiumDriver driver) throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
			WebElement viewbylistelement = wait.until(ExpectedConditions.elementToBeClickable(viewByList));
			viewbylistelement.click();
			Thread.sleep(4000);

			List <WebElement> propertypiclist = (List<WebElement>) driver.findElements(propertypic);
			WebElement property1pic = propertypiclist.get(0);
			property1pic.click();
			Thread.sleep(6000);
			
			WebElement property1typeelement = wait.until(ExpectedConditions.visibilityOfElementLocated(propertytype));
			String property1typetext = property1typeelement.getText();
			log.info(property1typetext);
			
			Boolean propertytypematch1 = Pattern.compile(Pattern.quote("Single Family"), Pattern.CASE_INSENSITIVE).matcher(property1typetext).find();

			
			if (propertytypematch1.equals(true))
			{
				diditfilter="yes";
				log.info("It filter by Single Family successfully.");
			}
			else
			{
				diditfilter="no";
				log.error("It filter by Single Family unsuccessfully.");
			}
			
			return diditfilter;

		}
		
		
		public String verifyFilterByTownHome (AppiumDriver driver) throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
			WebElement viewbylistelement = wait.until(ExpectedConditions.elementToBeClickable(viewByList));
			viewbylistelement.click();
			Thread.sleep(4000);

			List <WebElement> propertypiclist = (List<WebElement>) driver.findElements(propertypic);
			WebElement property1pic = propertypiclist.get(0);
			property1pic.click();
			Thread.sleep(6000);
			
			WebElement property1typeelement = wait.until(ExpectedConditions.visibilityOfElementLocated(propertytype));
			String property1typetext = property1typeelement.getText();
			log.info(property1typetext);

			Boolean propertytypematch1 = Pattern.compile(Pattern.quote("Townhouse"), Pattern.CASE_INSENSITIVE).matcher(property1typetext).find();
			
			
			if (propertytypematch1.equals(true))
			{
				diditfilter="yes";
				log.info("It filter by Townhouse successfully.");
			}
			else
			{
				diditfilter="no";
				log.error("It filter by Townhouse unsuccessfully.");
			}
			
			return diditfilter;

		}
		
		
		public String verifyFilterByCondo (AppiumDriver driver) throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
			WebElement viewbylistelement = wait.until(ExpectedConditions.elementToBeClickable(viewByList));
			viewbylistelement.click();
			Thread.sleep(4000);

			List <WebElement> propertypiclist = (List<WebElement>) driver.findElements(propertypic);
			WebElement property1pic = propertypiclist.get(0);
			property1pic.click();
			Thread.sleep(6000);
			
			WebElement property1typeelement = wait.until(ExpectedConditions.visibilityOfElementLocated(propertytype));
			String property1typetext = property1typeelement.getText();
			log.info(property1typetext);
			
			Boolean propertytypematch1 = Pattern.compile(Pattern.quote("Condo"), Pattern.CASE_INSENSITIVE).matcher(property1typetext).find();
			
			
			if (propertytypematch1.equals(true))
			{
				diditfilter="yes";
				log.info("It filter by condo successfully.");
			}
			else
			{
				diditfilter="no";
				log.error("It filter by condo unsuccessfully.");
			}
			
			return diditfilter;

		}
		
		
		public void clickApplyFilter (AppiumDriver driver) throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
			WebElement applybtnelement = wait.until(ExpectedConditions.elementToBeClickable(applybtn));
			applybtnelement.click();
			
			Thread.sleep(4000); //Must wait for this time to load
		}
		
		
		public void firstOpenAppFilterBySingleFamilyTownHomeCondo (AppiumDriver driver) throws InterruptedException
		{	
            MFilterResults_Page mfrp = new MFilterResults_Page();
            mfrp.clickFilterOption(driver);
            mfrp.uncheckAllProperty(driver);
            mfrp.clickSingleFamily(driver);
            mfrp.clickTownHome(driver);
            mfrp.clickCondo(driver);
            mfrp.clickApplyFilter(driver);

		}
}
