package com.hexaware.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import com.hexaware.dao.*;
import com.hexaware.entity.*;
import com.hexaware.exception.*;

public class OrderProcessorRepositoryImplTest {

	OrderProcessorRepositoryImpl repo = new OrderProcessorRepositoryImpl();

	@Test
	void testCreateProduct() {
	    Products product = new Products(1, "Laptop", 1200.00, "High-end laptop", 10);
	    boolean result = repo.createProduct(product);
	    assertTrue(result, "Product should be created successfully.");
	}
    
	@Test
	void testAddToCart() {
	    Customers customer = new Customers(1, "John Doe", "john@example.com", "password");
	    Products product = new Products(1, "Laptop", 1200.00, "High-end laptop", 10);
	    
	    boolean result = repo.addToCart(customer, product, 1);
	    assertTrue(result, "Product should be added to the cart successfully.");
	}
	
	@Test
	void testCustomerNotFoundException() {
	    assertThrows(CustomerNotFoundException.class, () -> {
	        repo.getOrdersByCustomer(999); 
	    });
	}

	@Test
	void testProductNotFoundException() {
	    assertThrows(ProductNotFoundException.class, () -> {
	        repo.deleteProduct(999); 
	    });
	}

}
