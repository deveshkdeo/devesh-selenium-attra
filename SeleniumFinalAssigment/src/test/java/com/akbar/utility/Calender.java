package com.akbar.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Calender {
	WebDriver driver;
	
	public Calender(WebDriver driver) {
		this.driver=driver;
	}
	
	
	public void setDate(String dateVal,WebElement element) {
		JavascriptExecutor js=((JavascriptExecutor) driver);
		System.out.println("inside setdate");
		String dateval="29 Jun’20";
		/*
		 * System.out.println("inner"
		 * +driver.findElement(By.xpath("//div[2]/ul/li[3]/h6")).getText());
		 * System.out.println("text"
		 * +driver.findElement(By.xpath("//div[2]/ul/li[3]/h6")).getAttribute("text"));
		 * System.out.println("value"
		 * +driver.findElement(By.xpath("//div[2]/ul/li[3]/h6")).getAttribute("value"))
		 */;
		//js.executeScript("arguments[0].setAttribute('innerHTML','"+dateVal+"');", element);
		js.executeScript("arguments[0].value='29 Jun’20';", driver.findElement(By.xpath("//div[2]/ul/li[3]/h6")));
		System.out.println("inside setd,,l,ate");

	}

}
