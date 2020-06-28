package com.akbar.testCases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.akbar.pages.AkbarHotelPage;
import com.akbar.utility.Xls_Reader;
import com.browser.BrowserUsed;

public class AkbarTestHotel {

	WebDriver driver;

	@BeforeTest
	public void startBrowser() {

		BrowserUsed browse = new BrowserUsed(driver);
		driver = browse.browserName("firefox");
	}

	@Test(dataProvider = "getData")
	public void hotel(String url, String PLace, String FromDate, String ToDate) {
		driver.get(url);
		AkbarHotelPage hotels = PageFactory.initElements(driver, AkbarHotelPage.class);
		hotels.dat(FromDate, ToDate);
		hotels.hotelBooking(PLace);
		String actual = hotels.actualText();
		List<String> Actual = Arrays.asList(actual.split("\n"));
		hotels.setExcelData();
		Xls_Reader excelReader = new Xls_Reader("./TestData/TestHotelData.xlsx");
		ArrayList<String> Expected = new ArrayList<String>();
		for (int i = 2; i <= excelReader.getRowCount("ExpectedData"); i++) {
			String xl = String.valueOf(excelReader.getCellData1("ExpectedData", 0, i));
			Expected.add(xl);
		}

		Assert.assertEquals(Actual, Expected);
		if (Expected.equals(Actual)) {
			System.out.println("Webpage returned correct");

		} else {
			System.out.println("incorrect results");
		}
		System.out.println("“Total hotels listed:” " + hotels.numberOfHotel());
	}

	@DataProvider(name = "dataHotel")
	public Iterator<Object[]> getData() {

		Xls_Reader excelReader = new Xls_Reader("./TestData/TestHotelData.xlsx");
		ArrayList<Object[]> dataFlight = new ArrayList<Object[]>();

		for (int i = 2; i <= excelReader.getRowCount("Hotel Details"); i++) {
			String url = excelReader.getCellData1("Hotel Details", 0, i);

			String Place = excelReader.getCellData1("Hotel Details", 1, i);

			String FromDate = excelReader.getCellData1("Hotel Details", 2, i);

			String ToDate = excelReader.getCellData1("Hotel Details", 3, i);

			Object ob[] = { url, Place, FromDate, ToDate };
			dataFlight.add(ob);
		}
		return dataFlight.iterator();

	}

	@AfterTest
	public void close() {
		driver.quit();
	}

}
