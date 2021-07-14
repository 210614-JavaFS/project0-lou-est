package com.revature.services;

import java.util.ArrayList;

import com.revature.daos.CustomerDAO;
import com.revature.models.Customer;

@Deprecated
public class CustomerService {

	CustomerDAO customerDAO = new CustomerDAO();

	public Customer createNewCustomer(String username, String password, String name, String address, long phoneNumber,
			int checkingAccountBalance, int savingsAccountBalance) {

		return new Customer(username, password, name, address, phoneNumber, checkingAccountBalance,
				savingsAccountBalance);
	}

	public void updatingPendingCustomerList(ArrayList<Customer> pendingCustomerList) {
		customerDAO.updatePendingCustomerList(pendingCustomerList);
	}

	public void updatingRegisteredCustomerList(ArrayList<Customer> registeredCustomerList) {
		customerDAO.updateRegisteredCustomerList(registeredCustomerList);
	}

	public ArrayList<Customer> getAllPendingCustomers() {
		return customerDAO.findAllPendingCustomers();
	}

	public ArrayList<Customer> getAllRegisteredCustomers() {
		return customerDAO.findAllRegisteredCustomers();
	}

	public void save(Customer customer) {
		customerDAO.writeCustomer(customer);
	}

}