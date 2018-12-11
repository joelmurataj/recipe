package com.journaldev.spring.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.journaldev.spring.dao.CategoryDao;
import com.journaldev.spring.model.Category;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
public class CategoryDaoImpl implements CategoryDao {

	private static final Logger logger = LoggerFactory.getLogger(CategoryDaoImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public List<Category> getAllCategory() {
		try {
			logger.debug("finding all categorys");
			Session session = this.sessionFactory.getCurrentSession();
			List<Category> categoryList = session.createQuery("from Category").list();
			for(Category category : categoryList){
				logger.info("Category List::"+category);
			}
			return categoryList;
		} catch (Exception e) {
			logger.error("error finding categorys: " + e.getMessage());
			return null;
		}
	}
}
