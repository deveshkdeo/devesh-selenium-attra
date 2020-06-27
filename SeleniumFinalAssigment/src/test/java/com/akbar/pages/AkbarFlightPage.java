package com.akbar.pages;

import java.util.LinkedHashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AkbarFlightPage {
	WebDriver driver;
	
	@FindBy(how=How.XPATH,using="//label[@class='mat-radio-label'][@for='mat-radio-3-input']//descendant-or-self::div[@class='mat-radio-outer-circle']")
	WebElement RoundTrip;
	
	@FindBy(how=How.XPATH,using="//label[text()='FROM']")
	WebElement FromPlace;
	
	@FindBy(how=How.XPATH,using="//input[@id='mat-input-5'][@placeholder='From']")
   WebElement InputFromPlace;
	
	@FindBy(how=How.XPATH,using="//div[@class='search-list']//following::span[text()='Dubai']")
	WebElement SelectFromPLace;
	
	@FindBy(how=How.XPATH,using="//label[text()='TO ']")
	WebElement ToPlace;
	
	@FindBy(how=How.XPATH,using="//input[@placeholder='To']")
	WebElement InputToPlace;
	
	@FindBy(how=How.XPATH,using="//span[text()='All Airports New York']")
	WebElement SelectToPlace;
	
	@FindBy(how=How.XPATH,using="//label[text()=' depart ']")
	WebElement SelectFromDate;
	
	
	
	@FindBy(how=How.XPATH,using="//div[@class='month-head'][text()='July ']//following::bi-week[3]//div[text()=' 15']")
	WebElement FromDate;
	
	@FindBy(how=How.XPATH,using="//div[@class='month-head'][text()='August ']//following::bi-week[3]//div[text()=' 15']")
	WebElement ToDate;
	
    @FindBy(how=How.XPATH,using="//li[1]//div[@class='btns'][text()='+']")
	WebElement Adults;
	
	@FindBy(how=How.XPATH,using="//li[2]//div[@class='btns'][text()='+']")
	WebElement Children;
	

	@FindBy(how=How.XPATH,using="//div[@class='btns'][text()='+ ']")
	WebElement Infants;
	
	@FindBy(how=How.XPATH,using="//mat-radio-button[@id='mat-radio-8']//div[@class='mat-radio-outer-circle']")
	WebElement Bussiness;
	
	@FindBy(how=How.XPATH,using="//span[@class='mat-button-wrapper'][text()='Apply']")
	WebElement Apply;
	
	@FindBy(how=How.XPATH,using="//li[@class='btnarea']//span[@class='mat-button-wrapper'][text()='Search Flights']")
	WebElement Search;
	
	@FindBy(how=How.XPATH,using="//div/section[1]/div[@class='second-section']//p[@class='ng-tns-c56-47'][text()='Departure']")
	WebElement Departure;
	
	@FindBy(how=How.XPATH,using="//div/section[1]/div[@class='second-section']//p[@class='ng-tns-c56-47'][text()='Return']")
	WebElement Return;
	
	@FindBy(how=How.XPATH,using="//section[1]/header/div[2]/div[1]/h2")
	WebElement Amount;
	
	
	public AkbarFlightPage(WebDriver driver) {
		this.driver=driver;
		
	}
	
	public void flightBooking(String fromPlace,String toPlace) {
		
		WebDriverWait wait=new WebDriverWait(driver, 30);
		Actions act = new Actions(driver);
		
		 try { Thread.sleep(6000); 
		 } 
		 catch (InterruptedException e) { 
			 e.printStackTrace(); }
		 
		RoundTrip.click();
		wait.until(ExpectedConditions.elementToBeClickable(FromPlace));
		FromPlace.click();
		InputFromPlace.sendKeys(fromPlace);
		SelectFromPLace.click();
		SelectFromPLace.click();
		ToPlace.click();
		InputToPlace.sendKeys(toPlace);
		wait.until(ExpectedConditions.elementToBeClickable(SelectToPlace));
		SelectToPlace.click();
		SelectFromDate.click();
		FromDate.click();
		ToDate.click();
		Adults.click();
		Children.click();
		Bussiness.click();
		Apply.click();
		act.moveToElement(Search).click().perform();
	}
	public static void SelectDates() {
		
		
	}
	
	 public LinkedHashMap<String,String> flightData() {
		 
		 String departure = driver
					.findElement(By.xpath(
							"//div/section[1]/div[@class='second-section']//p[@class='ng-tns-c56-47'][text()='Departure']"))
					.getText().trim();
			String Return = driver
					.findElement(By.xpath(
							"//div/section[1]/div[@class='second-section']//p[@class='ng-tns-c56-47'][text()='Return']"))
					.getText().trim();
			WebDriverWait wait=new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[1]/header/div[2]/div[1]/h2")));
			String amount = driver.findElement(By.xpath("//section[1]/header/div[2]/div[1]/h2")).getText();
			LinkedHashMap<String,String> filghtBookingData =new LinkedHashMap<String,String>();
			filghtBookingData.put("departure", departure);
			filghtBookingData.put("Return", Return);
			filghtBookingData.put("amount", amount);
			
			return filghtBookingData;
			
	 }
	 
	
	

}
