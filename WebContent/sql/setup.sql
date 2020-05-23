DROP DATABASE IF EXISTS store_db;

CREATE DATABASE store_db;

USE store_db;

CREATE TABLE IF NOT EXISTS products(
  id INT(6) UNSIGNED PRIMARY KEY,
  name varchar(64) NOT NULL,
  description varchar(255),
  size varchar(64),
  price decimal(10,2) NOT NULL,
  switch varchar(64),
  category varchar(64) NOT NULL
);

CREATE TABLE IF NOT EXISTS orders(
 id INT(10) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
 firstName varchar(20) NOT NULL,
 lastName varchar(20) NOT NULL,
 email varchar(20) NOT NULL,
 phone varchar(12) NOT NULL,
 address varchar(50) NOT NULL,
 city varchar(20) NOT NULL,
 state varchar(2) NOT NULL,
 zip varchar(10) NOT NULL,
 productID varchar(255),
 ship enum('overnight', '2-days', '6-days'),
 total varchar(10) NOT NULL
);
