package com.encryptedmessenger.enteties;

public interface Friendship {
	
	public int getFriendshipId();
	public User getFirstFriend();
	public User getSecondFriend();
	public void setFriendshipId(int id);
	public void setSecondFriend(User secondFriend);
	public void setFirstFriend(User firstFriend);
}
