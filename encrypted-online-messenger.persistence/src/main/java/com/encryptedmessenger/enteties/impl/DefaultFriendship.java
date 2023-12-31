package com.encryptedmessenger.enteties.impl;

import com.encryptedmessenger.enteties.Friendship;
import com.encryptedmessenger.enteties.User;

public class DefaultFriendship implements Friendship{
	private static int friendshipCounter = 0;
	private int friendshipId;
	private User firstFriend, secondFriend;
	
	{
		friendshipId = ++friendshipCounter;
	}
	public DefaultFriendship() {
		
	}
	
	public DefaultFriendship(User firstFriend, User secondFriend) {
		this.firstFriend = firstFriend;
		this.secondFriend = secondFriend;
	}
	
	@Override
	public User getFirstFriend() {
		return firstFriend;
	}
	
	@Override
	public User getSecondFriend() {
		return secondFriend;
	}
	
	@Override
	public int getFriendshipId() {
		return friendshipId;
	}
	@Override
	public void setFriendshipId(int id) {
		this.friendshipId = id;
	}
	@Override
	public void setSecondFriend(User secondFriend) {
		this.secondFriend = secondFriend;
	}
	@Override
	public void setFirstFriend(User firstFriend) {
		this.firstFriend = firstFriend;
	}
}
