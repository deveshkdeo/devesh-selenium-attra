package com.akbar.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class CalenderDate {

	String dateVal;

	public HashMap<String, String> setDate(String dateVal) {
		this.dateVal = dateVal;
		String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December" };

		String[] dd = dateVal.split("/");
		int year = Integer.parseInt(dd[2]);
		int date1 = Integer.parseInt(dd[1]);
		int month = Integer.parseInt(dd[0]);
		dd[0] = months[month - 1];
		int week = 0;
		String WeekOfMonth;

		Date date;
		try {
			date = new SimpleDateFormat("MMM").parse(dd[0]);

			Calendar cal = Calendar.getInstance();
			cal.setTime(date);

			int monthNumber = cal.get(Calendar.MONTH);
			cal.set(year, monthNumber, date1);
			week = cal.get(Calendar.WEEK_OF_MONTH);

		} catch (ParseException e) {

			e.printStackTrace();
		}

		WeekOfMonth = String.valueOf(week);
		HashMap<String, String> calendar = new HashMap<String, String>();
		calendar.put("MonthName", dd[0]);
		calendar.put("WeekOfMonth", WeekOfMonth);
		calendar.put("Date", dd[1]);
		calendar.put("Year", dd[2]);

		return calendar;

	}

}
