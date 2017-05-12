package com.woyou.jersey.crud;
import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;
import java.util.List;

public class TestEmployee {
	
	Employee testTarget = new Employee();
	Employee testTarget2 = new Employee();
	Employee testTarget3 = new Employee();
	MongoEmployeeAccess access = new MongoEmployeeAccess();
	 @Before
	public void setTestFixture() {
		testTarget = new Employee();
		testTarget.setFirstName("Jane");
		testTarget.setLastName("phili");
		testTarget.setAddress1("1234 Sunny Valley Dr.");
		testTarget.setCity("Fairfax");
		testTarget.setState("VA");
		testTarget.setZip("12345");
		testTarget.setPhoneNumber("702-123-4567");
		testTarget.setDateHired(new Date());
		
		testTarget2 = new Employee();
		testTarget2.setFirstName("Jane");
		testTarget2.setLastName("Jackson");
		testTarget2.setAddress1("3487 Glen Valley Dr.");
		testTarget2.setCity("Fairfax");
		testTarget2.setState("VA");
		testTarget2.setZip("22022");
		testTarget2.setPhoneNumber("702-123-3456");
		testTarget2.setDateHired(new Date());
		
		testTarget3 = new Employee();
		testTarget3.setFirstName("Michael");
		testTarget3.setLastName("Jackson");
		testTarget3.setAddress1("1111 Freedom Dr.");
		testTarget3.setCity("Reston");
		testTarget3.setState("VA");
		testTarget3.setZip("23498");
		testTarget3.setPhoneNumber("702-349-4567");
		testTarget3.setDateHired(new Date());
	}
	
	public void testResult(Employee e, Employee target) {
		assertEquals(e.getFirstName(),target.getFirstName());
		assertEquals(e.getLastName(),target.getLastName());
		assertEquals(e.getAddress1(),target.getAddress1());
		assertEquals(e.getAddress2(),target.getAddress2());
		assertEquals(e.getCity(),target.getCity());
		assertEquals(e.getState(),target.getState());
		assertEquals(e.getPhoneNumber(),target.getPhoneNumber());
	}
	
	
	
	@Test
	public void testInsertEmployee() {
		setTestFixture();
		access.insert(testTarget);
		List<Employee> list = access.getEmployeesByLastName("Jackson");
		if (list.size() < 2) {
			access.insert(testTarget2);
			access.insert(testTarget3);
		}
		Employee e = access.getOneEmployee( testTarget.getFirstName(), testTarget.getLastName());
		assertNotNull(e);
		testResult(testTarget,e);
	}
	
	@Test
	public void testDeleteEmployee() {
		setTestFixture();
		Employee e = access.getOneEmployee(testTarget.getFirstName(), testTarget.getLastName());
		Object id;
		if (e == null) {
			access.insert(testTarget);
			id = testTarget.getId();
		} else {
			id = e.getId();
			testTarget = e;
		}
		
		long deleteCount = access.delete(testTarget);
		assertEquals(1,deleteCount);
		e = access.getEmployeeById(id);
		assertNull("id=" + id,e);
	}
	@Test
	public void testUpdateEmployee() {
		Employee e = access.getOneEmployee(testTarget.getFirstName(), testTarget.getLastName());
		if (e == null) {
			access.insert(testTarget);
		} else {
			testTarget = e;
		}
		e = access.getEmployeeById( testTarget.getId());
		assertNotNull(e);
		e.setAddress2("#123456");
		access.updateOneEmployee( e);
		e = access.getEmployeeById(testTarget.getId());
		assertNotNull(e);
		testTarget.setAddress2("#123456");
		testResult(testTarget,e);
	}
	
	@Test
	public void testRetrieveEmployeeByLastName() {
		List<Employee> list = access.getEmployeesByLastName("Jackson");
		assertEquals(list.size(),2);
	}
	 @After
	 public void tearDown() {
		 
	 }
}
