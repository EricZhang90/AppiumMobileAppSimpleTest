import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class AndroidSimpleTest {
	private AppiumDriver<WebElement> driver;
	
	@Before
    public void setUp() throws Exception {
        // set up appium
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8");

        driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }
	
	@After
    public void tearDown() throws Exception {
        driver.quit();
    }
	

	public void acceptTerm() throws Exception{
		String context = driver.getContext(); 
		driver.context("NATIVE_APP");
		driver.findElementById("com.android.chrome:id/send_report_checkbox").click();
		driver.findElement(By.id("com.android.chrome:id/terms_accept")).click();
		driver.findElement(By.id("com.android.chrome:id/negative_button")).click();
		driver.context(context);
	}
	
	
	@Test
	public void webTest() throws Exception {
		driver.get("http://www.google.ca");
		
		acceptTerm();
		
		WebElement searchBox = driver.findElementByName("q");
		searchBox.sendKeys("CYSSC Ontario \n");
		
		Thread.sleep(2000);
		
		WebElement govLink = driver.findElement(By.linkText("Government | Ontario.ca"));
		
		govLink.click();
		
		Thread.sleep(7000);
		
	}
}
