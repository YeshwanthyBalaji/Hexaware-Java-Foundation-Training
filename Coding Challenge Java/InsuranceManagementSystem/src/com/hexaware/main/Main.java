package com.hexaware.main;

import java.util.List;
import java.util.Scanner;

import com.hexaware.dao.InsuranceServiceImpl;
import com.hexaware.entity.Policy;
import com.hexaware.exception.PolicyNotFoundException;

public class Main {

	 private static final Scanner sc = new Scanner(System.in);
	 private static final InsuranceServiceImpl policyService = new InsuranceServiceImpl();
	 public static void main(String[] args) {
		// TODO Auto-generated method stub

		 int choice;

	        while (true) {
	            System.out.println("Insurance Management System");
	            System.out.println("1. Create Policy");
	            System.out.println("2. View Policy");
	            System.out.println("3. View All Policies");
	            System.out.println("4. Update Policy");
	            System.out.println("5. Delete Policy");
	            System.out.println("6. Exit");
	            System.out.print("Enter your choice: ");
	            choice = sc.nextInt();
	            sc.nextLine(); //
	        
	        switch (choice) {
            case 1:
                createPolicy();
                break;
            case 2:
                viewPolicy();
                break;
            case 3:
                viewAllPolicies();
                break;
            case 4:
                updatePolicy();
                break;
            case 5:
                deletePolicy();
                break;
            case 6:
                System.out.println("Exiting the system...");
                return;
            default:
                System.out.println("Invalid choice! Please try again.");
	        }
        }
	 }
	        
	        private static void createPolicy() {
	            System.out.print("Enter Policy Name: ");
	            String policyName = sc.nextLine();
	            System.out.print("Enter Policy Amount: ");
	            double policyAmount = sc.nextDouble();

	            Policy policy = new Policy(0, policyName, policyAmount);
	            boolean result = policyService.createPolicy(policy);
	            if (result) {
	                System.out.println("Policy created successfully!");
	            } else {
	                System.out.println("Failed to create policy.");
	            }
	        }
	        private static void viewPolicy() {
	            System.out.print("Enter Policy ID: ");
	            int policyId = sc.nextInt();

	            try {
	                Policy policy = policyService.getPolicy(policyId);
	                System.out.println(policy);
	                
	            } catch (PolicyNotFoundException e) {
	                System.out.println(e.getMessage());
	            }
	        }
	        
	        private static void viewAllPolicies() {
	            List<Policy> policies = policyService.getAllPolicies();
	            if (policies.isEmpty()) {
	                System.out.println("No policies found.");
	            } else {
	                policies.forEach(System.out::println);
	            }
	        }
	        
	        private static void updatePolicy() {
	        System.out.print("Enter Policy ID to update: ");
	        int policyId = sc.nextInt();
	        sc.nextLine(); // Consume newline character
	        System.out.print("Enter new Policy Name: ");
	        String policyName = sc.nextLine();
	        System.out.print("Enter new Policy Amount: ");
	        double policyAmount = sc.nextDouble();

	        Policy policy = new Policy(policyId, policyName, policyAmount);
	        boolean result = policyService.updatePolicy(policy);
	        if (result) {
	            System.out.println("Policy updated successfully!");
	        } else {
	            System.out.println("Failed to update policy.");
	        }
	    }
	        
	        private static void deletePolicy() {
	        System.out.print("Enter Policy ID to delete: ");
	        int policyId = sc.nextInt();

	        boolean result = policyService.deletePolicy(policyId);
	        if (result) {
	            System.out.println("Policy deleted successfully!");
	        } else {
	            System.out.println("Failed to delete policy.");
	        }
	    }
}

