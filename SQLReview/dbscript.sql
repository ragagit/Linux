--Creating database
create database if not exists raga;
CREATE TABLE customers (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(20), age INT, address VARCHAR(50), salary FLOAT);
CREATE TABLE orders (oid INT PRIMARY KEY, date DATE, customer_id INT, amount FLOAT);
insert into customers values(1, 'Ramesh', 32, '123 Ahmedabad', 2000);
insert into customers values(2, 'Khilan', 25, '321 Delhi', 1500);
insert into customers values(3, 'Kota', 23, '321 Delhi', 2500);
insert into customers values(4, 'Mumbai', 25, '321 Delhi', 6500);
insert into customers values(5, 'Bhopal', 27, '321 Delhi', 8500);
insert into customers values(6, 'MP', 22, '321 Delhi', 4500);
insert into customers values(7, 'Indore', 24, '321 Delhi', 10000);

insert into orders values(102, NOW(), 3, 3000);
insert into orders values(100, NOW(), 3, 1500);
insert into orders values(101, NOW(), 2, 1560);
insert into orders values(103, NOW(), 4, 2060);
