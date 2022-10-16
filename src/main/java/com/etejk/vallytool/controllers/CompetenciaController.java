package com.etejk.vallytool.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.etejk.vallytool.repositories.CompetenciaRepository;

@Controller
public class CompetenciaController {
	
	@Autowired
	CompetenciaRepository cr;
	
	
	
	@GetMapping("avaliar")
	public String avaliar(Model model) {
		model.addAttribute("competencias", cr.findAll());
		return "site/avaliar_turma";
	}
}
