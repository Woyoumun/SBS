package com.woyou.jersey.crud;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import com.woyou.jersey.crud.utils.Constants;
import com.woyou.jersey.crud.utils.DateTimeConversion;

@Path("/employeecreate")
public class EmployeeCreate {
	@Context
	protected UriInfo info;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String create() {
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
		Employee e = new Employee();
		e.setFirstName(firstName);
		e.setLastName(lastName);
		e.setMiddleInit(middleInit);
		e.setActive(active);
		e.setDateHired(dateHired);
		e.setAddress1(addr1);
		e.setAddress2(addr2);
		e.setCity(city);
		e.setState(state);
		e.setZip(zip);
		e.setPhoneNumber(phone);
		System.out.println(position);
		e.setPosition(position);
		System.out.println(e.getPostion());
		MongoEmployeeAccess access = new MongoEmployeeAccess();
		access.insert(e);
		return "Done Creation position\n";
	}
}
