package com.encryptedmessenger.menu.impl;

import java.util.Scanner;

import com.encryptedmessenger.Main;
import com.encryptedmessenger.configs.ApplicationContext;
import com.encryptedmessenger.menu.Menu;
import com.encryptedmessenger.services.MessageManagementService;
import com.encryptedmessenger.services.impl.DefaultMessageManagementService;
import com.encryptedmessenger.services.impl.MySqlMessageManagementService;

public class MessageDraftMenu implements Menu{
	private ApplicationContext context;
	private MessageManagementService messageManagementService;
	
	private MySqlMessageManagementService SqlMessageManagementService;

	{
		context = ApplicationContext.getInstance();
		messageManagementService = DefaultMessageManagementService.getInstance();
		
		SqlMessageManagementService = MySqlMessageManagementService.getInstance();
	}

	@Override
	public void start() {
		printMenuHeader();
		Scanner sc = new Scanner(System.in);

		System.out.print("Message: ");
		String message = sc.nextLine();
		System.out.println("Please, enter an option." + System.lineSeparator() + "1. Send the message"
				+ System.lineSeparator() + "2. Discard the message");
		String option = sc.next();
		if (option.equalsIgnoreCase(Main.EXIT_COMMAND)) {
			System.exit(0);
		} else {
			int commandNumber = Integer.parseInt(option);
			switch (commandNumber) {

			case 1:
				SqlMessageManagementService.sendMessage(context.getLoggedInUser(), context.getSelectedChat(), message);
				break;
			case 2:
				return;
			default:
				System.out.println("Only 1, 2 is allowed. Try one more time");
				break;
			}
		}
	}
	
	@Override
	public void printMenuHeader() {
		System.out.println("***** DRAFT A MESSAGE *****");
	}
}
