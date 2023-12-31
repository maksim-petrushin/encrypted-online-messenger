package com.encryptedmessenger.services.impl;

import java.util.List;

import com.encryptedmessenger.dao.impl.MySqlJdbcFriendshipDao;
import com.encryptedmessenger.dao.impl.MySqlJdbcMessageDao;
import com.encryptedmessenger.dao.impl.MySqlJdbcUserDao;
import com.encryptedmessenger.dto.MessageDto;
import com.encryptedmessenger.dto.UserDto;
import com.encryptedmessenger.dto.converter.FriendshipDtoToFriendshipConverter;
import com.encryptedmessenger.dto.converter.MessageDtoToMessageConverter;
import com.encryptedmessenger.dto.converter.UserDtoToUserConverter;
import com.encryptedmessenger.enteties.Message;
import com.encryptedmessenger.enteties.User;
import com.encryptedmessenger.enteties.impl.DefaultMessage;
import com.encryptedmessenger.services.MessageManagementService;

public class MySqlMessageManagementService implements MessageManagementService{
	private static MySqlMessageManagementService instance;
	public MySqlJdbcUserDao userDao = MySqlJdbcUserDao.getInstance();
	public MySqlJdbcFriendshipDao friendshipDao = MySqlJdbcFriendshipDao.getInstance();
	public MySqlJdbcMessageDao messageDao = MySqlJdbcMessageDao.getInstance();

	public UserDtoToUserConverter userConverter = UserDtoToUserConverter.getInstance();
	public FriendshipDtoToFriendshipConverter friendshipConverter = FriendshipDtoToFriendshipConverter.getInstance();
	public MessageDtoToMessageConverter messageConverter = MessageDtoToMessageConverter.getInstance();
	
	private MySqlFriendshipManagementService friendshipManagementService = MySqlFriendshipManagementService.getInstance();
	
	public static MySqlMessageManagementService getInstance() {
		if (instance == null) {
			instance = new MySqlMessageManagementService();
		}
		return instance;
	}
	
	@Override
	public void sendMessage(User sender, User receiver, String messageBody) {
		if(sender == null || receiver == null || messageBody == null) {
			return;
		}
		
		if(friendshipManagementService.doesFriendshipExist(sender, receiver)) {
			Message message = new DefaultMessage(sender, receiver, messageBody);
			MessageDto msg = messageConverter.convertMessageToMessageDto(message);
			messageDao.saveMessage(msg);
		}
		
	}

	@Override
	public Message[] getMessagesBetween(User firstUser, User secondUser) {
		UserDto first = userConverter.convertUserToUserDto(firstUser);
		UserDto second = userConverter.convertUserToUserDto(secondUser);
		List<MessageDto> messages = messageDao.getMessagesByUsers(first, second);
		List<Message> mewM = messageConverter.convertMessageDtosToMessages(messages);
		Message[] result = new Message[mewM.size()];
		mewM.toArray(result);
		return result;
	}

	@Override
	public Message[] getLastNMessagesBetween(User firstUser, User secondUser, int N) {
		Message[] msg = MySqlMessageManagementService.getInstance().getMessagesBetween(firstUser, secondUser);
		//TODO:
		return null;
	}

}
