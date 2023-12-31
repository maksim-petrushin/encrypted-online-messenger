package com.encryptedmessenger.services;

import com.encryptedmessenger.enteties.Message;
import com.encryptedmessenger.enteties.User;

public interface MessageManagementService {
	public void sendMessage(User sender, User receiver, String messageBody);
	public Message[] getMessagesBetween(User firstUser, User secondUser);
	public Message[] getLastNMessagesBetween(User firstUser, User secondUser, int N);
}
