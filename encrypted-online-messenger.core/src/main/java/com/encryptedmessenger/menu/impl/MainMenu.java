package com.encryptedmessenger.menu.impl;

import com.encryptedmessenger.Main;
import com.encryptedmessenger.configs.ApplicationContext;
import com.encryptedmessenger.menu.Menu;
import com.encryptedmessenger.utils.db.DBUtils;

import java.sql.SQLException;
import java.util.Scanner;

public class MainMenu implements Menu {

	public static final String MENU_COMMAND = "menu";

	private static final String MAIN_MENU_TEXT_FOR_LOGGED_OUT_USER = "Please, enter number in console to proceed."
			+ System.lineSeparator() + "1. Sign Up" + System.lineSeparator() + "2. Sign In" + System.lineSeparator()
			+ "3. Manage Friends" + System.lineSeparator() + "4. Settings";

	private static final String MAIN_MENU_TEXT_FOR_LOGGED_IN_USER = "Please, enter number in console to proceed."
			+ System.lineSeparator() + "1. Sign Up" + System.lineSeparator() + "2. Sign Out" + System.lineSeparator()
			+ "3. Manage Friends" + System.lineSeparator() + "4. Settings";

	private ApplicationContext context;

	{
		context = ApplicationContext.getInstance();
	}

	private static void db() {
		try (var conn = DBUtils.getConnection()) {
			if(conn != null) {
				System.out.println("You made it, take control your database now!");
			}
			else {
				System.out.println("Failed to make connection!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void start() {
		while (true) {
			db();
			if (context.getMainMenu() == null) {
				context.setMainMenu(this);
			}

			Menu menuToNavigate = null;
			mainLoop: while (true) {
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
						menuToNavigate = new SignUpMenu();
						break mainLoop;
					case 2:
						if (context.getLoggedInUser() == null) {
							menuToNavigate = new SignInMenu();
						} else {
							menuToNavigate = new SignOutMenu();
						}
						break mainLoop;
					case 3:
						menuToNavigate = new ManageFriendsMenu();
						break mainLoop;
					case 4:
						menuToNavigate = new SettingsMenu();
						break mainLoop;
					default:
						System.out.println("Only 1, 2, 3, 4 is allowed. Try one more time");
						continue; // continue endless loop
					}
				}
			}
			menuToNavigate.start();
		}
	}

	@Override
	public void printMenuHeader() {
		System.out.println("***** MAIN MENU *****");
		if (context.getLoggedInUser() == null) {
			System.out.println(MAIN_MENU_TEXT_FOR_LOGGED_OUT_USER);
		} else {
			System.out.println(MAIN_MENU_TEXT_FOR_LOGGED_IN_USER);
		}
	}

}
