package com.encryptedmessenger.dto;

public class FriendshipDto {
	private Integer id;
	private UserDto firstFriend;
	private UserDto secondFriend;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public UserDto getFirstFriend() {
		return firstFriend;
	}
	public void setFirstFriend(UserDto firstFriend) {
		this.firstFriend = firstFriend;
	}
	public UserDto getSecondFriend() {
		return secondFriend;
	}
	public void setSecondFriend(UserDto secondFriend) {
		this.secondFriend = secondFriend;
	}
	
	
}
