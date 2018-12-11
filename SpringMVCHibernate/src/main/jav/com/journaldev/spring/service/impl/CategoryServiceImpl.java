package com.journaldev.spring.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.journaldev.spring.converters.CategoryConverter;
import com.journaldev.spring.dao.CategoryDao;
import com.journaldev.spring.dto.CategoryDto;
import com.journaldev.spring.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	private CategoryDao categoryDao;
	
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Override
	@Transactional
	public List<CategoryDto> getAllCategorys() {
		return CategoryConverter.toRecipeListDto(categoryDao.getAllCategory());
	}

}
