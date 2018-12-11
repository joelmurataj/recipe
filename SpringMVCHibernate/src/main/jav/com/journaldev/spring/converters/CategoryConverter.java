package com.journaldev.spring.converters;

import java.util.ArrayList;
import java.util.List;

import com.journaldev.spring.dto.CategoryDto;
import com.journaldev.spring.model.Category;

public class CategoryConverter {
	
	public static CategoryDto toCategoryDto(Category category) {
		if (category != null) {
			CategoryDto categoryDto = new CategoryDto();
			categoryDto.setId(category.getId());
			categoryDto.setName(category.getName());
			return categoryDto;
		} else {
			return null;
		}

	}
	
	public static List<CategoryDto> toRecipeListDto(List<Category> list) {
		ArrayList<CategoryDto> categoryDtoList = new ArrayList<CategoryDto>();
		if (list != null) {
			for (Category category : list) {
				categoryDtoList.add(toCategoryDto(category));
			}
			return categoryDtoList;
		} else {
			return categoryDtoList;
		}
	}
}
