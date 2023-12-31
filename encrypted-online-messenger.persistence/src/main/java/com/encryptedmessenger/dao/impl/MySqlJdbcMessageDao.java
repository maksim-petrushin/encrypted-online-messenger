package com.encryptedmessenger.dao.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.encryptedmessenger.dao.MessageDao;
import com.encryptedmessenger.dto.MessageDto;
import com.encryptedmessenger.dto.UserDto;
import com.encryptedmessenger.utils.db.DBUtils;

public class MySqlJdbcMessageDao implements MessageDao{
	private static MySqlJdbcMessageDao instance;
	private MySqlJdbcUserDao userDao;

	public static MySqlJdbcMessageDao getInstance() {
		if (instance == null) {
			instance = new MySqlJdbcMessageDao();
		}
		return instance;
	}
	@Override
	public boolean saveMessage(MessageDto message) {
		try (var conn = DBUtils.getConnection();
			var ps = conn.prepareStatement("INSERT INTO message (message_body, time_stamp, sender, receiver) VALUES (?, ?, ?, ?);")) {
			ps.setString(1, message.getMessageBody());
			ps.setLong(2, message.getTimeStamp());
			ps.setInt(3, message.getSender().getId());
			ps.setInt(4, message.getReceiver().getId());
			
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public List<MessageDto> getMessagesByUsers(UserDto first, UserDto second) {
		try (var conn = DBUtils.getConnection();
			var ps = conn.prepareStatement("SELECT * FROM message WHERE (sender = ? AND receiver = ?) OR (sender = ? AND receiver = ?) ORDER BY id")) {
			ps.setInt(1, first.getId());
			ps.setInt(2, second.getId());
			ps.setInt(3, second.getId());
			ps.setInt(4, first.getId());

			try (var rs = ps.executeQuery()) {
				List<MessageDto> messages = new ArrayList<>();

				while (rs.next()) {
					MessageDto message = new MessageDto();
					message.setId(rs.getLong("id"));
					message.setMessageBody(rs.getString("message_body"));
					message.setTimeStamp(rs.getLong("time_stamp"));
					message.setSender(userDao.getInstance().getUserById(rs.getInt("sender")));
					message.setReceiver(userDao.getInstance().getUserById(rs.getInt("receiver")));
					messages.add(message);
				}

				return messages;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
