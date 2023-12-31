package com.encryptedmessenger.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.encryptedmessenger.dao.UserDao;
import com.encryptedmessenger.dto.UserDto;
import com.encryptedmessenger.utils.db.DBUtils;

public class MySqlJdbcUserDao implements UserDao {
	
	private static MySqlJdbcUserDao instance;
	
	public static MySqlJdbcUserDao getInstance() {
		if(instance == null) {
			instance = new MySqlJdbcUserDao();
		}
		return instance;
	}
	@Override
	public UserDto getUserById(int id) {
		try (var conn = DBUtils.getConnection(); 
				var ps = conn.prepareStatement("SELECT * FROM user WHERE id = ?")) {

			ps.setInt(1, id);
			try (var rs = ps.executeQuery()) {
				if (rs.next()) {
					UserDto user = new UserDto();
					user.setId(rs.getInt("id"));
					user.setNickName(rs.getString("nick_name"));
					user.setPhone(rs.getString("phone"));
					user.setPassword(rs.getString("password"));
					return user;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public UserDto getUserByPhone(String phone) {
		try (var conn = DBUtils.getConnection();
			var ps = conn.prepareStatement("SELECT * FROM user WHERE phone = ?")) {
			
			ps.setString(1, phone);
			try (var rs = ps.executeQuery()) {
				if (rs.next()) {
					UserDto user = new UserDto();
					user.setId(rs.getInt("id"));
					user.setNickName(rs.getString("nick_name"));
					user.setPhone(rs.getString("phone"));
					user.setPassword(rs.getString("password"));
					return user;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public boolean saveUser(UserDto user) {
		try (var conn = DBUtils.getConnection();
			var ps = conn.prepareStatement("INSERT INTO user (nick_name, phone, password) VALUES (?, ?, ?);")) {
			ps.setString(1, user.getNickName());
			ps.setString(2, user.getPhone());
			ps.setString(3, user.getPassword());
			
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public List<UserDto> getUsers() {
		try (var conn = DBUtils.getConnection();
				var ps = conn.prepareStatement("SELECT * FROM user");
				var rs = ps.executeQuery()) {
			List<UserDto> users = new ArrayList<>();
			
			while (rs.next()) {
				UserDto user = new UserDto();
				user.setId(rs.getInt("id"));
				user.setNickName(rs.getString("nick_name"));
				user.setPhone(rs.getString("phone"));
				user.setPassword(rs.getString("password"));
				users.add(user);
			}
			
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
