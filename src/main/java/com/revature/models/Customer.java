package com.revature.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Customer {

	private static Logger log = LoggerFactory.getLogger(Customer.class);

	private int id;
	private String username;
	private String password;
	private String name;
	private String address;
	private long phoneNumber;
	private int checkingAccountBalance;
	private int savingsAccountBalance;

	public Customer(int id, String username, String password, String name, String address, long phoneNumber,
			int checkingAccountBalance, int savingsAccountBalance) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.checkingAccountBalance = checkingAccountBalance;
		this.savingsAccountBalance = savingsAccountBalance;
	}

	public Customer(String username, String password, String name, String address, long phoneNumber,
			int checkingAccountBalance, int savingsAccountBalance) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.checkingAccountBalance = checkingAccountBalance;
		this.savingsAccountBalance = savingsAccountBalance;
	}

	public Customer() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getCheckingAccountBalance() {
		return checkingAccountBalance;
	}

	public void setCheckingAccountBalance(int checkingAccountBalance) {
		if (checkingAccountBalance > 0) {
			this.checkingAccountBalance = checkingAccountBalance;
		} else {
			// log
		}
	}

	public int getSavingsAccountBalance() {
		return savingsAccountBalance;
	}

	public void setSavingsAccountBalance(int savingsAccountBalance) {
		if (savingsAccountBalance > 0) {
			this.savingsAccountBalance = savingsAccountBalance;
		} else {
			// log
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		long result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + checkingAccountBalance;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + phoneNumber;
		result = prime * result + savingsAccountBalance;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return (int) result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (checkingAccountBalance != other.checkingAccountBalance)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phoneNumber != other.phoneNumber)
			return false;
		if (savingsAccountBalance != other.savingsAccountBalance)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Customer Id: " + id + ", Username: " + username + ", Password: " + password + ", Name: " + name
				+ ", Address: " + address + ", Phone Number: " + phoneNumber + ", Checking Account Balance: "
				+ checkingAccountBalance + ", Savings Account Balance: " + savingsAccountBalance;// + ", toString()=" +
																									// super.toString()
																									// + "]";
	}

}