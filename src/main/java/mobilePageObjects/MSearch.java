package mobilePageObjects;


import java.time.Duration;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import base.TestBase;
import io.appium.java_client.AppiumDriver;



public class MSearch extends TestBase{
	    //****************************************//
		//***                                  ***//
		//*** Created by Angela Tong Apr 2018  ***//
		//***                                  ***//
		//****************************************//
		final static Logger log = LogManager.getLogger(MSearch.class);

		private AppiumDriver driver;

		private By dismissnavbar = By.xpath("(//android.support.v4.view.ViewPager[@resource-id=\"com.xome.android:id/listing_summary_imageViewPager\"])[2]/android.widget.ImageView");
		private By searchbar = By.id("com.xome.android:id/search_bar");
		private By searchbar2 = By.id("com.xome.android:id/etSuggest");
		private By autosuggestionlist = By.id("com.xome.android:id/recyclerView");
		private By autosuggestions = By.id("com.xome.android:id/tvTitle");
		
		//private By listProps = By.id("com.xome.android:id/listing_summary");
		private By propertyaddress = By.id("com.xome.android:id/listing_summary_address_textView");
		
		
		private String diditfilter = "";

		
		public MSearch(AppiumDriver driver) {
		    this.driver = driver;
		}

		public void clickSearchToDismissLeftMenu () throws InterruptedException
		{	
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			WebElement dismissnav = wait.until(ExpectedConditions.elementToBeClickable(dismissnavbar));
			dismissnav.click();
		}	
		

		public void searchHomes (String searchkeyword) throws InterruptedException
		{	
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			WebElement search_bar = wait.until(ExpectedConditions.elementToBeClickable(searchbar));
			search_bar.click();
			WebElement search_bar2 = wait.until(ExpectedConditions.elementToBeClickable(searchbar2));
			search_bar2.click();
			search_bar2.sendKeys(searchkeyword);
			
			Thread.sleep(3000); //Need a wait here for it to load or it creates flaky fails 


			// Re-locate children every time (RecyclerView safe)
			List<WebElement> titles = driver.findElements(autosuggestions);

			    log.info("Size of autosuggestions is : {}", titles.size());

			if (!titles.isEmpty()) {

				// Locate autosuggestion container
				WebElement recycler =
				        wait.until(ExpectedConditions.visibilityOfElementLocated(autosuggestionlist));

				// Find first row (parent of tvTitle)
				WebElement firstRow =
				        recycler.findElements(By.xpath("./*")).get(0);

				// Tap the ROW, not the TextView
				Rectangle rect = firstRow.getRect();

				PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
				Sequence tap = new Sequence(finger, 1);

				tap.addAction(finger.createPointerMove(
				        Duration.ZERO,
				        PointerInput.Origin.viewport(),
				        rect.getX() + rect.getWidth() / 2,
				        rect.getY() + rect.getHeight() / 2
				));
				tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
				tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

				driver.perform(List.of(tap));


			}else {
			        throw new RuntimeException("No autosuggestions found in list");
			}

		}
		
		public String verifySearchResults(String searchkeyword) throws InterruptedException
		{	
			
			// Fetch elements fresh AFTER wait
			Thread.sleep(4000);
			List<WebElement> addresslist = driver.findElements(propertyaddress);

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
