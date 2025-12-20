package mobilePageObjects;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import common.MiscMethods;
import io.appium.java_client.AppiumDriver;
import base.TestBase;

public class MFilterResults_Page extends TestBase{
	    //****************************************//
		//***                                  ***//
		//*** Created by Angela Tong Apr 2018  ***//
		//***                                  ***//
		//****************************************//
    	final static Logger log = LogManager.getLogger(MFilterResults_Page.class);

    	private AppiumDriver driver;

		//private By viewByList = By.id("com.xome.android:id/search_view_type_text");
		private By filterbtn = By.id("com.xome.android:id/menu_filter_tv");
		private By activestatus = By.id("com.xome.android:id/active_status_tv");
		private By pendingstatus = By.id("com.xome.android:id/pending_status_tv");
		private By soldstatus = By.id("com.xome.android:id/sold_status_tv");
		private By bedsdrpdown = By.id("com.xome.android:id/beds_spinner");
		private By bedselection = By.id("android:id/text1");
		private By bathsdrpdown = By.id("com.xome.android:id/bath_spinner");
		private By bathselection = By.id("android:id/text1");
		private By sqftmin = By.id("com.xome.android:id/filter_sqr_foot_min");
		private By sqftmax = By.id("com.xome.android:id/filter_sqr_foot_max");
		private By sqftselection = By.id("android:id/text1");
		private By yrmin = By.id("com.xome.android:id/filter_year_built_min");
		private By yrmax = By.id("com.xome.android:id/filter_year_built_max");
		private By yrselection = By.id("android:id/text1");
		private By listingDetails = By.id("com.xome.android:id/list");
		private By yearBuilt = By.xpath("//android.widget.TextView[@resource-id=\"com.xome.android:id/row_label\" and @text=\"Year Built:\"]/following-sibling::android.widget.TextView[1]");
		private By propertyhouse = By.id("com.xome.android:id/single_family_type_tv");
		private By propertytownhome = By.id("com.xome.android:id/townhome_type_tv");
		private By propertycondo = By.id("com.xome.android:id/condo_type_tv");
		private By keywordfield = By.id("com.xome.android:id/keyword_search_edit_text");
		private By applybtn = By.id("com.xome.android:id/apply");
		private By pendingtag = By.id("com.xome.android:id/listing_summary_status");
		private By    soldtag = By.id("com.xome.android:id/listing_summary_status");
		private By forsaletag = By.id("com.xome.android:id/listing_summary_status");
		private By propertybed = By.id("com.xome.android:id/listing_summary_bedrooms_textView");
		private By propertybath = By.id("com.xome.android:id/listing_summary_bathrooms_textView");
		private By propertysqft = By.id("com.xome.android:id/listing_summary_square_feet_textView");
		private By propertypic = By.id("com.xome.android:id/listing_summary_imageViewPager");
		private By propertydescription = By.id("com.xome.android:id/description");
		private By propertydescriptionshowmorebutton = By.id("com.xome.android:id/ldTvMoreButton");
		private By propertytype = By.xpath("//android.widget.TextView[@resource-id=\"com.xome.android:id/row_label\" and @text=\"Property Type:\"]/following-sibling::android.widget.TextView[1]");

		private String diditfilter = "";

		public MFilterResults_Page(AppiumDriver driver) {
            this.driver = driver;
        }
		
		public void clickFilterOption () throws InterruptedException 
		{
			WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
			WebElement filterbtnelement = wait.until(ExpectedConditions.elementToBeClickable(filterbtn));
			filterbtnelement.click();
			Thread.sleep(4000); //Must wait for this time to load
		}
		
