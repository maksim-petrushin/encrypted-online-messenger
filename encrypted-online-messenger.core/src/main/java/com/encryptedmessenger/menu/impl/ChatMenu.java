package com.encryptedmessenger.menu.impl;

import java.util.Scanner;

import com.encryptedmessenger.Main;
import com.encryptedmessenger.configs.ApplicationContext;
import com.encryptedmessenger.enteties.Message;
import com.encryptedmessenger.enteties.User;
import com.encryptedmessenger.menu.Menu;
import com.encryptedmessenger.services.MessageManagementService;
import com.encryptedmessenger.services.impl.DefaultMessageManagementService;
import com.encryptedmessenger.services.impl.MySqlMessageManagementService;

public class ChatMenu implements Menu {
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
		while (true) {
			printMenuHeader();
			Scanner sc = new Scanner(System.in);

			System.out.print("User input: ");
			String userInput = sc.next();
			if (userInput.equalsIgnoreCase(Main.EXIT_COMMAND)) {
				System.exit(0);
			} else {
				int commandNumber = Integer.parseInt(userInput);
				switch (commandNumber) {

				case 1:
					(new MessageDraftMenu()).start();
					break;
				case 2:
					return;
				default:
					System.out.println("Only 1, 2 is allowed. Try one more time");
					continue; // continue endless loop
				}
			}
		}
	}

	@Override
	public void printMenuHeader() {

		User logged = context.getLoggedInUser();
		User friend = context.getSelectedChat();
		System.out.println("***** CHAT WITH " + friend.getNickName() + " *****");
		System.out.println("**********************************************");
		Message[] lastTen = SqlMessageManagementService.getMessagesBetween(logged, friend);
		int L = lastTen.length;
		if (L >10) {
			L=10;
		}
		for (int i = 0; i  < L; i++) {
			Message msg = lastTen[i];
			if (msg != null) {
				User sender = msg.getSender();
				System.out.print("("+msg.getTime()+") ");
				if (sender.getId() == logged.getId()) {
					System.out.println("me: " + msg.getBody());
				} else {
					System.out.println(friend.getNickName() + ": " + msg.getBody());
				}
			}
		}
		System.out.println("**********************************************");
		System.out.println("Please, enter an option." + System.lineSeparator() + "1. Write a text"
				+ System.lineSeparator() + "2. Return");

	}

}
