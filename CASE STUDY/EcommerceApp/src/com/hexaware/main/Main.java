package com.hexaware.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.hexaware.dao.OrderProcessorRepositoryImpl;
import com.hexaware.entity.Customers;
import com.hexaware.entity.Products;
import com.hexaware.exception.CustomerNotFoundException;
import com.hexaware.exception.ProductNotFoundException;

public class Main {

	static OrderProcessorRepositoryImpl repo = new OrderProcessorRepositoryImpl();
    static Scanner sc = new Scanner(System.in);
    
	public static void main(String[] args) {
		while (true) {
            System.out.println("\n=== E-commerce Application Menu ===");
            System.out.println("1. Register Customer");
            System.out.println("2. Create Product");
            System.out.println("3. Delete Product");
            System.out.println("4. Add to Cart");
            System.out.println("5. View Cart");
            System.out.println("6. Place Order");
            System.out.println("7. View Customer Orders");
            System.out.println("8. Delete Customer");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");
            
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
            case 1:
                registerCustomer();
                break;
            case 2:
                createProduct();
                break;
            case 3:
                deleteProduct();
                break;
            case 4:
                addToCart();
                break;
            case 5:
                viewCart();
                break;
            case 6: 
            	placeOrder();
            	break;
            case 7:
            	viewCustomerOrders();
            	break;
            case 8:
            	deleteCustomer();
            	break;
            case 9:
                System.out.println("Exiting the system...");
                return;
            default:
                System.out.println("Invalid choice! Please try again.");
	        }
        }
    }


	//1 -> registerCustomer()
public static void registerCustomer() {
    System.out.print("Enter Name: ");
    String name = sc.nextLine();
    if (name.isEmpty()) {
        System.out.println("Name cannot be empty!");
        return;
    }
    System.out.print("Enter Email: ");
    String email = sc.nextLine();
    if (email.isEmpty()) {
        System.out.println("Email cannot be empty!");
        return;
    }
    System.out.print("Enter Password: ");
    String password = sc.nextLine();
    if (password.isEmpty()) {
        System.out.println("Password cannot be empty!");
        return;
    }


    Customers customer = new Customers(0, name, email, password);
    if (repo.createCustomer(customer)) {
        System.out.println("Customer Registered Successfully!");
    } else {
        System.out.println("Registration Failed.");
    }
}


//2 -> createProduct()
public static void createProduct() {
    System.out.print("Enter Product Name: ");
    String name = sc.nextLine();
    if (name.isEmpty()) {
        System.out.println("Product Name cannot be empty!");
        return;
    }
    System.out.print("Enter Price: ");
    double price = sc.nextDouble();
    sc.nextLine();
    if (price <= 0) {
        System.out.println("Price must be greater than 0!");
        return;
    }
    System.out.print("Enter Description: ");
    String description = sc.nextLine();
    if (description.isEmpty()) {
        System.out.println("Description cannot be empty!");
        return;
    }
    System.out.print("Enter Stock Quantity: ");
    int stockQuantity = sc.nextInt();
    if (stockQuantity <= 0) {
        System.out.println("Stock Quantity must be greater than 0!");
        return;
    }

    Products product = new Products(0, name, price, description, stockQuantity);
    if (repo.createProduct(product)) {
        System.out.println("Product Added Successfully!");
    } else {
        System.out.println("Failed to Add Product.");
    }
}

//3 -> deleteProduct()
public static void deleteProduct() {
    System.out.print("Enter Product ID to delete: ");
    int productId = sc.nextInt();
    
    if (productId <= 0) {
        System.out.println("Invalid Product ID!");
        return;
    }
    
    try {
        if (repo.deleteProduct(productId)) {
            System.out.println("Product Deleted Successfully!");
        }
    } catch (ProductNotFoundException e) {
        System.out.println(e.getMessage());
    }
}

//4 -> addToCart()
public static void addToCart() {
    System.out.print("Enter Customer ID: ");
    int customerId = sc.nextInt();
    System.out.print("Enter Product ID: ");
    int productId = sc.nextInt();
    System.out.print("Enter Quantity: ");
    int quantity = sc.nextInt();

    Customers customer = new Customers();
    customer.setCustomerId(customerId);

    Products product = new Products();
    product.setProductId(productId);

    if (repo.addToCart(customer, product, quantity)) {
        System.out.println("Product Added to Cart!");
    } else {
        System.out.println("Failed to Add Product to Cart.");
    }
}

//5 -> viewCart()
public static void viewCart() {
    System.out.print("Enter Customer ID to view cart: ");
    int customerId = sc.nextInt();

    Customers customer = new Customers();
    customer.setCustomerId(customerId);

    List<Products> products = repo.getAllFromCart(customer);

    if (products.isEmpty()) {
        System.out.println("Cart is Empty.");
    } else {
        System.out.println("\nProducts in Cart:");
        for (Products p : products) {
            System.out.println(p.getName() + " - " + p.getPrice() + " - Quantity: " + p.getStockQuantity());
        }
    }
}

//6 -> placeOrder()
public static void placeOrder() {
    System.out.print("Enter Customer ID: ");
    int customerId = sc.nextInt();
    sc.nextLine();  // Consume the newline

    Customers customer = new Customers();
    customer.setCustomerId(customerId);

    List<Map<Products, Integer>> productList = new ArrayList<>();

    System.out.println("Enter the number of products to order:");
    int numOfProducts = sc.nextInt();
    sc.nextLine();  // Consume the newline

    for (int i = 0; i < numOfProducts; i++) {
        System.out.print("Enter Product ID: ");
        int productId = sc.nextInt();
        System.out.print("Enter Quantity: ");
        int quantity = sc.nextInt();
        sc.nextLine();  // Consume the newline

        Products product = new Products();
        product.setProductId(productId);

        Map<Products, Integer> productMap = new HashMap<>();
        productMap.put(product, quantity);
        productList.add(productMap);
    }

    System.out.print("Enter Shipping Address: ");
    String address = sc.nextLine();

    boolean result = repo.placeOrder(customer, productList, address);
    if (result) {
        System.out.println("Order placed successfully!");
    } else {
        System.out.println("Failed to place the order.");
    }
}

    //7 -> viewCustomerOrders()
    public static void viewCustomerOrders() {
        System.out.print("Enter Customer ID to view orders: ");
        int customerId = sc.nextInt();
        sc.nextLine(); // Consume newline

        try {
            List<Map<Products, Integer>> orders = repo.getOrdersByCustomer(customerId);

            if (orders.isEmpty()) {
                System.out.println("No orders found for this customer.");
            } else {
                System.out.println("\nCustomer Orders:");
                for (Map<Products, Integer> order : orders) {
                    for (Products product : order.keySet()) {
                        int quantity = order.get(product);
                        System.out.println(product.getName() + " - " + product.getPrice() + " - Quantity: " + quantity);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //8 -> deleteCustomer()
    public static void deleteCustomer() {
        System.out.print("Enter Customer ID to delete: ");
        int customerId = sc.nextInt();
        
        if (customerId <= 0) {
            System.out.println("Invalid Customer ID!");
            return;
        }
        
        try {
            if (repo.deleteCustomer(customerId)) {
                System.out.println("Customer Deleted Successfully!");
            }
        } catch (CustomerNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}

