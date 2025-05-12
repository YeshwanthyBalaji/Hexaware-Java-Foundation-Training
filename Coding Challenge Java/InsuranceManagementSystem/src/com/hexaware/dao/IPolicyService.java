package com.hexaware.dao;

import java.util.List;

import com.hexaware.entity.Policy;
import com.hexaware.exception.PolicyNotFoundException;

public interface IPolicyService
{
	// createPolicy() 
	 boolean createPolicy(Policy policy);
	 
	 // getPolicy()
	 Policy getPolicy(int policyId) throws PolicyNotFoundException;
	 
	 //getAllPolicies() 
	 List<Policy> getAllPolicies();
	 
	 //updatePolicy() 
	 boolean updatePolicy(Policy policy);
	 
	 //deletePolicy() 
	 boolean deletePolicy(int policyId);
}
