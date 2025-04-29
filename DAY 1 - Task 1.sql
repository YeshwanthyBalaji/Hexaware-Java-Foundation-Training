CREATE DATABASE SISDB;
USE SISDB;

CREATE TABLE Students (
    student_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    date_of_birth DATE,
    email_id VARCHAR(100),
    phone_no VARCHAR(15)
);
CREATE TABLE Teacher (
    teacher_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email_id VARCHAR(100)
);
CREATE TABLE Courses (
    course_id INT PRIMARY KEY AUTO_INCREMENT,
    course_name VARCHAR(100),
    credits INT,
    teacher_id INT,
    FOREIGN KEY (teacher_id) REFERENCES Teacher(teacher_id)
);
CREATE TABLE Enrollments (
    enrollment_id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT,
    course_id INT,
    enrollment_date DATE,
    FOREIGN KEY (student_id) REFERENCES Students(student_id),
    FOREIGN KEY (course_id) REFERENCES Courses(course_id)
);
CREATE TABLE Payments (
    payment_id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT,
    amount DECIMAL(10,3),
    payment_date DATE,
    FOREIGN KEY (student_id) REFERENCES Students(student_id)
);
INSERT INTO Students (first_name, last_name, date_of_birth, email, phone_number) VALUES
('Yeshwanthy', 'Balaji', '2000-11-25', 'yeshbala@gmail.com', '9826534715'),
('Balaji', 'Rama', '2001-08-10', 'balajirama@gmail.com', '9854625941'),
('Renuka', 'Balaji', '2000-01-11', 'renuka@gmail.com', '9854263179'),
('Diviya', 'Shift', '2003-05-18', 'diviya.shift@email.com', '9567890123'),
('Emma', 'Johnson', '2002-11-30', 'emma24johnson@email.com', '8678901234'),
('John', 'Lee', '2001-09-25', 'john225lee@email.com', '6789012345'),
('Grace', 'Shoba', '2000-02-14', 'graceshoba2000@email.com', '7890123456'),
('Hulk', 'Moore', '2003-08-09', 'hulkkkkmoore@gamil.com', '8901234567'),
('Ivy', 'Shift', '2001-06-17', 'ivy.shift@example.com', '9012345678'),
('Jack', 'Lopez', '2002-12-03', 'jack.lopez@example.com', '9123456789');
INSERT INTO Teacher (first_name, last_name, email) VALUES
('Jersey', 'Rayley', 'jersey@edu.com'),
('Laiya', 'Starve', 'laiya@edu.com'),
('Oviya', 'jais', 'oviya@edu.com'),
('Noah', 'Anderson', 'noah.anderson@school.com'),
('Ava', 'Thomas', 'ava.thomas@school.com'),
('James', 'Jackson', 'james.jackson@school.com'),
('Sophia', 'White', 'sophia.white@school.com'),
('William', 'Harris', 'william.harris@school.com'),
('Isabella', 'Martin', 'isabella.martin@school.com'),
('Benjamin', 'Thompson', 'benjamin.thompson@school.com');
INSERT INTO Courses (course_name, credits, teacher_id) VALUES
('Maths', 5, 1),
('English', 5, 2),
('Tamil', 5, 3),
('Biology', 4, 4),
('Physics', 2, 5),
('MySQL', 3, 6),
('C Programming', 3, 7),
('Computer Science', 4, 8),
('Python', 2, 9),
('Java', 1, 10);
INSERT INTO Enrollments (student_id, course_id, enrollment_date) VALUES
(1, 1, '2021-11-24'),
(2, 2, '2021-11-24'),
(3, 3, '2021-11-24'),
(4, 4, '2024-09-02'),
(5, 5, '2024-09-03'),
(6, 6, '2024-09-03'),
(7, 7, '2024-09-04'),
(8, 8, '2024-09-04'),
(9, 9, '2024-09-05'),
(10, 10, '2024-09-05');
INSERT INTO Payments (student_id, amount, payment_date) VALUES
(1, 1000.00, '2021-09-10'),
(2, 1000.00, '2021-09-11'),
(3, 1000.00, '2021-09-12'),
(4, 1000.00, '2024-09-13'),
(5, 1000.00, '2024-09-14'),
(6, 1000.00, '2024-09-15'),
(7, 1000.00, '2024-09-16'),
(8, 1000.00, '2024-09-17'),
(9, 1000.00, '2024-09-18'),
(10, 1000.00, '2024-09-19');

show tables;

