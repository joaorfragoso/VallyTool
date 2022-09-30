package com.etejk.vallytool.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.etejk.vallytool.dto.RequisicaoNovoUsuario;
import com.etejk.vallytool.services.UsuarioService;

@Controller
public class ViewController {
	
	@Autowired
	public UsuarioService service;
	
	@GetMapping("")
	public String redirect() {
		return "redirect:/login";
	}
	
	@GetMapping("sop")
	public String register(RequisicaoNovoUsuario usuario) {
		return "site/sop";
	}
	
	@GetMapping("professor")
	public String usuarios(Model model) {
		return "site/professor";
	}
	
	@GetMapping("login")
	public String login() {
		return "site/login";
	}
}
