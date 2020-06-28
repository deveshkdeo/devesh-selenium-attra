package com.akbar.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.akbar.utility.CalenderDate;
import com.akbar.utility.Xls_Reader;

public class AkbarFlightPage {
	WebDriver driver;

	@FindBy(how = How.XPATH, using = "//label[@class='mat-radio-label'][@for='mat-radio-3-input']//descendant-or-self::div[@class='mat-radio-outer-circle']")
	WebElement RoundTrip;

	@FindBy(how = How.XPATH, using = "//label[text()='FROM']")
	WebElement FromPlace;

	@FindBy(how = How.XPATH, using = "//input[@id='mat-input-5'][@placeholder='From']")
	WebElement InputFromPlace;

	@FindBy(how = How.XPATH, using = "//ul[@id='list']/li[2]")
	WebElement SelectFromPLace;

	@FindBy(how = How.XPATH, using = "//label[text()='TO ']")
	WebElement ToPlace;

	@FindBy(how = How.XPATH, using = "//input[@placeholder='To']")
	WebElement InputToPlace;

	@FindBy(how = How.XPATH, using = "//ul[@id='list']/li[2]")
	WebElement SelectToPlace;

	@FindBy(how = How.XPATH, using = "//label[text()=' depart ']")
	WebElement SelectFromDate;

	@FindBy(how = How.XPATH, using = "//li[1]//div[@class='btns'][text()='+']")
	WebElement Adults;

	@FindBy(how = How.XPATH, using = "//li[2]//div[@class='btns'][text()='+']")
	WebElement Children;

	@FindBy(how = How.XPATH, using = "//div[@class='btns'][text()='+ ']")
	WebElement Infants;

	@FindBy(how = How.XPATH, using = "//mat-radio-button[@id='mat-radio-8']//div[@class='mat-radio-outer-circle']")
	WebElement Bussiness;

	@FindBy(how = How.XPATH, using = "//span[@class='mat-button-wrapper'][text()='Apply']")
	WebElement Apply;

	@FindBy(how = How.XPATH, using = "//li[@class='btnarea']//span[@class='mat-button-wrapper'][text()='Search Flights']")
	WebElement Search;

	@FindBy(how = How.XPATH, using = "//div/section[1]/div[@class='second-section']//p[@class='ng-tns-c56-47'][text()='Departure']")
	WebElement Departure;

	@FindBy(how = How.XPATH, using = "//div/section[1]/div[@class='second-section']//p[@class='ng-tns-c56-47'][text()='Return']")
	WebElement Return;

	@FindBy(how = How.XPATH, using = "//section[1]/header/div[2]/div[1]/h2")
	WebElement Amount;

	@FindBy(how = How.XPATH, using = "//ul[@class='left']")
	WebElement ActualText;

	@FindBy(how = How.XPATH, using = "//li[@id='liOn']/p")
	WebElement FromWeekDay;

	@FindBy(how = How.XPATH, using = "//li[@id='liRT']/div/p")
	WebElement ToWeekDay;

	@FindBy(how = How.XPATH, using = "//li[@id='liFrom']/div/p")
	WebElement FromPLace;

	@FindBy(how = How.XPATH, using = "//li[@id='liTo']/div/p")
	WebElement Toplace;

	@FindBy(how = How.XPATH, using = "//li[@id='liTo']/div/h6")
	WebElement ToFullName;

	@FindBy(how = How.XPATH, using = "//li[@id='liFrom']/div/h6")
	WebElement FromFullName;

	String FromDat;
	String ToDat;
	String dateFrom;
	String dateTo;
	String ToFullNames;
	String FromFullNames;
	static String WeekFromDay;
	static String WeekToDay;
	String placeFromDes;
	String placeToDes;

	public AkbarFlightPage(WebDriver driver) {
		this.driver = driver;

	}

