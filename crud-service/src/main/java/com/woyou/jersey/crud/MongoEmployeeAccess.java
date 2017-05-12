package com.woyou.jersey.crud;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.After;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;

public class MongoEmployeeAccess {
	private static String dbName = "test";
	private static String collectionName = "employeeCollection";
	
	MongoClient mgClient = new MongoClient("localhost",27017);
	MongoDatabase database;
	
	public MongoEmployeeAccess() {
		database = mgClient.getDatabase(dbName);
	}
	
	public static Document mapping(Employee e, Object id) {
		Document doc = new Document("firstName",e.getFirstName())
				.append("lastName", e.getLastName())
				.append("middleInit", e.getMiddleInit())
				.append("active", e.getActive())
				.append("address1", e.getAddress1())
				.append("address2", e.getAddress2())
				.append("city", e.getCity())
				.append("state", e.getState())
				.append("zip", e.getZip())
				.append("phoneNumber", e.getPhoneNumber());
		if (id != null) 
			doc.append("_id", id);
		return doc;
	}
	public static Employee getEmployeeFromDoc(Document doc) {
		Employee e = new Employee();
		e.setFirstName(doc.getString("firstName"));
		e.setLastName(doc.getString("lastName"));
		e.setActive(doc.getBoolean("active"));
		e.setAddress1(doc.getString("address1"));
		e.setAddress2(doc.getString("address2"));
		e.setCity(doc.getString("city"));
		e.setState(doc.getString("state"));
		e.setPhoneNumber(doc.getString("phoneNumber"));
		e.setId(doc.get("_id"));
		return e;
	}
	
	
	public Object insert(Employee e) {
		Document doc = mapping(e, null);	
		MongoCollection<Document> coll = database.getCollection(collectionName);
		coll.insertOne(doc);
		e.setId(doc.get("_id"));
		return doc.get("_id");
	}
	
	public Employee getOneEmployee(String firstName, String lastName) {
		MongoCollection<Document> coll = database.getCollection(collectionName);
		Bson filter = and(eq("firstName",firstName),eq("lastName",lastName));
		ArrayList<Document> inter = coll.find(filter)
				.into(new ArrayList<Document>());
		if (inter == null || inter.isEmpty())
			return null;
		Document doc = inter.get(0);
		return getEmployeeFromDoc(doc);
	}
	
	public Employee getEmployeeById(Object id) {
		Bson filter = eq("_id",id);
		MongoCollection<Document> coll = database.getCollection(collectionName);
		Document doc = coll.find(filter).first();
		if (doc == null)
			return null;
		return getEmployeeFromDoc(doc);
	}
	
	public Employee updateOneEmployee( Employee e) {
		MongoCollection<Document> coll = database.getCollection(collectionName);
		Bson filter = eq("_id",e.getId());
		Document doc = mapping(e, e.getId());
		
		return getEmployeeFromDoc(coll.findOneAndReplace(filter, doc));
	}
	
	public long delete(Employee e) {
		MongoCollection<Document> coll = database.getCollection(collectionName);
		Bson filter = Filters.eq("_id",e.getId());
		DeleteResult result = coll.deleteOne(filter);
		return result.getDeletedCount();
	}
	
	public List<Employee> getEmployeesByLastName(String lastName) {
		MongoCollection<Document> coll = database.getCollection(collectionName);
		List<Document> dl = coll.find(eq("lastName",lastName)).into(new ArrayList<Document>());
		ArrayList<Employee> elist = new ArrayList<Employee>();
		for (Document d : dl) {
			elist.add(getEmployeeFromDoc(d));
		}
		return elist;
	}
	public void shutdown() {
		mgClient.close();
	}
}
