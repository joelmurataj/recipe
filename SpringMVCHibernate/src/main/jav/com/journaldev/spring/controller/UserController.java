package com.journaldev.spring.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.validation.BindingResult;

import com.journaldev.spring.dto.UserDto;
import com.journaldev.spring.service.UserService;
import com.journaldev.spring.validation.UserValidation;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired(required = true)
	@Qualifier(value = "userService")
	private UserService userService;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showForm(ModelMap model, HttpSession session) {
		if (session.getAttribute("user") == null) {
			model.put("userData", new UserDto());
			return "register/register";
		} else {
			return "redirect:/user/login";
		}
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String saveForm(ModelMap model ,@ModelAttribute("userData") @Valid UserDto userDto, BindingResult bindingResult,
			HttpSession session) {
		UserValidation userValidation = new UserValidation();
		userValidation.validate(userValidation, bindingResult);
		if (bindingResult.hasErrors()) {
			return "register/register";
		} else {
			if (userDto.getConfirmPassword().equals(userDto.getPassword())) {
				userService.create(userDto);
				return "redirect:/user/login";
			} else
				return "register/register";
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLogin(ModelMap model, HttpSession session) {
		if (session.getAttribute("user") == null) {
			model.put("userData", new UserDto());
			return "login/login";
		} else {
			return "redirect:/allrecipe";
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(ModelMap model, @ModelAttribute("userData") UserDto userDto, HttpSession session) {
		if (userDto.getUsername() != null && userDto.getPassword() != null) {
			userDto = userService.exist(userDto.getUsername(), userDto.getPassword());
			if (userDto != null) {
				session.setAttribute("user", userDto);
				return "redirect:/allrecipe";
			} else {
				model.put("failed", "Login Failed");
				return "login/login";
			}
		} else {
			return "redirect:/recipes";
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logOut(ModelMap model, HttpSession session) {
		session.removeAttribute("user");
		return "redirect:login";
	}


	@RequestMapping(value = "/changepassword", method = RequestMethod.GET)
	public String showPasswordForm(ModelMap model, HttpSession session) {
		if (session.getAttribute("user") != null) {
			model.put("userData", new UserDto());
			return "/changepassword";
		} else {
			return "redirect:/user/login";
		}
	}

	@RequestMapping(value = "/changepassword", method = RequestMethod.POST)
	public String changepassword(@ModelAttribute("userData") UserDto userDto, HttpSession session) {
		UserDto userLooged = (UserDto) session.getAttribute("user");
		BasicPasswordEncryptor encryptor = new BasicPasswordEncryptor();
		if (encryptor.checkPassword(userDto.getOldPassword(),userLooged.getPassword())
				&& userDto.getConfirmPassword().equals(userDto.getPassword())) {
			if (this.userService.update(userDto.getPassword(), userLooged.getId())) {
				userLooged.setPassword(userDto.getPassword());
				session.setAttribute("user", userLooged);
				return "redirect:/recipes";
			} 
		}
		return "/changepassword";
	}
}
