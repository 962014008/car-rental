CREATE DATABASE IF NOT EXISTS car_rental;
USE car_rental;
CREATE TABLE IF NOT EXISTS car(name VARCHAR(20) NOT NULL, description VARCHAR(200) NOT NULL, price VARCHAR(20) NOT NULL, quantity INT UNSIGNED NOT NULL)ENGINE=InnoDB DEFAULT CHARSET='utf8';
CREATE TABLE IF NOT EXISTS  borrowed_date(start_date VARCHAR(20) NOT NULL, end_date VARCHAR(20) NOT NULL, car_id INT UNSIGNED NOT NULL)ENGINE=InnoDB DEFAULT CHARSET='utf8';
INSERT INTO car (name, description, price, quantity) VALUES ('Toyota Camry', 'The Toyota Camry is an automobile sold internationally by the Japanese manufacturer Toyota since 1982, spanning multiple generations.', 150, 2),('BMW 650', 'The BMW 650 is available as a convertible and a coupe. Inventory prices for the 2018 650 range from $55,880 to $80,064. It gets EPA-estimated 19-20 MPG combined.', 200, 2);
INSERT INTO borrowed_date(start_date, end_date, car_id) VALUES('1000-01-01 00:00:01', '1001-01-01 00:00:01', 1),('1000-01-01 00:00:01', '1001-01-01 00:00:01', 2);