package com.woyou.jersey.crud;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Date;

import org.junit.Test;

import com.woyou.jersey.crud.utils.DateTimeConversion;

public class TestLocalDate {
	
	@Test
	public void testDateZero() {
		Date d = DateTimeConversion.toDate("06-23-1987");
		String fdate = DateTimeConversion.toDateText(d);
		System.out.println(fdate);
		assertEquals(fdate,"06-23-1987");
	}
	@Test
	public void testLocalDateNoZero() {
		Date d = DateTimeConversion.toDate("12-23-1987");
		String fdate = DateTimeConversion.toDateText(d);
		System.out.println(fdate);
		assertEquals(fdate,"12-23-1987");
	}
	
	@Test
	public void testLocalDateInValid() {
		assertFalse( DateTimeConversion.isValidLocalDate("02-31-1987"));
		assertFalse( DateTimeConversion.isValidLocalDate("04-31-1987"));
		assertFalse( DateTimeConversion.isValidLocalDate("06-31-1987"));
		assertFalse( DateTimeConversion.isValidLocalDate("09-31-1987"));
		assertFalse( DateTimeConversion.isValidLocalDate("11-31-1987"));
		assertFalse( DateTimeConversion.isValidLocalDate("00-31-1987"));
		assertFalse( DateTimeConversion.isValidLocalDate("01-00-1987"));
		
	}
	@Test
	public void testLocalDateTrue() {
		assertTrue( DateTimeConversion.isValidLocalDate("02-27-1987"));
		assertTrue( DateTimeConversion.isValidLocalDate("01-31-1987"));
		assertTrue( DateTimeConversion.isValidLocalDate("07-31-1987"));
		assertTrue( DateTimeConversion.isValidLocalDate("08-31-1987"));
		assertTrue( DateTimeConversion.isValidLocalDate("12-31-1987"));
		
	}
	

}
