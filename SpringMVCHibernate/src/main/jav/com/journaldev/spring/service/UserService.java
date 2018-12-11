package com.journaldev.spring.service;

import com.journaldev.spring.dto.UserDto;

public interface UserService {
	public boolean create(UserDto user);
	public boolean update(UserDto userDto);
	public UserDto existUser( String username, String email);
	
	public UserDto exist(String username, String password);
	public boolean update(String password, int userId);
}
