package com.encryptedmessenger.dao;

import java.util.List;

import com.encryptedmessenger.dto.FriendshipDto;
import com.encryptedmessenger.dto.UserDto;

public interface FriendshipDao {
	public FriendshipDto getFriendshipById(int id);
	
	public boolean areFriends(UserDto first, UserDto second);
	public boolean saveFriendship(FriendshipDto friendship);
	public List<FriendshipDto> getFriendshipsByUser(UserDto user);
	public List<UserDto> getFriendsByUser(UserDto user);
}
