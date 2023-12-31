package com.encryptedmessenger.menu.impl;

import java.util.Scanner;

import com.encryptedmessenger.configs.ApplicationContext;
import com.encryptedmessenger.enteties.User;
import com.encryptedmessenger.menu.Menu;
import com.encryptedmessenger.services.UserManagementService;
import com.encryptedmessenger.services.impl.DefaultUserManagementService;
import com.encryptedmessenger.services.impl.MySqlUserManagementService;

public class SignInMenu implements Menu {

	private ApplicationContext context;
	private UserManagementService userManagementService;
	private MySqlUserManagementService SqlUserManagementService;
	

	{
		context = ApplicationContext.getInstance();
		userManagementService = DefaultUserManagementService.getInstance();
		SqlUserManagementService = MySqlUserManagementService.getInstance();
	}

	@Override
	public void start() {
		printMenuHeader();
		Scanner sc = new Scanner(System.in);

		System.out.print("Please, enter your phone: ");
		String userPhone = sc.next();

		System.out.print("Please, enter your password: ");
		String userPassword = sc.next();

		User user = SqlUserManagementService.getUserByPhone(userPhone);
		if (user != null && user.getPassword().equals(userPassword)) {
			System.out.printf("Glad to see you back %s", user.getNickName() + System.lineSeparator());
			context.setLoggedInUser(user);
		} else {
			System.out.println("Unfortunately, such login and password doesn't exist");
		}
	}

	@Override
	public void printMenuHeader() {
		System.out.println("***** Sign In *****");		
	}

}