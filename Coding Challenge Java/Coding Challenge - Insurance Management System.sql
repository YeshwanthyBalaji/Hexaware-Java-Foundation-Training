CREATE DATABASE Insurance;
USE Insurance;

CREATE TABLE User (
    userId INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50),
    password VARCHAR(50),
    role VARCHAR(20)
);

CREATE TABLE Client (
    clientId INT PRIMARY KEY AUTO_INCREMENT,
    clientName VARCHAR(100),
    contactInfo VARCHAR(100),
    policyId INT,
    FOREIGN KEY (policyId) REFERENCES Policy(policyId)
);

CREATE TABLE Policy (
    policyId INT PRIMARY KEY AUTO_INCREMENT,
    policyName VARCHAR(100),
    policyAmount DECIMAL(10, 2)
);


CREATE TABLE Claim (
    claimId INT PRIMARY KEY AUTO_INCREMENT,
    claimNumber VARCHAR(50),
    dateFiled DATE,
    claimAmount DECIMAL(10, 2),
    status VARCHAR(20),
    policyId INT,
    clientId INT,
    FOREIGN KEY (policyId) REFERENCES Policy(policyId),
    FOREIGN KEY (clientId) REFERENCES Client(clientId)
);

CREATE TABLE Payment (
    paymentId INT PRIMARY KEY AUTO_INCREMENT,
    paymentDate DATE,
    paymentAmount DECIMAL(10, 2),
    clientId INT,
    FOREIGN KEY (clientId) REFERENCES Client(clientId)
);

SELECT * FROM User;
SELECT * FROM Client;
SELECT * FROM Claim;
SELECT * FROM Policy;
SELECT * FROM Payment;
