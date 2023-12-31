package com.encryptedmessenger.services.impl;

import java.util.Scanner;

import com.encryptedmessenger.services.TextInputService;

public class DefaultTextInputService implements TextInputService{
	
	@Override
	public String getInputText() {
		Scanner sc = new Scanner(System.in);
		String userInput = sc.next();
		return userInput;
	}
}
