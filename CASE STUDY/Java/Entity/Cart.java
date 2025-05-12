package com.hexaware.entity;

public class Cart
{
	private int cartId;
    private int customerId;
    private int productId;
    private int quantity;
    
    //Default Constructor
	public Cart() {
		// TODO Auto-generated constructor stub
	}

	//Parameterized Constructor
	public Cart(int cartId, int customerId, int productId, int quantity) {
		super();
		this.cartId = cartId;
		this.customerId = customerId;
		this.productId = productId;
		this.quantity = quantity;
	}

	//Getters and Setters
	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
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
