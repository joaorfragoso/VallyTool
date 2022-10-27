package com.etejk.vallytool.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.etejk.vallytool.repositories.TurmaRepository;
import com.etejk.vallytool.repositories.UsuarioRepository;


@Controller
public class MainController {
	@GetMapping("")
	public String redirect() {
		return "redirect:/inicio";
	}
	
	@GetMapping("configuracoes")
	public String configuracoes() {
		return "site/configuracoes";
	}
	
	@GetMapping("login")
	public String login() {
		return "site/login";
	}
}
