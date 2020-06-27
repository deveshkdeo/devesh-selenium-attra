package com.akbar.testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.akbar.pages.AkbarLoginPage;
import com.browser.BrowserUsed;

public class AkbarTestVerifyRegisterName {
	WebDriver driver;

	@BeforeTest
	public void startBrowser() {

		BrowserUsed browse = new BrowserUsed(driver);
		driver = browse.browserName("firefox");
	}

	@Test
	@Parameters({ "userName", "password" ,"url"})
	public void VerifyRegisterName(String userName, String password,String url) {

		AkbarLoginPage login = PageFactory.initElements(driver, AkbarLoginPage.class);
		driver.get(url);
		login.login(userName, password);
		String user_id=login.getUserName();
		System.out.println("Expected text on button:" + userName);
		System.out.println("Actual text on button: " + user_id);
		if (user_id.equals(userName)) {

			System.out.println("Expected text on button matches ");
		} else
			System.out.println("mismatches Actual button text");
	}

	@AfterTest
	public void close() {

		driver.quit();
	}

}
