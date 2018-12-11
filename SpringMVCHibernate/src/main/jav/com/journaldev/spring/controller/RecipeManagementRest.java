package com.journaldev.spring.controller;

import javax.servlet.http.HttpSession;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.journaldev.spring.dto.RecipeDto;
import com.journaldev.spring.dto.UserDto;
import com.journaldev.spring.service.CategoryService;
import com.journaldev.spring.service.RecipeService;

@Controller
public class RecipeManagementRest {

	@Autowired(required = true)
	@Qualifier(value = "recipeService")
	private RecipeService recipeService;
	@Autowired(required = true)
	@Qualifier(value = "categoryService")
	private CategoryService categoryService;

	@RequestMapping(value = "/recipe/add", method = RequestMethod.POST)
	public String save(@ModelAttribute("recipe") RecipeDto recipeDto, HttpSession session) {
		if (session.getAttribute("user") != null) {
			if (recipeDto.getId() == 0) {

				UserDto userDto = (UserDto) session.getAttribute("user");
				recipeDto.setUserId(userDto.getId());
				this.recipeService.create(recipeDto);
			} else {
				RecipeDto recipeDtoDB = this.recipeService.findById(recipeDto.getId());
				recipeDto.setCategoryId(recipeDtoDB.getCategoryId());
				recipeDto.setUserId(recipeDtoDB.getUserId());
				this.recipeService.update(recipeDto);
			}
			return "redirect:/recipes";
		} else {
			return "redirect:/user/login";
		}
	}

	@RequestMapping(value = "/recipes", method = RequestMethod.GET)
	public String list(Model model, HttpSession session) {
		if (session.getAttribute("user") != null) {
			model.addAttribute("listCategorys", categoryService.getAllCategorys());
			model.addAttribute("recipe", new RecipeDto());
			UserDto userDto = (UserDto) session.getAttribute("user");
			model.addAttribute("listRecipes", this.recipeService.getAllRecipes(userDto.getId()));
			return "recipe";
		} else {
			return "redirect:/user/login";
		}
	}

	@RequestMapping(value = "/allrecipe", method = RequestMethod.GET)
	public String listAllRecipe(Model model, HttpSession session, @ModelAttribute("recipeData") RecipeDto recipeDto) {
		if (session.getAttribute("user") != null) {
				model.addAttribute("listAllRecipes", this.recipeService.getAllRecipes());
		} else {
			return "redirect:/user/login";
		}
		return "allrecipe";
	}

	@RequestMapping("/recipeRemove/{id}")
	public String removeRecipe(@PathVariable("id") int id, HttpSession session) {
		if (session.getAttribute("user") != null) {
			this.recipeService.delete(id);
			return "redirect:/recipes";
		} else {
			return "redirect:/user/login";
		}
	}

	@RequestMapping("/recipeEdit/{id}")
	public String editRecipe(@PathVariable("id") int id, Model model, HttpSession session) {
		if (session.getAttribute("user") != null) {
			model.addAttribute("recipe", this.recipeService.findById(id));
			model.addAttribute("listRecipes", this.recipeService.getAllRecipes());
			return "recipe";
		} else {
			return "redirect:/user/login";
		}
	}

	@RequestMapping(value = "/filter", method = RequestMethod.POST)
	public String changepassword(@ModelAttribute("recipeData") RecipeDto recipeDto, Model model, HttpSession session) {
		if (recipeDto.getTitleFilter() != null) {
			model.addAttribute("recipeData", recipeDto);
			model.addAttribute("listAllRecipes", this.recipeService.filterByTitle(recipeDto.getTitleFilter()));
		}
		return "allrecipe";
	}
}
