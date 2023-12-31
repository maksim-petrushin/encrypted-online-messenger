package com.encryptedmessenger.menu.impl;

import com.encryptedmessenger.configs.ApplicationContext;
import com.encryptedmessenger.enteties.User;
import com.encryptedmessenger.enteties.impl.DefaultUser;
import com.encryptedmessenger.menu.Menu;
import com.encryptedmessenger.services.UserManagementService;
import com.encryptedmessenger.services.impl.DefaultUserManagementService;
import com.encryptedmessenger.services.impl.MySqlUserManagementService;

import java.util.Scanner;



public class SignUpMenu implements Menu {

	private UserManagementService userManagementService;
	private MySqlUserManagementService SqlUserManagementService;
	private ApplicationContext context;

	{
		SqlUserManagementService = MySqlUserManagementService.getInstance();
		userManagementService = DefaultUserManagementService.getInstance();
		context = ApplicationContext.getInstance();
	}

	@Override
	public void start() {
		printMenuHeader();
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Please, enter your nick-name: ");
		String nickName = sc.next();
		System.out.print("Please, enter your password: ");
		String password = sc.next();
		System.out.print("Please, enter your phone: ");
		sc = new Scanner(System.in);
		String phone = sc.nextLine();
		User user = new DefaultUser(nickName, password, phone);
		
		String errorMessage = SqlUserManagementService.registerUser(user);
		if (errorMessage == null || errorMessage.isEmpty()) {
			context.setLoggedInUser(user);
			System.out.println("New user is created");
		} else {
			System.out.println(errorMessage);
		}
	}

	@Override
	public void printMenuHeader() {
		System.out.println("***** SIGN UP *****");		
	}

}

