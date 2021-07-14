package com.revature.daos;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.models.BankAdmin;

public class BankAdminDAO {

	private static Logger log = LoggerFactory.getLogger(BankAdminDAO.class);

	public ArrayList<BankAdmin> fetchAllBankAdmins() {
		ArrayList<BankAdmin> allBankAdmins = new ArrayList<>();
		try (Scanner scan = new Scanner(new File("src\\main\\resources\\bankAdmins.txt"))) {
			while (scan.hasNextLine()) {
				String bankAdminString = scan.nextLine();
				String[] bankAdminStringParts = bankAdminString.split(",");
				allBankAdmins.add(new BankAdmin(Integer.valueOf(bankAdminStringParts[0]), bankAdminStringParts[1]));
			}
		} catch (IOException e) {
			log.error("Something went wrong retrieving bank admins: " + e.getMessage());
		}
		return allBankAdmins;
	}

}