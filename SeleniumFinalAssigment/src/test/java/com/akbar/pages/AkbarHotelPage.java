package com.akbar.pages;

import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.akbar.utility.CalenderDate;
import com.akbar.utility.Xls_Reader;

public class AkbarHotelPage {
	WebDriver driver;

	@FindBy(how = How.XPATH, using = "//flight-home//h3[text()='Hotel']")
	WebElement Hotel;

	@FindBy(how = How.XPATH, using = "//label[text()='Enter your Destination or Property']")
	WebElement EnterDestination;

	@FindBy(how = How.XPATH, using = "//input[@placeholder='Enter City/Hotel/Area/building']")
	WebElement InputDestination;

	@FindBy(how = How.XPATH, using = "//destination//ul[1]/li[2]")
	WebElement ClickDestination;

	@FindBy(how = How.XPATH, using = "//li[1]//div[text()='+ ']")
	WebElement Adult;

	@FindBy(how = How.XPATH, using = "//li[2]//div[text()='+ ']")
	WebElement Children;

	@FindBy(how = How.XPATH, using = "//div[@class='mat-form-field-infix']/mat-select[@aria-label='Child Age']")
	WebElement ChildAge;

	@FindBy(how = How.XPATH, using = "//span[text()=' 4 ']")
	WebElement ChildAgeSelected;

	@FindBy(how = How.XPATH, using = "//li[@class='btnarea']//span[text()='Search Hotels']")
	WebElement SearchHotel;

	@FindBy(how = How.XPATH, using = "//section[@class='search-criteria']/ul[@class='left']")
	WebElement ActualText;

	@FindBy(how = How.XPATH, using = "//span[text()='Done']")
	WebElement Done;

	@FindBy(how = How.XPATH, using = "//p[contains(text(),' hotels found')]")
	WebElement Hotels;

	@FindBy(how = How.XPATH, using = "//ul/li[2]/p")
	WebElement FromWeekDay;

	@FindBy(how = How.XPATH, using = "//ul/li[3]/p")
	WebElement ToWeekDay;

	@FindBy(how = How.XPATH, using = "//div[@class='nights']/span")
	WebElement Nights;

	@FindBy(how = How.XPATH, using = "//li[@class='cityHotel']/div/h6")
	WebElement Destination;

	String FromDat;
	String ToDat;
	String dateFrom;
	String dateTo;
	static String WeekFromDay;
	static String WeekToDay;
	static String nights;
	String placeFromHotel;

	public AkbarHotelPage(WebDriver driver) {
		this.driver = driver;
	}
//method to book hotel
	public void hotelBooking(String HotelName) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(EnterDestination));
		EnterDestination.click();
		InputDestination.sendKeys(HotelName);
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ClickDestination.click();
		driver.findElement(By.xpath(FromDat)).click();
		driver.findElement(By.xpath(ToDat)).click();
		Adult.click();
		Children.click();
		ChildAge.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", ChildAgeSelected);
		ChildAgeSelected.click();
		Done.click();
		WeekFromDay = FromWeekDay.getText();
		WeekToDay = ToWeekDay.getText();
		nights = Nights.getText();
		placeFromHotel = Destination.getText();
		SearchHotel.click();
	}
//Method to get actual data for comparision
	public String actualText() {

		String actualText = ActualText.getText().trim();
		return actualText;
	}
//Method to form xpath of fromdate and todate
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

	public String numberOfHotel() {
		String hotels = Hotels.getText();
		hotels = hotels.substring(8, 11);
		return hotels;

	}

	public ArrayList<String> setDateVal() {
		CalenderDate cd = new CalenderDate();
		HashMap<String, String> datesfrm = cd.setDate(dateFrom);
		String month = datesfrm.get("MonthName").substring(0, 3);
		String dateFormat = datesfrm.get("Date") + " " + month + "’" + datesfrm.get("Year") + " , " + WeekFromDay;
		HashMap<String, String> datesto = cd.setDate(dateTo);
		String month1 = datesto.get("MonthName").substring(0, 3);
		String dateFormat1 = datesto.get("Date") + " " + month1 + "’" + datesto.get("Year") + " , " + WeekToDay;
		nights = nights + " NightS";
		ArrayList<String> setData = new ArrayList<String>();
		setData.add(dateFormat);
		setData.add(dateFormat1);
		setData.add(nights);
		setData.add(placeFromHotel);
		return setData;

	}
//set actual data into excel
	public void setExcelData() {
		Xls_Reader excelReader = new Xls_Reader("./TestData/TestHotelData.xlsx");

		ArrayList<String> fromDataInput = setDateVal();
		excelReader.setCellData("ExpectedData", "ExpectedData", 4, fromDataInput.get(0));
		excelReader.setCellData("ExpectedData", "ExpectedData", 7, fromDataInput.get(1));
		excelReader.setCellData("ExpectedData", "ExpectedData", 5, fromDataInput.get(2));
		excelReader.setCellData("ExpectedData", "ExpectedData", 2, fromDataInput.get(3));
	}

}
