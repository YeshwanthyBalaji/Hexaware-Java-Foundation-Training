package com.hexaware.dao;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hexaware.entity.Customers;
import com.hexaware.entity.Products;
import com.hexaware.exception.CustomerNotFoundException;
import com.hexaware.exception.OrderNotFoundException;
import com.hexaware.exception.ProductNotFoundException;
import com.hexaware.util.DBConnUtil;

public class OrderProcessorRepositoryImpl implements OrderProcessorRepository {

	@Override
	public boolean createProduct(Products product) {
		String query = "INSERT INTO Products (name_id, price, descr, stock) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
             
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setString(3, product.getDescription());
            ps.setInt(4, product.getStockQuantity());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
		return false;
        }
	}

	@Override
	public boolean createCustomer(Customers customer) {
		 String query = "INSERT INTO Customers (name_id, email_id, password_id) VALUES (?, ?, ?)";
	        try (Connection conn = DBConnUtil.getConnection();
	             PreparedStatement ps = conn.prepareStatement(query)) {
	             
	            ps.setString(1, customer.getName());
	            ps.setString(2, customer.getEmail());
	            ps.setString(3, customer.getPassword());

	            int rowsAffected = ps.executeUpdate();
	            return rowsAffected > 0;

	        } catch (SQLException e) {
	            e.printStackTrace();
		return false;
	        }
	}

	@Override
	public boolean deleteProduct(int productId) throws ProductNotFoundException {
		String query = "DELETE FROM Products WHERE product_id = ?";
	    try (Connection conn = DBConnUtil.getConnection();
	         PreparedStatement ps = conn.prepareStatement(query)) {

	        ps.setInt(1, productId);

	        int rowsAffected = ps.executeUpdate();
	        
	        if (rowsAffected == 0) {
	            throw new ProductNotFoundException("Product with ID " + productId + " not found.");
	        }
	        return true;

	    } catch (SQLException e) {
	        e.printStackTrace();
		return false;
	    }
	}

	@Override
	public boolean deleteCustomer(int customerId) throws CustomerNotFoundException {
		String query = "DELETE FROM Customers WHERE customer_id = ?";
	    try (Connection conn = DBConnUtil.getConnection();
	         PreparedStatement ps = conn.prepareStatement(query)) {

	        ps.setInt(1, customerId);

	        int rowsAffected = ps.executeUpdate();
	        
	        if (rowsAffected == 0) {
	        throw new CustomerNotFoundException("Customer with ID " + customerId + " not found.");
        }
        return true;

	    } catch (SQLException e) {
	        e.printStackTrace();
		return false;
	    }
	}

	@Override
	public boolean addToCart(Customers customer, Products product, int quantity) {
		String query = "INSERT INTO Cart (customer_id, product_id, quantity) VALUES (?, ?, ?)";
	    try (Connection conn = DBConnUtil.getConnection();
	         PreparedStatement ps = conn.prepareStatement(query)) {

	        ps.setInt(1, customer.getCustomerId());
	        ps.setInt(2, product.getProductId());
	        ps.setInt(3, quantity);

	        int rowsAffected = ps.executeUpdate();
	        return rowsAffected > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
		return false;
	    }
	}

	@Override
	public boolean removeFromCart(Customers customer, Products product) {
		String query = "DELETE FROM Cart WHERE customer_id = ? AND product_id = ?";
	    try (Connection conn = DBConnUtil.getConnection();
	         PreparedStatement ps = conn.prepareStatement(query)) {

	        ps.setInt(1, customer.getCustomerId());
	        ps.setInt(2, product.getProductId());

	        int rowsAffected = ps.executeUpdate();
	        return rowsAffected > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
		return false;
	    }
	}

	@Override
	public List<Products> getAllFromCart(Customers customer) {
		 List<Products> productList = new ArrayList<>();
		    String query = "SELECT p.product_id, p.name_id, p.price, p.descr, p.stock " +
		                   "FROM Products p JOIN Cart c ON p.product_id = c.product_id " +
		                   "WHERE c.customer_id = ?";

		    try (Connection conn = DBConnUtil.getConnection();
		         PreparedStatement ps = conn.prepareStatement(query)) {

		        ps.setInt(1, customer.getCustomerId());
		        var resultSet = ps.executeQuery();

		        while (resultSet.next()) {
		        	Products product = new Products();
		        	product.setProductId(resultSet.getInt("product_id"));
		        	product.setName(resultSet.getString("name_id"));
		        	product.setPrice(resultSet.getDouble("price"));
		            product.setDescription(resultSet.getString("descr"));
		            product.setStockQuantity(resultSet.getInt("stock"));
		            productList.add(product);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return productList;
		}


	@Override
	public boolean placeOrder(Customers customer, List<Map<Products, Integer>> products, String shippingAddress) {
		String orderQuery = "INSERT INTO Orders (customer_id, total_price, ship_address) VALUES (?, ?, ?)";
	    String orderItemQuery = "INSERT INTO OrderItems (order_id, product_id, quantity) VALUES (?, ?, ?)";
	    String updateProductStockQuery = "UPDATE Products SET stock = stock - ? WHERE product_id = ?";
	    
	    double totalPrice = 0.0;

	    try (Connection conn = DBConnUtil.getConnection()) {
	        conn.setAutoCommit(false);  // Start transaction

	        // 1️⃣ Calculate Total Price
	        for (Map<Products, Integer> entry : products) {
	            for (Products product : entry.keySet()) {
	                int quantity = entry.get(product);
	                totalPrice += product.getPrice() * quantity;
	            }
	        }

	        // 2️⃣ Insert into Orders
	        try (PreparedStatement psOrder = conn.prepareStatement(orderQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
	            psOrder.setInt(1, customer.getCustomerId());
	            psOrder.setDouble(2, totalPrice);
	            psOrder.setString(3, shippingAddress);
	            psOrder.executeUpdate();

	            // Retrieve generated order_id
	            var rs = psOrder.getGeneratedKeys();
	            int orderId = 0;
	            if (rs.next()) {
	                orderId = rs.getInt(1);
	            }

	            // 3️⃣ Insert into Order Items and update product stock
	            try (PreparedStatement psOrderItem = conn.prepareStatement(orderItemQuery);
	                 PreparedStatement psUpdateStock = conn.prepareStatement(updateProductStockQuery)) {
	                
	                for (Map<Products, Integer> entry : products) {
	                    for (Products product : entry.keySet()) {
	                        int quantity = entry.get(product);

	                        // Add to order_items
	                        psOrderItem.setInt(1, orderId);
	                        psOrderItem.setInt(2, product.getProductId());
	                        psOrderItem.setInt(3, quantity);
	                        psOrderItem.addBatch();

	                        // Update product stock
	                        psUpdateStock.setInt(1, quantity);
	                        psUpdateStock.setInt(2, product.getProductId());
	                        psUpdateStock.addBatch();
	                    }
	                }

	                psOrderItem.executeBatch();
	                psUpdateStock.executeBatch();
	            }
	        }

	        conn.commit();  // Commit transaction
	        return true;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}


	@Override
	public List<Map<Products, Integer>> getOrdersByCustomer(int customerId) throws OrderNotFoundException {
		List<Map<Products, Integer>> ordersList = new ArrayList<>();
	    String query = "SELECT p.product_id, p.name_id, p.price, p.descr, p.stock, oi.quantity " +
	                   "FROM Products p " +
	                   "JOIN orderItems oi ON p.product_id = oi.product_id " +
	                   "JOIN Orders o ON oi.order_id = o.order_id " +
	                   "WHERE o.customer_id = ?";

	    try (Connection conn = DBConnUtil.getConnection();
	         PreparedStatement ps = conn.prepareStatement(query)) {

	        ps.setInt(1, customerId);
	        var resultSet = ps.executeQuery();
	        
	        if (!resultSet.isBeforeFirst()) {  // If no rows found
	            throw new OrderNotFoundException("No orders found for customer with ID " + customerId);
	        }

	        while (resultSet.next()) {
	            Products product = new Products();
	            product.setProductId(resultSet.getInt("product_id"));
	            product.setName(resultSet.getString("name_id"));
	            product.setPrice(resultSet.getDouble("price"));
	            product.setDescription(resultSet.getString("descr"));
	            product.setStockQuantity(resultSet.getInt("stock"));

	            Map<Products, Integer> orderMap = new HashMap<>();
	            orderMap.put(product, resultSet.getInt("quantity"));
	            ordersList.add(orderMap);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return ordersList;
	}
	}

