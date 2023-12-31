package com.encryptedmessenger.services.impl;

import com.encryptedmessenger.enteties.Message;
import com.encryptedmessenger.enteties.User;
import com.encryptedmessenger.enteties.impl.DefaultMessage;
import com.encryptedmessenger.services.FriendshipManagementService;
import com.encryptedmessenger.services.MessageManagementService;

public class DefaultMessageManagementService implements MessageManagementService {

	private static final String FRIENDSHIP_DOESNT_EXIST_ERROR_MESSAGE = "The given friendship doesn't exist";
	private static final String NO_ERROR_MESSAGE = "";

	private static final int DEFAULT_MESSAGE_CAPACITY = 10;

	private static DefaultMessageManagementService instance;
	private static FriendshipManagementService defaultFriendshipManagementService;

	private Message[] messages;
	private int lastMessageIndex;

	{
		messages = new Message[DEFAULT_MESSAGE_CAPACITY];
		defaultFriendshipManagementService = DefaultFriendshipManagementService.getInstance();
	}

	private DefaultMessageManagementService() {
	}

	@Override
	public void sendMessage(User sender, User receiver, String messageBody) {
		if (defaultFriendshipManagementService.doesFriendshipExist(sender, receiver)) {
			Message message = new DefaultMessage(sender, receiver, messageBody);
			messages[lastMessageIndex++] = message;
			System.out.println(NO_ERROR_MESSAGE);
		} else {
			System.out.println(FRIENDSHIP_DOESNT_EXIST_ERROR_MESSAGE);
		}
	}

	@Override
	public Message[] getMessagesBetween(User firstUser, User secondUser) {
		Message[] nonNullMessages;
		if (defaultFriendshipManagementService.doesFriendshipExist(firstUser, secondUser)) {
			int nonNullMessagesAmount = 0;
			for (Message msg : messages) {
				if (msg != null && ((msg.getSender() == firstUser && msg.getReceiver() == secondUser)
						|| (msg.getSender() == secondUser && msg.getReceiver() == firstUser))) {
					nonNullMessagesAmount++;
				}
			}

			nonNullMessages = new Message[nonNullMessagesAmount];

			int index = 0;
			for (Message msg : messages) {
				if (msg != null && ((msg.getSender() == firstUser && msg.getReceiver() == secondUser)
						|| (msg.getSender() == secondUser && msg.getReceiver() == firstUser))) {
					nonNullMessages[index++] = msg;
				}
			}

		} else {
			System.out.println(FRIENDSHIP_DOESNT_EXIST_ERROR_MESSAGE);
			nonNullMessages = new Message[0];
		}
		return nonNullMessages;
	}

	@Override
	public Message[] getLastNMessagesBetween(User firstUser, User secondUser, int N) {
		Message[] nonNullMessages;
		if (defaultFriendshipManagementService.doesFriendshipExist(firstUser, secondUser)) {
			nonNullMessages = new Message[N];
			int msgIndex = lastMessageIndex;
			int index = 0;
			while (index < 10) {
				if(msgIndex < 0) {
					break;
				}
				Message msg = messages[msgIndex--];
				if (msg != null && ((msg.getSender() == firstUser && msg.getReceiver() == secondUser)
						|| (msg.getSender() == secondUser && msg.getReceiver() == firstUser))) {
					nonNullMessages[index++] = msg;
				}
			}

		} else {
			System.out.println(FRIENDSHIP_DOESNT_EXIST_ERROR_MESSAGE);
			nonNullMessages = new Message[N];
		}
		return nonNullMessages;
	}

	public static DefaultMessageManagementService getInstance() {
		if (instance == null) {
			instance = new DefaultMessageManagementService();
		}
		return instance;
	}

}
