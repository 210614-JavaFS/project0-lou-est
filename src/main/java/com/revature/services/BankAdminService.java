package com.revature.services;

import java.util.ArrayList;

import com.revature.daos.BankAdminDAO;
import com.revature.models.BankAdmin;

public class BankAdminService {

	BankAdminDAO bankAdminDAO = new BankAdminDAO();

	public ArrayList<BankAdmin> getAllBankAdmins() {
		return bankAdminDAO.fetchAllBankAdmins();
	}

	public boolean isLogin(int employeeIdentificationNumber) {
		ArrayList<BankAdmin> allBankAdmins = getAllBankAdmins();
		for (BankAdmin ba : allBankAdmins) {
			if (employeeIdentificationNumber == ba.getIdNumber())
				return true;
		}
		return false;
	}

}