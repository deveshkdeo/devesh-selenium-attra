/**
 * 
 */
package com.akbar.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author devesh Login page
 *
 */
public class AkbarLoginPage {
	WebDriver driver;
	@FindBy(how=How.XPATH,using="//button[@class='mat-button logged-out']//following::*[text()=' Login / Register ']")
	WebElement login_register_tab;
	
	@FindBy(how=How.XPATH,using="//div[@class='mat-menu-content']//following::*[text()='Login ']")
	WebElement login_tab;
	
	@FindBy(how=How.XPATH,using="//input[@formcontrolname='ClientID'][@placeholder='Email ID']")
	WebElement userName;
	
	@FindBy(how=How.XPATH,using="//input[@placeholder='Password']")
	WebElement password;
	
	@FindBy(how=How.XPATH,using="//span[@class='mat-button-wrapper'][text()='Login']")
	WebElement login_button;
	
	@FindBy(how=How.XPATH,using="//button[@class='mat-button afterlogin']//following::*[contains(text(),'Hi ')]")
	WebElement user_id;
	
	
    public AkbarLoginPage(WebDriver driver) {
    	this.driver=driver;
    	
    }
	public void login(String uname, String pass) {
		WebDriverWait wait=new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(login_register_tab));
		login_register_tab.click();
		wait.until(ExpectedConditions.elementToBeClickable(login_tab));
		login_tab.click();
		
		userName.sendKeys(uname);
		password.sendKeys(pass);
		login_button.click();

	}
	
	public String getUserName() {
		String user_id = driver
				.findElement(By.xpath("//button[@class='mat-button afterlogin']//following::*[contains(text(),'Hi ')]"))
				.getText();
		user_id = user_id.replaceAll("Hi ", "");
		
		return user_id;
		
		
	}

}
