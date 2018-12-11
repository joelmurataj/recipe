package com.journaldev.spring.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.journaldev.spring.dao.RecipeDao;
import com.journaldev.spring.model.Recipe;

@Repository
public class RecipeDaoImpl implements RecipeDao {

	private static final Logger logger = LoggerFactory.getLogger(RecipeDaoImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public boolean create(Recipe recipe) {
		try {
			logger.debug("creating recipe {}.", recipe.getTitle());
			Session session = this.sessionFactory.getCurrentSession();
			session.persist(recipe);
			logger.debug("the recipe was added.");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error adding recipe:" + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean delete(int recipeId) {
		try {
			logger.debug("deleting recipe with id {}.", recipeId);
			Session session = this.sessionFactory.getCurrentSession();
			session.createQuery("update Recipe recipe set recipe.active=0 where recipe.id=:id")
					.setParameter("id", recipeId).executeUpdate();
			logger.debug("recipe was delted.");
			return true;
		} catch (Exception e) {
			logger.error("Error deleting project:" + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean update(Recipe recipe) {
		try {
			logger.debug("updating recipe {}.", recipe.getTitle());
			Session session = this.sessionFactory.getCurrentSession();
			session.update(recipe);
			logger.debug("the recipe was updated.");
			return true;
		} catch (Exception e) {
			logger.error("Error updating recipe:" + e.getMessage());
			return false;
		}
	}

	@Override
	public Recipe findById(int id) {
		try {
			logger.debug("finding recipe with id {}.", id);
			Session session = this.sessionFactory.getCurrentSession();
			return (Recipe) session.load(Recipe.class, id);
		} catch (Exception e) {
			logger.error("Error finding recipe:" + e.getMessage());
			return null;
		}
	}

	@Override
	public List<Recipe> getAllRecipes(int userId) {
		try {
			logger.debug("finding all recipe for user whit id{}", userId);
			Session session = this.sessionFactory.getCurrentSession();
			return (List<Recipe>) session.createQuery(
					"select recipe from Recipe recipe where recipe.user.id=:userId and recipe.active=1  ORDER BY recipe.date DESC")
					.setParameter("userId", userId).list();

		} catch (Exception e) {
			logger.error("error finding recipes" + e.getMessage());
			return null;
		}
	}

	@Override
	public List<Recipe> getAllRecipes() {
		try {
			logger.debug("finding all recipes");
			Session session = this.sessionFactory.getCurrentSession();
			return (List<Recipe>) session
					.createQuery("select recipe from Recipe recipe where recipe.active=1  ORDER BY recipe.date DESC")
					.list();
		} catch (Exception e) {
			logger.error("error finding recipes: " + e.getMessage());
			return null;
		}
	}

	@Override
	public List<Recipe> filterByTitle(String title) {
		try {
			logger.debug("filter list of recipes");
			Session session = this.sessionFactory.getCurrentSession();
			ArrayList<Recipe> resultList = (ArrayList<Recipe>) session.createQuery(
					"select recipe from Recipe recipe " + "where recipe.title LIKE :title " + "and recipe.active=1")
					.setParameter("title", "%" + title + "%").list();
			if (resultList.isEmpty()) {
				logger.debug("not any recipe found with this parameters");
				return null;
			} else {
				logger.debug("recipes retrieved:" + resultList);
				return resultList;
			}
		} catch (Exception e) {
			logger.error("error finding recipes:" + e.getMessage());
			return null;
		}
	}

}
