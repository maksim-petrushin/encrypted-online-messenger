package com.encryptedmessenger.services.impl;

import java.util.Arrays;

import com.encryptedmessenger.enteties.User;
import com.encryptedmessenger.services.UserManagementService;


public class DefaultUserManagementService implements UserManagementService {
	
	private static final String NOT_UNIQUE_PHONE_ERROR_MESSAGE = "This phone is already used by another user. Please, use another phone";
	private static final String EMPTY_PHONE_ERROR_MESSAGE = "You have to input phone to register. Please, try one more time";
	private static final String NO_ERROR_MESSAGE = "";
	
	private static final int DEFAULT_USERS_CAPACITY = 10;
	
	private static DefaultUserManagementService instance;
	
	private User[] users;
	private int lastUserIndex=0;
	
	{
		users = new User[DEFAULT_USERS_CAPACITY];
	}

	private DefaultUserManagementService() {
	}
	
	@Override
	public String registerUser(User user) {
		if (user == null) {
			return NO_ERROR_MESSAGE;
		}
		
		String errorMessage = checkUniquePhone(user.getPhone());
		if (errorMessage != null && !errorMessage.isEmpty()) {
			return errorMessage;
		}
		
		if (users.length <= lastUserIndex) {
			users = Arrays.copyOf(users, users.length << 1);
		}
		
		users[lastUserIndex++] = user;
		return NO_ERROR_MESSAGE;
	}

	private String checkUniquePhone(String email) {
		if (email == null || email.isEmpty()) {
			return EMPTY_PHONE_ERROR_MESSAGE;
		}
		for (User user : users) {
			if (user != null && 
					user.getPhone() != null &&
					user.getPhone().equalsIgnoreCase(email)) {
				return NOT_UNIQUE_PHONE_ERROR_MESSAGE;
			}
		}
		return NO_ERROR_MESSAGE;
	}

	public static UserManagementService getInstance() {
		if (instance == null) {
			instance = new DefaultUserManagementService();
		}
		return instance;
	}

	
	@Override
	public User[] getUsers() {
		int nonNullUsersAmount = 0;
		for (User user : users) {
			if (user != null) {
				nonNullUsersAmount++;
			}
		}
		
		User[] nonNullUsers = new User[nonNullUsersAmount];
		
		int index = 0;
		for (User user : users) {
			if (user != null) {
				nonNullUsers[index++] = user;
			}
		}
		
		return nonNullUsers;
	}

	@Override
	public User getUserByPhone(String userPhone) {
		for (User user : users) {
			if (user != null && user.getPhone().equalsIgnoreCase(userPhone)) {
				return user;
			}
		}
		return null;
	}
	
	void clearServiceState() {
		lastUserIndex = 0;
		users = new User[DEFAULT_USERS_CAPACITY];
	}
	
	
//	// Stream API version of the method
//	@Override
//	public User[] getUsers() {
//		return Arrays.stream(users)
//				.filter(Objects::nonNull)
//				.toArray(User[]::new);
//	}

}