	public void flightBooking(String fromPlace, String toPlace) {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		Actions act = new Actions(driver);

		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		RoundTrip.click();
		wait.until(ExpectedConditions.elementToBeClickable(FromPlace));
		FromPlace.click();
		InputFromPlace.sendKeys(fromPlace);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		SelectFromPLace.click();
		SelectFromPLace.click();
		ToPlace.click();
		InputToPlace.sendKeys(toPlace);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		SelectToPlace.click();
		SelectFromDate.click();

		driver.findElement(By.xpath(FromDat)).click();
		driver.findElement(By.xpath(ToDat)).click();
		Adults.click();
		Children.click();
		Infants.click();
		Bussiness.click();
		Apply.click();
		placeFromDes = FromPLace.getText();
		placeToDes = Toplace.getText();
		WeekFromDay = FromWeekDay.getText();
		WeekToDay = ToWeekDay.getText();
		ToFullNames = ToFullName.getText();
		FromFullNames = FromFullName.getText();
		act.moveToElement(Search).click().perform();

	}

	public LinkedHashMap<String, String> flightData() {

		String departure = Departure.getText().trim();
		String returnData = Return.getText().trim();
		String amount = Amount.getText();
		LinkedHashMap<String, String> filghtBookingData = new LinkedHashMap<String, String>();
		filghtBookingData.put("departure", departure);
		filghtBookingData.put("Return", returnData);
		filghtBookingData.put("amount", amount);
		return filghtBookingData;
	}

	public String actualText() {

		String actualText = ActualText.getText().trim();
		return actualText;
	}

	public void dat(String dateFrom, String dateTo) {
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		CalenderDate cd = new CalenderDate();
		HashMap<String, String> datesfrm = cd.setDate(dateFrom);
		FromDat = "//div[@class='month-head'][text()='" + datesfrm.get("MonthName") + " " + "']//following::bi-week["
				+ datesfrm.get("WeekOfMonth") + "]//div[text()=' " + datesfrm.get("Date") + "']";
		HashMap<String, String> datesto = cd.setDate(dateTo);
		ToDat = "//div[@class='month-head'][text()='" + datesto.get("MonthName") + " " + "']//following::bi-week["
				+ datesto.get("WeekOfMonth") + "]//div[text()=' " + datesto.get("Date") + "']";

	}

	public ArrayList<String> setDateVal() {
		CalenderDate cd = new CalenderDate();
		HashMap<String, String> datesfrm = cd.setDate(dateFrom);
		String month = datesfrm.get("MonthName").substring(0, 3);
		String dateFormat = datesfrm.get("Date") + " " + month + "’" + datesfrm.get("Year") + " , " + WeekFromDay;
		HashMap<String, String> datesto = cd.setDate(dateTo);
		String month1 = datesto.get("MonthName").substring(0, 3);
		String dateFormat1 = datesto.get("Date") + " " + month1 + "’" + datesto.get("Year") + " , " + WeekToDay;
		placeFromDes = placeFromDes.substring(1, 4);
		placeToDes = placeToDes.substring(1, 4);
		ArrayList<String> setData = new ArrayList<String>();
		setData.add(dateFormat);
		setData.add(dateFormat1);
		setData.add(placeFromDes);
		setData.add(placeToDes);
		setData.add(FromFullNames);
		setData.add(ToFullNames);

		return setData;

	}

	public void setExcelData() {
		Xls_Reader excelReader = new Xls_Reader("./TestData/TestData.xlsx");

		ArrayList<String> fromDataInput = setDateVal();
		excelReader.setCellData("FlightDate", "Data", 7, fromDataInput.get(0));
		excelReader.setCellData("FlightDate", "Data", 9, fromDataInput.get(1));
		excelReader.setCellData("FlightDate", "Data", 2, fromDataInput.get(2));
		excelReader.setCellData("FlightDate", "Data", 4, fromDataInput.get(3));
		excelReader.setCellData("FlightDate", "Data", 3, fromDataInput.get(4));
		excelReader.setCellData("FlightDate", "Data", 5, fromDataInput.get(5));
	}

}
