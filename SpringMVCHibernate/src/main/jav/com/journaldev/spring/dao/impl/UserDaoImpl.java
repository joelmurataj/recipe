package com.journaldev.spring.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Repository;

import com.journaldev.spring.dao.UserDao;
import com.journaldev.spring.model.User;

@Repository
public class UserDaoImpl implements UserDao {
	
	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public User exist(String username, String password) {
		try {
			logger.debug("finding if user exist");
			Session session = this.sessionFactory.getCurrentSession();
			User user =  (User) session
					.createQuery("Select user From User user Where user.username=:username and user.active=1")
					.setParameter("username", username).uniqueResult();
			BasicPasswordEncryptor encryptor = new BasicPasswordEncryptor();
			if (encryptor.checkPassword(password, user.getPassword())) {
				logger.debug("user exist");
				return user;
			} else {
				logger.debug("user dont exist");
				return null;
			}
		} catch (Exception e) {
			logger.error("error finding user:" + e.getMessage());
			return null;

		}
	}

	@Override
	public User existUser(String username, String email) {
		try {
			logger.debug("finding user by username");
			Session session = this.sessionFactory.getCurrentSession();
			return (User) session.createQuery(
					"Select user From User user Where user.username=:username or user.email=:email")
					.setParameter("username", username).setParameter("email", email).uniqueResult();
		} catch (Exception e) {
			logger.error("error finding user " + e.getMessage());
			return null;

		}
	}

	@Override
	public boolean create(User user) {
		try {
			logger.debug("creating user.");
			Session session = this.sessionFactory.getCurrentSession();
			session.persist(user);
			logger.debug("user created succesfuly");
			return true;
		} catch (Exception e) {
			logger.error("Error creating user:" + e.getMessage());
			return false;
		}
	}
	
	@Override
	public boolean update(User user) {
		try {
			logger.debug("updating user {}.",user.getUsername());
			Session session = this.sessionFactory.getCurrentSession();
			session.update(user);
			logger.debug("user updated succesfuly");
			return true;
		} catch (Exception e) {
			logger.error("Error updating user:" + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean update(String password, int userId) {
		try {
			logger.debug("updating menager for user with id {}.", userId);
			Session session = this.sessionFactory.getCurrentSession();
			BasicPasswordEncryptor encryptor = new BasicPasswordEncryptor();
			password= encryptor.encryptPassword(password);
			session.createQuery("update User user set user.password=:password where user.id=:id")
			.setParameter("password", password).setParameter("id", userId).executeUpdate();
			logger.debug("user updated succesfuly");
			return true;
		} catch (Exception e) {
			logger.error("Error updating user:" + e.getMessage());
			return false;
		}
	}
}
