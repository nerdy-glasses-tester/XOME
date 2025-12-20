package webPageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Random;



public class Login_Page {
	
	//****************************************//
	//***                                  ***//
	//*** Created by Angela Tong		   ***//
	//***                                  ***//
	//****************************************//
	final static Logger log = LogManager.getLogger(Login_Page.class);
	
	private By accept_cookies = By.id("onetrust-accept-btn-handler");
	private By signinlink1 = By.linkText("Sign In/Sign Up");
	private By signinlink2 = By.linkText("Sign In");
	private By emaillogin = By.id("security_loginname");
	private By pwdlogin = By.id("security_password");
	private By submitbtn = By.cssSelector("input.btn.btn-primary.submit-button.js-submit-button.captcha-submit-button");
	private By emailpwdrequired = By.linkText("The email and password fields are required. Please enter highlighted fields in red.");
	private By verifysignedin = By.cssSelector("header.home-header.auctions-header>ul.nav-bar>li.sub-menu.dropdown.logged-in>a");
	private By emailpwddoesntmatchtext = By.linkText("This combination of email or password is invalid.");
    private By xomelogo = By.className("xome-logo");
	

    public void acceptcookies (WebDriver webdriver) throws InterruptedException
    {
        WebDriverWait wait = new WebDriverWait(webdriver, Duration.ofSeconds(60));
        try {
            WebElement accept = wait.until(ExpectedConditions.elementToBeClickable(accept_cookies));
            accept.click();
            log.info("Clicked cookie button without iframe.");
        } catch (Exception e) {
            log.warn("Cookie button not found outside iframe. Searching frames...");

            // 2. Search ALL iframes for the cookie button
            int totalFrames = webdriver.findElements(By.tagName("iframe")).size();
            boolean clicked = false;

            for (int i = 0; i < totalFrames; i++) {
                try {
                    webdriver.switchTo().defaultContent();
                    webdriver.switchTo().frame(i);

                    WebElement accept = webdriver.findElement(accept_cookies);
                    if (accept.isDisplayed()) {
                        ((JavascriptExecutor) webdriver).executeScript("arguments[0].click();", accept);
                        log.info("Cookie button clicked inside iframe index: " + i);
                        clicked = true;
                        break;
                    }
                } catch (Exception ignored) {}
            }

            webdriver.switchTo().defaultContent();

            if (!clicked) {
                throw new RuntimeException("Cookie accept button not found in any iframe.");
            }
        }
    }

    public void clickSignIn (WebDriver webdriver) throws InterruptedException
    {
        WebDriverWait wait = new WebDriverWait(webdriver, Duration.ofSeconds(60));
   
        acceptcookies(webdriver);

    	WebElement signin = wait.until(ExpectedConditions.elementToBeClickable(signinlink1));
    	signin.click(); 
    	WebElement iframeSwitch = webdriver.findElement(By.className("cboxIframe"));
    	webdriver.switchTo().frame(iframeSwitch);
    	WebElement signin2 = wait.until(ExpectedConditions.elementToBeClickable(signinlink2));
    	signin2.click(); 
	    
	    
	    Thread.sleep(3000);
    }

    
    public String signinBlankFields (WebDriver webdriver)
    {
        WebDriverWait wait = new WebDriverWait(webdriver, Duration.ofSeconds(60));
		WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(submitbtn));
    		
        submit.click();
        
    	WebElement required = wait.until(ExpectedConditions.presenceOfElementLocated(emailpwdrequired));
        String fieldsrequired = required.getText();
        
	    log.info("Check email and password field are required: "+fieldsrequired);

	    return fieldsrequired;
    }
    
   
    public String signinWrongPwd (WebDriver webdriver, String login, String password) throws InterruptedException
    {
        WebDriverWait wait = new WebDriverWait(webdriver, Duration.ofSeconds(60));
    	WebElement email = wait.until(ExpectedConditions.elementToBeClickable(emaillogin));
    	WebElement pwd = wait.until(ExpectedConditions.elementToBeClickable(pwdlogin));
    	WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(submitbtn));
        email.click();
        email.clear();
        email.sendKeys(login);
        
        Thread.sleep(1000 + new Random().nextInt(1500));
        
        pwd.click();
        pwd.clear();
        pwd.sendKeys(password);

        Actions actions = new Actions(webdriver);
        actions.moveByOffset(10, 10).pause(Duration.ofSeconds(2)).perform();
        
        submit.click();
        
        Thread.sleep(2000);
	    
    	WebElement wrongpwd_text = wait.until(ExpectedConditions.presenceOfElementLocated(emailpwddoesntmatchtext));
        String wrongemailpwdtext = wrongpwd_text.getText();
        
	    log.info("Wrong password text is : "+wrongemailpwdtext);

	    return wrongemailpwdtext;
    }
    
    public String login (WebDriver webdriver, String login, String password) throws InterruptedException
    {
    	WebDriverWait wait = new WebDriverWait(webdriver, Duration.ofSeconds(60));
    	
    	WebElement email = wait.until(ExpectedConditions.elementToBeClickable(emaillogin));
    	WebElement pwd = wait.until(ExpectedConditions.elementToBeClickable(pwdlogin));
    	WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(submitbtn));

    	
        email.click();
    	email.clear();
        email.sendKeys(login);
        
        JavascriptExecutor js = (JavascriptExecutor) webdriver;
        js.executeScript("window.scrollBy(0, 150);");
        Thread.sleep(1000 + new Random().nextInt(1500));
        
        pwd.click();
        pwd.clear();
        pwd.sendKeys(password);

        Actions actions = new Actions(webdriver);
        actions.moveByOffset(10, 10).pause(Duration.ofSeconds(2)).perform();
 
        
        submit.click();
        
        //Wait a bit for it to signin
        Thread.sleep(3000);
        
        js.executeScript("document.querySelectorAll('.modal, .popup').forEach(e => e.remove());");
        
        //Wait a bit for it to dismiss the popups
        Thread.sleep(3000);
        
        webdriver.switchTo().defaultContent();
        
        WebElement signedinuser = wait.until(ExpectedConditions.visibilityOfElementLocated(verifysignedin));
        
        String signedinusername = signedinuser.getText();
        log.info("Signed in user name is: "+signedinusername);

        WebElement logo = wait.until(ExpectedConditions.elementToBeClickable(xomelogo));
        
        logo.click(); //have to click on homepage again to refresh page to pickup elements
        
    	return signedinusername;
    }
    
}
