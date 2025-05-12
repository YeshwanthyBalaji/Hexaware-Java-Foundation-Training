package com.hexaware.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hexaware.entity.Policy;
import com.hexaware.exception.PolicyNotFoundException;
import com.hexaware.util.DBConnUtil;

public class InsuranceServiceImpl implements IPolicyService {

	//createPolicy()
	@Override
	public boolean createPolicy(Policy policy) {
		String query = "INSERT INTO Policy (policyName, policyAmount) VALUES (?, ?)";
        try (Connection conn = DBConnUtil.getConnection();
        		PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, policy.getPolicyName());
            pstmt.setDouble(2, policy.getPolicyAmount());
            int rows = pstmt.executeUpdate();
            return rows > 0;
            
        } catch (SQLException e) {
        	e.printStackTrace();
        	}
		return false;
	}

	//getPolicy()
	@Override
	public Policy getPolicy(int policyId) throws PolicyNotFoundException {
		String query = "SELECT * FROM Policy WHERE policyId = ?";
        try (Connection conn = DBConnUtil.getConnection();
        		PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, policyId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Policy(
                    rs.getInt("policyId"),
                    rs.getString("policyName"),
                    rs.getDouble("policyAmount")
                );
            } else {
                throw new PolicyNotFoundException("Policy with ID " + policyId + " not found.");
            }
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        return null;
	}

	//getAllPolicies()
	@Override
	public List<Policy> getAllPolicies() {
		List<Policy> policies = new ArrayList<>();
        String query = "SELECT * FROM Policy";
        try (Connection conn = DBConnUtil.getConnection();
        		Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                policies.add(new Policy(
                    rs.getInt("policyId"),
                    rs.getString("policyName"),
                    rs.getDouble("policyAmount")
                ));
            }
        } catch (SQLException e) {
        	e.printStackTrace();
        	}
		return null;
	}

	//updatePolicy()
	@Override
	public boolean updatePolicy(Policy policy) {
		String query = "UPDATE Policy SET policyName = ?, policyAmount = ? WHERE policyId = ?";
        try (Connection conn = DBConnUtil.getConnection();
        		PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, policy.getPolicyName());
            pstmt.setDouble(2, policy.getPolicyAmount());
            pstmt.setInt(3, policy.getPolicyId());
            int rows = pstmt.executeUpdate();
            return rows > 0;
            
        } catch (SQLException e) {
        	e.printStackTrace();
        }
            return false;
        }

	//deletePolicy()
	@Override
	public boolean deletePolicy(int policyId) {
		 String query = "DELETE FROM Policy WHERE policyId = ?";
	        try (Connection conn = DBConnUtil.getConnection();
	        		PreparedStatement pstmt = conn.prepareStatement(query)) {
	            pstmt.setInt(1, policyId);
	            int rows = pstmt.executeUpdate();
	            return rows > 0;
	        } catch (SQLException e) {
	        	e.printStackTrace();
	        }
	        return false;
	}
}
