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
		
		private By xomelogo = By.className("SiteHeadLogo-image");
		private By homepagesearchfield = By.id("homepage-search-field");
		private By searchautocompletebycity = By.cssSelector("div.autocomplete-entry>div.text>span.location");
		private By searchautocompletebyaddress = By.cssSelector("div.autocomplete-entry>div.text>span.name");
		private By searchbutton = By.cssSelector("button.call-to-action.search-field-button");
		private By searchingmsg = By.cssSelector("div#mapsearch-searching-message[style='top: 163.984px']>div#display-message");
		private By nomoresearchingmsg = By.id("div#mapsearch-searching-message[style='top: 163.984px; opacity: 0; display: none;']>div#display-message");	
		private By searchresults = By.cssSelector("div.mapsearch-singleprop.mapsearch-map-singleprop.mapsearch-two-columns-view.included.slick-already-processed");
		private By addressline2 = By.cssSelector("div.address-line-2.second-field");
		private By searchbyaddressresult_address1= By.cssSelector("h1.address-line-1.first-field.bolded");
		private By searchbyaddressresult_address2= By.cssSelector("div.address-line-2.second-field");
		
	    public void searchByCity (WebDriver webdriver, String searchkeyword)
	    {
	    	Wait<WebDriver> wait = new FluentWait<WebDriver>(webdriver)   
	    		    .withTimeout(Duration.ofSeconds(30))    
	    		    .pollingEvery(Duration.ofSeconds(1))   
	    		    .ignoring(NoSuchElementException.class);
	    	
	    	WebElement searchbuy = (WebElement) wait.until(new Function<WebDriver, WebElement>(){
	    		public WebElement apply(WebDriver webdriver) {
	    			return webdriver.findElement(homepagesearchfield);
	    		}
	    	});
	    
	        searchbuy.click(); 
            searchbuy.clear();
            searchbuy.sendKeys(searchkeyword);
            
	    	WebElement searchbtn = (WebElement) wait.until(new Function<WebDriver, WebElement>(){
	    		public WebElement apply(WebDriver webdriver) {
	    			return webdriver.findElement(searchbutton);
	    		}
	    	});
	    	
            searchbtn.click();
            
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

	    	while(searchr.isEmpty())
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
	    			return webdriver.findElement(addressline2);
	    		}
	    	});
	    	
            String addressline2text = addline2.getText();
            
            if(addressline2text.contains(searchkeyword))
            {
            	return true;
            }
            else
            {
            	return false;
            }
	    	
	    }
	
	    
	    public void searchSpecificAddress (WebDriver webdriver, String address)
	    {
	    	WebDriverWait wait = new WebDriverWait (webdriver, 60);
	    	WebElement searchbuy = wait.until(ExpectedConditions.elementToBeClickable(homepagesearchfield));
	        searchbuy.click(); 
            searchbuy.clear();
            searchbuy.sendKeys(address);

            WebElement autocomplete = wait.until(ExpectedConditions.elementToBeClickable(searchautocompletebyaddress));
            autocomplete.click();
            
            webdriver.manage().timeouts().implicitlyWait(60,TimeUnit.MILLISECONDS);//Need some time for page to load in order to be able to locate some elements later.
 	       
                
        }
	    
	    public String searchandVerifyAddressSearched(WebDriver webdriver, String address)
	    {
			Search search = new Search();
			search.searchSpecificAddress(webdriver,address);
			WebDriverWait wait = new WebDriverWait (webdriver, 60);
			WebElement resultaddress1 = wait.until(ExpectedConditions.visibilityOfElementLocated(searchbyaddressresult_address1));
			WebElement resultaddress2 = wait.until(ExpectedConditions.visibilityOfElementLocated(searchbyaddressresult_address2));
			String address1=resultaddress1.getText();
			String address2=resultaddress2.getText();
			String resultaddress=address1+" "+address2;
			log.info("resultaddress is: "+resultaddress);
	    		return resultaddress;
	    	
	    }
	    
}
