package Appium.JenkinsTest;

import java.io.File;
import java.net.MalformedURLException;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class Calculator {

	static AndroidDriver<AndroidElement> driver;

	public static void main(String[] args) throws MalformedURLException {	
		openCalculator();
	}

	private static void openCalculator() throws MalformedURLException {
		AppiumDriverLocalService service = new AppiumServiceBuilder()
		.withAppiumJS(new File("C:/Users/arvin/AppData/Roaming/npm/node_modules/appium/build/lib/main.js"))
		.withIPAddress("127.0.0.1").usingPort(4723).build();
		
		service.start();
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "Redmi Note 11 Pro Plus 5G");
		capabilities.setCapability("udid", "1178b7a17532");
		capabilities.setCapability("appPackage", "com.google.android.calculator");
		capabilities.setCapability("appActivity", "com.android.calculator2.Calculator");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "11");
				
		driver = new AndroidDriver<AndroidElement>(capabilities);
		
		 AndroidElement one = driver.findElement(By.id("com.google.android.calculator:id/digit_1"));
		 AndroidElement two = driver.findElement(By.id("com.google.android.calculator:id/digit_2"));
		 AndroidElement plus = driver.findElement(By.id("com.google.android.calculator:id/op_add"));
		 AndroidElement equal = driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc='equals']"));
		 one.click();
		 plus.click();
		 two.click();
		 equal.click();
		 
		 AndroidElement result = driver.findElement(By.id("com.google.android.calculator:id/result_final"));
		 String actual = result.getText();
		 int actualResult = Integer.parseInt(actual);
		 String expected = "3";
		 int expectedResult = Integer.parseInt(expected);
		 
		 if (actualResult==expectedResult) {
			System.out.println("Test Passed");
		} else {
			System.out.println("Test Failed");
		}
		 
		 driver.quit();
		 service.stop();
	}

}
