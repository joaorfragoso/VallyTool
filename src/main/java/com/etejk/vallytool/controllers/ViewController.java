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
	
	@GetMapping("register")
	public String register(RequisicaoNovoUsuario usuario) {
		return "register";
	}
	
	@GetMapping("usuarios")
	public String usuarios(Model model) {
		model.addAttribute("usuarios", service.findUsuarios());
		return "usuarios";
	}
}
