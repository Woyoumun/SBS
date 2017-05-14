package com.woyou.jersey.crud.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public  class DateTimeConversion {
	
	public static String toDateText(Date d) {
		SimpleDateFormat fmt = new SimpleDateFormat("MM-dd-yyyy");
		return fmt.format(d);
	}
	
	public static Date toDate(String dateString) {
		try {
			SimpleDateFormat fmt = new SimpleDateFormat("MM-dd-yyyy");
			return fmt.parse(dateString);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static boolean isValidLocalDate(String dateStr) {
		try {
			Date d = DateTimeConversion.toDate(dateStr);
			String fdate = DateTimeConversion.toDateText(d);
			return fdate.equals(dateStr);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
}
