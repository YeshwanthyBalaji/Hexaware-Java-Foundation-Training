package com.hexaware.entity;

public class Customers
{
	//Defining table names
	private int customerId;
    private String name;
    private String email;
    private String password;
    
    //Default Constructor
	public Customers() {
		// TODO Auto-generated constructor stub
	}

	//Parameterized Constructor
	public Customers(int customerId, String name, String email, String password) {
		super();
		this.customerId = customerId;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	//Getters and Setters
	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
}
