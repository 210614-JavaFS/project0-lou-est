package com.revature.services;

import java.util.ArrayList;

import com.revature.daos.EmployeeDAO;
import com.revature.models.Employee;

public class EmployeeService {

	EmployeeDAO employeeDAO = new EmployeeDAO();

	public ArrayList<Employee> getAllEmployees() {
		return employeeDAO.fetchAllEmployees();
	}

	public boolean isLogin(int employeeIdentificationNumber) {
		ArrayList<Employee> allEmployees = getAllEmployees();
		for (Employee e : allEmployees) {
			if (employeeIdentificationNumber == e.getIdNumber())
				return true;
		}
		return false;
	}

}