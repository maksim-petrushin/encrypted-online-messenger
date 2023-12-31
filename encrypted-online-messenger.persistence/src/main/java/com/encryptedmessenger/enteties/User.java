package com.encryptedmessenger.enteties;

public interface User {
	
	public int getId();
	public String getNickName();
	public String getPassword();
	public String getPhone();
	
	public void setId(int id);
	public void setNickName(String newNickName);
	public void setPassword(String newPassword);
	public void setPhone(String newPhone);
	
}