package com.revature.services;

import java.util.ArrayList;

import com.revature.daos.RegisteredCustomerDAOImpl;
import com.revature.models.Customer;

public class RegisteredCustomerService {

	RegisteredCustomerDAOImpl registeredCustomerDAO = new RegisteredCustomerDAOImpl();

	public Customer getRegisteredCustomerByUsername(String username) {
		return registeredCustomerDAO.findByUsername(username);
	}
	
	public Customer getRegisteredCustomerById(int id) {
		return registeredCustomerDAO.findById(id);
	}

	public int updateRegisteredCustomer(Customer registeredCustomer) {
		return registeredCustomerDAO.updateCustomer(registeredCustomer);
	}

	public ArrayList<Customer> getAllRegisteredCustomers() {
		return (ArrayList<Customer>) registeredCustomerDAO.findAll();
	}

	public void save(Customer customer) {
		registeredCustomerDAO.addCustomer(customer);
	}

	public boolean deleteAccount(Customer customer) {
		return registeredCustomerDAO.deleteCustomer(customer.getId());
	}

}