CREATE DATABASE Ecommerce;
USE Ecommerce;

CREATE TABLE Customers (
  customer_id INT PRIMARY KEY AUTO_INCREMENT,
  name_id VARCHAR(100),
  email_id VARCHAR(100),
  password_id VARCHAR(100)
  );
 
CREATE TABLE Products (
  product_id INT PRIMARY KEY AUTO_INCREMENT,
  name_id VARCHAR(100),
  price DECIMAL(10,2), 
  descr VARCHAR(1000),
  stock INT
  );
  
CREATE TABLE Cart (
  cart_id INT PRIMARY KEY AUTO_INCREMENT,
  customer_id INT,
  product_id INT,
  quantity INT,
  FOREIGN KEY (customer_id) REFERENCES Customers (customer_id),
  FOREIGN KEY (product_id) REFERENCES Products (product_id)
  );

CREATE TABLE Orders (
  order_id INT PRIMARY KEY AUTO_INCREMENT,
  customer_id INT,
  order_date DATE,
  total_price DECIMAL(10,3),
  ship_address VARCHAR(500),
  FOREIGN KEY (customer_id) REFERENCES Customers(customer_id)
  );
 
CREATE TABLE OrderItems (
  order_item_id INT PRIMARY KEY AUTO_INCREMENT,
  order_id INT,
  product_id INT,
  quantity INT,
  FOREIGN KEY (order_id) REFERENCES Orders (order_id),
  FOREIGN KEY (product_id) REFERENCES Products (product_id)
  ); -- creation
  
  INSERT INTO Customers (name_id, email_id, password_id) VALUES
('Amit Sharma', 'amit.sharma@email.com', 'amitsharma'),
('Priya Iyer', 'priya.iyer@email.com', 'priyaiyer'),
('Rajesh Kumar', 'rajesh.kumar@email.com', 'rajeshkumar'),
('Neha Patel', 'neha.patel@email.com', 'nehapatel'),
('Vikram Singh', 'vikram.singh@email.com', 'vikramsingh');

  
  INSERT INTO Products (name_id, price, descr, stock) VALUES
('Apple iPhone', 79999.00, 'Latest model with High camera quality', 20),
('Samsung Galaxy', 69999.00, ' Android smartphone', 15),
('One plus Airdopes', 29999.00, 'Noise-cancelling wireless headphones', 30),
('Dell Laptop', 54999.00, 'Intel Core i3 with 16GB RAM', 10),
('Lenovo Laptop', 64999.00, 'Lightweight and comfortable', 50);

INSERT INTO Cart (customer_id, product_id, quantity) VALUES
(1, 2, 1),
(3, 5, 2),
(4, 1, 1),
(5, 4, 1),
(2, 3, 1);

INSERT INTO Orders (customer_id, order_date, total_price, ship_address) VALUES
(1, '2025-05-01', 69999.00, 'Mumbai, Maharashtra'),
(2, '2025-05-02', 29999.00, 'Chennai, Tamil Nadu'),
(3, '2025-05-03', 13998.00, 'Bangalore, Karnataka'),
(4, '2025-05-04', 79999.00, 'Ahmedabad, Gujarat'),
(5, '2025-05-05', 54999.00, 'New Delhi, Delhi');

INSERT INTO OrderItems (order_id, product_id, quantity) VALUES
(1, 2, 1),
(2, 3, 1),
(3, 5, 2),
(4, 1, 1),
(5, 4, 1);

SELECT * FROM Customers;
SELECT * FROM Products;
SELECT * FROM Cart;
SELECT * FROM Orders;
SELECT * FROM OrderItems;
