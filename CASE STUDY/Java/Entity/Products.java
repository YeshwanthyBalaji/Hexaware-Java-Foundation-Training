package com.hexaware.entity;

public class Products
{
	private int productId;
    private String name;
    private double price;
    private String description;
    private int stockQuantity;
    
    //Default Constructor
	public Products() {
		// TODO Auto-generated constructor stub
	}

	//Parameterized Constructor
	public Products(int productId, String name, double price, String description, int stockQuantity) {
		super();
		this.productId = productId;
		this.name = name;
		this.price = price;
		this.description = description;
		this.stockQuantity = stockQuantity;
	}

	//Getters and Setters
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
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

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
    
}
