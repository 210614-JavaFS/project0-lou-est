package com.revature.controllers;

import java.util.ArrayList;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.models.Customer;
import com.revature.services.BankAdminService;
import com.revature.services.EmployeeService;
import com.revature.services.PendingCustomerService;
import com.revature.services.RegisteredCustomerService;

public class EmployeeBankController {

	private static Logger log = LoggerFactory.getLogger(EmployeeBankController.class);
	private static Scanner scan = new Scanner(System.in);

	BankAdminService bankAdminService = new BankAdminService();
	EmployeeService employeeService = new EmployeeService();
	CustomerBankController customerBankController = new CustomerBankController();

	PendingCustomerService pendingCustomerService = new PendingCustomerService();
	RegisteredCustomerService registeredCustomerService = new RegisteredCustomerService();

	public void employeeMenu() {
		int response = 0;
		boolean isLogin = false;
		while (true) {

			// If employee is not logged in
			if (!isLogin) {
				try {
					System.out.println("Please enter your Employee Identification Number:");
					response = scan.nextInt();
					scan.nextLine();

					isLogin = employeeService.isLogin(response);

					if (!isLogin) {
						System.out.println("Incorrect Employee Identification Number");
					}
				} catch (Exception e) {
					System.out.println("Invalid Choice!");
					scan.nextLine();
				}
			} else {

				// employee menu after login
				System.out.println(
						"Welcome Uhura, Please make a selection: \n" + "1) View pending customer information. \n"
								+ "2) View registered customer information. \n" + "3) Exit.");
				int choice = scan.nextInt();
				if (choice == 1) {
					viewPendingCustomer();
				} else if (choice == 2) {
					viewRegisteredCustomer();
				} else if (choice == 3) {
					return;
				} else {
					System.out.println("That is invalid. Try again.");
				}
			}
		}
	}

	private void viewRegisteredCustomer() {

		ArrayList<Customer> allRegisteredCustomers = registeredCustomerService.getAllRegisteredCustomers();

		System.out.println("Here are all registered customers.");

		for (Customer registeredCustomer : allRegisteredCustomers) {
			System.out.println(registeredCustomer);
		}

		System.out.println("Select Customer, Enter Id: ");
		int customerId = scan.nextInt();
		scan.nextLine();

		Customer currentCustomer = registeredCustomerService.getRegisteredCustomerById(customerId);

		if (currentCustomer != null) {
			customerBankController.customerMenu(currentCustomer);
		} else {
			System.out.println("Invalid Customer Id");
		}

	}

	public void viewPendingCustomer() {
		ArrayList<Customer> allPendingCustomers = pendingCustomerService.getAllPendingCustomers();

		System.out.println("Here are all pending customers.");

		for (Customer pendingCustomer : allPendingCustomers) {
			System.out.println(pendingCustomer);
		}

		System.out.println("Select Customer Id: ");
		int customerId = scan.nextInt();
		scan.nextLine();

		Customer currentCustomer = pendingCustomerService.getPendingCustomerById(customerId);

		if (currentCustomer != null) {

			System.out.println("Type a to accept. Type d to deny.");
			String choice1 = scan.nextLine();

			if (choice1.contains("a")) {

				pendingCustomerService.deleteAccount(currentCustomer);
				registeredCustomerService.save(currentCustomer);

			} else if (choice1.contains("d")) {

				pendingCustomerService.deleteAccount(currentCustomer);

			} else {
				System.out.println("Invaid Choice");
			}
		} else {
			System.out.println("Invalid Customer Id");
		}

	}

}