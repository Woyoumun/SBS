package com.woyou.jersey.crud;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.woyou.jersey.crud.utils.Constants;

public class MongoEmployeeAccess {
	private static String dbName = "test";
	private static String collectionName = "employeeCollection";
	
	MongoClient mgClient = new MongoClient("localhost",27017);
	MongoDatabase database;
	
	public MongoEmployeeAccess() {
		database = mgClient.getDatabase(dbName);
	}
	
	public static Document mapping(Employee e, Object id) {
		Document doc = new Document(Constants.firstName,e.getFirstName())
				.append(Constants.lastName, e.getLastName())
				.append(Constants.middleInit, e.getMiddleInit())
				.append(Constants.active, e.getActive())
				.append(Constants.address1, e.getAddress1())
				.append(Constants.address2, e.getAddress2())
				.append(Constants.city, e.getCity())
				.append(Constants.state, e.getState())
				.append(Constants.zip, e.getZip())
				.append(Constants.dateHired, e.getDateHired())
				.append(Constants.position, e.getPostion())
				.append(Constants.phoneNumber, e.getPhoneNumber());
				
		if (id != null) 
			doc.append(Constants.id, id);
		return doc;
	}
	public static Employee getEmployeeFromDoc(Document doc) {
		Employee e = new Employee();
		e.setFirstName(doc.getString(Constants.firstName));
		e.setLastName(doc.getString(Constants.lastName));
		e.setMiddleInit(doc.getString(Constants.middleInit));
		e.setActive(doc.getBoolean(Constants.active));
		e.setAddress1(doc.getString(Constants.address1));
		e.setAddress2(doc.getString(Constants.address2));
		e.setCity(doc.getString(Constants.city));
		e.setState(doc.getString(Constants.state));
		e.setZip(doc.getString(Constants.zip));
		e.setPhoneNumber(doc.getString(Constants.phoneNumber));
		e.setDateHired(doc.getDate(Constants.dateHired));
//		System.out.println("position in doc " + doc.getString(Constants.position));
		e.setPosition(doc.getString(Constants.position));
//		System.out.println("position in e " + e.getPostion());
		e.setId(doc.get(Constants.id));
		return e;
	}
	
	
	public Object insert(Employee e) {
		Document doc = mapping(e, null);	
		MongoCollection<Document> coll = database.getCollection(collectionName);
		coll.insertOne(doc);
		e.setId(doc.get(Constants.id));
		return doc.get(Constants.id);
	}
	
	public Employee getOneEmployee(String firstName, String lastName) {
		MongoCollection<Document> coll = database.getCollection(collectionName);
		Bson filter = and(eq(Constants.firstName,firstName),eq(Constants.lastName,lastName));
		ArrayList<Document> inter = coll.find(filter)
				.into(new ArrayList<Document>());
		if (inter == null || inter.isEmpty())
			return null;
		Document doc = inter.get(0);
		return getEmployeeFromDoc(doc);
	}
	
	public Employee getEmployeeById(Object id) {
		Bson filter = eq(Constants.id,id);
		MongoCollection<Document> coll = database.getCollection(collectionName);
		Document doc = coll.find(filter).first();
		if (doc == null)
			return null;
		return getEmployeeFromDoc(doc);
	}
	
	public Employee updateOneEmployee( Employee e) {
		MongoCollection<Document> coll = database.getCollection(collectionName);
		Bson filter = eq(Constants.id,e.getId());
		Document doc = mapping(e, e.getId());
		
		return getEmployeeFromDoc(coll.findOneAndReplace(filter, doc));
	}
	
	public long delete(Employee e) {
		MongoCollection<Document> coll = database.getCollection(collectionName);
		Bson filter = Filters.eq(Constants.id,e.getId());
		DeleteResult result = coll.deleteOne(filter);
		return result.getDeletedCount();
	}
	
	public List<Employee> getEmployeesByLastName(String lastName) {
		MongoCollection<Document> coll = database.getCollection(collectionName);
		List<Document> dl = coll.find(eq(Constants.lastName,lastName)).into(new ArrayList<Document>());
		ArrayList<Employee> elist = new ArrayList<Employee>();
		for (Document d : dl) {
			elist.add(getEmployeeFromDoc(d));
		}
		return elist;
	}
	
	public ArrayList<Employee> getAllEmployees() {
		MongoCollection<Document> coll = database.getCollection(collectionName);
		List<Document> dl = coll.find().into(new ArrayList<Document>());
		ArrayList<Employee> elist = new ArrayList<Employee>();
		for (Document d : dl) {
			elist.add(getEmployeeFromDoc(d));
		}
		return elist;
	}
	public Employee findById(String id) {
		BasicDBObject query = new BasicDBObject("_id",new ObjectId(id));
		Document empDoc = database.getCollection(collectionName).find(query).first();
		if (empDoc == null)
			return null;
		return MongoEmployeeAccess.getEmployeeFromDoc(empDoc);
	}
	public void shutdown() {
		mgClient.close();
	}
}
