package com.etejk.vallytool.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ViewController {
	
	@GetMapping("")
	public String redirect() {
		return "redirect:/login";
	}
	
	@GetMapping("register")
	public String register() {
		return "register";
	}
	
	@GetMapping("usuarios")
	public String usuarios() {
		return "usuarios";
	}

	@GetMapping("professor")
	public String usuarios() {
		return "professor";
	}
	
	@GetMapping("login")
	public String login() {
		return "login/login";
	}
}
