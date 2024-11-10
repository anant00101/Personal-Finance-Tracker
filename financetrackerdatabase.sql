-- Create Database
CREATE DATABASE FinanceTracker;

-- Use the database
USE FinanceTracker;

-- Create Transactions Table
CREATE TABLE transactions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255),
    amount DECIMAL(10, 2),
    date DATE
);
