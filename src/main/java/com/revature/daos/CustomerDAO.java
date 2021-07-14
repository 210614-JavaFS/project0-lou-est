package com.revature.daos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.models.Customer;

@Deprecated
public class CustomerDAO {

	private static Logger log = LoggerFactory.getLogger(CustomerDAO.class);

	public CustomerDAO() {

	}

	public void writeCustomer(Customer customer) {
		File pendingCustomers = new File("src//main//resources//pendingCustomers.txt");

		try {
			if (pendingCustomers.createNewFile()) {
				log.info("Created new pending customers file");
			} else {
				log.info("pending customers file already exists");
			}
		} catch (IOException e) {
			log.error("Something went wrong trying to access pending customers file: " + e.getMessage());
		}

		try (FileWriter writer = new FileWriter("src//main//resources//pendingCustomers.txt", true)) {
			StringBuilder builder = new StringBuilder();
			builder.append(customer.getUsername());
			builder.append("," + customer.getPassword());
			builder.append("," + customer.getName());
			builder.append("," + customer.getAddress());
			builder.append("," + customer.getPhoneNumber());
			builder.append("," + customer.getCheckingAccountBalance());
			builder.append("," + customer.getSavingsAccountBalance() + "\n");
			String pendingCustomerString = new String(builder);
			writer.write(pendingCustomerString);
		} catch (IOException e) {
			log.error("Could not write to file: " + e.getMessage());
		}
	}

	public ArrayList<Customer> findAllPendingCustomers() {
		ArrayList<Customer> allPendingCustomers = new ArrayList<>();
		try (Scanner scan = new Scanner(new File("src\\main\\resources\\pendingCustomers.txt"))) {
			while (scan.hasNextLine()) {
				String pendingCustomerString = scan.nextLine();
				String[] pendingCustomerParts = pendingCustomerString.split(",");
				allPendingCustomers.add(new Customer(pendingCustomerParts[0], (pendingCustomerParts[1]),
						(pendingCustomerParts[2]), (pendingCustomerParts[3]), Integer.valueOf(pendingCustomerParts[4]),
						Integer.valueOf(pendingCustomerParts[5]), Integer.valueOf(pendingCustomerParts[6])));

			}
		} catch (IOException e) {
			log.error("Something went wrong retrieving pending customers: " + e.getMessage());
		}
		return allPendingCustomers;
	}

	public ArrayList<Customer> findAllRegisteredCustomers() {
		ArrayList<Customer> allRegisteredCustomers = new ArrayList<>();
		try (Scanner scan = new Scanner(new File("src\\main\\resources\\registeredCustomers.txt"))) {
			while (scan.hasNextLine()) {
				String registeredCustomerString = scan.nextLine();
				String[] registeredCustomerParts = registeredCustomerString.split(",");
				allRegisteredCustomers.add(new Customer(registeredCustomerParts[0], (registeredCustomerParts[1]),
						(registeredCustomerParts[2]), (registeredCustomerParts[3]),
						Integer.valueOf(registeredCustomerParts[4]), Integer.valueOf(registeredCustomerParts[5]),
						Integer.valueOf(registeredCustomerParts[6])));

			}
		} catch (IOException e) {
			log.error("Something went wrong retrieving registered customers: " + e.getMessage());
		}
		return allRegisteredCustomers;
	}

	public void updatePendingCustomerList(ArrayList<Customer> pendingCustomerList) {
		File pendingCustomers = new File("src//main//resources//pendingCustomers.txt");

		try {
			if (pendingCustomers.createNewFile()) {
				log.info("Created new pending customers file");
			} else {
				log.info("pending customers file already exists");
			}
		} catch (IOException e) {
			log.error("Something went wrong trying to access pending customers file: " + e.getMessage());
		}

		StringBuilder builder = new StringBuilder();

		if (pendingCustomerList.size() == 0) {
			try (FileWriter writer = new FileWriter("src//main//resources//pendingCustomers.txt", false)) {
				writer.write("");
			} catch (Exception e) {
				log.error("Could not write to file: " + e.getMessage());
			}
		}

		for (Customer pendingCustomer : pendingCustomerList) {
			try (FileWriter writer = new FileWriter("src//main//resources//pendingCustomers.txt", false)) {
				builder.append(pendingCustomer.getUsername());
				builder.append("," + pendingCustomer.getPassword());
				builder.append("," + pendingCustomer.getName());
				builder.append("," + pendingCustomer.getAddress());
				builder.append("," + pendingCustomer.getPhoneNumber());
				builder.append("," + pendingCustomer.getCheckingAccountBalance());
				builder.append("," + pendingCustomer.getSavingsAccountBalance() + "\n");
				String pendingCustomerString = new String(builder);
				writer.write(pendingCustomerString);
			} catch (IOException e) {
				log.error("Could not write to file: " + e.getMessage());
			}
		}
	}

	public void updateRegisteredCustomerList(ArrayList<Customer> registeredCustomerList) {
		File pendingCustomers = new File("src//main//resources//registeredCustomers.txt");

		try {
			if (pendingCustomers.createNewFile()) {
				log.info("Created new registered customers file");
			} else {
				log.info("registered customers file already exists");
			}
		} catch (IOException e) {
			log.error("Something went wrong trying to access registered customers file: " + e.getMessage());
		}

		if (registeredCustomerList.size() == 0) {
			try (FileWriter writer = new FileWriter("src//main//resources//registeredCustomers.txt", false)) {
				writer.write("");
			} catch (Exception e) {
				log.error("Could not write to file: " + e.getMessage());
			}
		}
			
		StringBuilder builder = new StringBuilder();
		for (Customer registeredCustomer : registeredCustomerList) {
			try (FileWriter writer = new FileWriter("src//main//resources//registeredCustomers.txt", false)) {
				builder.append(registeredCustomer.getUsername());
				builder.append("," + registeredCustomer.getPassword());
				builder.append("," + registeredCustomer.getName());
				builder.append("," + registeredCustomer.getAddress());
				builder.append("," + registeredCustomer.getPhoneNumber());
				builder.append("," + registeredCustomer.getCheckingAccountBalance());
				builder.append("," + registeredCustomer.getSavingsAccountBalance() + "\n");
				String registeredCustomerString = new String(builder);
				writer.write(registeredCustomerString);
			} catch (IOException e) {
				log.error("Could not write to file: " + e.getMessage());
			}
		}
	}

}