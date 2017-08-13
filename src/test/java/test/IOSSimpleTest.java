package test;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class IOSSimpleTest {
	private AppiumDriver<WebElement> driver;
	
	@Before
    public void setUp() throws Exception {
		
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10.3");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 6");
        //desiredCapabilities.setCapability(MobileCapabilityType.APP, "/Users/EricZhang/Desktop/Just Cook.app");
        desiredCapabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari");
        desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 120);
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
        
        driver = new IOSDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS); 
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void testUIComputation() throws Exception {
  	    
    	driver.get("http://www.google.com");
    	
        WebElement searchBox = driver.findElementByName("q");
        searchBox.sendKeys("CYSSC Ontario \n");
        
        Thread.sleep(2000); // No need in real automation testing. It just lets user to see what page is loaded.
        
        WebElement govLink = driver.findElement(By.linkText("Government | Ontario.ca"));
        
        govLink.click();
        
        Thread.sleep(7000);
    }
}
