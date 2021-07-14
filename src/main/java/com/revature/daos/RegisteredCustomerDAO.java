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
public class RegisteredCustomerDAO {

	private static Logger log = LoggerFactory.getLogger(CustomerDAO.class);

	public void writeRegisteredCustomer(Customer customer) {
		File registeredCustomers = new File("src//main//resources//registeredCustomers.txt");

		try {
			if (registeredCustomers.createNewFile()) {
				log.info("Created new registered customers file");
			} else {
				log.info("registered customers file already exists");
			}
		} catch (IOException e) {
			log.error("Something went wrong trying to access registered customers file: " + e.getMessage());
		}

		try (FileWriter writer = new FileWriter("src//main//resources//registeredCustomers.txt", true)) {
			StringBuilder builder = new StringBuilder(customer.getName());
			builder.append("," + customer.getUsername());
			builder.append("," + customer.getPassword());
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
			log.error("Something went wrong retrieving pending customers: " + e.getMessage());
		}
		return allRegisteredCustomers;
	}

	public void writeRegisteredCustomer() {
		// TODO Auto-generated method stub

	}

}