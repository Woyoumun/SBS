package com.woyou.jersey.crud;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import com.woyou.jersey.crud.utils.Constants;
import com.woyou.jersey.crud.utils.DateTimeConversion;



@Path("/employeeupdate/{id}")

public class EmpUpdate {
	@Context
	protected UriInfo info;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String update(@PathParam("id") String id) {
		MongoEmployeeAccess access = new MongoEmployeeAccess();
		Employee org = null;
		ArrayList<Employee> all = access.getAllEmployees();
		if (all == null) {
			return "all is null";
		}
		for (Employee e : all) {
			if (id.equals(e.getId().toString())) {
				org = e;
				break;
			}
		}
		if (org == null) {
			return "no op\n";
		}
		if (info == null) {
			return "info is null\n";
		}
		if (info.getQueryParameters() == null && info.getQueryParameters().size() == 0) {
			return "no parameters\n";
		}
		String firstName = info.getQueryParameters().getFirst(Constants.firstName);
		String lastName = info.getQueryParameters().getFirst(Constants.lastName);
		String middleInit =  info.getQueryParameters().getFirst(Constants.middleInit);
		String active = info.getQueryParameters().getFirst(Constants.active);
		String dateHired = info.getQueryParameters().getFirst(Constants.dateHired);
		String addr1 = info.getQueryParameters().getFirst("addr1");
		String addr2 = info.getQueryParameters().getFirst("addr2");
		String city = info.getQueryParameters().getFirst(Constants.city);
		String state = info.getQueryParameters().getFirst(Constants.state);
		String zip = info.getQueryParameters().getFirst(Constants.zip);
		String phone = info.getQueryParameters().getFirst("phone");
		String position = info.getQueryParameters().getFirst(Constants.position);
		if (firstName != null)
			org.setFirstName(firstName);
		if (lastName != null) 
			org.setLastName(lastName);
		if (middleInit != null)
			org.setMiddleInit(middleInit);
		org.setActive(active);
		if (addr1 != null)	
			org.setAddress1(addr1);
		if (addr2 != null)
			org.setAddress2(addr2);
		if (city != null)
			org.setCity(city);
		if (state != null)
			org.setState(state);
		if (zip != null)
		    org.setZip(zip);
		if (phone != null)
			org.setPhoneNumber(phone);
		org.setDateHired(dateHired);
		org.setPosition(position);
		access.updateOneEmployee(org);
		return "done + \n";
	}

}
