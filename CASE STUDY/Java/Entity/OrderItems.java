package com.hexaware.entity;

public class OrderItems
{
	private int orderItemId;
	private int orderId;
	private int productId;
	private int quantity;
	
	//Default Constructor
	public OrderItems() {
		// TODO Auto-generated constructor stub
	}

	//Parameterized Constructor
	public OrderItems(int orderItemId, int orderId, int productId, int quantity) {
		super();
		this.orderItemId = orderItemId;
		this.orderId = orderId;
		this.productId = productId;
		this.quantity = quantity;
	}


	//Getters and Setters
	public int getOrderItemId() {
		return orderItemId;
	}


	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}


	public int getOrderId() {
		return orderId;
	}


	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}


	public int getProductId() {
		return productId;
	}


	public void setProductId(int productId) {
		this.productId = productId;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
}
