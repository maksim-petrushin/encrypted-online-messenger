package com.encryptedmessenger.services;

import java.util.List;

import com.encryptedmessenger.enteties.User;

public interface FriendshipManagementService {

	public String createFriendship(User firstUser, User secondUser);
	public boolean doesFriendshipExist(User firstUser, User secondUser);
	public User[] getFriendsByUser(User user);
	public List<User> getFriendsListByUser(User user);
}
