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
import io.appium.java_client.PerformsActions;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;

public class MSearch {
	    //****************************************//
		//***                                  ***//
		//*** Created by Angela Tong Apr 2018  ***//
		//***                                  ***//
		//****************************************//
		final static Logger log = LogManager.getLogger(MSearch.class);

		
		private By findHomes = By.id("com.xome.android:id/nav_search_menu");
		private By enterlocation = By.id("com.xome.android:id/enter_location_text_view");
		private By searchbar = By.id("com.xome.android:id/search_bar_input");
		private By autosuggestion = By.id("com.xome.android:id/search_suggestion_item_name");
		
		private By listProps = By.id("com.xome.android:id/search_view_type_text");
		private By propertyaddress = By.id("com.xome.android:id/listing_card_address");
		
		
		private String diditfilter = "";
		
		public void clickSearchtoDismissLeftMenu (AppiumDriver driver) throws InterruptedException
		{	
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			WebElement findHomeselement = wait.until(ExpectedConditions.elementToBeClickable(findHomes));
			findHomeselement.click();
		}	
		
		public void searchhomes (AppiumDriver driver, String searchkeyword) throws InterruptedException
		{	
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
			WebElement findHomeselement = wait.until(ExpectedConditions.elementToBeClickable(findHomes));
			findHomeselement.click();
			WebElement enterLocationelement = wait.until(ExpectedConditions.elementToBeClickable(enterlocation));
			enterLocationelement.click();
			WebElement searchbarelement = wait.until(ExpectedConditions.presenceOfElementLocated(searchbar));
			searchbarelement.sendKeys(searchkeyword);
			
			Thread.sleep(3000); //Need a wait here for it to load or it creates flaky fails 
			List <WebElement> autosuggestionelements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(autosuggestion));
			int size = autosuggestionelements.size();
			//System.out.print("size of autosuggestions is "+ Integer.toString(size)+"\n");

			if(size==1)
			{
				WebElement autosuggest = wait.until(ExpectedConditions.elementToBeClickable(autosuggestion));
				autosuggest.click();
			}
			else if (size > 1)
			{
				autosuggestionelements.get(0).click();
			}
			
			/***
			TouchAction action = new TouchAction((PerformsTouchActions) driver);
			//if else sometimes the autosuggestion picks up 1 result only other times it picks up more autosuggestions
			if (size==1)
			{
				WebElement autosuggest = wait.until(ExpectedConditions.elementToBeClickable(autosuggestion));
				action.tap((TapOptions) (PerformsActions) new TapOptions().withElement(new ElementOption().withElement((WebElement) autosuggest))).perform();
			}
			else if (size > 1)
			{
			action.tap(new TapOptions().withElement(new ElementOption().withElement(autosuggestionelements.get(1)))).perform();
			}
			***/
			
			Thread.sleep(4000); //Must wait for this time to load
		}
		
		public String verifySearchResults(AppiumDriver driver, String searchkeyword) throws InterruptedException
		{	
			
			@SuppressWarnings("unchecked")
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
			WebElement listPropselement = wait.until(ExpectedConditions.elementToBeClickable(listProps));
			listPropselement.click();
			
			
			List <WebElement> addresslist = (List<WebElement>) driver.findElements(propertyaddress);
			String property1address = addresslist.get(0).getText();
			String property2address = addresslist.get(1).getText();
			String property3address = addresslist.get(2).getText();
				

			
			if ((property1address.contains(searchkeyword)) && (property2address.contains(searchkeyword)) && (property3address.contains(searchkeyword)))
			{
				diditfilter="yes";
				log.info("It filtered out the addresses correctly.");
			}
			else
			{
				diditfilter="no";
				log.error("It filter out the addresses incorrectly.");
			}
			
			
			return diditfilter;
		}
}
