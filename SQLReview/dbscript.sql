--Creating database
create database if not exists raga;
CREATE TABLE customers (name VARCHAR(20), age INT, address VARCHAR(50), salary FLOAT);
CREATE TABLE orders (date DATE, customer_id INT, amount FLOAT);
