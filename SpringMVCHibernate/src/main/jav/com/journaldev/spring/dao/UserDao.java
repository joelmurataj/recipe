package com.journaldev.spring.dao;

import com.journaldev.spring.model.User;

public interface UserDao {

	public boolean create(User user);
	public boolean update(User user);
	
	public User exist(String username, String password);
	public User existUser(String username, String email);
	public boolean update(String password, int userId);
}
