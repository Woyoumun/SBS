package com.woyou.jersey.crud;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/employeeresource/{lastname}")
public class EmployeeResource {
	
	@GET
	@Produces("application/json")
	public List<Employee>  getEmployee(@PathParam("lastname") String lastName) {
		MongoEmployeeAccess access = new MongoEmployeeAccess();
		List<Employee> elist = access.getEmployeesByLastName(lastName);
		for (Employee e : elist) {
			System.out.println(" the position is " + e.getPostion());
		}
		return elist;
	}
}
