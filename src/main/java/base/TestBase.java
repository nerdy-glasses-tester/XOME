package base;

import common.ExcelMethods;
import common.ReadProperties;
import common.ScreenshotURL;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.edge.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.time.Duration;
import java.util.*;

public class TestBase{

    protected WebDriver webdriver;
    protected AppiumDriver mobileDriver;
    public static final String SCREENSHOT_BASE = "/Users/geeky/eclipse-workspace/XOME/screencaptures";

    // ======================================================
    // SETUP
    // ======================================================

    @BeforeMethod(alwaysRun = true)
    public void setup() throws Exception {
        // Retrieve global properties
        ReadProperties.retrieveGlobalProperties();

        // Initialize WebDriver for Web automation
        if (isWebAutomation()) {
            String browser = ReadProperties.globalProp.getProperty("webbrowser").toLowerCase();
            String os = System.getProperty("os.name");
            webdriver = initWebDriver(browser, os);

            webdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            webdriver.get(ReadProperties.globalProp.getProperty("weburl"));
            webdriver.manage().window().maximize();

        // Initialize AppiumDriver for Mobile automation
        } else if (isMobileAutomation()) {
            mobileDriver = getMobileDriver();
        }
    }

    private boolean isWebAutomation() {
        return "yes".equalsIgnoreCase(ReadProperties.globalProp.getProperty("webautomation")) &&
               "no".equalsIgnoreCase(ReadProperties.globalProp.getProperty("mobileautomation"));
    }

    private boolean isMobileAutomation() {
        return "no".equalsIgnoreCase(ReadProperties.globalProp.getProperty("webautomation")) &&
               "yes".equalsIgnoreCase(ReadProperties.globalProp.getProperty("mobileautomation"));
    }

    // ======================================================
    // WEB DRIVER SETUP (All Browsers with Options)
    // ======================================================

    private WebDriver initWebDriver(String browser, String os) {
        switch (browser) {
            case "chrome":
                return setupChrome(os);
            case "firefox":
                return setupFirefox(os);
            case "safari":
                return setupSafari();
            case "microsoftedge":
                return setupEdge();
            default:
                throw new RuntimeException("Unsupported browser: " + browser);
        }
    }

