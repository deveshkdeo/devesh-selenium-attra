package com.akbar.testCases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.akbar.pages.AkbarFlightPage;
import com.akbar.pages.AkbarLoginPage;
import com.akbar.utility.Calender;
import com.akbar.utility.Xls_Reader;
import com.browser.BrowserUsed;

public class AkbarTestsFlight {
	WebDriver driver;

	@BeforeTest
	public void startBrowser() {

		BrowserUsed browse = new BrowserUsed(driver);
		driver = browse.browserName("firefox");
	}

	@Test(dataProvider="getData")
	//@DataProvider (name="data")
	public void flights(String FromPlace,String ToPlace) {
		AkbarLoginPage login = PageFactory.initElements(driver, AkbarLoginPage.class);
	
		driver.get("https://www.akbartravels.com");
		
		login.login("devesh24deo@gmail.com", "Password1");
		AkbarFlightPage flights= PageFactory.initElements(driver,AkbarFlightPage.class);
		//flights.flightBooking("Dubai", "New York");
		System.out.println("FromPlace  "+FromPlace+"  ToPlace  "+ToPlace);
		flights.flightBooking(FromPlace, ToPlace);
		String actual = driver.findElement(By.xpath("//ul[@class='left']")).getText().trim();
		List<String> Actual = Arrays.asList(actual.split("\n"));
		Xls_Reader excelReader=new Xls_Reader("./TestData/TestData.xlsx");
		ArrayList<String> Expected=new ArrayList<String>();
		for(int i=1;i<=excelReader.getRowCount("Sheet1");i++) {
			String xl= String.valueOf(excelReader.getCellData1("Sheet1", 0, i));
		
		Expected.add(xl);
	}
		if(Expected.equals(Actual)) {
			System.out.println("Webpage returned correct");
			
		}
		else {
			System.out.println("incorrect results");
		}
		LinkedHashMap<String,String> dataFromFlight=flights.flightData();
		
		System.out.println("Departure details    " + dataFromFlight.get("departure"));
		System.out.println("Return details     " + dataFromFlight.get("Return"));
		System.out.println("Cost   " + dataFromFlight.get("amount"));
		
	}

	
	/*
	 * @DataProvider(name="data") public Iterator<Object> getData() { ReadExcel
	 * excelFile=new ReadExcel("./TestData/TestData.xlsx");
	 * //excelFile.excel("./TestData/TestData.xlsx"); int
	 * row_count=excelFile.getRowCount(); ArrayList<Object> data=new
	 * ArrayList<Object>(); for(int i=0;i<row_count;i++) {
	 * data.addAll(excelFile.getData(0, i, 0));
	 * 
	 * } return data.iterator();
	 * 
	 * }
	 */
	 	  
	 @DataProvider
	 public Iterator<Object []> getData() {
	  
	  
	  Xls_Reader excelReader=new Xls_Reader("./TestData/TestData.xlsx");
	  ArrayList<Object []> data=new ArrayList<Object []>();
	  for(int i=0;i<excelReader.getRowCount("Flight Details");i++) 
	  { 
		  Object Exldata []={(Object)excelReader.getCellData("Flight Details", 0, i)}; 
	  data.add(Exldata); 
	  }
	  System.out.println(data.toString());
	 

	return data.iterator();

	}

	@AfterTest
	public void close() {

		driver.quit();
	}
}
