package com.hexaware.entity;

public class Policy
{
	 private int policyId;
	 private String policyName;
	 private double policyAmount;
	
	 //Default Constructor
	public Policy() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//Parameterized Constructor
	public Policy(int policyId, String policyName, double policyAmount) {
		super();
		this.policyId = policyId;
		this.policyName = policyName;
		this.policyAmount = policyAmount;
	}
	
	//Getter and Setter
	public int getPolicyId() {
		return policyId;
	}
	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}
	public String getPolicyName() {
		return policyName;
	}
	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}
	public double getPolicyAmount() {
		return policyAmount;
	}
	public void setPolicyAmount(double policyAmount) {
		this.policyAmount = policyAmount;
	}
	 
	 
}
