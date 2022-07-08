package common;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;
import base.TestBase;
import io.appium.java_client.AppiumDriver;


public class MiscMethods extends TestBase{
		
		public static ArrayList<Integer> sortDescending(ArrayList<Integer> arr){
		    Comparator<Integer> c = Collections.reverseOrder();
		    Collections.sort(arr,c);
		    return arr;
		  }


		public static ArrayList<Integer> sortAscending(ArrayList<Integer> arr){   
		    Collections.sort(arr);
		    return arr;
		  }
		
	    public static String returnsStringforIntegerArrayList(ArrayList<Integer> arr) {
	    		//String listString = arr.stream().map(Object::toString).collect(Collectors.joining(", ")); //This needs Java 1.8
	    		String listString = arr.toString().replaceAll(",","");
	    		return listString;
	    }

		
	    public static boolean isElementPresent(WebDriver webdriver, By selector) {
	        webdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	        boolean returnVal = true;
	        try{
	            webdriver.findElement(selector);
	        } catch (NoSuchElementException e){
	            returnVal = false;
	        } finally {
	        	webdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	        }
	        return returnVal;
	    }

	    
	    public static void drag(AppiumDriver appiumdriver, WebElement element) {
	    	((JavascriptExecutor) appiumdriver).executeScript("mobile: dragGesture", ImmutableMap.of(
	    		    "elementId", ((RemoteWebElement) element).getId(),
	    		    "endX", 40,
	    		    "endY", 40
	    		));
	    }
	    
	    public static void swipeUp75(AppiumDriver appiumdriver) {
	    	((JavascriptExecutor) appiumdriver).executeScript("mobile: swipeGesture", ImmutableMap.of(
	    		    "left", 400, "top", 900, "width", 400, "height", 900,
	    		    "direction", "up",
	    		    "percent", 0.75
	    		));
	    }
	    

	    
	    public static void swipeDown75(AppiumDriver appiumdriver) {
	    	((JavascriptExecutor) appiumdriver).executeScript("mobile: swipeGesture", ImmutableMap.of(
	    		    "left", 400, "top", 900, "width", 400, "height", 900,
	    		    "direction", "down",
	    		    "percent", 0.75
	    		));
	    }
	   
	    
	    
	    public static void scrollUp(AppiumDriver appiumdriver) {
	    	((JavascriptExecutor) appiumdriver).executeScript("mobile: scrollGesture", ImmutableMap.of(
	    		    "left", 100, "top", 100, "width", 400, "height", 400,
	    		    "direction", "up",
	    		    "percent", 8.0,
	    		    "speed", 10000
	    		));
	    }
	    
	    
	    public static void scrollDown(AppiumDriver appiumdriver) {
	    	 ((JavascriptExecutor) appiumdriver).executeScript("mobile: scrollGesture", ImmutableMap.of(
	    		    "left", 100, "top", 100, "width", 400, "height", 400,
	    		    "direction", "down",
	    		    "percent", 5.0,
	    		    "speed", 10000
	    		));
	    }
	    
	    public static void scrollDown1(AppiumDriver appiumdriver) {
	    	 ((JavascriptExecutor) appiumdriver).executeScript("mobile: scrollGesture", ImmutableMap.of(
	    		    "left", 100, "top", 100, "width", 400, "height", 400,
	    		    "direction", "down",
	    		    "percent", 1.0,
	    		    "speed", 10000
	    		));
	    }

}
