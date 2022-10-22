package com.etejk.vallytool.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.etejk.vallytool.repositories.TurmaRepository;
import com.etejk.vallytool.repositories.UsuarioRepository;


@Controller
public class ViewController {
	
	@Autowired
	UsuarioRepository ur;
	@Autowired
	TurmaRepository tr;
	
	@GetMapping("")
	public String redirect() {
		return "redirect:/inicio";
	}
	
	@GetMapping("redefinir")
	public String redefinir() {
		return "site/redefinir_senha";
	}
	
	@GetMapping("configuracoes")
	public String configuracoes() {
		return "site/configuracoes";
	}
	
	@GetMapping("turmas")
	public String turmas(Model model) {
		model.addAttribute("turmas", tr.findAll());
		return "site/turmas";
	}
	
	@GetMapping("inicio")
	public String inicio(Model model) {
		model.addAttribute("usuarios", ur.findAll());
		return "site/inicio";
	}
	
	@GetMapping("login")
	public String login() {
		return "site/login";
	}
}
