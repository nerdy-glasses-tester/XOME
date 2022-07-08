package mobilePageObjects;

import java.time.Duration;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.AppiumDriver;

public class MSortResults_Page {
	//****************************************//
	//***                                  ***//
	//*** Created by Angela Tong Apr 2018  ***//
	//***                                  ***//
	//****************************************//
	final static Logger log = LogManager.getLogger(MSortResults_Page.class);

	private By viewByList = By.id("com.xome.android:id/search_view_type_text");
	private By sortoption = By.id("com.xome.android:id/layers_or_sort_label");
	private By mostrecent = By.id("com.xome.android:id/most_recent_radio_button");
	private By pricelowtohigh = By.id("com.xome.android:id/price_low_to_high_radio_button");
	private By pricehightolow = By.id("com.xome.android:id/price_high_to_low_radio_button");
	private By beds = By.id("com.xome.android:id/beds_radio_button");
	private By baths = By.id("com.xome.android:id/baths_radio_button");
	private By squarefeet = By.id("com.xome.android:id/sqft_radio_button");
	
	private By propertyprice = By.id("com.xome.android:id/listing_card_price");
	private By propertybed = By.id("com.xome.android:id/listing_card_beds_value");
	private By propertybath = By.id("com.xome.android:id/listing_card_baths_value");
	private By propertysquarefeet = By.id("com.xome.android:id/listing_card_sq_ft_value");
	private By property1newtag = By.id("com.xome.android:id/listing_card_new_label");
	private By property2newtag = By.id("com.xome.android:id/listing_card_new_label");
	private By property3newtag = By.id("com.xome.android:id/listing_card_new_label");
	
	
	private String diditsort = "";

