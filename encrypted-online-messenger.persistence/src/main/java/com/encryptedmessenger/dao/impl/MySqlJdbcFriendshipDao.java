package com.encryptedmessenger.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.encryptedmessenger.dao.FriendshipDao;
import com.encryptedmessenger.dto.FriendshipDto;
import com.encryptedmessenger.dto.UserDto;
import com.encryptedmessenger.utils.db.DBUtils;

public class MySqlJdbcFriendshipDao implements FriendshipDao {

	private static MySqlJdbcFriendshipDao instance;
	private MySqlJdbcUserDao userDao;

	public static MySqlJdbcFriendshipDao getInstance() {
		if (instance == null) {
			instance = new MySqlJdbcFriendshipDao();
		}
		return instance;
	}

	@Override
	public FriendshipDto getFriendshipById(int id) {

		try (var conn = DBUtils.getConnection();
				var ps = conn.prepareStatement("SELECT * FROM friendship WHERE id = ?")) {

			ps.setInt(1, id);
			try (var rs = ps.executeQuery()) {
				if (rs.next()) {
					FriendshipDto friendship = new FriendshipDto();
					friendship.setId(rs.getInt("id"));
					friendship.setFirstFriend(userDao.getInstance().getUserById(rs.getInt("first_friend")));
					friendship.setSecondFriend(userDao.getInstance().getUserById(rs.getInt("second_friend")));
					return friendship;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public boolean areFriends(UserDto first, UserDto second) {
		try (var conn = DBUtils.getConnection();
				var ps = conn.prepareStatement("SELECT * FROM friendship WHERE (first_friend = ? AND second_friend = ?) OR (first_friend = ? AND second_friend = ?)")) {
				ps.setInt(1, first.getId());
				ps.setInt(2, second.getId());
				ps.setInt(3, second.getId());
				ps.setInt(4, first.getId());

				try (var rs = ps.executeQuery()) {
					if (rs.next()) {
						return true;
					}
					return false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
	}

	@Override
	public boolean saveFriendship(FriendshipDto friendship) {
		try (var conn = DBUtils.getConnection();
				var ps = conn.prepareStatement("INSERT INTO friendship (first_friend, second_friend) VALUES (?, ?);")) {
			ps.setInt(1, friendship.getFirstFriend().getId());
			ps.setInt(2, friendship.getSecondFriend().getId());

			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public List<FriendshipDto> getFriendshipsByUser(UserDto user) {
		try (var conn = DBUtils.getConnection();
			var ps = conn.prepareStatement("SELECT * FROM friendship WHERE first_friend = ? OR second_friend = ?")) {
			ps.setInt(1, user.getId());
			ps.setInt(2, user.getId());

			try (var rs = ps.executeQuery()) {
				List<FriendshipDto> friendships = new ArrayList<>();

				while (rs.next()) {
					FriendshipDto friendship = new FriendshipDto();
					friendship.setId(rs.getInt("id"));
					friendship.setFirstFriend(userDao.getInstance().getUserById(rs.getInt("first_friend")));
					friendship.setSecondFriend(userDao.getInstance().getUserById(rs.getInt("second_friend")));
					friendships.add(friendship);
				}

				return friendships;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<UserDto> getFriendsByUser(UserDto user) {
		try (var conn = DBUtils.getConnection();
			var ps = conn.prepareStatement("SELECT * FROM friendship WHERE first_friend = ? OR second_friend = ?")) {
			ps.setInt(1, user.getId());
			ps.setInt(2, user.getId());

			try (var rs = ps.executeQuery()) {
				List<UserDto> users = new ArrayList<>();

				while (rs.next()) {
					UserDto friend = new UserDto();
					int one = rs.getInt("first_friend");
					int two = rs.getInt("second_friend");
					int him;
					UserDto curr;
					if(user.getId() == one) {
						him = two;
					}
					else {
						him = one;
					}
					curr = userDao.getInstance().getUserById(him);
					friend.setId(him);
					friend.setNickName(curr.getNickName());
					friend.setPhone(curr.getPhone());
					friend.setPassword(curr.getPassword());
					users.add(friend);
				}

				return users;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
