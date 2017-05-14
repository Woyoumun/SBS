package com.woyou.jersey.crud;

import java.util.Date;

import com.woyou.jersey.crud.utils.Constants;
import com.woyou.jersey.crud.utils.DateTimeConversion;

public class Employee { 
	private Object id;
	private String firstName = "";
	private String lastName = "";
	private String middleInit = "";
	private String phoneNumber = "";
	private Date dateHired = new Date();
	private String address1="";
	private String address2 = "";
	private String city= "";
	private String state = "";
	private String zip = "";
	private Boolean active = true;
	private String position = Constants.Direct;
	public Employee() {
		
	}
	public Employee(Employee e) {
		this.firstName = e.firstName;
		this.lastName = e.lastName;
		this.middleInit = e.middleInit;
		this.phoneNumber = e.phoneNumber;
		this.dateHired = e.dateHired;
		this.address1 = e.address1;
		this.address2 = e.address2;
		this.city = e.city;
		this.state = e.state;
		this.zip = e.zip;
		this.active = e.active;
		this.position = e.position;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMiddleInit() {
		return middleInit;
	}
	public void setMiddleInit(String middleInit) {
		this.middleInit = middleInit;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Date getDateHired() {
		return dateHired;
	}
	public void setDateHired(Date dateHired) {
		this.dateHired = dateHired;
	}
	public void setDateHired(String dateHired) {
		if (DateTimeConversion.isValidLocalDate(dateHired))
			this.dateHired = DateTimeConversion.toDate(dateHired);
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public void setActive(String active) {
		this.active = active != null && "true".equals(active.toLowerCase()) ? true : false ;
	}
	public Object getId() {
		return id;
	}
	public void setId(Object id) {
		this.id = id;
	}
	public String getPostion() {
		return position;
	}
	public void setPosition(String postion) {
		this.position = postion;
	}
}
