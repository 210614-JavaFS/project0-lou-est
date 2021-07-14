package com.revature;

import java.util.Scanner;

import com.revature.controllers.BankAdminBankController;
import com.revature.controllers.CustomerBankController;
import com.revature.controllers.EmployeeBankController;

public class Main {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		CustomerBankController customerBankController = new CustomerBankController();
		BankAdminBankController bankAdminController = new BankAdminBankController();
		EmployeeBankController employeeBankController = new EmployeeBankController();

		int selectedOption = 0;
		while (true) {
			try {
				System.out.println("Welcome to The Bank.");
				System.out.println("Please select\n1. Customer Menu\n2. Admin Menu\n3. Employee Menu\n4. Exit");

				selectedOption = scan.nextInt();
				scan.nextLine();

				if (selectedOption == 1) {
					customerBankController.customerMenu();
				} else if (selectedOption == 2) {
					bankAdminController.bankAdminMenu();
				} else if (selectedOption == 3) {
					employeeBankController.employeeMenu();
				} else if (selectedOption == 4) {
					System.out.println("Exiting the system.");
					scan.close();
					return;
				} else {
					System.out.println("Invalid Choice!");
				}

			} catch (Exception e) {
				System.out.println("Invalid Choice!");
				scan.nextLine();
			}
		}

	}

}
