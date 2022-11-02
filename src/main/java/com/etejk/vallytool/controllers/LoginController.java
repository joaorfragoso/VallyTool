package com.etejk.vallytool.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	
	@GetMapping("login")
	public String login() {
		return "site/login";
	}
	
	@GetMapping("login-error")
	public ModelAndView loginError(ModelMap model) {
		model.addAttribute("error", "Usuário ou senha incorretos.");
		return new ModelAndView("redirect:/login", model);
	}
}
