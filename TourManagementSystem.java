package com.tour.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TourManagementSystem {

    public static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/tour management system";
        String user = "root"; 
        String password = "Deekshitha@1";

        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database.");
            e.printStackTrace();
            return null;
        }
    }

    public static void addCustomer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Customer Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Customer Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Customer Phone: ");
        String phone = scanner.nextLine();

        System.out.print("Enter Customer Address: ");
        String address = scanner.nextLine();

        String query = "INSERT INTO Customers(Name, Email, Phone, Address) VALUES (?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, phone);
            stmt.setString(4, address);

            int rowsInserted = stmt.executeUpdate();
            System.out.println(rowsInserted + " customer(s) added successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addTourPackage() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Destination: ");
        String destination = scanner.nextLine();

        System.out.print("Enter Cost: ");
        double cost = scanner.nextDouble();

        System.out.print("Enter Duration (in days): ");
        int duration = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Enter Description: ");
        String description = scanner.nextLine();

        String query = "INSERT INTO tour_packages(destination, cost, duration, description) VALUES (?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, destination);
            stmt.setDouble(2, cost);
            stmt.setInt(3, duration);
            stmt.setString(4, description);

            int rowsInserted = stmt.executeUpdate();
            System.out.println(rowsInserted + " tour package(s) added successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addBooking() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Customer ID: ");
        int customerId = scanner.nextInt();

        System.out.print("Enter Package ID: ");
        int packageId = scanner.nextInt();

        System.out.print("Enter Booking Date (YYYY-MM-DD): ");
        String bookingDate = scanner.next();

        System.out.print("Enter Booking Status: ");
        String status = scanner.next();

        String query = "INSERT INTO bookings(customer_id, package_id, booking_date, status) VALUES (?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, customerId);
            stmt.setInt(2, packageId);
            stmt.setDate(3, java.sql.Date.valueOf(bookingDate));
            stmt.setString(4, status);

            int rowsInserted = stmt.executeUpdate();
            System.out.println(rowsInserted + " booking(s) created successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void viewAllCustomers() {
        String query = "SELECT * FROM Customers";

        try (Connection connection = getConnection();
             Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(query)) {

            System.out.println("Customers:");
            while (resultSet.next()) {
                System.out.println("Customer ID: " + resultSet.getInt("customer_id"));
                System.out.println("Name: " + resultSet.getString("Name"));
                System.out.println("Email: " + resultSet.getString("Email"));
                System.out.println("Phone: " + resultSet.getString("Phone"));
                System.out.println("Address: " + resultSet.getString("Address"));
                System.out.println("--------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void viewAllTourPackages() {
        String query = "SELECT * FROM tour_packages";

        try (Connection connection = getConnection();
             Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(query)) {

            System.out.println("Tour Packages:");
            while (resultSet.next()) {
                System.out.println("Package ID: " + resultSet.getInt("package_id"));
                System.out.println("Destination: " + resultSet.getString("destination"));
                System.out.println("Cost: " + resultSet.getDouble("cost"));
                System.out.println("Duration: " + resultSet.getInt("duration"));
                System.out.println("Description: " + resultSet.getString("description"));
                System.out.println("--------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void viewCustomerBookings() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Customer ID: ");
        int customerId = scanner.nextInt();

        String query = "SELECT * FROM bookings WHERE customer_id = ?";

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, customerId);
            ResultSet resultSet = stmt.executeQuery();

            System.out.println("Bookings for Customer ID: " + customerId);
            while (resultSet.next()) {
                System.out.println("Booking ID: " + resultSet.getInt("booking_id"));
                System.out.println("Package ID: " + resultSet.getInt("package_id"));
                System.out.println("Booking Date: " + resultSet.getDate("booking_date"));
                System.out.println("Status: " + resultSet.getString("status"));
                System.out.println("--------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void viewAllPayments() {
        String query = "SELECT * FROM payments";

        try (Connection connection = getConnection();
             Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(query)) {

            System.out.println("Payments:");
            while (resultSet.next()) {
                System.out.println("Payment ID: " + resultSet.getInt("payment_id"));
                System.out.println("Booking ID: " + resultSet.getInt("booking_id"));
                System.out.println("Amount: " + resultSet.getDouble("amount"));
                System.out.println("Payment Date: " + resultSet.getDate("payment_date"));
                System.out.println("Payment Status: " + resultSet.getString("payment_status"));
                System.out.println("--------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	public static void main(String[] args) {
		 Scanner scanner = new Scanner(System.in);
	        int choice;

	        do {
	            System.out.println("\n=== Tour Management System ===");
	            System.out.println("1. Add Customer");
	            System.out.println("2. Add Tour Package");
	            System.out.println("3. Add Booking");
	            System.out.println("4. View All Customers");
	            System.out.println("5. View All Tour Packages");
	            System.out.println("6. View Customer Bookings");
	            System.out.println("7. View All Payments");
	            System.out.println("8. Exit");
	            System.out.print("Enter your choice: ");
	            choice = scanner.nextInt();

	            switch (choice) {
	                case 1:
	                    addCustomer();
	                    break;
	                case 2:
	                    addTourPackage();
	                    break;
	                case 3:
	                    addBooking();
	                    break;
	                case 4:
	                    viewAllCustomers();
	                    break;
	                case 5:
	                    viewAllTourPackages();
	                    break;
	                case 6:
	                    viewCustomerBookings();
	                    break;
	                case 7:
	                    viewAllPayments();
	                    break;
	                case 8:
	                    System.out.println("THANKYOU..! VISIT AGAIN");
	                    break;
	                default:
	                    System.out.println("Invalid choice. Please try again.");
	            }
	        } while (choice != 8);

	        scanner.close();
	    }
	

	}