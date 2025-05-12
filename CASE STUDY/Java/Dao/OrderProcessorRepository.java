package com.hexaware.dao;

import java.util.List;
import java.util.Map;

import com.hexaware.entity.Customers;
import com.hexaware.entity.Products;
import com.hexaware.exception.CustomerNotFoundException;
import com.hexaware.exception.OrderNotFoundException;
import com.hexaware.exception.ProductNotFoundException;

public interface OrderProcessorRepository
{
	// 1. createProduct() 
    boolean createProduct(Products product);

    // 2. createCustomer() 
    boolean createCustomer(Customers customer);

    // 3. deleteProduct()
    boolean deleteProduct(int productId) throws ProductNotFoundException;

    // 4. deleteCustomer(customerId)
    boolean deleteCustomer(int customerId) throws CustomerNotFoundException;

    // 5. addToCart()
    boolean addToCart(Customers customer, Products product, int quantity);

    // 6. removeFromCart()
    boolean removeFromCart(Customers customer, Products product);

    // 7. getAllFromCart(Customer customer)
    List<Products> getAllFromCart(Customers customer);

    // 8. placeOrder(Customer customer, List<Map<Product,quantity>>, string shippingAddress)
    boolean placeOrder(Customers customer, List<Map<Products, Integer>> product, String shippingAddress);

    // 9. getOrdersByCustomer()
    List<Map<Products, Integer>> getOrdersByCustomer(int customerId) throws OrderNotFoundException;
}

