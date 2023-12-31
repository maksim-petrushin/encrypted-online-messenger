package com.encryptedmessenger.services.impl;

import java.util.List;

import com.encryptedmessenger.dao.impl.MySqlJdbcFriendshipDao;
import com.encryptedmessenger.dao.impl.MySqlJdbcUserDao;
import com.encryptedmessenger.dto.UserDto;
import com.encryptedmessenger.dto.converter.FriendshipDtoToFriendshipConverter;
import com.encryptedmessenger.dto.converter.UserDtoToUserConverter;
import com.encryptedmessenger.enteties.Friendship;
import com.encryptedmessenger.enteties.User;
import com.encryptedmessenger.enteties.impl.DefaultFriendship;
import com.encryptedmessenger.services.FriendshipManagementService;

public class MySqlFriendshipManagementService implements FriendshipManagementService {
	private static MySqlFriendshipManagementService instance;
	public MySqlJdbcUserDao userDao = MySqlJdbcUserDao.getInstance();
	public MySqlJdbcFriendshipDao friendshipDao = MySqlJdbcFriendshipDao.getInstance();

	public UserDtoToUserConverter userConverter = UserDtoToUserConverter.getInstance();
	public FriendshipDtoToFriendshipConverter friendshipConverter = FriendshipDtoToFriendshipConverter.getInstance();

	public static MySqlFriendshipManagementService getInstance() {
		if (instance == null) {
			instance = new MySqlFriendshipManagementService();
		}
		return instance;
	}

	@Override
	public boolean doesFriendshipExist(User firstUser, User secondUser) {
		return friendshipDao.areFriends(userConverter.convertUserToUserDto(firstUser),
				userConverter.convertUserToUserDto(secondUser));
	}

	@Override
	public String createFriendship(User firstUser, User secondUser) {
		if (firstUser == null || secondUser == null) {
			return "Error";
		}

		if (!doesFriendshipExist(firstUser, secondUser)) {
			Friendship friendship = new DefaultFriendship(firstUser, secondUser);

			boolean created = friendshipDao
					.saveFriendship(friendshipConverter.convertFriendshipToFriendshipDto(friendship));
			if (created) {
				return "Friend added";
			} else {
				return "Error";
			}

		}
		return "Already friends";
	}

	@Override
	public User[] getFriendsByUser(User user) {
		List<UserDto> friends = friendshipDao.getFriendsByUser(userConverter.convertUserToUserDto(user));
		List<User> usrs = userConverter.convertUserDtosToUsers(friends);
		User[] result = new User[usrs.size()];
		usrs.toArray(result);
		return result;
		
	}

}
