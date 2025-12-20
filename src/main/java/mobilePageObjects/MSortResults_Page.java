package mobilePageObjects;

import java.time.Duration;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import base.TestBase;
import io.appium.java_client.AppiumDriver;

public class MSortResults_Page extends TestBase{
	//****************************************//
	//***                                  ***//
	//*** Created by Angela Tong Apr 2018  ***//
	//***                                  ***//
	//****************************************//
	final static Logger log = LogManager.getLogger(MSortResults_Page.class);

    private AppiumDriver driver;
    
	private By sortoption = By.id("com.xome.android:id/tvSortOption");
	private By mostrecent = By.xpath("//android.widget.CheckedTextView[@resource-id=\"android:id/text1\" and @text=\"Most Recent\"]");
	private By pricelowtohigh = By.xpath("//android.widget.CheckedTextView[@resource-id=\"android:id/text1\" and @text=\"Price, Low to High\"]");
	private By pricehightolow = By.xpath("//android.widget.CheckedTextView[@resource-id=\"android:id/text1\" and @text=\"Price, High to Low\"]");
	private By beds = By.xpath("//android.widget.CheckedTextView[@resource-id=\"android:id/text1\" and @text=\"Beds\"]");
	private By baths = By.xpath("//android.widget.CheckedTextView[@resource-id=\"android:id/text1\" and @text=\"Baths\"]");
	private By squarefeet = By.xpath("//android.widget.CheckedTextView[@resource-id=\"android:id/text1\" and @text=\"Square Feet\"]");
	
	private By propertyprice = By.id("com.xome.android:id/listing_summary_price_textView");
	private By propertybed = By.id("com.xome.android:id/listing_summary_bedrooms_textView");
	private By propertybath = By.id("com.xome.android:id/listing_summary_bathrooms_textView");
	private By propertysquarefeet = By.id("com.xome.android:id/listing_summary_square_feet_textView");
	private By property1newtag = By.id("com.xome.android:id/card_summary_secondary_tag");
	private By property2newtag = By.id("com.xome.android:id/card_summary_secondary_tag");
	private By property3newtag = By.id("com.xome.android:id/card_summary_secondary_tag");
	
	
	private String diditsort = "";

	public MSortResults_Page(AppiumDriver driver) {
        this.driver = driver;
    }

	public void clickSortOption () throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
		WebElement sortoptionelement = wait.until(ExpectedConditions.elementToBeClickable(sortoption));
		sortoptionelement.click();
		Thread.sleep(4000); //Must wait for this time to load
	}
	
	
	public void sortByBeds (AppiumDriver driver) throws InterruptedException
	{	
		WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
		MSortResults_Page msrp = new MSortResults_Page(driver);
		msrp.clickSortOption();
		
		WebElement bedselement = wait.until(ExpectedConditions.elementToBeClickable(beds));
		bedselement.click();
		Thread.sleep(4000);
	}
	
	public void sortByBaths () throws InterruptedException
	{	
		WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
		MSortResults_Page msrp = new MSortResults_Page(driver);
		msrp.clickSortOption();
		
		WebElement bathselement = wait.until(ExpectedConditions.elementToBeClickable(baths));
		bathselement.click();
		Thread.sleep(4000);
	}
	
	
	public void sortByLowtoHighPrice () throws InterruptedException
	{	
		WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
		
		MSortResults_Page msrp = new MSortResults_Page(driver);
		msrp.clickSortOption();
		
		WebElement pricelowtohighelement = wait.until(ExpectedConditions.elementToBeClickable(pricelowtohigh));
		pricelowtohighelement.click();
		Thread.sleep(4000);
	}
	
	public void sortByHightoLowPrice () throws InterruptedException
	{	
		WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
		
		MSortResults_Page msrp = new MSortResults_Page(driver);
		msrp.clickSortOption();
		
		WebElement pricehightolowelement = wait.until(ExpectedConditions.elementToBeClickable(pricehightolow));
		pricehightolowelement.click();
		Thread.sleep(4000);
	}
	
	
	public void sortBySqFt () throws InterruptedException
	{	
		WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
		
		MSortResults_Page msrp = new MSortResults_Page(driver);
		msrp.clickSortOption();
		
		WebElement squarefeetelement = wait.until(ExpectedConditions.elementToBeClickable(squarefeet));
		squarefeetelement.click();
		Thread.sleep(4000);
	}
	
	
	public void sortByMostRecent () throws InterruptedException
	{	
		WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(60));
		
		MSortResults_Page msrp = new MSortResults_Page(driver);
		msrp.clickSortOption();
		
		WebElement mostrecentelement = wait.until(ExpectedConditions.elementToBeClickable(mostrecent));
		mostrecentelement.click();
		Thread.sleep(4000);
	}
	
	
	public String verifySortByLowtoHighPrice () throws InterruptedException 
	{	

		
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
	
	
	public String verifySortByHightoLowPrice () throws InterruptedException
	{	

		
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
		
	
	public String verifySortBySquareFeet () throws InterruptedException 
	{	

		
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
	
	public String verifySortByMostRecent () throws InterruptedException 
	{	

		
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
		
		
		
		
		if  (prop1newtag.equalsIgnoreCase("NEW") || prop2newtag.equalsIgnoreCase("NEW") || prop3newtag.equalsIgnoreCase("NEW") || prop1newtag.equalsIgnoreCase("OPEN HOUSE") || prop2newtag.equalsIgnoreCase("OPEN HOUSE") || prop3newtag.equalsIgnoreCase("OPEN HOUSE"))
		{
			diditsort="yes";
			log.info("It sorted by most recent successfully.");
		}
		else if (prop1newtagsort.equalsIgnoreCase("no") && prop2newtagsort.equalsIgnoreCase("no") && prop3newtagsort.equalsIgnoreCase("no"))
		{
			diditsort="no";
			log.error("It sorted unsuccessfully.");
		}
		else
		{
			diditsort="no";
			log.error("It sorted unsuccessfully.");
		}
		
		return diditsort;
	}

	
	public String sortAndVerifyByBeds () throws InterruptedException
	{		

		
		//Have to sort by high price to get large bedrooms then sort by beds to compare with smaller number of bedrooms
		MSortResults_Page msrp = new MSortResults_Page(driver);
		msrp.sortByHightoLowPrice();
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
	
	public String sortAndVerifyByBaths () throws InterruptedException
	{			

		//Have to sort by high price to get large bathrooms then sort by baths to compare with smaller number of bathrooms
		MSortResults_Page msrp = new MSortResults_Page(driver);
		msrp.sortByHightoLowPrice();
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
