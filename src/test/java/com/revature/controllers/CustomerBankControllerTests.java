package com.revature.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustomerBankControllerTests {
	
	public static CustomerBankController customerBankController;
	public static int checkingAccount;
	public static int savingsAccount;
	public static int withdrawAmount;
	public static int depositAmount;
	public static int withdrawFromCheckingResult;
	public static int depositIntoSavingsResult;
	
	@BeforeAll
	public static void setCustomerBankController() {
	customerBankController = new CustomerBankController();
	
	}
	
	@BeforeEach
	public void setInts() {
		checkingAccount = 50;
		savingsAccount = 75;
		withdrawAmount = 20;
		depositAmount = 70;
		withdrawFromCheckingResult = 30;
		depositIntoSavingsResult = 145;
	
		
		
	}
	
	@Test
	public void testWithdrawFromChecking() {
		withdrawFromCheckingResult = checkingAccount - withdrawAmount;
		assertEquals(withdrawFromCheckingResult, 30);
	}
		
		
	@Test
	public void testDepositIntoSavings() {
		depositIntoSavingsResult = depositAmount + savingsAccount;
		assertEquals(depositIntoSavingsResult, 145);
		
	
	}
}