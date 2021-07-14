package com.revature.services;

import java.util.ArrayList;

import com.revature.daos.PendingCustomerDAO;
import com.revature.daos.PendingCustomerDAOImpl;
import com.revature.models.Customer;

public class PendingCustomerService {

	PendingCustomerDAO pendingCustomerDAO = new PendingCustomerDAOImpl();

	public Customer createNewCustomer(String username, String password, String name, String address, long phoneNumber,
			int checkingAccountBalance, int savingsAccountBalance) {

		return new Customer(username, password, name, address, phoneNumber, checkingAccountBalance,
				savingsAccountBalance);
	}

	public Customer getPendingCustomerByUsername(String username) {
		return pendingCustomerDAO.findByUsername(username);
	}

	public Customer getPendingCustomerById(int id) {
		return pendingCustomerDAO.findById(id);
	}

	public int updatePendingCustomer(Customer pendingCustomer) {
		return pendingCustomerDAO.updateCustomer(pendingCustomer);
	}

	public ArrayList<Customer> getAllPendingCustomers() {
		return (ArrayList<Customer>) pendingCustomerDAO.findAll();
	}

	public void save(Customer customer) {
		pendingCustomerDAO.addCustomer(customer);
	}

	public boolean deleteAccount(Customer customer) {
		return pendingCustomerDAO.deleteCustomer(customer.getId());
	}

}

