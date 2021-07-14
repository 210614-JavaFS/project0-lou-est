package com.revature.daos;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.models.Employee;

public class EmployeeDAO {

	private static Logger log = LoggerFactory.getLogger(EmployeeDAO.class);

	public ArrayList<Employee> fetchAllEmployees() {
		ArrayList<Employee> allEmployees = new ArrayList<>();
		try (Scanner scan = new Scanner(new File("src\\main\\resources\\employeeLogin.txt"))) {
			while (scan.hasNextLine()) {
				String employeeString = scan.nextLine();
				String[] employeeStringParts = employeeString.split(",");
				allEmployees.add(new Employee(Integer.valueOf(employeeStringParts[0]), employeeStringParts[1]));
			}
		} catch (IOException e) {
			log.error("Something went wrong retrieving employees: " + e.getMessage());
		}
		return allEmployees;
	}

}