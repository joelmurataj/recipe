package com.journaldev.spring.service.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.journaldev.spring.converters.UserConverter;
import com.journaldev.spring.dao.UserDao;
import com.journaldev.spring.dto.UserDto;
import com.journaldev.spring.model.User;
import com.journaldev.spring.service.UserService;


@Service
public class UserServiceImpl implements UserService{

	private UserDao userDao;

	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	@Transactional
	public UserDto exist(String username, String password) {
		User user = userDao.exist(username, password);
		if (user != null) {
			
			return UserConverter.toUserDto(user);
		} else {
			return null;
		}
	}

	@Override
	@Transactional
	public UserDto existUser(String username, String email) {
		return UserConverter.toUserDto(userDao.existUser(username,email));
	}

	@Override
	@Transactional
	public boolean create(UserDto user) {
		return userDao.create(UserConverter.toUser(user));
	}

	@Override
	@Transactional
	public boolean update(UserDto userDto) {
		return userDao.update(UserConverter.toEditUser(userDto));
	}

	@Override
	@Transactional
	public boolean update(String password, int userId) {
		return userDao.update(password, userId);
	}

}