	public void clickSortOption (AppiumDriver driver) throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
		WebElement sortoptionelement = wait.until(ExpectedConditions.elementToBeClickable(sortoption));
		sortoptionelement.click();
		Thread.sleep(4000); //Must wait for this time to load
	}
	
	
	public void sortByBeds (AppiumDriver driver) throws InterruptedException
	{	
		WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
		MSortResults_Page msrp = new MSortResults_Page();
		msrp.clickSortOption(driver);
		
		WebElement bedselement = wait.until(ExpectedConditions.elementToBeClickable(beds));
		bedselement.click();
		Thread.sleep(4000);
	}
	
	public void sortByBaths (AppiumDriver driver) throws InterruptedException
	{	
		WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
		MSortResults_Page msrp = new MSortResults_Page();
		msrp.clickSortOption(driver);
		
		WebElement bathselement = wait.until(ExpectedConditions.elementToBeClickable(baths));
		bathselement.click();
		Thread.sleep(4000);
	}
	
	
	public void sortByLowtoHighPrice (AppiumDriver driver) throws InterruptedException
	{	
		WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
		WebElement viewbylistelement = wait.until(ExpectedConditions.elementToBeClickable(viewByList));
		viewbylistelement.click();
		Thread.sleep(4000);
		
		MSortResults_Page msrp = new MSortResults_Page();
		msrp.clickSortOption(driver);
		
		WebElement pricelowtohighelement = wait.until(ExpectedConditions.elementToBeClickable(pricelowtohigh));
		pricelowtohighelement.click();
		Thread.sleep(4000);
	}
	
	public void sortByHightoLowPrice (AppiumDriver driver) throws InterruptedException
	{	
		WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
		WebElement viewbylistelement = wait.until(ExpectedConditions.elementToBeClickable(viewByList));
		viewbylistelement.click();
		Thread.sleep(4000);
		
		MSortResults_Page msrp = new MSortResults_Page();
		msrp.clickSortOption(driver);
		
		WebElement pricehightolowelement = wait.until(ExpectedConditions.elementToBeClickable(pricehightolow));
		pricehightolowelement.click();
		Thread.sleep(4000);
	}
	
	
	public void sortBySqFt (AppiumDriver driver) throws InterruptedException
	{	
		WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
		WebElement viewbylistelement = wait.until(ExpectedConditions.elementToBeClickable(viewByList));
		viewbylistelement.click();
		Thread.sleep(4000);
		
		MSortResults_Page msrp = new MSortResults_Page();
		msrp.clickSortOption(driver);
		
		WebElement squarefeetelement = wait.until(ExpectedConditions.elementToBeClickable(squarefeet));
		squarefeetelement.click();
		Thread.sleep(4000);
	}
	
	
	public void sortByMostRecent (AppiumDriver driver) throws InterruptedException
	{	
		WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
		
		MSortResults_Page msrp = new MSortResults_Page();
		msrp.clickSortOption(driver);
		
		WebElement mostrecentelement = wait.until(ExpectedConditions.elementToBeClickable(mostrecent));
		mostrecentelement.click();
		Thread.sleep(4000);
	}
	
	
	public String verifySortByLowtoHighPrice (AppiumDriver driver) throws InterruptedException 
	{	
		WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));

		
		List <WebElement> propertypricelist = (List<WebElement>) driver.findElements(propertyprice);
		WebElement prop1priceelement = propertypricelist.get(0);
		String prop1pricestring = prop1priceelement.getText();
		prop1pricestring = prop1pricestring.replaceAll("\\$", "");
		prop1pricestring = prop1pricestring.replaceAll(",", "");
		int prop1price = Integer.parseInt(prop1pricestring);
		
		WebElement prop2priceelement = propertypricelist.get(1);
		String prop2pricestring = prop2priceelement.getText();
		prop2pricestring = prop2pricestring.replaceAll("\\$", "");
		prop2pricestring = prop2pricestring.replaceAll(",", "");
		int prop2price = Integer.parseInt(prop2pricestring);
		
		WebElement prop3priceelement = propertypricelist.get(2);
		String prop3pricestring = prop3priceelement.getText();
		prop3pricestring = prop3pricestring.replaceAll("\\$", "");
		prop3pricestring = prop3pricestring.replaceAll(",", "");
		int prop3price = Integer.parseInt(prop3pricestring);

		if ( prop1price <= prop2price && prop2price <= prop3price)
		{
			diditsort="yes";
			log.info("It sorted by low to high price successfully.");
		}
		else
		{
			diditsort="no";
			log.error("It sorted by low to high price unsuccessfully.");
		}
		
		return diditsort;
	}
	
	
	public String verifySortByHightoLowPrice (AppiumDriver driver) throws InterruptedException
	{	
		WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));	
		
		List <WebElement> propertypricelist = (List<WebElement>) driver.findElements(propertyprice);
		WebElement prop1priceelement = propertypricelist.get(0);
		String prop1pricestring = prop1priceelement.getText();
		prop1pricestring = prop1pricestring.replaceAll("\\$", "");
		prop1pricestring = prop1pricestring.replaceAll(",", "");
		int prop1price = Integer.parseInt(prop1pricestring);
		
		WebElement prop2priceelement = propertypricelist.get(1);
		String prop2pricestring = prop2priceelement.getText();
		prop2pricestring = prop2pricestring.replaceAll("\\$", "");
		prop2pricestring = prop2pricestring.replaceAll(",", "");
		int prop2price = Integer.parseInt(prop2pricestring);
		
		WebElement prop3priceelement = propertypricelist.get(2);
		String prop3pricestring = prop3priceelement.getText();
		prop3pricestring = prop3pricestring.replaceAll("\\$", "");
		prop3pricestring = prop3pricestring.replaceAll(",", "");
		int prop3price = Integer.parseInt(prop3pricestring);

		if ( prop1price >= prop2price && prop2price >= prop3price)
		{
			diditsort="yes";
			log.info("It sorted by high to low price successfully.");
		}
		else
		{
			diditsort="no";
			log.error("It sorted by high to low price unsuccessfully.");
		}
		
		return diditsort;
	}
		
	
	public String verifySortBySquareFeet (AppiumDriver driver) throws InterruptedException 
	{	
		WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
		
		List <WebElement> propertysquarefeetlist = (List<WebElement>) driver.findElements(propertysquarefeet);
		WebElement prop1squarefeetelement = propertysquarefeetlist.get(0);
		String prop1squarefeetstring = prop1squarefeetelement.getText();
		int prop1squarefeet = Integer.parseInt(prop1squarefeetstring);
		
		WebElement prop2squarefeetelement = propertysquarefeetlist.get(1);
		String prop2squarefeetstring = prop2squarefeetelement.getText();
		int prop2squarefeet = Integer.parseInt(prop2squarefeetstring);
		
		WebElement prop3squarefeetelement = propertysquarefeetlist.get(2);
		String prop3squarefeetstring = prop3squarefeetelement.getText();
		int prop3squarefeet = Integer.parseInt(prop3squarefeetstring);

		if (prop1squarefeet <= prop2squarefeet && prop2squarefeet <= prop3squarefeet)
		{
			diditsort="yes";
			log.info("It sorted by square feet successfully.");
		}
		else
		{
			diditsort="no";
			log.error("It sorted by square feet unsuccessfully.");
		}
		
		return diditsort;
	}
	
	public String verifySortByMostRecent (AppiumDriver driver) throws InterruptedException 
	{	
		WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));

		
		List <WebElement> property1newtaglist = (List<WebElement>) driver.findElements(property1newtag);
		List <WebElement> property2newtaglist = (List<WebElement>) driver.findElements(property2newtag);
		List <WebElement> property3newtaglist = (List<WebElement>) driver.findElements(property3newtag);

		
		String prop1newtag = "";
		String prop2newtag = "";
		String prop3newtag = "";
		String prop1newtagsort = "";
		String prop2newtagsort = "";
		String prop3newtagsort = "";
		

		
		if (property1newtaglist.size() >0)
		{
			WebElement property1newtag = property1newtaglist.get(0);
			prop1newtag = property1newtag.getText();
		}
		else
		{
			prop1newtagsort = "no";
		}

		if (property2newtaglist.size() >0)
		{
			WebElement property2newtag = property2newtaglist.get(0);
			prop2newtag = property2newtag.getText();
		}
		else
		{
			prop2newtagsort = "no";
		}
		
		if (property3newtaglist.size() >0)
		{
			WebElement property3newtag = property3newtaglist.get(0);
			prop3newtag = property3newtag.getText();
		}
		else
		{
			prop3newtagsort = "no";
		}
		
		
		
		
		
		
		if  (prop1newtag.equalsIgnoreCase("NEW")  && prop2newtag.equalsIgnoreCase("NEW") && prop3newtag.equalsIgnoreCase("NEW"))
		{
			diditsort="yes";
			log.info("It sorted by most recent successfully.");
		}
		else
		{
			diditsort="no";
			log.error("It sorted by most recent unsuccessfully.");
		}
		
		return diditsort;
	}

	
	public String sortAndVerifyByBeds (AppiumDriver driver) throws InterruptedException
	{		
		WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
		
		//Have to sort by high price to get large bedrooms then sort by beds to compare with smaller number of bedrooms
		MSortResults_Page msrp = new MSortResults_Page();
		msrp.sortByHightoLowPrice(driver);
		Thread.sleep(4000);
				
		
		List <WebElement> propertybedlist = (List<WebElement>) driver.findElements(propertybed);
		WebElement prop1bedelement = propertybedlist.get(0);
		String prop1bedstring = prop1bedelement.getText();
		int prop1bed = Integer.parseInt(prop1bedstring);
		log.info("prop1bed: "+prop1bed);
		
		WebElement prop2bedelement = propertybedlist.get(1);
		String prop2bedstring = prop2bedelement.getText();
		int prop2bed = Integer.parseInt(prop2bedstring);
		log.info("prop2bed: "+prop2bed);
		
		WebElement prop3bedelement = propertybedlist.get(2);
		String prop3bedstring = prop3bedelement.getText();
		int prop3bed = Integer.parseInt(prop3bedstring);
		log.info("prop3bed: "+prop3bed);
		
		if (prop1bed >= prop2bed && prop2bed >= prop3bed )
		{
			diditsort="yes";
			log.info("It sorted by bed successfully.");
		}
		else
		{
			diditsort="no";
			log.error("It sorted by bed unsuccessfully.");
		}
		
		return diditsort;
	}
	
	public String sortAndVerifyByBaths (AppiumDriver driver) throws InterruptedException
	{	
		WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));		

		//Have to sort by high price to get large bathrooms then sort by baths to compare with smaller number of bathrooms
		MSortResults_Page msrp = new MSortResults_Page();
		msrp.sortByHightoLowPrice(driver);
		Thread.sleep(4000);
				
		
		List <WebElement> propertybathlist = (List<WebElement>) driver.findElements(propertybath);
		WebElement prop1bathelement = propertybathlist.get(0);
		String prop1bathstring = prop1bathelement.getText();
		int prop1bath = Integer.parseInt(prop1bathstring);
		log.info("prop1bath: "+prop1bath);
		
		WebElement prop2bathelement = propertybathlist.get(1);
		String prop2bathstring = prop2bathelement.getText();
		int prop2bath = Integer.parseInt(prop2bathstring);
		log.info("prop2bath: "+prop2bath);
		
		WebElement prop3bathelement = propertybathlist.get(2);
		String prop3bathstring = prop3bathelement.getText();
		int prop3bath = Integer.parseInt(prop3bathstring);
		log.info("prop3bath: "+prop3bath);
		
		if (prop1bath >=prop2bath && prop2bath >= prop3bath)
		{
			diditsort="yes";
			log.info("It sorted by bath successfully.");
		}
		else
		{
			diditsort="no";
			log.error("It sorted by bath unsuccessfully.");
		}
		
		return diditsort;
	}
	
	
	
}
