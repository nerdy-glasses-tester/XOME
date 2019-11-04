package base;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import common.ReadProperties;

public class LoginTest {


	public static void main (String[] args) throws InterruptedException
	{
		SoftAssert softAssert = new SoftAssert();
		
		System.setProperty("webdriver.gecko.driver", "/Users/macbookpro/seleniumgrid/geckodriver");
		
		WebDriver driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver, 60);
        
        driver.get("http://www.udemy.com");
        driver.manage().window().maximize(); 
        
        
        driver.findElement(By.cssSelector("button.btn.btn-quaternary")).click();
        
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div>input#email--1")));
        driver.findElement(By.cssSelector("div>input#email--1")).sendKeys("angeetong@gmail.com");
        
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div>input#id_password")));
        driver.findElement(By.cssSelector("div>input#id_password")).sendKeys("P00pieB@o");
        
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div>input#submit-id-submit")));
        driver.findElement(By.cssSelector("div>input#submit-id-submit")).click();
        
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[data-purpose='user-dropdown']>div.user-avatar.user-avatar--initials>div.user-avatar__inner.fx-c>span.user-initials")));
        String username = driver.findElement(By.cssSelector("a[data-purpose='user-dropdown']>div.user-avatar.user-avatar--initials>div.user-avatar__inner.fx-c>span.user-initials")).getText();
        
        try {
            Assert.assertEquals(username, "AT");
        	System.out.println("Verified username after login.");
        }
        catch (AssertionError e)
        {
        	System.out.println("Failed to verify username after login.");
        	softAssert.fail();
        }
        
        driver.quit();
	}
}
