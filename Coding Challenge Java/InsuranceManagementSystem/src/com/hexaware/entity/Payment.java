package com.hexaware.entity;

import java.util.Date;

public class Payment
{
	private int paymentId;
    private Date paymentDate;
    private double paymentAmount;
    private Client client;
 
    //Default Constructor
	public Payment() {
		// TODO Auto-generated constructor stub
	}
	
	//Parameterized Constructor
	public Payment(int paymentId, Date paymentDate, double paymentAmount, Client client) {
		super();
		this.paymentId = paymentId;
		this.paymentDate = paymentDate;
		this.paymentAmount = paymentAmount;
		this.client = client;
	}
	
	//Getter and Setter
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	public double getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
    
    
}
