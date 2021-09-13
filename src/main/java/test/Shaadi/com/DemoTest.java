package test.Shaadi.com;

import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class DemoTest {

	protected AndroidDriver<MobileElement> driver;
	protected WebDriverWait wait;

	By login = By.id("com.shaadi.android:id/btn_morph_login");
	By userName = By.id("com.shaadi.android:id/edt_username");
	By password = By.id("com.shaadi.android:id/edt_password");
	By loginButton = By.id("com.shaadi.android:id/btn_login");
	By myShaadi = By.id("com.shaadi.android:id/imgMyShaadi");
	By myShaadiDashboard = By.id("com.shaadi.android:id/tvDashboard");
	By premiumMatch = By.xpath("//*[contains(@text,'Premium Matches')]");
	By newMatch = By.xpath("//*[contains(@text,'New Matches')]");
	By connectFromPremiumMatch = By
			.xpath("//*[contains(@text,'Premium Matches')]//following::*[contains(@text,'Connect Now')]");
	By RequestSentForPremiumMatch = By
			.xpath("//*[contains(@text,'Premium Matches')]//following::*[contains(@text,'Shaadi Chat')]");
	By connectFromNewMatch = By
			.xpath("//*[contains(@text,'New Matches')]//following::*[contains(@text,'Connect Now')]");
	By RequestSentForNewMatch = By
			.xpath("//*[contains(@text,'New Matches')]//following::*[contains(@text,'Shaadi Chat')]");
	
	By closeMsg = By.id("com.shaadi.android:id/iv_cancel");
	
	
	@BeforeMethod
	public void configuration() {

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "Jack");
		capabilities.setCapability(CapabilityType.VERSION, "7.0");
		capabilities.setCapability("platformName", "android");
		capabilities.setCapability("autoGrantPermissions", true);
		capabilities.setCapability("appPackage", "com.shaadi.android");
		capabilities.setCapability("appActivity", "com.shaadi.android.ui.login.NewLoginActivity");

		try {
			driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			wait = new WebDriverWait(driver, 10);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void sampleTest() {

		loginToShaadiApp();
		navigateToMyShaadi();
		verifyWidgets();
		sendConnectRequest();
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}

	public void loginToShaadiApp() {
		try {

			wait.until(ExpectedConditions.visibilityOfElementLocated(userName));

			driver.findElement(userName).clear();
			driver.findElement(userName).sendKeys("username");

			driver.findElement(password).sendKeys("password");

			driver.findElement(loginButton).click();
			waitABit(5);
			wait.until(ExpectedConditions.visibilityOfElementLocated(myShaadi));

			Assert.assertTrue(true, "User Logged In Successfully");

		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false, "Login Unsuccessful");
		}

	}

	public void navigateToMyShaadi() {

		try {
			
			driver.findElement(myShaadi).click();
			waitABit(3);
			Assert.assertTrue(driver.findElement(myShaadiDashboard).isDisplayed(),"User Navigates to My Shaadi");

		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}

	}

	public void verifyWidgets() {
		try {
			waitABit(3);
			Assert.assertTrue(driver.findElement(premiumMatch).isDisplayed(),"Premium Match widget displayed");		
			waitABit(3);
			Assert.assertTrue(driver.findElement(newMatch).isDisplayed(),"New Match widget displayed");
	
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}

	}

	public void sendConnectRequest() {
		try {
			
			driver.findElement(connectFromPremiumMatch).click();	
			driver.findElement(closeMsg).click();
			Assert.assertTrue(driver.findElement(RequestSentForPremiumMatch).isDisplayed(),"Connect Request Sent For PremiumMatch");
			waitABit(3);
			driver.findElement(connectFromNewMatch).click();	
			driver.findElement(closeMsg).click();
			Assert.assertTrue(driver.findElement(RequestSentForNewMatch).isDisplayed(),"Connect Request Sent For NewMatch");

		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}

	}
	
	public void waitABit(int timeout) {
		try {
			Thread.sleep(timeout*1000);
		}catch(Exception e) {
			
		}
		
	}

}
