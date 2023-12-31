package com.encryptedmessenger.menu.impl;

import java.util.Scanner;

import com.encryptedmessenger.Main;
import com.encryptedmessenger.configs.ApplicationContext;
import com.encryptedmessenger.enteties.User;
import com.encryptedmessenger.menu.Menu;
import com.encryptedmessenger.services.impl.MySqlFriendshipManagementService;

public class MyChatsMenu implements Menu {

	private ApplicationContext context;
	private MySqlFriendshipManagementService SqlFriendshipManagementService;

	{
		context = ApplicationContext.getInstance();
		SqlFriendshipManagementService = MySqlFriendshipManagementService.getInstance();

	}

	@Override
	public void start() {
		User currentUser = context.getLoggedInUser();
		printMenuHeader();

		User[] friends = SqlFriendshipManagementService.getFriendsByUser(currentUser);
		int count = 0;
		for (User friend : friends) {
			System.out.println((count + 1) + ": " + friend.getNickName());
			count++;
		}
		if (count == 0) {
			System.out.println("No friends yet.");
			return;
		}
		System.out.print("Select the chat (number): ");
		
		Scanner sc = new Scanner(System.in);
		String userInput = sc.next();
		if (userInput.equalsIgnoreCase(Main.EXIT_COMMAND)) {
			System.exit(0);
		} else {
			int commandNumber = Integer.parseInt(userInput)-1;
			if (commandNumber >= 0 && commandNumber < count) {
				context.setSelectedChat(friends[commandNumber]);
				(new ChatMenu()).start();
				context.setSelectedChat(null);
				return;

			} else {
				System.out.println(commandNumber + "Is out of bound");
				return;
			}
		}
	}

	@Override
	public void printMenuHeader() {
		System.out.println("***** LISTING FRIENDS *****");
	}
}
