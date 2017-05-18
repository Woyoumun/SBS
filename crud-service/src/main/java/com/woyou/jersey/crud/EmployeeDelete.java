package com.woyou.jersey.crud;

import java.nio.file.DirectoryStream.Filter;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bson.types.ObjectId;

@Path("/employeedelete/{id}")
public class EmployeeDelete {
	String _id;
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteEmployee(@PathParam("id") String id) {
		MongoEmployeeAccess access = new MongoEmployeeAccess();
		
		Employee e = access.findById(id);
		access.delete(e);
		return "Deleted for " + id.toString();
	}

}
