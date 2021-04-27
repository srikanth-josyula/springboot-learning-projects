package com.sample.POJOs;

import java.util.Calendar;

public class CustomDate {

	int date;
	String month;
	int year;
	String day;
	
	String[] weekDays = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
	String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

	public CustomDate(Calendar c) {
		this.date = c.get(Calendar.DATE);
		this.month = monthNames[c.get(Calendar.MONTH)];
		this.year = c.get(Calendar.YEAR);
		this.day = weekDays[c.get(Calendar.DAY_OF_WEEK)-1];
		
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}
	
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}


}
