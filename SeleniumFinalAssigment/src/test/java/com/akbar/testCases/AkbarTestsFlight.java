package com.akbar.testCases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.akbar.pages.AkbarFlightPage;
import com.akbar.pages.AkbarLoginPage;
import com.akbar.utility.Xls_Reader;
import com.browser.BrowserUsed;

public class AkbarTestsFlight {
	WebDriver driver;

	@BeforeTest
	public void startBrowser() {

		BrowserUsed browse = new BrowserUsed(driver);
		driver = browse.browserName("firefox");
	}

	@Test(dataProvider = "getData")
	public void flights(String url, String userName, String password, String FromPlace, String ToPlace, String FromDate,
			String ToDate) {

		AkbarLoginPage login = PageFactory.initElements(driver, AkbarLoginPage.class);
		driver.get(url);
		login.login(userName, password);
		AkbarFlightPage flights = PageFactory.initElements(driver, AkbarFlightPage.class);
		flights.dat(FromDate, ToDate);
		Xls_Reader excelReader = new Xls_Reader("./TestData/TestData.xlsx");
		flights.flightBooking(FromPlace, ToPlace);

		String actual = flights.actualText();
		List<String> Actual = Arrays.asList(actual.split("\n"));
		flights.setExcelData();
		ArrayList<String> Expected = new ArrayList<String>();
		for (int i = 2; i <= excelReader.getRowCount("FlightDate"); i++) {
			String xl = String.valueOf(excelReader.getCellData1("FlightDate", 0, i));
			Expected.add(xl);
		}
		if (Expected.equals(Actual)) {
			System.out.println("Webpage returned correct");

		} else {
			System.out.println("incorrect results");
		}

		LinkedHashMap<String, String> dataFromFlight = flights.flightData();
		System.out.println("Departure details    " + dataFromFlight.get("departure"));
		System.out.println("Return details     " + dataFromFlight.get("Return"));
		System.out.println("Cost   " + dataFromFlight.get("amount"));

		Assert.assertEquals(Actual, Expected);
	}

	@DataProvider(name = "dataFlight")
	public Iterator<Object[]> getData() {

		Xls_Reader excelReader = new Xls_Reader("./TestData/TestData.xlsx");
		ArrayList<Object[]> dataFlight = new ArrayList<Object[]>();

		for (int i = 2; i <= excelReader.getRowCount("Flight Details"); i++) {
			String url = excelReader.getCellData1("Flight Details", 0, i);
			String userName = excelReader.getCellData1("Flight Details", 1, i);
			String password = excelReader.getCellData1("Flight Details", 2, i);
			String FromPlace = excelReader.getCellData1("Flight Details", 3, i);
			String ToPLace = excelReader.getCellData1("Flight Details", 4, i);
			String FromDate = excelReader.getCellData1("Flight Details", 5, i);
			String ToDate = excelReader.getCellData1("Flight Details", 6, i);
			Object ob[] = { url, userName, password, FromPlace, ToPLace, FromDate, ToDate };
			dataFlight.add(ob);
		}

		return dataFlight.iterator();

	}

	@AfterTest
	public void close() {
		driver.quit();
	}
}
