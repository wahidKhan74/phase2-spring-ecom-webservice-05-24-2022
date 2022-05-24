package com.simplilearn.webservice.model;

import java.util.Date;

public class Product {

	// properties
	private int id;
	private String name;
	private double price;
	private String description;
	private boolean available;
	private Date createdAt;
	
	public Product() {}
	public Product(int id, String name, double price, String description, boolean available) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.available = available;
		this.createdAt = new Date();
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}
