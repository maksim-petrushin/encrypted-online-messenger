package com.encryptedmessenger.menu.impl;

import java.util.Scanner;

import com.encryptedmessenger.Main;
import com.encryptedmessenger.configs.ApplicationContext;
import com.encryptedmessenger.enteties.User;
import com.encryptedmessenger.menu.Menu;
import com.encryptedmessenger.services.FriendshipManagementService;
import com.encryptedmessenger.services.UserManagementService;
import com.encryptedmessenger.services.impl.DefaultFriendshipManagementService;
import com.encryptedmessenger.services.impl.DefaultUserManagementService;
import com.encryptedmessenger.services.impl.MySqlFriendshipManagementService;
import com.encryptedmessenger.services.impl.MySqlUserManagementService;

public class ManageFriendsMenu implements Menu {

	private ApplicationContext context;
	private FriendshipManagementService friendshipManagementService;
	private UserManagementService userManagementService;
	
	private MySqlUserManagementService SqlUserManagementService;
	private MySqlFriendshipManagementService SqlFriendshipManagementService;

	{
		context = ApplicationContext.getInstance();
		friendshipManagementService = DefaultFriendshipManagementService.getInstance();
		userManagementService = DefaultUserManagementService.getInstance();
		
		SqlUserManagementService = MySqlUserManagementService.getInstance();
		SqlFriendshipManagementService = MySqlFriendshipManagementService.getInstance();

	}

	/*
	
	*/
	@Override
	public void start() {
		User currentUser = context.getLoggedInUser();

		if (currentUser == null) {
			System.out.println("You are not logged in. Please, sign in or create new account");
			return;
		}

		printMenuHeader();
		String userInput = readUserInput();
		if (userInput.equalsIgnoreCase(MainMenu.MENU_COMMAND)) {
			return;
		}
		if (userInput.equalsIgnoreCase(Main.EXIT_COMMAND)) {
			System.exit(0);
		} else {
			int commandNumber = Integer.parseInt(userInput);
			switch (commandNumber) {
			case 1:
				(new MyChatsMenu()).start();// List menu
				return;
			case 2:
				System.out.println("***** ADDING A FRIEND *****");
				System.out.println("Enter the users phone number: ");
				Scanner sc = new Scanner(System.in);

				String userPhone = sc.next();

				User newFriend = SqlUserManagementService.getUserByPhone(userPhone);
				if (newFriend != null) {
					if (!SqlFriendshipManagementService.doesFriendshipExist(newFriend, currentUser)) {
						SqlFriendshipManagementService.createFriendship(currentUser, newFriend);
						System.out.println(
								"User " + newFriend.getNickName() + " was successfully added to your friends.");
					} else {
						System.out.println("This user is already your friend");
					}
				} else {
					System.out.println("Unfortunately, such user doesn't exist");
				}
				return;
			default:
				System.out.println("Only 1, 2 is allowed.");
				return;
			}
		}
	}

	private String readUserInput() {
		Scanner sc = new Scanner(System.in);
		String userInput = sc.next();
		return userInput;
	}

	@Override
	public void printMenuHeader() {
		System.out.println("***** MANAGE FRIENDS MENU *****");
		System.out.println("Please, enter number in console to proceed." + System.lineSeparator()
				+ "1. View Friends List" + System.lineSeparator() + "2. Add Friend (By phone number)");
	}

}
