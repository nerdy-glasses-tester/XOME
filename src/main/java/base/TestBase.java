package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import common.ExcelMethods;
import common.ReadProperties;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;



public class TestBase {

	//****************************************//
	//***                                  ***//
	//*** Created by Angela Tong APR 2018  ***//
	//***                                  ***//
	//****************************************//
	
	//Run mvn dependency:copy-dependencies -DoutputDirectory=lib to extract all the maven dependencies/jars into the lib folder
	//If using for windows, please change paths to windows paths.
	
        @SuppressWarnings("rawtypes")
        protected AppiumDriver driver;
	    protected WebDriver webdriver;
	    
	    //For Mac -comment out if using for windows
	    public static String folderpath = "/Users/geeky/OneDrive/eclipse-workspace/XOME/screencaptures";
		//For Windows -comment out if using for mac
	    //public static String folderpath = "C:\\EclipseProjects\\XOME\\screencaptures";
	    
	    public String runonrealdevice = "";
		public String methodname = "";


        @SuppressWarnings({ "rawtypes", "deprecation"})
		@BeforeMethod (alwaysRun=true)
		//Use before method instead of before class or before test so each method/test will open in new browser; 
		//This was tested and found before method was the only one that works.
		public void Setup() throws IOException{
	         
        	ReadProperties.retrieveGlobalProperties();
        	
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			String os = System.getProperty("os.name");
			
			if (ReadProperties.globalProp.getProperty("webautomation").contains("yes") && ReadProperties.globalProp.getProperty("mobileautomation").contains("no"))
			{
				if(ReadProperties.globalProp.getProperty("webbrowser").contains("firefox"))
				{
					//ProfilesIni profile = new ProfilesIni();
					//FirefoxProfile myprofile = profile.getProfile("2ypnpwex.Selenium Firefox Profile");
					//Users/geeky/Library/ApplicationSupport/Firefox/Profiles/2ypnpwex.Selenium Firefox Profile
					
					String downloadFilepath="//Users//geeky//Library//ApplicationSupport//Firefox//Profiles//2ypnpwex.Selenium Firefox Profile";
					FirefoxProfile myprofile = new FirefoxProfile();
					myprofile.setPreference("browser.download.dir", downloadFilepath);
					
					//capabilities.setCapability(CapabilityType.ELEMENT_SCROLL_BEHAVIOR, 1);
					if(os.contains("MAC") || os.contains("mac") || os.contains("Mac"))
					{
						System.setProperty("webdriver.gecko.driver", ReadProperties.globalProp.getProperty("macgeckodriver"));
						FirefoxOptions options = new FirefoxOptions();
						options.setCapability("platform", "MAC");
						options.setCapability("browser", "firefox");
						options.setCapability("newCommandTimeout", 5000);
						options.setProfile(myprofile);
						options.setLogLevel(FirefoxDriverLogLevel.TRACE);
					}
					else
					{
						System.setProperty("webdriver.gecko.driver", ReadProperties.globalProp.getProperty("pcgeckodriver"));
						FirefoxOptions options = new FirefoxOptions();
						options.setCapability("platform", "WINDOWS");
						options.setCapability("browser", "firefox");
						options.setCapability("newCommandTimeout", 5000);
						options.setProfile(myprofile);
						options.setLogLevel(FirefoxDriverLogLevel.TRACE);
					}
					webdriver = new FirefoxDriver();

					
				}
				else if (ReadProperties.globalProp.getProperty("webbrowser").contains("chrome"))
				{
					if(os.contains("MAC") || os.contains("mac") || os.contains("Mac"))
					{
						System.setProperty("webdriver.chrome.driver", ReadProperties.globalProp.getProperty("macchromedriver"));
						ChromeOptions chromeOptions = new ChromeOptions();
						chromeOptions.setCapability("platform", "MAC");
						chromeOptions.setCapability("browser", "chrome");
						chromeOptions.setCapability("newCommandTimeout", 5000);
					}
					else
					{
						System.setProperty("webdriver.chrome.driver", ReadProperties.globalProp.getProperty("pcchromedriver"));
						ChromeOptions chromeOptions = new ChromeOptions();
						chromeOptions.setCapability("platform", "WINDOWS");
						chromeOptions.setCapability("browser", "chrome");
						chromeOptions.setCapability("newCommandTimeout", 5000);
					}
					webdriver = new ChromeDriver();
	
				}
				else if (ReadProperties.globalProp.getProperty("webbrowser").contains("safari"))
				{
					SafariOptions safariOptions = new SafariOptions();
					safariOptions.setCapability("platform", "MAC");
					safariOptions.setCapability("browser", "safari");
					safariOptions.setCapability("newCommandTimeout", 5000);
				}
				else if(ReadProperties.globalProp.getProperty("webbrowser").contains("MicrosoftEdge"))
				{
					System.setProperty("webdriver.edge.driver", ReadProperties.globalProp.getProperty("edgedriver"));
					EdgeOptions edgeOptions = new EdgeOptions();
					edgeOptions.setCapability("platform", "WINDOWS");
					edgeOptions.setCapability("browser", "edge");
					edgeOptions.setCapability("ensureCleanSession", true);
					edgeOptions.setCapability("ignoreZoomSetting", true);
					edgeOptions.setCapability("ignoreProtectedModeSettings", true);
					edgeOptions.setCapability("ignore-certificate-error", true);
					edgeOptions.setCapability("acceptSslCerts", "true");
					edgeOptions.setCapability("newCommandTimeout", 5000);
					webdriver = new EdgeDriver();
				}
				else if(ReadProperties.globalProp.getProperty("webbrowser").contains("internet explorer"))
				{
					System.setProperty("webdriver.ie.driver", ReadProperties.globalProp.getProperty("iedriver"));
					InternetExplorerOptions iexplorerOptions = new InternetExplorerOptions();
					iexplorerOptions.setCapability("platform", "WINDOWS");
					iexplorerOptions.setCapability("browser", "iexplorer");
					iexplorerOptions.setCapability("ensureCleanSession", true);
					iexplorerOptions.setCapability("ignoreZoomSetting", true);
					iexplorerOptions.setCapability("ignoreProtectedModeSettings", true);
					iexplorerOptions.setCapability("ignore-certificate-error", true);
					iexplorerOptions.setCapability("capabilityType.ACCEPT_SSL_CERTS", true);
					iexplorerOptions.setCapability("newCommandTimeout", 5000);
					webdriver = new InternetExplorerDriver();
				}
			
				    webdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
				    String weburl = ReadProperties.globalProp.getProperty("weburl");
					webdriver.get(weburl);
					webdriver.manage().window().maximize(); 
			}
			else if (ReadProperties.globalProp.getProperty("webautomation").contains("no") && ReadProperties.globalProp.getProperty("mobileautomation").contains("yes"))
			{

					if (ReadProperties.globalProp.getProperty("mobiledevice").contains("Android")&& ReadProperties.globalProp.getProperty("runonrealdevice").contains("yes"))
					{
						//Remove "Unlock" and "Appium settings" apps from Android device
                        //Appium installs those apps automatically. (In my case those apps were not compatible after updating Android from version 6 to version 7.
			            capabilities.setCapability("platformName", "Android");     
						capabilities.setCapability("platformVersion", "7.1.1");     
						capabilities.setCapability("deviceName", "Android");    
						capabilities.setCapability("automationName", "uiautomator2");//need this or else can't locate some elements
						
						if (ReadProperties.globalProp.getProperty("reset").contains("fullReset")) { // uninstall and install client
				            System.out.println("Driver DO FULL-RESET");
				            capabilities.setCapability("fullReset", true);
				            capabilities.setCapability("noReset", false);
				        } else if (ReadProperties.globalProp.getProperty("reset").contains("fastReset")) { // clears cache and settings without reinstall
				            System.out.println("Driver DO FAST-RESET");
				            capabilities.setCapability("fullReset", false);
				            capabilities.setCapability("noReset", false);
				        } else { // just start client
				            System.out.println("Driver DO NORMAL start"); 
				            capabilities.setCapability("fullReset", false);
				            capabilities.setCapability("noReset", true);
				        }
						
						//noReset if set to TRUE would reset the app data, i.e. it will clear app data.
						//fullReset if set to TRUE uninstalls the app.
						
						capabilities.setCapability("newCommandTimeout", 120); 
						
			            File appDir = new File(ReadProperties.globalProp.getProperty("apkfile")); 
			            File app = new File(appDir, "base.apk");
						capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
						capabilities.setCapability("appPackage", "com.xome.android");
						capabilities.setCapability("appActivity", "com.xome.android.ui.map.MapActivity2");
						
						runonrealdevice = "yes";

						driver = new AndroidDriver (new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
					}
					else if (ReadProperties.globalProp.getProperty("mobiledevice").contains("iOS")&& ReadProperties.globalProp.getProperty("runonrealdevice").contains("yes"))
					{
     
			            File appDir = new File(ReadProperties.globalProp.getProperty("iosappfile")); //this ipa was done with automatic signing
			            File app = new File(appDir, "TestApp.ipa");
			            capabilities.setCapability("app", app.getAbsolutePath());
						capabilities.setCapability("platformName", "iOS");     
						capabilities.setCapability("platformVersion", "10.3.3");     
						capabilities.setCapability("deviceName", "iPhone 5");     
						capabilities.setCapability("udid", "8319807bbbc1d04c9bbc0634e14d28aca946b536");
						capabilities.setCapability("automationName", "Appium");
						capabilities.setCapability("appiumVersion","7.0.0");
						capabilities.setCapability("agentPath", "/usr/local/lib/node_modules/appium/node_modules/appium-xcuitest-driver/WebDriverAgent/WebDriverAgent.xcodeproj"); //must use this or won't work
						capabilities.setCapability("bootstrapPath", "/usr/local/lib/node_modules/appium/node_modules/appium-xcuitest-driver/WebDriverAgent"); //must use this or won't work
						capabilities.setCapability("xcodeOrgid", "Angela Tong");
						capabilities.setCapability("xcodeSigningId", "iPhone Developer");
			            capabilities.setCapability("fullReset", false);
			            capabilities.setCapability("noReset", true);
						capabilities.setCapability("newCommandTimeout", 120);    

						
						runonrealdevice = "yes";
						
						driver = new IOSDriver (new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
					}
					else if (ReadProperties.globalProp.getProperty("simulator").contains("Android")&& ReadProperties.globalProp.getProperty("runonrealdevice").contains("no"))
					{
						//Tested work for Android simulator
						//Mac
			            File appDir = new File(ReadProperties.globalProp.getProperty("simulatormacandroidappfile"));
					    //Windows
			            //File appDir = new File(ReadProperties.globalProp.getProperty("simulatorpcandroidappfile"));
			            File app = new File(appDir, "base.apk");
			            capabilities.setCapability("app", app.getAbsolutePath());
						capabilities.setCapability("platformName", "Android");
						capabilities.setCapability("platformVersion", "11.0");     
						capabilities.setCapability("deviceName", "Pixel_3a");  
						//capabilities.setCapability("session-override", true); //set this in appium desktop settings
						capabilities.setCapability("newCommandTimeout", 120);
						 // Launches the below android virtual device and waits for 120 seconds for AVD to be ready
						capabilities.setCapability("avd", "Pixel_3a"); //need underscore for spaces in name for appium inspector
						capabilities.setCapability("avdReadyTimeout", 120000);
						capabilities.setCapability("appPackage", "com.xome.android");
						capabilities.setCapability("appActivity", "com.xome.android.home.HomeActivity");
						capabilities.setCapability("automationName", "uiautomator2");
						capabilities.setCapability("unicodeKeyboard", true);
						capabilities.setCapability("resetKeyboard", true);
						
						
						driver = new AndroidDriver (new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
											
						
					}
					else if (ReadProperties.globalProp.getProperty("simulator").contains("iOS")&& ReadProperties.globalProp.getProperty("runonrealdevice").contains("no"))
					{
     
			            File appDir = new File(ReadProperties.globalProp.getProperty("simulatormaciosappfile"));
			            File app = new File(appDir, "TestApp.app");
						capabilities.setCapability("platformName", "iOS");
						capabilities.setCapability("platformVersion", "11.0");   
						capabilities.setCapability("deviceName", "iPhone Simulator"); 
			            capabilities.setCapability("app", app.getAbsolutePath());
						capabilities.setCapability("newCommandTimeout", 120);
						
						driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
					}
				   
					

			}
			
				
	    }

        
    	
    	@DataProvider(name = "getData")
    	public Object[][] getData(Method method) throws Exception {
    		String testName= method.getName();
    		Object[][] arrayObject = null;
    			arrayObject = ExcelMethods.getDataFromExcelTestData(testName);

    		return (arrayObject);
    	}
        
    	@DataProvider(name = "getMobileData")
    	public Object[][] getMobileData(Method method) throws Exception {
    		String testName= method.getName();
    		Object[][] arrayObject = null;
    			arrayObject = ExcelMethods.getMobileDataFromExcelTestData(testName);

    		return (arrayObject);
    	}
    	
    	
    	@AfterMethod(alwaysRun=true) 
    	public void OnFailure(ITestResult testResult) throws IOException { 
    		if (testResult.getStatus() == ITestResult.FAILURE) 
    		{ 
    			System.out.println(testResult.getStatus()); 
    		} 
    	} 
    	
    	

    @AfterMethod(alwaysRun=true)
    //Use after method instead of after class or after test so each method/test will open in new browser; 
  	//This was tested and found aftermethod was the only one that works.  	
    public void tearDown() {
        if (driver != null) 
        {
            driver.quit();
        }
        else if (webdriver != null)
        {
            webdriver.quit();
        }
    }


}
