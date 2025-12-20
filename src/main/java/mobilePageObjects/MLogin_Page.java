package mobilePageObjects;

import base.TestBase;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.SupportsContextSwitching;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.AppiumDriver;
import java.time.Duration;
import java.util.Set;

public class MLogin_Page extends TestBase {
    //****************************************//
    //***                                  ***//
    //*** Created by Angela Tong Apr 2018  ***//
    //***                                  ***//
    //****************************************//
    final static Logger log = LogManager.getLogger(MLogin_Page.class);

    private AppiumDriver driver;

    // Locators for various elements on the login page
    private By location = By.id("android:id/button1");
    private By location2 = By.id("com.android.packageinstaller:id/permission_allow_button");
    private By settingsicon = By.xpath("//android.widget.ImageButton[@content-desc=\"Open navigation drawer\"]");
    private By signinbtn = By.id("com.xome.android:id/cont_title");
    private By emaillogin = By.id("com.xome.android:id/edittext_login_email");
    private By pwdlogin = By.id("com.xome.android:id/textview_login_password");
    private By signinlogin = By.id("com.xome.android:id/button_login");
    private By hamburgericon = By.xpath("//android.widget.ImageButton[@content-desc=\"Open navigation drawer\"]");
    private By loggedinuser = By.id("com.xome.android:id/cont_title");

    private String diditlogin = "";

    public MLogin_Page(AppiumDriver driver) {
        this.driver = driver;
    }

    // Method to allow device location access if prompted
    public void allowDeviceLocationAccess() throws InterruptedException {
        

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        // Handle location permission dialogs
        WebElement allowdeviceaccesselement1 = wait.until(ExpectedConditions.elementToBeClickable(location));
        allowdeviceaccesselement1.click();
        Thread.sleep(4000); // Wait for the permission to be granted
        WebElement allowdeviceaccesselement2 = wait.until(ExpectedConditions.elementToBeClickable(location2));
        allowdeviceaccesselement2.click();
        Thread.sleep(4000);
    }

    // Method to perform mobile login
    public String mobileLogin(String login, String password, String searchkeyword, String name) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Open the settings / navigation drawer
        WebElement settingsIcon = wait.until(ExpectedConditions.elementToBeClickable(settingsicon));
        settingsIcon.click();

        Thread.sleep(6000); // Wait for the navigation menu to load

        // Click on the Sign In button
        WebElement signInBtn = wait.until(ExpectedConditions.elementToBeClickable(signinbtn));
        signInBtn.click();

        Thread.sleep(6000); // Wait for login screen to appear

        // Context switching for WebView (handling cases when app contains a WebView context)
        if (driver instanceof AndroidDriver) {
            AndroidDriver androidDriver = (AndroidDriver) driver;
            Set<String> contextNames = androidDriver.getContextHandles();  // Get all contexts (native and web)
            for (String contextName : contextNames) {
                if (contextName.contains("WEBVIEW")) {
                    androidDriver.context(contextName);  // Switch to the WebView context if present
                    log.info("Switched to WebView context: " + contextName);
                    break;
                }
            }
        } else if (driver instanceof IOSDriver) {
            IOSDriver iosDriver = (IOSDriver) driver;
            Set<String> contextNames = iosDriver.getContextHandles();  // Get all contexts (native and web)
            for (String contextName : contextNames) {
                if (contextName.contains("WEBVIEW")) {
                    iosDriver.context(contextName);  // Switch to the WebView context if present
                    log.info("Switched to WebView context: " + contextName);
                    break;
                }
            }
        }

        // Wait for the email input field to be visible
        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(emaillogin));
        emailInput.clear();
        emailInput.sendKeys(login);

        // Wait for the password input field to be visible and enter password
        WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(pwdlogin));
        passwordInput.clear();
        passwordInput.sendKeys(password);

        Thread.sleep(1000); // Ensure keys are entered

        // Click the sign-in button
        WebElement signInLoginBtn = wait.until(ExpectedConditions.elementToBeClickable(signinlogin));
        signInLoginBtn.click();

        Thread.sleep(8000);

        // After login click
        ((SupportsContextSwitching) driver).context("NATIVE_APP");

        // Wait for the hamburger / main screen to appear
        WebElement hamburger =  wait.until(ExpectedConditions.visibilityOfElementLocated(hamburgericon));
        hamburger.click();

        Thread.sleep(8000);

        // Check the logged-in user
        WebElement loggedInUserElement = wait.until(ExpectedConditions.visibilityOfElementLocated(loggedinuser));
        String loggedInUserText = loggedInUserElement.getText();

        // Verify login success based on the username shown
        if (loggedInUserText.toLowerCase().contains(name.toLowerCase())) {
            diditlogin = "yes";
            log.info("Logged in successfully as " + loggedInUserText);
        } else {
            diditlogin = "no";
            log.error("Login unsuccessful. Expected user: " + name + " but found: " + loggedInUserText);
        }

        return diditlogin;
    }
}

