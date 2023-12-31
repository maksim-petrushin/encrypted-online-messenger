package com.encryptedmessenger.dao;

import java.util.List;

import com.encryptedmessenger.dto.UserDto;

public interface UserDao {
	
	public boolean saveUser(UserDto user);
	
	public List<UserDto> getUsers();

	public UserDto getUserByPhone(String userPhone);

	public UserDto getUserById(int id);

}
