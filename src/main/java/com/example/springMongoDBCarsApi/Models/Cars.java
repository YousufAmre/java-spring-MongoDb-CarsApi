package com.example.springMongoDBCarsApi.Models;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Cars")
public class Cars {

	private ObjectId id;

	private String Make;
	
	private String Model;
	
	private int TopSpeed;
	
	public Cars() {
		
	}
	public Cars(String make, String model, int topSpeed) {
		this.Make = make;
		this.Model = model;
		this.TopSpeed = topSpeed;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getMake() {
		return Make;
	}

	public void setMake(String make) {
		this.Make = make;
	}

	public String getModel() {
		return Model;
	}

	public void setModel(String model) {
		this.Model = model;
	}

	public int getTopSpeed() {
		return TopSpeed;
	}

	public void setTopSpeed(int topSpeed) {
		this.TopSpeed = topSpeed;
	}
	
}