		public void uncheckAllStatus () throws InterruptedException
		{
			
			WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
			WebElement activestatuselement = wait.until(ExpectedConditions.elementToBeClickable(activestatus));
			WebElement pendingstatuselement = wait.until(ExpectedConditions.elementToBeClickable(pendingstatus));
			WebElement soldstatuselement = wait.until(ExpectedConditions.elementToBeClickable(soldstatus));
			
			String activeselected = activestatuselement.getAttribute("selected");
			String pendingselected = pendingstatuselement.getAttribute("selected");
			String soldselected = soldstatuselement.getAttribute("selected");
			
			if (activeselected.equals("true"))
			{
				activestatuselement.click();
				Thread.sleep(2000);
				log.info("Active status is unchecked.");
			}
			else
			{
				log.info("Active status is unchecked.");
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
		
		public void checkPendingStatus () throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
			WebElement pendingstatuselement = wait.until(ExpectedConditions.elementToBeClickable(pendingstatus));
			pendingstatuselement.click();
			log.info("Pending status is checked.");
			Thread.sleep(4000);
		}
		
		public void checkSoldStatus () throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
			WebElement soldstatuselement = wait.until(ExpectedConditions.elementToBeClickable(soldstatus));
			soldstatuselement.click();
			log.info("Sold status is checked.");
			Thread.sleep(4000);
		}
		
		
		public void checkActiveStatus () throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
			WebElement activestatuselement = wait.until(ExpectedConditions.elementToBeClickable(activestatus));
			activestatuselement.click();
			log.info("Active status is checked.");
			Thread.sleep(4000);
		}
		
		public void clickFilterByBed (String beds) throws InterruptedException
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
		
		public void clickFilterByBath (String baths) throws InterruptedException
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
		
		public void clickFilterByMinSqFt (String minsqft) throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
			WebElement sqftminelement = wait.until(ExpectedConditions.elementToBeClickable(sqftmin));
			sqftminelement.click();
			Thread.sleep(4000);
			
			List <WebElement> sqftselectionlist = (List<WebElement>) driver.findElements(sqftselection);
			
			
			switch (minsqft)
			{
				case "250": 
					WebElement sqft1 = sqftselectionlist.get(1);
					sqft1.click();
					break;
				case "500":
					WebElement sqft2 = sqftselectionlist.get(2);
					sqft2.click();
					break;
				case "750":
					WebElement sqft3 = sqftselectionlist.get(3);
					sqft3.click();
					break;
				case "1000":
					WebElement sqft4 = sqftselectionlist.get(4);
					sqft4.click();
					break;
				case "1250":
					WebElement sqft5 = sqftselectionlist.get(5);
					sqft5.click();
					break;
				case "1500":
					WebElement sqft6 = sqftselectionlist.get(6);
					sqft6.click();
					break;
				case "1750": 
					WebElement sqft7 = sqftselectionlist.get(7);
					sqft7.click();
					break;
				case "2000":
					WebElement sqft8 = sqftselectionlist.get(8);
					sqft8.click();
					break;
				case "2250":
					WebElement sqft9 = sqftselectionlist.get(9);
					sqft9.click();
					break;
				case "2500":
					WebElement sqft10 = sqftselectionlist.get(10);
					sqft10.click();
					break;
				case "2750":
					WebElement sqft11 = sqftselectionlist.get(11);
					sqft11.click();
					break;
				case "3000":
					WebElement sqft12 = sqftselectionlist.get(12);
					sqft12.click();
					break;	
				case "3500": 
					WebElement sqft13 = sqftselectionlist.get(13);
					sqft13.click();
					break;
				case "4000":
					WebElement sqft14 = sqftselectionlist.get(14);
					sqft14.click();
					break;
				case "5000":
					WebElement sqft15 = sqftselectionlist.get(15);
					sqft15.click();
					break;
				case "7500":
					WebElement sqft16 = sqftselectionlist.get(16);
					sqft16.click();
					break;									
			}
			
			Thread.sleep(4000);	

		}
		
		
		public void clickFilterByMaxSqFt (String maxsqft) throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
			WebElement sqftmaxelement = wait.until(ExpectedConditions.elementToBeClickable(sqftmax));
			sqftmaxelement.click();
			Thread.sleep(4000);	
			
			List <WebElement> sqftselectionlist = (List<WebElement>) driver.findElements(sqftselection);
			
			
			switch (maxsqft)
			{
				case "250": 
					WebElement sqft1 = sqftselectionlist.get(1);
					sqft1.click();
					break;
				case "500":
					WebElement sqft2 = sqftselectionlist.get(2);
					sqft2.click();
					break;
				case "750":
					WebElement sqft3 = sqftselectionlist.get(3);
					sqft3.click();
					break;
				case "1000":
					WebElement sqft4 = sqftselectionlist.get(4);
					sqft4.click();
					break;
				case "1250":
					WebElement sqft5 = sqftselectionlist.get(5);
					sqft5.click();
					break;
				case "1500":
					WebElement sqft6 = sqftselectionlist.get(6);
					sqft6.click();
					break;
				case "1750": 
					WebElement sqft7 = sqftselectionlist.get(7);
					sqft7.click();
					break;
				case "2000":
					WebElement sqft8 = sqftselectionlist.get(8);
					sqft8.click();
					break;
				case "2250":
					WebElement sqft9 = sqftselectionlist.get(9);
					sqft9.click();
					break;
				case "2500":
					WebElement sqft10 = sqftselectionlist.get(10);
					sqft10.click();
					break;
				case "2750":
					WebElement sqft11 = sqftselectionlist.get(11);
					sqft11.click();
					break;
				case "3000":
					WebElement sqft12 = sqftselectionlist.get(12);
					sqft12.click();
					break;	
				case "3500": 
					WebElement sqft13 = sqftselectionlist.get(13);
					sqft13.click();
					break;
				case "4000":
					WebElement sqft14 = sqftselectionlist.get(14);
					sqft14.click();
					break;
				case "5000":
					WebElement sqft15 = sqftselectionlist.get(15);
					sqft15.click();
					break;
				case "7500":
					WebElement sqft16 = sqftselectionlist.get(16);
					sqft16.click();
					break;	
			}
			
			Thread.sleep(4000);	

		}
		
		public void clickFilterByKeyword (String keyword) throws InterruptedException, IOException
		{
			WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
	        MiscMethods.scrollDown(driver);
			WebElement keywordelement = wait.until(ExpectedConditions.elementToBeClickable(keywordfield));
			keywordelement.click();
			keywordelement.clear();
			keywordelement.sendKeys(keyword);
			Thread.sleep(4000);		
			
		
		}	
		
		public void clickFilterByMinYear (String minyear) throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
			WebElement yrminelement = wait.until(ExpectedConditions.elementToBeClickable(yrmin));
			yrminelement.click();
			Thread.sleep(4000);
			
			List <WebElement> yrselectionlist = (List<WebElement>) driver.findElements(yrselection);
			
			switch (minyear)
			{
				case "No Min":
					WebElement yrselectionelement1 = yrselectionlist.get(0);
					yrselectionelement1.click();
					break;
				case "2017":
					WebElement yrselectionelement2 = yrselectionlist.get(1);
					yrselectionelement2.click();
					break;
				case "2016":
					WebElement yrselectionelement3 = yrselectionlist.get(2);
					yrselectionelement3.click();
					break;
				case "2015":
					WebElement yrselectionelement4 = yrselectionlist.get(3);
					yrselectionelement4.click();
					break;
				case "2014":
					WebElement yrselectionelement5 = yrselectionlist.get(4);
					yrselectionelement5.click();
					break;
				case "2005":
					WebElement yrselectionelement6 = yrselectionlist.get(5);
					yrselectionelement6.click();
					break;
				case "2000":
					WebElement yrselectionelement7 = yrselectionlist.get(6);
					yrselectionelement7.click();
					break;
				case "1995":
					WebElement yrselectionelement8 = yrselectionlist.get(7);
					yrselectionelement8.click();
					break;
				case "1990":
					WebElement yrselectionelement9 = yrselectionlist.get(8);
					yrselectionelement9.click();
					break;
				case "1980":
					WebElement yrselectionelement10 = yrselectionlist.get(9);
					yrselectionelement10.click();
					break;
				case "1970":
					WebElement yrselectionelement11 = yrselectionlist.get(10);
					yrselectionelement11.click();
					break;	
				case "1960":
					WebElement yrselectionelement12 = yrselectionlist.get(11);
					yrselectionelement12.click();
					break;
				case "1950":
					WebElement yrselectionelement13 = yrselectionlist.get(12);
					yrselectionelement13.click();
					break;
				case "1940":
					WebElement yrselectionelement14 = yrselectionlist.get(13);
					yrselectionelement14.click();
					break;
				case "1920":
					WebElement yrselectionelement15 = yrselectionlist.get(14);
					yrselectionelement15.click();
					break;
				case "1900":
					WebElement yrselectionelement16 = yrselectionlist.get(15);
					yrselectionelement16.click();
					break;																							
			}
			
			Thread.sleep(4000);
			
		}
		
		
		public void clickFilterByMaxYear (String maxyear) throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
			WebElement yrmaxelement = wait.until(ExpectedConditions.elementToBeClickable(yrmax));
			yrmaxelement.click();
			Thread.sleep(4000);
			
			List <WebElement> yrselectionlist = (List<WebElement>) driver.findElements(yrselection);
			
			switch (maxyear)
			{
				case "No Max":
					WebElement yrselectionelement1 = yrselectionlist.get(0);
					yrselectionelement1.click();
					break;
				case "2017":
					WebElement yrselectionelement2 = yrselectionlist.get(1);
					yrselectionelement2.click();
					break;
				case "2016":
					WebElement yrselectionelement3 = yrselectionlist.get(2);
					yrselectionelement3.click();
					break;
				case "2015":
					WebElement yrselectionelement4 = yrselectionlist.get(3);
					yrselectionelement4.click();
					break;
				case "2014":
					WebElement yrselectionelement5 = yrselectionlist.get(4);
					yrselectionelement5.click();
					break;
				case "2005":
					WebElement yrselectionelement6 = yrselectionlist.get(5);
					yrselectionelement6.click();
					break;
				case "2000":
					WebElement yrselectionelement7 = yrselectionlist.get(6);
					yrselectionelement7.click();
					break;
				case "1995":
					WebElement yrselectionelement8 = yrselectionlist.get(7);
					yrselectionelement8.click();
					break;
				case "1990":
					WebElement yrselectionelement9 = yrselectionlist.get(8);
					yrselectionelement9.click();
					break;
				case "1980":
					WebElement yrselectionelement10 = yrselectionlist.get(9);
					yrselectionelement10.click();
					break;
				case "1970":
					WebElement yrselectionelement11 = yrselectionlist.get(10);
					yrselectionelement11.click();
					break;	
				case "1960":
					WebElement yrselectionelement12 = yrselectionlist.get(11);
					yrselectionelement12.click();
					break;
				case "1950":
					WebElement yrselectionelement13 = yrselectionlist.get(12);
					yrselectionelement13.click();
					break;
				case "1940":
					WebElement yrselectionelement14 = yrselectionlist.get(13);
					yrselectionelement14.click();
					break;
				case "1920":
					WebElement yrselectionelement15 = yrselectionlist.get(14);
					yrselectionelement15.click();
					break;
				case "1900":
					WebElement yrselectionelement16 = yrselectionlist.get(15);
					yrselectionelement16.click();
					break;							
			}
			
			Thread.sleep(4000);
			
		}
		
		public String verifyFilterByYear (String minyear, String maxyear) throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
			
			int min_year = Integer.parseInt(minyear);
			int max_year = Integer.parseInt(maxyear);
			
			
			List <WebElement> propertypiclist = (List<WebElement>) driver.findElements(propertypic);
			WebElement property1pic = propertypiclist.get(0);
			property1pic.click();
			Thread.sleep(4000);
	        
	        MiscMethods.scrollDown(driver);
	        Thread.sleep(4000);

	        
	        WebElement listingDetailsElement = wait.until(ExpectedConditions.elementToBeClickable(listingDetails));
	        listingDetailsElement.click();
	        
	        MiscMethods.scrollDown(driver);
	        Thread.sleep(4000);
	        
	        //Year is the 8th record value
	        WebElement yearBuiltElement = wait.until(ExpectedConditions.presenceOfElementLocated(yearBuilt));
	        String yearofproperty1 = yearBuiltElement.getText();
			
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
			
		public String verifyFilterByKeyword (String keyword) throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
			
			List <WebElement> propertypiclist = (List<WebElement>) driver.findElements(propertypic);
			WebElement property1pic = propertypiclist.get(0);
			property1pic.click();
			Thread.sleep(4000);
			
	        MiscMethods.scrollDown(driver);
	        Thread.sleep(4000);
			
			WebElement showmorelink = wait.until(ExpectedConditions.elementToBeClickable(propertydescriptionshowmorebutton));
			showmorelink.click();
			
			
	        MiscMethods.scrollDown(driver);
	        Thread.sleep(4000);
				
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
		
		public String verifyFilterBySqFt (String minsqft, String maxsqft) throws InterruptedException
		{
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
		
		public String verifyFilterByBeds (String beds) throws InterruptedException
		{

			
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
		
		public String verifyFilterByBaths (String baths) throws InterruptedException
		{

			
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
		

		public String verifyPendingStatus () throws InterruptedException
		{
			Thread.sleep(3000);


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
		
		public String verifySoldStatus () throws InterruptedException
		{
			Thread.sleep(3000);

			
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
		
		public String verifyForSaleStatus () throws InterruptedException
		{
			Thread.sleep(3000);
	
			
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
		
		
		
		
		public void clickSingleFamily () throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
			WebElement propertysinglefamilyelement = wait.until(ExpectedConditions.elementToBeClickable(propertyhouse));
			propertysinglefamilyelement.click();
		    Thread.sleep(4000);
		}
		
		public void clickTownHome () throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
			WebElement propertytownhomeelement = wait.until(ExpectedConditions.elementToBeClickable(propertytownhome));
			propertytownhomeelement.click();
		    Thread.sleep(4000);
		}
		
		public void clickCondo () throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
			WebElement propertycondoelement = wait.until(ExpectedConditions.elementToBeClickable(propertycondo));
			propertycondoelement.click();
		    Thread.sleep(4000);
		}
		
		public String verifyFilterBySingleFamily () throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));


			List <WebElement> propertypiclist = (List<WebElement>) driver.findElements(propertypic);
			WebElement property1pic = propertypiclist.get(0);
			property1pic.click();
			Thread.sleep(6000);
			
			MiscMethods.scrollDown(driver);
			
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
		
		
		public String verifyFilterByTownHome () throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));


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
		
		
		public String verifyFilterByCondo () throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));


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
		
		
		public void clickApplyFilter () throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
			WebElement applybtnelement = wait.until(ExpectedConditions.elementToBeClickable(applybtn));
			applybtnelement.click();
			
			Thread.sleep(4000); //Must wait for this time to load
		}
		
		
		public void firstOpenAppFilterBySingleFamilyTownHomeCondo () throws InterruptedException
		{	
            MFilterResults_Page mfrp = new MFilterResults_Page(driver);
            mfrp.clickFilterOption();
            mfrp.clickSingleFamily();
            mfrp.clickTownHome();
            mfrp.clickCondo();
            mfrp.clickApplyFilter();

		}
}
