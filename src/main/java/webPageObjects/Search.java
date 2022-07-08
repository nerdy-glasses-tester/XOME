package webPageObjects;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.function.Function;

public class Search {

	    //****************************************//
		//***                                  ***//
		//*** Created by Angela Tong Apr 2018  ***//
		//***                                  ***//
		//****************************************//
		final static Logger log = LogManager.getLogger(Search.class);
		
		private By xomelogo = By.className("xome-logo");
		private By traditionallistings = By.xpath(".//a[contains(text(), 'Traditional Listings')]");
		private By homepagesearchfield = By.id("location-retail");
		private By searchautocompletebycity = By.cssSelector("div.autocomplete-entry>div.text>span.location");
		private By searchautocompletebyaddress = By.xpath(".//div[@class='autocomplete-entry']//a");
		private By searchautocomplete = By.xpath(".//div[@class='autocomplete-entry']//a");
		private By searchingmsg = By.cssSelector("div#mapsearch-searching-message[style='top: 163.984px']>div#display-message");
		private By nomoresearchingmsg = By.id("div#mapsearch-searching-message[style='top: 163.984px; opacity: 0; display: none;']>div#display-message");	
		private By searchresults = By.cssSelector("div.mapsearch-singleprop.mapsearch-map-singleprop.mapsearch-two-columns-view.included.slick-already-processed");

		private By searchbyaddressresult_address1= By.className("address-line-1");
		private By searchbyaddressresult_address2= By.className("address-line-2");
		
	    public void searchByCity (WebDriver webdriver, String searchkeyword)
	    {
	    	Wait<WebDriver> wait = new FluentWait<WebDriver>(webdriver)   
	    		    .withTimeout(Duration.ofSeconds(30))    
	    		    .pollingEvery(Duration.ofSeconds(1))   
	    		    .ignoring(NoSuchElementException.class);
	    	
	    	
	    	WebElement traditionallistingslink = (WebElement) wait.until(new Function<WebDriver, WebElement>(){
	    		public WebElement apply(WebDriver webdriver) {
	    			return webdriver.findElement(traditionallistings);
	    		}
	    	});
	    	
			traditionallistingslink.click();
	    	
	    	WebElement searchbuy = (WebElement) wait.until(new Function<WebDriver, WebElement>(){
	    		public WebElement apply(WebDriver webdriver) {
	    			return webdriver.findElement(homepagesearchfield);
	    		}
	    	});
	    
	        searchbuy.click(); 
            searchbuy.clear();
            searchbuy.sendKeys(searchkeyword);
            
	    	WebElement autocomplete = (WebElement) wait.until(new Function<WebDriver, WebElement>(){
	    		public WebElement apply(WebDriver webdriver) {
	    			return webdriver.findElement(searchautocomplete);
	    		}
	    	});
	    	
            autocomplete.click();
            
	    }
	    
	    public boolean loginsearchfor6thhouseinresults (WebDriver webdriver, String searchkeyword)
	    {
	    	Search search = new Search();
	    	search.searchByCity(webdriver, searchkeyword); 
	    		
	    	Wait<WebDriver> wait = new FluentWait<WebDriver>(webdriver)    
	    		    .withTimeout(Duration.ofSeconds(30))    
	    		    .pollingEvery(Duration.ofSeconds(1))   
	    		    .ignoring(NoSuchElementException.class);
	    	
	    	List<WebElement> searchr =  (List<WebElement>) wait.until(new Function<WebDriver, List<WebElement>>(){
	    		public List<WebElement>apply(WebDriver webdriver) {
	    			return webdriver.findElements(searchresults);
	    		}
	    	});

	
	    	if(searchr.isEmpty())
	    	{
	    		searchr =  (List<WebElement>) wait.until(new Function<WebDriver, List<WebElement>>(){
		    		public List<WebElement>apply(WebDriver webdriver) {
		    			return webdriver.findElements(searchresults);
		    		}
		    	});
	    		
	    	}
	        
	    	searchr.get(5).click();
	    	
	        WebElement addline2 = (WebElement) wait.until(new Function<WebDriver, WebElement>(){
	    		public WebElement apply(WebDriver webdriver) {
	    			return webdriver.findElement(searchbyaddressresult_address2);
	    		}
	    	});
	    	
            String addressline2text = addline2.getText();
            String address2text = addressline2text.substring(0,10);
            
            System.out.print("address2text is "+address2text+" \n");
            System.out.print("searchkeyword is "+searchkeyword+" \n");
            
            Boolean result = address2text.equalsIgnoreCase(searchkeyword);
            
            System.out.print("result is "+Boolean.toString(result));
            
            return result;
         
	    	
	    }
	
	    
	    public void searchSpecificAddress (WebDriver webdriver, String address) throws InterruptedException
	    {
	        WebDriverWait wait = new WebDriverWait(webdriver, Duration.ofSeconds(60));
	        
	    	WebElement traditionallistingslink = wait.until(ExpectedConditions.elementToBeClickable(traditionallistings));
	    	
			traditionallistingslink.click();
	        
	    	WebElement searchbuy = wait.until(ExpectedConditions.elementToBeClickable(homepagesearchfield));
	        searchbuy.click(); 
            searchbuy.clear();
            searchbuy.sendKeys(address);

            WebElement autocomplete = wait.until(ExpectedConditions.elementToBeClickable(searchautocompletebyaddress));
            autocomplete.click();
            
            Thread.sleep(60);//Need some time for page to load in order to be able to locate some elements later.
   
                
        }
	    
	    public String searchandVerifyAddressSearched(WebDriver webdriver, String address) throws InterruptedException
	    {
			Search search = new Search();
			search.searchSpecificAddress(webdriver,address);
	        WebDriverWait wait = new WebDriverWait(webdriver, Duration.ofSeconds(120));
			WebElement resultaddress1 = wait.until(ExpectedConditions.visibilityOfElementLocated(searchbyaddressresult_address1));
			WebElement resultaddress2 = wait.until(ExpectedConditions.visibilityOfElementLocated(searchbyaddressresult_address2));
			String address1=resultaddress1.getText();
			String address2=resultaddress2.getText();
			String resultaddress=address1+" "+address2;
			log.info("resultaddress is: "+resultaddress);
	    		return resultaddress;
	    	
	    }
	    
}
