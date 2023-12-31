package com.encryptedmessenger.services;

import com.encryptedmessenger.enteties.User;
import com.encryptedmessenger.enteties.Friendship;

public interface FriendshipManagementService {

	public String createFriendship(User firstUser, User secondUser);
	public boolean doesFriendshipExist(User firstUser, User secondUser);
	public User[] getFriendsByUser(User user);
}
