package com.revature.daos;

import java.util.List;

import com.revature.models.Customer;

public interface RegisteredCustomersDAO {

	public List<Customer> findAll();

	public Customer findByUsername(String username);

	public Customer findById(int id);

	public int updateCustomer(Customer customer);

	public boolean addCustomer(Customer customer);

	public boolean deleteCustomer(int id);

}