package com.encryptedmessenger.configs;

import com.encryptedmessenger.enteties.User;
import com.encryptedmessenger.menu.Menu;

public class ApplicationContext {
	
	private static ApplicationContext instance;
	
	private User loggedInUser;
	private Menu mainMenu;
	private User selectedChat;
	
	private ApplicationContext() {
	}
	
	public void setLoggedInUser(User user) {
		this.loggedInUser = user;
	}
	
	public User getLoggedInUser() {
		return this.loggedInUser;
	}
	
	public void setSelectedChat(User user) {
		this.selectedChat = user;
	}
	
	public User getSelectedChat() {
		return this.selectedChat;
	}
	
	public void setMainMenu(Menu menu) {
		this.mainMenu = menu;
	}
	
	public Menu getMainMenu() {
		return this.mainMenu;
	}

	public static ApplicationContext getInstance() {
		if (instance == null) {
			instance = new ApplicationContext();
		}
		return instance;
	}
	
	//get user chats or so..

}
