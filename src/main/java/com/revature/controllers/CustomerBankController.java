package com.revature.controllers;

import java.util.ArrayList;
import java.util.Scanner;

import com.revature.models.Customer;
import com.revature.services.PendingCustomerService;
import com.revature.services.RegisteredCustomerService;

public class CustomerBankController {

	private static Scanner scan = new Scanner(System.in);
	PendingCustomerService pendingCustomerService = new PendingCustomerService();
	RegisteredCustomerService registeredCustomerService = new RegisteredCustomerService();

	public void customerMenu() {
		while (true) {
			try {
				System.out.println("Thank you for choosing our bank. Below are your options.");
				System.out.println("1) Register \n" + "2) Login \n" + "3) Main Menu");

				int selection = scan.nextInt();
				scan.nextLine();

				if (selection == 1) {
					createNewCustomer();
				} else if (selection == 2) {
					customerLogin();
				} else if (selection == 3) {
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

	private void customerLogin() {
		ArrayList<Customer> registeredCustomerList = registeredCustomerService.getAllRegisteredCustomers();
		Customer currentCustomer = null;

		try {
			System.out.println("Login to continue");
			System.out.print("Enter Username: ");
			String username = scan.nextLine();
			System.out.print("Enter Password: ");
			String password = scan.nextLine();

			for (Customer customer : registeredCustomerList) {
				if (customer.getUsername().equals(username) && customer.getPassword().equals(password)) {
					currentCustomer = customer;
					break;
				}
			}

			// If Customer found it takes forward else returns back to customer main menu
			if (currentCustomer == null) {
				System.out.println("Invalid username or password!");
				return;
			}

		} catch (Exception e) {
			System.out.println("Invalid Choice!");
		}

		System.out.println("\nLogin Successful, Welcome " + currentCustomer.getUsername());
		customerMenu(currentCustomer);
	}

	public void customerMenu(Customer currentCustomer) {
		while (true) {
			try {
				System.out.println("Customer Menu\n" + "1) Deposit Into Checking Account \n"
						+ "2) Deposit Into Savings Account \n" + "3) Withdraw from Checking Account \n"
						+ "4) Withdraw from Savings Account" + "\n5) Transfer from Savings to Checking Account \n"
						+ "6) Transfer from Checking to Savings Account\n" + "7) Log out\n");
				int option = scan.nextInt();
				scan.nextLine();

				if (option == 1) {
					while (true) {
						try {
							System.out.print("Enter Deposit Amount: ");
							int amount = scan.nextInt();
							scan.nextLine();
							if (amount <= 0) {
								System.out.println("Must be positive value");
							} else {
								currentCustomer.setCheckingAccountBalance(
										currentCustomer.getCheckingAccountBalance() + amount);

								registeredCustomerService.updateRegisteredCustomer(currentCustomer);

								System.out.println("Deposit Successful. Your current Checking Account Balance is now "
										+ currentCustomer.getCheckingAccountBalance());
								break;
							}
						} catch (Exception e) {
							System.out.println("Invalid Input!");
							scan.nextLine();
						}
					}
				} else if (option == 2) {
					while (true) {
						try {
							System.out.print("Enter Deposit Amount: ");
							int amount = scan.nextInt();
							scan.nextLine();
							if (amount <= 0) {
								System.out.println("Must be positive value");
							} else {
								currentCustomer
										.setSavingsAccountBalance(currentCustomer.getSavingsAccountBalance() + amount);

								registeredCustomerService.updateRegisteredCustomer(currentCustomer);

								System.out.println("Deposit Successful. Your current Checking Account Balance is now "
										+ currentCustomer.getSavingsAccountBalance());
								break;
							}
						} catch (Exception e) {
							System.out.println("Invalid Input!");
							scan.nextLine();
						}
					}
				}

				else if (option == 3) {
					while (true) {
						try {
							System.out.print("Enter Withdraw Amount: ");
							int amount = scan.nextInt();
							scan.nextLine();
							if (amount <= 0) {
								System.out.println("Must be positive value");
							} else if (amount > currentCustomer.getCheckingAccountBalance()) {
								System.out.println("Insufficient Balance, Current Balance: "
										+ currentCustomer.getCheckingAccountBalance());
							} else {
								currentCustomer.setCheckingAccountBalance(
										currentCustomer.getCheckingAccountBalance() - amount);

								registeredCustomerService.updateRegisteredCustomer(currentCustomer);

								System.out
										.println("Withdrawal Successful. Your current Checking Account balance is now "
												+ currentCustomer.getCheckingAccountBalance());

								break;
							}
						} catch (Exception e) {
							System.out.println("Invalid Input!");
							scan.nextLine();
						}
					}
				} else if (option == 4) {
					while (true) {
						try {
							System.out.print("Enter Withdraw Amount: ");
							int amount = scan.nextInt();
							scan.nextLine();
							if (amount <= 0) {
								System.out.println("Must be positive value");
							} else if (amount > currentCustomer.getSavingsAccountBalance()) {
								System.out.println("Insufficient Balance, Current Balance: "
										+ currentCustomer.getSavingsAccountBalance());
							} else {
								currentCustomer
										.setSavingsAccountBalance(currentCustomer.getSavingsAccountBalance() - amount);

								registeredCustomerService.updateRegisteredCustomer(currentCustomer);

								System.out.println("Withdrawal Successful. Your current Savings Account balance is now "
										+ currentCustomer.getCheckingAccountBalance());
								break;
							}
						} catch (Exception e) {
							System.out.println("Invalid Input!");
							scan.nextLine();
						}
					}
				} else if (option == 5) {
					while (true) {
						try {
							System.out.print("Enter Transfer Amount: ");
							int amount = scan.nextInt();
							scan.nextLine();
							if (amount <= 0) {
								System.out.println("Must be positive value");
							} else if (amount > currentCustomer.getSavingsAccountBalance()) {
								System.out.println("Insufficient Balance, Current Savings Account Balance: "
										+ currentCustomer.getSavingsAccountBalance());
							} else {
								currentCustomer.setCheckingAccountBalance(
										currentCustomer.getCheckingAccountBalance() + amount);
								currentCustomer
										.setSavingsAccountBalance(currentCustomer.getSavingsAccountBalance() - amount);

								registeredCustomerService.updateRegisteredCustomer(currentCustomer);

								System.out.println("Transfer Successful. Your current Checking Account balance is "
										+ currentCustomer.getCheckingAccountBalance());
								System.out.println("You now have " + currentCustomer.getSavingsAccountBalance()
										+ " in your Savings Account.");
								break;
							}
						} catch (Exception e) {
							System.out.println("Invalid Input!");
							scan.nextLine();
						}
					}
				} else if (option == 6) {
					while (true) {
						try {
							System.out.print("Enter Transfer Amount: ");
							int amount = scan.nextInt();
							scan.nextLine();
							if (amount <= 0) {
								System.out.println("Must be positive value");
							} else if (amount > currentCustomer.getCheckingAccountBalance()) {
								System.out.println("Insufficient Balance, Current Checking Account Balance: "
										+ currentCustomer.getCheckingAccountBalance());
							} else {
								currentCustomer
										.setSavingsAccountBalance(currentCustomer.getSavingsAccountBalance() + amount);
								currentCustomer.setCheckingAccountBalance(
										currentCustomer.getCheckingAccountBalance() - amount);

								registeredCustomerService.updateRegisteredCustomer(currentCustomer);

								System.out.println("Transfer Successful. Your current Savings Account balance is "
										+ currentCustomer.getSavingsAccountBalance());
								System.out.println("You now have " + currentCustomer.getCheckingAccountBalance()
										+ " in your Checking Account.");
								break;
							}
						} catch (Exception e) {
							System.out.println("Invalid Input!");
							scan.nextLine();
						}
					}
				} else if (option == 7) {
					System.out.println("Logging out from " + currentCustomer.getUsername());
					return;
				}

			} catch (Exception e) {
				System.out.println("Invalid Choice!");
				scan.nextLine();
			}

		}
	}

	public void customerMenuForAdmin(Customer currentCustomer) {
		while (true) {
			try {
				System.out.println("Customer Menu\n" + "1) Deposit Into Checking Account \n"
						+ "2) Deposit Into Savings Account \n" + "3) Withdraw from Checking Account \n"
						+ "4) Withdraw from Savings Account" + "\n5) Transfer from Savings to Checking Account \n"
						+ "6) Transfer from Checking to Savings Account\n" + "7) Delete Account \n"
						+ "8) Back to Main Menu");

				int option = scan.nextInt();
				scan.nextLine();

				if (option == 1) {
					while (true) {
						try {
							System.out.print("Enter Deposit Amount: ");
							int amount = scan.nextInt();
							scan.nextLine();
							if (amount <= 0) {
								System.out.println("Must be positive value");
							} else {
								currentCustomer.setCheckingAccountBalance(
										currentCustomer.getCheckingAccountBalance() + amount);

								registeredCustomerService.updateRegisteredCustomer(currentCustomer);

								System.out.println("Deposit Successful. Your current Checking Account Balance is now "
										+ currentCustomer.getCheckingAccountBalance());
								break;
							}
						} catch (Exception e) {
							System.out.println("Invalid Input!");
							scan.nextLine();
						}
					}
				} else if (option == 2) {
					while (true) {
						try {
							System.out.print("Enter Deposit Amount: ");
							int amount = scan.nextInt();
							scan.nextLine();
							if (amount <= 0) {
								System.out.println("Must be positive value");
							} else {
								currentCustomer
										.setSavingsAccountBalance(currentCustomer.getSavingsAccountBalance() + amount);

								registeredCustomerService.updateRegisteredCustomer(currentCustomer);

								System.out.println("Deposit Successful. Your current Checking Account Balance is now "
										+ currentCustomer.getSavingsAccountBalance());
								break;
							}
						} catch (Exception e) {
							System.out.println("Invalid Input!");
							scan.nextLine();
						}
					}
				}

				else if (option == 3) {
					while (true) {
						try {
							System.out.print("Enter Withdraw Amount: ");
							int amount = scan.nextInt();
							scan.nextLine();
							if (amount <= 0) {
								System.out.println("Must be positive value");
							} else if (amount > currentCustomer.getCheckingAccountBalance()) {
								System.out.println("Insufficient Balance, Current Balance: "
										+ currentCustomer.getCheckingAccountBalance());
							} else {
								currentCustomer.setCheckingAccountBalance(
										currentCustomer.getCheckingAccountBalance() - amount);

								registeredCustomerService.updateRegisteredCustomer(currentCustomer);

								System.out
										.println("Withdrawal Successful. Your current Checking Account balance is now "
												+ currentCustomer.getCheckingAccountBalance());

								break;
							}
						} catch (Exception e) {
							System.out.println("Invalid Input!");
							scan.nextLine();
						}
					}
				} else if (option == 4) {
					while (true) {
						try {
							System.out.print("Enter Withdraw Amount: ");
							int amount = scan.nextInt();
							scan.nextLine();
							if (amount <= 0) {
								System.out.println("Must be positive value");
							} else if (amount > currentCustomer.getSavingsAccountBalance()) {
								System.out.println("Insufficient Balance, Current Balance: "
										+ currentCustomer.getSavingsAccountBalance());
							} else {
								currentCustomer
										.setSavingsAccountBalance(currentCustomer.getSavingsAccountBalance() - amount);

								registeredCustomerService.updateRegisteredCustomer(currentCustomer);

								System.out.println("Withdrawal Successful. Your current Savings Account balance is now "
										+ currentCustomer.getCheckingAccountBalance());
								break;
							}
						} catch (Exception e) {
							System.out.println("Invalid Input!");
							scan.nextLine();
						}
					}
				} else if (option == 5) {
					while (true) {
						try {
							System.out.print("Enter Transfer Amount: ");
							int amount = scan.nextInt();
							scan.nextLine();
							if (amount <= 0) {
								System.out.println("Must be positive value");
							} else if (amount > currentCustomer.getSavingsAccountBalance()) {
								System.out.println("Insufficient Balance, Current Savings Account Balance: "
										+ currentCustomer.getSavingsAccountBalance());
							} else {
								currentCustomer.setCheckingAccountBalance(
										currentCustomer.getCheckingAccountBalance() + amount);
								currentCustomer
										.setSavingsAccountBalance(currentCustomer.getSavingsAccountBalance() - amount);

								registeredCustomerService.updateRegisteredCustomer(currentCustomer);

								System.out.println("Transfer Successful. Your current Checking Account balance is "
										+ currentCustomer.getCheckingAccountBalance());
								System.out.println("You now have " + currentCustomer.getSavingsAccountBalance()
										+ " in your Savings Account.");
								break;
							}
						} catch (Exception e) {
							System.out.println("Invalid Input!");
							scan.nextLine();
						}
					}
				} else if (option == 6) {
					while (true) {
						try {
							System.out.print("Enter Transfer Amount: ");
							int amount = scan.nextInt();
							scan.nextLine();
							if (amount <= 0) {
								System.out.println("Must be positive value");
							} else if (amount > currentCustomer.getCheckingAccountBalance()) {
								System.out.println("Insufficient Balance, Current Checking Account Balance: "
										+ currentCustomer.getCheckingAccountBalance());
							} else {
								currentCustomer
										.setSavingsAccountBalance(currentCustomer.getSavingsAccountBalance() + amount);
								currentCustomer.setCheckingAccountBalance(
										currentCustomer.getCheckingAccountBalance() - amount);

								registeredCustomerService.updateRegisteredCustomer(currentCustomer);

								System.out.println("Transfer Successful. Your current Savings Account balance is "
										+ currentCustomer.getSavingsAccountBalance());
								System.out.println("You now have " + currentCustomer.getCheckingAccountBalance()
										+ " in your Checking Account.");
								break;
							}
						} catch (Exception e) {
							System.out.println("Invalid Input!");
							scan.nextLine();
						}
					}
				} else if (option == 7) {

					registeredCustomerService.deleteAccount(currentCustomer);
					System.out.println(currentCustomer.getUsername() + " Account Deleted, Going back to main menu");

					return;
				} else if (option == 8) {
					System.out.println("Logging out from " + currentCustomer.getUsername());
					return;
				}

			} catch (Exception e) {
				System.out.println("Invalid Choice!");
				scan.nextLine();
			}

		}
	}

	private void createNewCustomer() {
		Customer customer = null;
		System.out.println("Please create a username:");
		String username = scan.nextLine();
		System.out.println("Please create a password:");
		String password = scan.nextLine();
		System.out.println("Please enter your name:");
		String name = scan.nextLine();
		System.out.println("Please enter your address:");
		String address = scan.nextLine();
		System.out.println("Please enter your phone number:");
		long phoneNumber = scan.nextLong();
		System.out.println("How much would you like to add to your checking account?");
		int checkingAccountBalance = scan.nextInt();
		System.out.println("How much would you like to add to your savings account?");
		int savingsAccountBalance = scan.nextInt();
		scan.nextLine();

		customer = pendingCustomerService.createNewCustomer(username, password, name, address, phoneNumber,
				checkingAccountBalance, savingsAccountBalance);

		pendingCustomerService.save(customer);
	}

}