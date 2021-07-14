package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.models.Customer;
import com.revature.utils.ConnectionUtil;

public class RegisteredCustomerDAOImpl implements RegisteredCustomersDAO {

	private static Logger log = LoggerFactory.getLogger(RegisteredCustomerDAOImpl.class);

	@Override
	public List<Customer> findAll() {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM registered_customers";

			Statement statement = conn.createStatement();

			log.info("findAll: " + sql);

			ResultSet result = statement.executeQuery(sql);

			List<Customer> list = new ArrayList<>();

			// ResultSets have a cursor similarly to Scanner
			while (result.next()) {
				Customer customer = new Customer();
				customer.setId(result.getInt("registered_customer_id"));
				customer.setUsername(result.getString("username"));
				customer.setPassword(result.getString("pass_word"));
				customer.setName(result.getString("customer_name"));
				customer.setAddress(result.getString("address"));
				customer.setPhoneNumber(result.getLong("phone_number"));
				customer.setCheckingAccountBalance(result.getInt("checking_account_balance"));
				customer.setSavingsAccountBalance(result.getInt("savings_account_balance"));
				list.add(customer);

			}
			return list;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Customer findByUsername(String username) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM registered_customers WHERE username = ?;";

			PreparedStatement statement = conn.prepareStatement(sql);

			// this is where sql injection is checked for
			statement.setString(1, username);

			log.info("findByUsername: " + statement.toString());

			ResultSet result = statement.executeQuery();

			// ResultSets have a cursor similarly to Scanner
			if (result.next()) {

				Customer customer = new Customer();
				customer.setId(result.getInt("registered_customer_id"));
				customer.setUsername(result.getString("username"));
				customer.setPassword(result.getString("pass_word"));
				customer.setName(result.getString("customer_name"));
				customer.setAddress(result.getString("address"));
				customer.setPhoneNumber(result.getLong("phone_number"));
				customer.setCheckingAccountBalance(result.getInt("checking_account_balance"));
				customer.setSavingsAccountBalance(result.getInt("savings_account_balance"));

				return customer;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Customer findById(int id) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM registered_customers WHERE registered_customer_id = ?;";

			PreparedStatement statement = conn.prepareStatement(sql);

			// this is where sql injection is checked for
			statement.setInt(1, id);

			log.info("findById: " + statement.toString());

			ResultSet result = statement.executeQuery();

			// ResultSets have a cursor similarly to Scanner
			if (result.next()) {

				Customer customer = new Customer();
				customer.setId(result.getInt("registered_customer_id"));
				customer.setUsername(result.getString("username"));
				customer.setPassword(result.getString("pass_word"));
				customer.setName(result.getString("customer_name"));
				customer.setAddress(result.getString("address"));
				customer.setPhoneNumber(result.getLong("phone_number"));
				customer.setCheckingAccountBalance(result.getInt("checking_account_balance"));
				customer.setSavingsAccountBalance(result.getInt("savings_account_balance"));

				return customer;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int updateCustomer(Customer customer) {
		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"UPDATE registered_customers SET checking_account_balance=?, savings_account_balance=? WHERE registered_customer_id=?")) {
			statement.setInt(1, customer.getCheckingAccountBalance());
			statement.setInt(2, customer.getSavingsAccountBalance());
			statement.setInt(3, customer.getId());

			log.info("updateCustomer: " + statement.toString());

			return statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public boolean addCustomer(Customer customer) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO registered_customers (username, pass_word, customer_name, address, phone_number, checking_account_balance, savings_account_balance)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?);";

			PreparedStatement statement = conn.prepareStatement(sql);

			int index = 0;
			statement.setString(++index, customer.getUsername());
			statement.setString(++index, customer.getPassword());
			statement.setString(++index, customer.getName());
			statement.setString(++index, customer.getAddress());
			statement.setLong(++index, customer.getPhoneNumber());
			statement.setInt(++index, customer.getCheckingAccountBalance());
			statement.setInt(++index, customer.getSavingsAccountBalance());

			log.info("addCustomer: " + statement.toString());

			statement.execute();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteCustomer(int id) {
		boolean rowDeleted = false;
		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement statement = connection
						.prepareStatement("DELETE FROM registered_customers where registered_customer_id = ?;")) {
			statement.setInt(1, id);

			log.info("deleteCustomer: " + statement.toString());

			rowDeleted = statement.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowDeleted;
	}

}