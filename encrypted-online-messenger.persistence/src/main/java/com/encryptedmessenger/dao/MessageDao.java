package com.encryptedmessenger.dao;

import java.util.List;

import com.encryptedmessenger.dto.MessageDto;
import com.encryptedmessenger.dto.UserDto;

public interface MessageDao {
	public boolean saveMessage(MessageDto message);
	public List<MessageDto> getMessagesByUsers(UserDto first, UserDto second);
}
