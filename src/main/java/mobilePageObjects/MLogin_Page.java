package mobilePageObjects;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.AppiumDriver;

public class MLogin_Page {
	//****************************************//
	//***                                  ***//
	//*** Created by Angela Tong Apr 2018  ***//
	//***                                  ***//
	//****************************************//
	final static Logger log = LogManager.getLogger(MLogin_Page.class);

	private By location = By.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button");
	private By settingsicon = By.id("com.xome.android:id/nav_more_menu");
	private By registerbtn = By.id("com.xome.android:id/btn_create_account");
	private By fname = By.id("com.xome.android:id/register_fname_input");
	private By lname = By.id("com.xome.android:id/register_lname_input");
	private By e_mail = By.id("com.xome.android:id/register_email_input");
	private By pwd = By.id("com.xome.android:id/register_password_input");
	private By finalregisterbtn = By.id("com.xome.android:id/register_submit_btn");
	private By signinbtn = By.id("com.xome.android:id/more_login_logout_option");
	private By alreadyregisteredlogin = By.id("com.xome.android:id/txt_already_member_login");
	private By emaillogin = By.id("com.xome.android:id/login_email_input");
	private By pwdlogin = By.id("com.xome.android:id/login_password_input");
	private By signinlogin = By.id("com.xome.android:id/login_submit_btn");
    private By loggedinuser = By.id("com.xome.android:id/more_account_subtitle");
	
	private String diditlogin = "";
	private String diditregister = "";
	
	public void allowDeviceLocationAccess (AppiumDriver driver) throws InterruptedException
	{	
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		WebElement allowdeviceacesselement = wait.until(ExpectedConditions.elementToBeClickable(location));
		allowdeviceacesselement.click();
		Thread.sleep(4000); //Must wait for this time to load
		
	}
	
	public String mobileSignUp (AppiumDriver driver, String firstname, String lastname, String email, String password) throws InterruptedException
	{	
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		WebElement settingsiconelement = wait.until(ExpectedConditions.elementToBeClickable(settingsicon));
		settingsiconelement.click();
		Thread.sleep(4000); //Must wait for this time to load
	
		WebElement signinbtnelement= wait.until(ExpectedConditions.elementToBeClickable(signinbtn));
		signinbtnelement.click();
		
		WebElement registerbtnelement= wait.until(ExpectedConditions.elementToBeClickable(registerbtn));
		registerbtnelement.click();
		
		WebElement firstnameelement = wait.until(ExpectedConditions.elementToBeClickable(fname));
		firstnameelement.click();
		firstnameelement.clear();
		firstnameelement.sendKeys(firstname);
		
		WebElement lastnameelement = wait.until(ExpectedConditions.elementToBeClickable(lname));
		lastnameelement.click();
		lastnameelement.clear();
		lastnameelement.sendKeys(lastname);
		
		String [] emailarray = email.split("@");
	    
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMddyyyyHHmmss");
		LocalDateTime now = LocalDateTime.now();
	    String append = dtf.format(now).toString();
	    
	    String newemail = emailarray[0]+"+"+append+"@"+emailarray[1];
	    
	    log.info(newemail);
		
		WebElement emailelement = wait.until(ExpectedConditions.elementToBeClickable(e_mail));
		emailelement.click();
		emailelement.clear();
		emailelement.sendKeys(newemail);
		
		WebElement pwdelement = wait.until(ExpectedConditions.elementToBeClickable(pwd));
		pwdelement.click();
		pwdelement.clear();
		pwdelement.sendKeys(password);

		WebElement finalregisterbtnelement = wait.until(ExpectedConditions.elementToBeClickable(finalregisterbtn));
		finalregisterbtnelement.click();
		Thread.sleep(4000);
			
		WebElement loggedinuserelement = wait.until(ExpectedConditions.elementToBeClickable(loggedinuser));
		String loggedinusertext = loggedinuserelement.getText();
		
		if (loggedinusertext.contains("Account - sqatester2018+"))
		{
			diditregister="yes";
			log.info("Signed up successfully.");
		}
		else 
		{
			diditregister="no";
			log.error("Signed up unsuccessfully.");
		}
		
		return diditregister;
	
	}
	
	public String mobileLogin (AppiumDriver driver, String login, String password, String searchkeyword) throws InterruptedException
	{	
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		WebElement settingsiconelement = wait.until(ExpectedConditions.elementToBeClickable(settingsicon));
		settingsiconelement.click();
	

		Thread.sleep(4000); //Must wait for this time to load
		
		WebElement signinbtnelement= wait.until(ExpectedConditions.elementToBeClickable(signinbtn));
		signinbtnelement.click();
		
		WebElement alreadyregisteredloginelement= wait.until(ExpectedConditions.elementToBeClickable(alreadyregisteredlogin));
		alreadyregisteredloginelement.click();
		
		WebElement emailloginelement = wait.until(ExpectedConditions.elementToBeClickable(emaillogin));
		emailloginelement.click();
		emailloginelement.clear();
		emailloginelement.sendKeys(login);
		
		WebElement pwdloginelement = wait.until(ExpectedConditions.elementToBeClickable(pwdlogin));
		pwdloginelement.click();
		pwdloginelement.clear();
		pwdloginelement.sendKeys(password);
		Thread.sleep(4000); //Must wait for this time to load
		
		WebElement signinloginelement = wait.until(ExpectedConditions.elementToBeClickable(signinlogin));
		signinloginelement.click();
		
		WebElement loggedinuserelement = wait.until(ExpectedConditions.elementToBeClickable(loggedinuser));
		String loggedinusertext = loggedinuserelement.getText();
		
		if (loggedinusertext.equalsIgnoreCase("Account - sqatester2018@gmail.com"))
		{
			diditlogin="yes";
			log.info("Logged in successfully.");
		}
		else 
		{
			diditlogin="no";
			log.error("Logged in unsuccessfully.");
		}
		
		return diditlogin;
	}
	
}
