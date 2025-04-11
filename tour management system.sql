create table Customers(
       customer_id int primary key auto_increment,
       Name varchar(255),
       Email varchar(100),
       Phone varchar(15),
       Address text
   );
insert into Customers(customer_id,Name,Email,Phone,Address)values
(1, 'Rohit', 'rohit@gmail.com', 9876543210,'123 Main Road, Bengaluru'),
(2, 'Priya', 'priya@gmail.com', 8765432109,'45 South Avenue, Delhi'),
(3, 'Kiran', 'kiran@gmail.com', 7654321098,'78 West Street, Kolkata'),
(4, 'meenakshi', 'meenakshi@gmail.com', 7896541230,'90 MG Road, Pune'),
(5, 'Arjun', 'arjun@gmail.com', 9123456780, '23 Jubilee Hills, Hyderabad'),
(6,'Sanjana','sanjana@gmail.com',8876543210,'nagole,Hyderabad');
select * from Customers;

   create table tour_packages (
       package_id int primary key auto_increment,
       destination varchar(255),
       cost decimal(10, 2),
       duration int,
       description text
   );
insert into tour_packages (package_id, destination, cost, duration, description) values
(1, 'Shimla, India', 25000.00, 5, 'Enjoy the scenic beauty of hills and snow.'),
(2, 'Darjeeling, India', 28000.00, 5, 'Witness tea gardens and stunning Himalayan views.'),
(3, 'Leh-Ladakh, India', 35000.00, 7, 'Experience breathtaking landscapes, monasteries, and adventurous road trips.'),
(4, 'Kerala Backwaters and Beaches', 30000.00, 6, 'Relax on houseboats, enjoy lush greenery, and savor Kerala cuisine.'),
(5, 'Goa, India', 20000.00, 4, 'Enjoy pristine beaches, vibrant nightlife, and water sports.'),
(6, 'Andaman Islands', 50000.00, 7, 'Snorkeling, tropical beaches, and serene island life.');
select * from tour_packages;

   create table bookings (
       booking_id int primary key auto_increment,
       customer_id int,
       package_id int,
       booking_date DATE,
       status varchar(50),
       FOREIGN KEY (customer_id) REFERENCES customers(customer_id),
       FOREIGN KEY (package_id) REFERENCES tour_packages(package_id)
   );
insert into bookings (booking_id, customer_id, package_id, booking_date, status) values 
(1, 1, 1, '2025-03-01', 'Confirmed'),
(2, 2, 2, '2025-03-05', 'Pending'),
(3, 3, 3, '2025-03-07', 'Cancelled'),
(4, 4, 4, '2025-03-10', 'Confirmed'),
(5, 5, 5, '2025-03-15', 'Pending'),
(6, 6, 6, '2025-03-18', 'Confirmed');
select * from bookings;

       create table payments (
       payment_id int primary key auto_increment,
       booking_id int,
       amount decimal(10, 2),
       payment_date DATE,
       payment_status varchar(50),
       FOREIGN KEY (booking_id) REFERENCES bookings(booking_id)
   );
   select * from payments;
insert into payments (payment_id, booking_id, amount, payment_date, payment_status) values
(7, 1, 25000.00, '2025-03-02', 'Completed'),
(8, 2, 28000.00, '2025-03-06', 'Pending'),
(9, 3, 0.00, '2025-03-08', 'Refunded'),
(10, 4, 30000.00, '2025-03-11', 'Completed'),
(11, 5, 20000.00, '2025-03-16', 'Pending'),
(12, 6, 50000.00, '2025-03-19', 'Completed');
ALTER TABLE payments MODIFY payment_id INT AUTO_INCREMENT;
DELETE FROM payments WHERE payment_id IN (1, 2, 3, 4,5,6,100,101,102,103,104,105);
select * from payments;

