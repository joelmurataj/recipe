package com.journaldev.spring.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.journaldev.spring.converters.RecipeConverter;
import com.journaldev.spring.dao.RecipeDao;
import com.journaldev.spring.dto.RecipeDto;
import com.journaldev.spring.service.RecipeService;

@Service
public class RecipeServiceImpl implements RecipeService{

	private RecipeDao recipeDao;
	
	public void setRecipeDao(RecipeDao recipeDao) {
		this.recipeDao = recipeDao;
	}

	@Override
	@Transactional
	public boolean create(RecipeDto recipeDto) {
		return recipeDao.create(RecipeConverter.toRecipe(recipeDto));
	}

	@Override
	@Transactional
	public boolean delete(int recipeId) {
		return recipeDao.delete(recipeId);
	}

	@Override
	@Transactional
	public boolean update(RecipeDto recipeDto) {
		return recipeDao.update(RecipeConverter.toEditRecipe(recipeDto));
	}

	@Override
	@Transactional
	public RecipeDto findById(int id) {
		return RecipeConverter.toRecipeDto(recipeDao.findById(id));
	}

	@Override
	@Transactional
	public List<RecipeDto> getAllRecipes(int userId) {
		return RecipeConverter.toRecipeListDto(recipeDao.getAllRecipes(userId));
	}

	@Override
	@Transactional
	public List<RecipeDto> getAllRecipes() {
		return RecipeConverter.toRecipeListDto(recipeDao.getAllRecipes());
	}
	
	@Override
	@Transactional
	public List<RecipeDto> filterByTitle(String title){
		return RecipeConverter.toRecipeListDto(recipeDao.filterByTitle(title));
	}
}