    private WebDriver setupChrome(String os) {
        ChromeOptions options = new ChromeOptions();
        String driverPath = getDriverPath("chrome", os);

        System.setProperty("webdriver.chrome.driver", driverPath);

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);

        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-blink-features=AutomationControlled",
                             "--disable-infobars",
                             "--start-maximized");
        options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);

        WebDriver driver = new ChromeDriver(options);
        ((JavascriptExecutor) driver).executeScript(
                "Object.defineProperty(navigator, 'webdriver', {get: () => undefined})");

        return driver;
    }

    private WebDriver setupFirefox(String os) {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("privacy.trackingprotection.enabled", false);
        profile.setPreference("dom.webnotifications.enabled", false);
        profile.setPreference("media.peerconnection.enabled", false);
        profile.setPreference("useAutomationExtension", false);
        profile.setPreference("dom.webdriver.enabled", false);

        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(profile);

        System.setProperty("webdriver.gecko.driver", ReadProperties.globalProp.getProperty("macgeckodriver"));

        WebDriver driver = new FirefoxDriver(options);
        ((JavascriptExecutor) driver).executeScript(
                "Object.defineProperty(navigator, 'webdriver', {get: () => undefined})");

        return driver;
    }

    private WebDriver setupSafari() {
        SafariOptions options = new SafariOptions();
        options.setCapability("platform", "MAC");
        options.setCapability("browser", "safari");
        return new org.openqa.selenium.safari.SafariDriver(options);
    }

    private WebDriver setupEdge() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64)");
        System.setProperty("webdriver.edge.driver", ReadProperties.globalProp.getProperty("edgedriver"));
        return new EdgeDriver(options);
    }

    // Helper function for getting driver path
    private String getDriverPath(String browser, String os) {
        String driverPath;
        if (os.toLowerCase().contains("mac")) {
            driverPath = ReadProperties.globalProp.getProperty("mac" + browser + "driver");
        } else {
            driverPath = ReadProperties.globalProp.getProperty("pc" + browser + "driver");
        }

        if (driverPath == null || driverPath.isEmpty()) {
            throw new RuntimeException("Driver path for " + browser + " not found in properties file!");
        }

        return driverPath;
    }


    // ======================================================
    // MOBILE DRIVER SETUP (Android & iOS Real Devices/Simulators)
    // ======================================================

    protected AppiumDriver getMobileDriver() throws Exception {
        String deviceType = ReadProperties.globalProp.getProperty("mobiledevice").toLowerCase();
        String runReal = ReadProperties.globalProp.getProperty("runonrealdevice", "no").toLowerCase();

        if (deviceType.contains("android") && runReal.equals("yes")) {
            return initAndroidRealDevice();
        } else if (deviceType.contains("android") && runReal.equals("no")) {
            return initAndroidSimulator();
        } else if (deviceType.contains("ios") && runReal.equals("yes")) {
            return initiOSRealDevice();
        } else if (deviceType.contains("ios") && runReal.equals("no")) {
            return initiOSSimulator();
        } else {
            throw new RuntimeException("Unsupported mobile configuration");
        }
    }

    private AndroidDriver initAndroidRealDevice() throws Exception {
    File apk = new File(ReadProperties.globalProp.getProperty("apkfile"), "base-clean.apk");
    System.out.println("APK Path: " + apk.getAbsolutePath()); // Validate the file path


    // Make sure the device name is correct
    UiAutomator2Options options = new UiAutomator2Options()
            .setPlatformName("Android")
            .setDeviceName("ecbfd827b282a9dc")
            .setApp(new File("/Users/geeky/eclipse-workspace/XOME/src/test/resources/base-clean.apk").getAbsolutePath())
            .setAppPackage("com.xome.android")
            .setAppActivity("com.xome.android.ui.start.SplashActivity")
            .setAutoGrantPermissions(true)
            .setAutomationName("UIAutomator2")
            .setNoReset(false)
            .setFullReset(false);


    System.out.println("APK Path: " + new File("/Users/geeky/eclipse-workspace/XOME/src/test/resources/base-clean.apk").getAbsolutePath());
    
    // Create the driver instance by passing the URL and options
    AppiumDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
    return (AndroidDriver) driver;
    }


	private AndroidDriver initAndroidSimulator() throws Exception {
    File apk = new File(ReadProperties.globalProp.getProperty("apkfile"), "base-clean.apk");
    System.out.println("APK Path: " + apk.getAbsolutePath()); // Validate the file path

    UiAutomator2Options options = new UiAutomator2Options()
            .setPlatformName("Android")
            .setDeviceName("Medium_Tablet")
            .setApp(new File("/Users/geeky/eclipse-workspace/XOME/src/test/resources/base-clean.apk").getAbsolutePath())
            .setAppPackage("com.xome.android")
            .setAppActivity("com.xome.android.ui.start.SplashActivity")
            .setNoReset(false)
            .setFullReset(false);

    System.out.println("APK Path: " + new File("/Users/geeky/eclipse-workspace/XOME/src/test/resources/base-clean.apk").getAbsolutePath());

    // Create the driver instance by passing the URL and options
    AppiumDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
    return (AndroidDriver) driver;
    }


    private IOSDriver initiOSRealDevice() throws Exception {
    XCUITestOptions options = new XCUITestOptions()
            .setPlatformName("iOS")
            .setDeviceName("iPhone 13 Pro")
            .setApp(new File("/path/to/your/app.app").getAbsolutePath())
            .setNoReset(false)
            .setFullReset(false);

    // Create the driver instance by passing the URL and options
    AppiumDriver driver = new IOSDriver(new URL("http://127.0.0.1:4723"), options);
    return (IOSDriver) driver;
    }

	private IOSDriver initiOSSimulator() throws Exception {
    XCUITestOptions options = new XCUITestOptions()
            .setPlatformName("iOS")
            .setDeviceName("iPhone 13 Pro")
            .setApp(new File("/path/to/your/app.app").getAbsolutePath())
            .setNoReset(false)
            .setFullReset(false);

    // Create the driver instance by passing the URL and options
    AppiumDriver driver = new IOSDriver(new URL("http://127.0.0.1:4723"), options);
    return (IOSDriver) driver;
    }


    // ======================================================
    // DATA PROVIDERS (Test Data from Excel)
    // ======================================================

    @DataProvider(name = "getData")
    public Object[][] getData(Method method) throws Exception {
        return ExcelMethods.getDataFromExcelTestData(method.getName());
    }

    @DataProvider(name = "getMobileData")
    public Object[][] getMobileData(Method method) throws Exception {
        return ExcelMethods.getMobileDataFromExcelTestData(method.getName());
    }

    // ======================================================
    // TEARDOWN + SCREENSHOTURL
    // ======================================================

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            Object activeDriver = (webdriver != null) ? webdriver : mobileDriver;
            ScreenshotURL.capture(activeDriver, SCREENSHOT_BASE, getClass().getSimpleName(), result.getName());
        }
        quitDrivers();
    }

    private void quitDrivers() {
        try {
            if (mobileDriver != null) mobileDriver.quit();
        } catch (Exception ignored) {}

        try {
            if (webdriver != null) webdriver.quit();
        } catch (Exception ignored) {}

        mobileDriver = null;
        webdriver = null;
    }
}



