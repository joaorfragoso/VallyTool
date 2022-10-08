package com.etejk.vallytool.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ViewController {
	
	@GetMapping("")
	public String redirect() {
		return "redirect:/sop";
	}
	
	@GetMapping("register")
	public String register() {
		return "register";
	}
	
	@GetMapping("avaliar")
	public String avaliar() {
		return "site/avaliar_turma";
	}
	
	@GetMapping("redefinir")
	public String redefinir() {
		return "site/redefinir_senha";
	}
	
	@GetMapping("sop")
	public String sop() {
		return "site/sop";
	}

	@GetMapping("professor")
	public String professor() {
		return "site/professor";
	}
	
	@GetMapping("login")
	public String login() {
		return "site/login";
	}
}
