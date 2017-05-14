package com.woyou.jersey.crud;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/employeedelete/{id}")
public class EmployeeDelete {
	String _id;
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteEmployee(@PathParam("id") String id) {
		MongoEmployeeAccess access = new MongoEmployeeAccess();
		ArrayList<Employee> all = access.getAllEmployees();
		Employee found = null;
		for (Employee e : all) {
			if (e.getId().toString().equals(id)) {
				found = e;
				break;
			}
		}
		if (found != null) {
			access.delete(found);
			return "DONE";
		}
		return "NO OP for " + id.toString();
	}

}
